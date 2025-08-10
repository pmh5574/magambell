package com.magambell.server.goods.domain.repository;

import static com.magambell.server.goods.domain.model.QGoods.goods;
import static com.magambell.server.stock.domain.model.QStock.stock;
import static com.magambell.server.store.domain.model.QStore.store;
import static com.magambell.server.user.domain.model.QUser.user;

import com.magambell.server.goods.domain.enums.SaleStatus;
import com.magambell.server.goods.domain.model.Goods;
import com.magambell.server.store.domain.enums.Approved;
import com.magambell.server.user.domain.enums.UserStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoodsRepositoryImpl implements GoodsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Goods> findWithStoreAndUserById(final Long goodsId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(goods)
                        .innerJoin(store).on(store.id.eq(goods.store.id)).fetchJoin()
                        .innerJoin(user).on(user.id.eq(store.user.id)).fetchJoin()
                        .innerJoin(stock).on(stock.goods.id.eq(goods.id)).fetchJoin()
                        .where(
                                goods.id.eq(goodsId)
                                        .and(store.approved.eq(Approved.APPROVED))
                                        .and(user.userStatus.eq(UserStatus.ACTIVE))
                        )
                        .fetchOne()
        );
    }

    @Override
    public Optional<Goods> findOwnedGoodsWithRelations(final Long goodsId, final Long userId) {

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(goods)
                        .innerJoin(store).on(store.id.eq(goods.store.id)).fetchJoin()
                        .innerJoin(user).on(user.id.eq(store.user.id)).fetchJoin()
                        .innerJoin(stock).on(stock.goods.id.eq(goods.id)).fetchJoin()
                        .where(
                                goods.id.eq(goodsId)
                                        .and(store.approved.eq(Approved.APPROVED))
                                        .and(user.userStatus.eq(UserStatus.ACTIVE))
                                        .and(user.id.eq(userId))
                        )
                        .fetchOne()
        );
    }

    @Override
    public List<Goods> findExpiredGoods(final LocalDateTime now) {
        return queryFactory
                .selectFrom(goods)
                .innerJoin(store).on(store.id.eq(goods.store.id)).fetchJoin()
                .innerJoin(user).on(user.id.eq(store.user.id)).fetchJoin()
                .where(
                        user.userStatus.eq(UserStatus.ACTIVE)
                                .and(goods.endTime.lt(now))
                                .and(goods.saleStatus.eq(SaleStatus.ON))
                )
                .fetch();
    }
}
