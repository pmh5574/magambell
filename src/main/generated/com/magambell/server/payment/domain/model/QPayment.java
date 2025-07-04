package com.magambell.server.payment.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 291840721L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath cancelReason = createString("cancelReason");

    public final StringPath cardName = createString("cardName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.magambell.server.payment.domain.enums.EasyPayProvider> easyPayProvider = createEnum("easyPayProvider", com.magambell.server.payment.domain.enums.EasyPayProvider.class);

    public final StringPath failReason = createString("failReason");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath merchantUid = createString("merchantUid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.magambell.server.order.domain.model.QOrder order;

    public final DateTimePath<java.time.LocalDateTime> paidAt = createDateTime("paidAt", java.time.LocalDateTime.class);

    public final EnumPath<com.magambell.server.payment.domain.enums.PaymentStatus> paymentStatus = createEnum("paymentStatus", com.magambell.server.payment.domain.enums.PaymentStatus.class);

    public final EnumPath<com.magambell.server.payment.domain.enums.PayType> payType = createEnum("payType", com.magambell.server.payment.domain.enums.PayType.class);

    public final StringPath transactionId = createString("transactionId");

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.magambell.server.order.domain.model.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

