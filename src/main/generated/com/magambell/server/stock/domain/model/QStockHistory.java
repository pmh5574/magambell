package com.magambell.server.stock.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStockHistory is a Querydsl query type for StockHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStockHistory extends EntityPathBase<StockHistory> {

    private static final long serialVersionUID = 306611011L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockHistory stockHistory = new QStockHistory("stockHistory");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    public final NumberPath<Integer> beforeQuantity = createNumber("beforeQuantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.magambell.server.goods.domain.model.QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<Integer> resultQuantity = createNumber("resultQuantity", Integer.class);

    public final EnumPath<com.magambell.server.stock.domain.enums.StockType> stockType = createEnum("stockType", com.magambell.server.stock.domain.enums.StockType.class);

    public QStockHistory(String variable) {
        this(StockHistory.class, forVariable(variable), INITS);
    }

    public QStockHistory(Path<? extends StockHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStockHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStockHistory(PathMetadata metadata, PathInits inits) {
        this(StockHistory.class, metadata, inits);
    }

    public QStockHistory(Class<? extends StockHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goods = inits.isInitialized("goods") ? new com.magambell.server.goods.domain.model.QGoods(forProperty("goods"), inits.get("goods")) : null;
    }

}

