package com.magambell.server.order.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 341334289L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<OrderGoods, QOrderGoods> orderGoodsList = this.<OrderGoods, QOrderGoods>createList("orderGoodsList", OrderGoods.class, QOrderGoods.class, PathInits.DIRECT2);

    public final EnumPath<com.magambell.server.order.domain.enums.OrderStatus> orderStatus = createEnum("orderStatus", com.magambell.server.order.domain.enums.OrderStatus.class);

    public final com.magambell.server.payment.domain.model.QPayment payment;

    public final DateTimePath<java.time.LocalDateTime> pickupTime = createDateTime("pickupTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final com.magambell.server.user.domain.model.QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new com.magambell.server.payment.domain.model.QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.user = inits.isInitialized("user") ? new com.magambell.server.user.domain.model.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

