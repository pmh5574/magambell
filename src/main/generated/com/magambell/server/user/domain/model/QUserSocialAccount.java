package com.magambell.server.user.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSocialAccount is a Querydsl query type for UserSocialAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSocialAccount extends EntityPathBase<UserSocialAccount> {

    private static final long serialVersionUID = -1935481865L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSocialAccount userSocialAccount = new QUserSocialAccount("userSocialAccount");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath providerId = createString("providerId");

    public final EnumPath<com.magambell.server.auth.domain.ProviderType> providerType = createEnum("providerType", com.magambell.server.auth.domain.ProviderType.class);

    public final QUser user;

    public QUserSocialAccount(String variable) {
        this(UserSocialAccount.class, forVariable(variable), INITS);
    }

    public QUserSocialAccount(Path<? extends UserSocialAccount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSocialAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSocialAccount(PathMetadata metadata, PathInits inits) {
        this(UserSocialAccount.class, metadata, inits);
    }

    public QUserSocialAccount(Class<? extends UserSocialAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

