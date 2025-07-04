package com.magambell.server.store.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 1095160369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final EnumPath<com.magambell.server.store.domain.enums.Approved> approved = createEnum("approved", com.magambell.server.store.domain.enums.Approved.class);

    public final StringPath bankAccount = createString("bankAccount");

    public final EnumPath<com.magambell.server.store.domain.enums.Bank> bankName = createEnum("bankName", com.magambell.server.store.domain.enums.Bank.class);

    public final StringPath businessNumber = createString("businessNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<com.magambell.server.favorite.domain.model.Favorite, com.magambell.server.favorite.domain.model.QFavorite> favorites = this.<com.magambell.server.favorite.domain.model.Favorite, com.magambell.server.favorite.domain.model.QFavorite>createList("favorites", com.magambell.server.favorite.domain.model.Favorite.class, com.magambell.server.favorite.domain.model.QFavorite.class, PathInits.DIRECT2);

    public final ListPath<com.magambell.server.notification.domain.model.FcmToken, com.magambell.server.notification.domain.model.QFcmToken> fcmTokens = this.<com.magambell.server.notification.domain.model.FcmToken, com.magambell.server.notification.domain.model.QFcmToken>createList("fcmTokens", com.magambell.server.notification.domain.model.FcmToken.class, com.magambell.server.notification.domain.model.QFcmToken.class, PathInits.DIRECT2);

    public final ListPath<com.magambell.server.goods.domain.model.Goods, com.magambell.server.goods.domain.model.QGoods> goods = this.<com.magambell.server.goods.domain.model.Goods, com.magambell.server.goods.domain.model.QGoods>createList("goods", com.magambell.server.goods.domain.model.Goods.class, com.magambell.server.goods.domain.model.QGoods.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath ownerName = createString("ownerName");

    public final StringPath ownerPhone = createString("ownerPhone");

    public final ListPath<StoreImage, QStoreImage> storeImages = this.<StoreImage, QStoreImage>createList("storeImages", StoreImage.class, QStoreImage.class, PathInits.DIRECT2);

    public final com.magambell.server.user.domain.model.QUser user;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.magambell.server.user.domain.model.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

