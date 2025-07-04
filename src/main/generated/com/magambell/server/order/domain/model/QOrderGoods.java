package com.magambell.server.order.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderGoods is a Querydsl query type for OrderGoods
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderGoods extends EntityPathBase<OrderGoods> {

    private static final long serialVersionUID = -485076795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderGoods orderGoods = new QOrderGoods("orderGoods");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.magambell.server.goods.domain.model.QGoods goods;

    public final StringPath goodsName = createString("goodsName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QOrder order;

    public final NumberPath<Integer> originalPrice = createNumber("originalPrice", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public QOrderGoods(String variable) {
        this(OrderGoods.class, forVariable(variable), INITS);
    }

    public QOrderGoods(Path<? extends OrderGoods> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderGoods(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderGoods(PathMetadata metadata, PathInits inits) {
        this(OrderGoods.class, metadata, inits);
    }

    public QOrderGoods(Class<? extends OrderGoods> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goods = inits.isInitialized("goods") ? new com.magambell.server.goods.domain.model.QGoods(forProperty("goods"), inits.get("goods")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

