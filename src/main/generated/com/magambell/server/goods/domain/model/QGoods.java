package com.magambell.server.goods.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoods is a Querydsl query type for Goods
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoods extends EntityPathBase<Goods> {

    private static final long serialVersionUID = -133362671L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoods goods = new QGoods("goods");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> originalPrice = createNumber("originalPrice", Integer.class);

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public final EnumPath<com.magambell.server.goods.domain.enums.SaleStatus> saleStatus = createEnum("saleStatus", com.magambell.server.goods.domain.enums.SaleStatus.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final com.magambell.server.stock.domain.model.QStock stock;

    public final ListPath<com.magambell.server.stock.domain.model.StockHistory, com.magambell.server.stock.domain.model.QStockHistory> stockHistory = this.<com.magambell.server.stock.domain.model.StockHistory, com.magambell.server.stock.domain.model.QStockHistory>createList("stockHistory", com.magambell.server.stock.domain.model.StockHistory.class, com.magambell.server.stock.domain.model.QStockHistory.class, PathInits.DIRECT2);

    public final com.magambell.server.store.domain.model.QStore store;

    public QGoods(String variable) {
        this(Goods.class, forVariable(variable), INITS);
    }

    public QGoods(Path<? extends Goods> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoods(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoods(PathMetadata metadata, PathInits inits) {
        this(Goods.class, metadata, inits);
    }

    public QGoods(Class<? extends Goods> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stock = inits.isInitialized("stock") ? new com.magambell.server.stock.domain.model.QStock(forProperty("stock"), inits.get("stock")) : null;
        this.store = inits.isInitialized("store") ? new com.magambell.server.store.domain.model.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

