package com.magambell.server.notification.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.magambell.server.auth.domain.ProviderType;
import com.magambell.server.goods.domain.repository.GoodsRepository;
import com.magambell.server.notification.app.port.in.request.SaveFcmTokenServiceRequest;
import com.magambell.server.notification.app.port.in.request.SaveStoreOpenFcmTokenServiceRequest;
import com.magambell.server.notification.domain.model.FcmToken;
import com.magambell.server.notification.domain.repository.FcmTokenRepository;
import com.magambell.server.stock.domain.repository.StockHistoryRepository;
import com.magambell.server.stock.domain.repository.StockRepository;
import com.magambell.server.store.app.port.in.dto.RegisterStoreDTO;
import com.magambell.server.store.domain.enums.Approved;
import com.magambell.server.store.domain.enums.Bank;
import com.magambell.server.store.domain.model.Store;
import com.magambell.server.store.domain.repository.StoreRepository;
import com.magambell.server.user.app.port.in.dto.UserSocialAccountDTO;
import com.magambell.server.user.domain.enums.UserRole;
import com.magambell.server.user.domain.model.User;
import com.magambell.server.user.domain.repository.UserRepository;
import com.magambell.server.user.domain.repository.UserSocialAccountRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FcmTokenRepository fcmTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSocialAccountRepository userSocialAccountRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    private User user;
    private Store store;

    @BeforeEach
    void setUp() {
        UserSocialAccountDTO userSocialAccountDTO = new UserSocialAccountDTO("test@test.com", "테스트이름", "닉네임",
                "01012341234",
                ProviderType.KAKAO,
                "testId", UserRole.OWNER);
        user = userSocialAccountDTO.toUser();
        user.addUserSocialAccount(userSocialAccountDTO.toUserSocialAccount());

        RegisterStoreDTO registerStoreDTO = new RegisterStoreDTO(
                "테스트 매장",
                "서울 강서구 테스트 211",
                1238.123213,
                5457.123213,
                "대표이름",
                "01012345678",
                "123491923",
                Bank.KB국민,
                "102391485",
                List.of(),
                Approved.APPROVED,
                user
        );
        store = registerStoreDTO.toEntity();
        user.addStore(store);
        user = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        fcmTokenRepository.deleteAllInBatch();
        stockHistoryRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
        goodsRepository.deleteAllInBatch();
        storeRepository.deleteAllInBatch();
        userSocialAccountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("매장 오픈 FCM 토큰을 저장한다.")
    @Test
    void saveStoreOpenToken() {
        // given
        SaveStoreOpenFcmTokenServiceRequest request = new SaveStoreOpenFcmTokenServiceRequest(store.getId(),
                "testToken", user.getId());

        // when
        notificationService.saveStoreOpenToken(request);

        // then
        List<FcmToken> fcmTokenList = fcmTokenRepository.findAll();
        assertThat(fcmTokenList.size()).isEqualTo(1);
        assertThat(fcmTokenList.get(0).getToken()).isEqualTo("testToken");
    }

    @DisplayName("FCM용 토큰을 저장한다.")
    @Test
    void saveToken() {
        // given
        SaveFcmTokenServiceRequest request = new SaveFcmTokenServiceRequest("testToken", user.getId());

        // when
        notificationService.saveToken(request);

        // then
        List<FcmToken> fcmTokenList = fcmTokenRepository.findAll();
        assertThat(fcmTokenList.size()).isEqualTo(1);
        assertThat(fcmTokenList.get(0).getToken()).isEqualTo("testToken");
    }
}