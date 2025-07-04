package com.magambell.server.user.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1508083575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final ListPath<com.magambell.server.favorite.domain.model.Favorite, com.magambell.server.favorite.domain.model.QFavorite> favorites = this.<com.magambell.server.favorite.domain.model.Favorite, com.magambell.server.favorite.domain.model.QFavorite>createList("favorites", com.magambell.server.favorite.domain.model.Favorite.class, com.magambell.server.favorite.domain.model.QFavorite.class, PathInits.DIRECT2);

    public final ListPath<com.magambell.server.notification.domain.model.FcmToken, com.magambell.server.notification.domain.model.QFcmToken> fcmTokens = this.<com.magambell.server.notification.domain.model.FcmToken, com.magambell.server.notification.domain.model.QFcmToken>createList("fcmTokens", com.magambell.server.notification.domain.model.FcmToken.class, com.magambell.server.notification.domain.model.QFcmToken.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<com.magambell.server.order.domain.model.Order, com.magambell.server.order.domain.model.QOrder> orders = this.<com.magambell.server.order.domain.model.Order, com.magambell.server.order.domain.model.QOrder>createList("orders", com.magambell.server.order.domain.model.Order.class, com.magambell.server.order.domain.model.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<com.magambell.server.review.domain.model.Review, com.magambell.server.review.domain.model.QReview> reviews = this.<com.magambell.server.review.domain.model.Review, com.magambell.server.review.domain.model.QReview>createList("reviews", com.magambell.server.review.domain.model.Review.class, com.magambell.server.review.domain.model.QReview.class, PathInits.DIRECT2);

    public final com.magambell.server.store.domain.model.QStore store;

    public final EnumPath<com.magambell.server.user.domain.enums.UserRole> userRole = createEnum("userRole", com.magambell.server.user.domain.enums.UserRole.class);

    public final ListPath<UserSocialAccount, QUserSocialAccount> userSocialAccounts = this.<UserSocialAccount, QUserSocialAccount>createList("userSocialAccounts", UserSocialAccount.class, QUserSocialAccount.class, PathInits.DIRECT2);

    public final EnumPath<com.magambell.server.user.domain.enums.UserStatus> userStatus = createEnum("userStatus", com.magambell.server.user.domain.enums.UserStatus.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.magambell.server.store.domain.model.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

