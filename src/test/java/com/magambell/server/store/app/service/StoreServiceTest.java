package com.magambell.server.store.app.service;

import static com.magambell.server.goods.domain.enums.SaleStatus.ON;
import static org.assertj.core.api.Assertions.assertThat;

import com.magambell.server.auth.domain.ProviderType;
import com.magambell.server.common.s3.dto.ImageRegister;
import com.magambell.server.goods.app.port.in.dto.RegisterGoodsDTO;
import com.magambell.server.goods.domain.model.Goods;
import com.magambell.server.goods.domain.repository.GoodsRepository;
import com.magambell.server.stock.domain.model.Stock;
import com.magambell.server.stock.domain.repository.StockHistoryRepository;
import com.magambell.server.stock.domain.repository.StockRepository;
import com.magambell.server.store.adapter.in.web.StoreImagesRegister;
import com.magambell.server.store.adapter.out.persistence.StoreDetailResponse;
import com.magambell.server.store.adapter.out.persistence.StoreListResponse;
import com.magambell.server.store.app.port.in.dto.RegisterStoreDTO;
import com.magambell.server.store.app.port.in.request.CloseStoreListServiceRequest;
import com.magambell.server.store.app.port.in.request.RegisterStoreServiceRequest;
import com.magambell.server.store.app.port.in.request.SearchStoreListServiceRequest;
import com.magambell.server.store.app.port.in.request.WaitingStoreListServiceRequest;
import com.magambell.server.store.app.port.out.response.OwnerStoreDetailDTO;
import com.magambell.server.store.app.port.out.response.StoreListDTOResponse;
import com.magambell.server.store.domain.enums.Approved;
import com.magambell.server.store.domain.enums.Bank;
import com.magambell.server.store.domain.enums.SearchSortType;
import com.magambell.server.store.domain.model.Store;
import com.magambell.server.store.domain.model.StoreImage;
import com.magambell.server.store.domain.repository.StoreImageRepository;
import com.magambell.server.store.domain.repository.StoreRepository;
import com.magambell.server.user.app.port.in.dto.UserSocialAccountDTO;
import com.magambell.server.user.domain.enums.UserRole;
import com.magambell.server.user.domain.model.User;
import com.magambell.server.user.domain.repository.UserRepository;
import com.magambell.server.user.domain.repository.UserSocialAccountRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class StoreServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSocialAccountRepository userSocialAccountRepository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreImageRepository storeImageRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private StockHistoryRepository stockHistoryRepository;
    @Autowired
    private StockRepository stockRepository;
    private User user;

    @BeforeEach
    void setUp() {
        UserSocialAccountDTO userSocialAccountDTO = new UserSocialAccountDTO("test@test.com", "테스트이름", "닉네임",
                "01012341234",
                ProviderType.KAKAO,
                "testId", UserRole.OWNER);
        user = userSocialAccountDTO.toUser();
        user.addUserSocialAccount(userSocialAccountDTO.toUserSocialAccount());
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        stockHistoryRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
        goodsRepository.deleteAllInBatch();
        storeImageRepository.deleteAllInBatch();
        storeRepository.deleteAllInBatch();
        userSocialAccountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("매장을 등록한다.")
    @Test
    void registerStore() {
        // given
        RegisterStoreServiceRequest request = new RegisterStoreServiceRequest(
                "테스트 매장",
                "서울 강서구 테스트 211",
                1238.123213,
                5457.123213,
                "대표이름",
                "01012345678",
                "123491923",
                Bank.KB국민,
                "102391485",
                List.of(new StoreImagesRegister(0, "test"))
        );

        // when
        storeService.registerStore(request, user.getId());

        // then
        Store store = storeRepository.findAll().get(0);
        assertThat(store).extracting("name", "address", "ownerPhone")
                .contains(
                        "테스트 매장",
                        "서울 강서구 테스트 211",
                        "01012345678");
    }

    @DisplayName("매장 리스트를 가져온다.")
    @Test
    void getStoreList() {
        // given
        SearchStoreListServiceRequest request = new SearchStoreListServiceRequest(
                37.5665, 37.5665, "", SearchSortType.RECENT_DESC, true, 1, 30
        );

        List<Store> storeList = IntStream.range(1, 31)
                .mapToObj(this::createStore)
                .toList();

        storeRepository.saveAll(storeList);

        // when
        StoreListResponse storeListResponse = storeService.getStoreList(request);

        // then
        StoreListDTOResponse store = storeListResponse.storeListDTOResponses().get(0);
        assertThat(store)
                .extracting("storeName", "startTime", "endTime", "originPrice", "discount", "salePrice",
                        "quantity")
                .contains(
                        "테스트 매장30",
                        LocalDateTime.of(2025, 1, 1, 9, 0),
                        LocalDateTime.of(2025, 1, 1, 18, 0),
                        10000,
                        10,
                        9000,
                        30
                );
    }

    @DisplayName("매장 상세 정보를 조회한다")
    @Test
    void getStoreDetail() {
        // given
        Store store = createStore(1);
        storeRepository.save(store);

        // when
        StoreDetailResponse result = storeService.getStoreDetail(store.getId());

        // then
        assertThat(result).isNotNull();
        assertThat(result.storeId()).isEqualTo(String.valueOf(store.getId()));
        assertThat(result.storeName()).isEqualTo("테스트 매장1");
        assertThat(result.description()).isEqualTo("상품설명");
        assertThat(result.salePrice()).isEqualTo(9000);
        assertThat(result.images()).isEmpty();
    }

    @DisplayName("관리자 매장 상세 정보를 조회한다")
    @Test
    void getOwnerStoreInfo() {
        // given
        Store store = createStore(1); // storeId = 1L
        storeRepository.save(store);

        // when
        OwnerStoreDetailDTO ownerStoreInfo = storeService.getOwnerStoreInfo(user.getId());

        // then
        assertThat(ownerStoreInfo).isNotNull();
        assertThat(ownerStoreInfo.storeName()).isEqualTo("테스트 매장1");
        assertThat(ownerStoreInfo.goodsList().get(0).description()).isEqualTo("상품설명");
        assertThat(ownerStoreInfo.goodsList().get(0).salePrice()).isEqualTo(9000);
    }

    @DisplayName("내 주변 매장 리스트")
    @Test
    void getCloseStoreList() {
        // given
        CloseStoreListServiceRequest request = new CloseStoreListServiceRequest(37.6000, 37.5665);

        List<Store> storeList = IntStream.range(1, 31)
                .mapToObj(this::createStore)
                .toList();

        storeRepository.saveAll(storeList);

        // when
        StoreListResponse closeStoreList = storeService.getCloseStoreList(request);

        // then
        assertThat(closeStoreList.storeListDTOResponses()).hasSize(30);
    }

    @DisplayName("내 주변 매장 리스트 5km 근처에 없을 때")
    @Test
    void getCloseStoreListIs5KmLimit() {
        // given
        CloseStoreListServiceRequest request = new CloseStoreListServiceRequest(37.6300, 37.5665);

        List<Store> storeList = IntStream.range(1, 31)
                .mapToObj(this::createStore)
                .toList();

        storeRepository.saveAll(storeList);

        // when
        StoreListResponse closeStoreList = storeService.getCloseStoreList(request);

        // then
        assertThat(closeStoreList.storeListDTOResponses()).hasSize(0);
    }

    @DisplayName("승인 대기중인 매장 리스트를 가져온다.")
    @Test
    void getWaitingStoreList() {
        // given
        WaitingStoreListServiceRequest request = new WaitingStoreListServiceRequest(1, 10);

        List<Store> storeList = IntStream.range(1, 31)
                .mapToObj(this::createStore)
                .toList();

        storeRepository.saveAll(storeList);

        // when
        StoreListResponse storeListResponse = storeService.getWaitingStoreList(request);

        // then
        assertThat(storeListResponse.storeListDTOResponses()).hasSize(0);
    }

    private Store createStore(int i) {
        UserSocialAccountDTO userSocialAccountDTO = new UserSocialAccountDTO("test" + i + "@test.com", "테스트이름", "닉네임",
                "01012341234",
                ProviderType.KAKAO,
                "testId", UserRole.OWNER);
        user = userSocialAccountDTO.toUser();
        user.addUserSocialAccount(userSocialAccountDTO.toUserSocialAccount());

        RegisterStoreDTO registerStoreDTO = new RegisterStoreDTO(
                "테스트 매장" + i,
                "서울 강서구 테스트 211",
                37.5665,
                37.5665,
                "대표이름",
                "01012345678",
                "123491923",
                Bank.KB국민,
                "102391485",
                List.of(),
                Approved.APPROVED,
                user
        );

        Store store = registerStoreDTO.toEntity();
        List<ImageRegister> images = registerStoreDTO.toImage();
        images.forEach(image -> store.addStoreImage(StoreImage.create(image.key(), image.id())));

        RegisterGoodsDTO registerGoodsDTO = new RegisterGoodsDTO(
                LocalDateTime.of(2025, 1, 1, 9, 0), LocalDateTime.of(2025, 1, 1, 18, 0),
                i, 10000, 10, 9000, "상품설명", store
        );
        user.addStore(store);

        Goods goods = registerGoodsDTO.toGoods();
        store.addGoods(goods);
        Stock stock = Stock.create(registerGoodsDTO.quantity());
        goods.addStock(stock);

        userRepository.save(user);
        goods.changeStatus(user, ON, LocalDateTime.of(2025, 1, 1, 8, 0));
        return store;
    }
}