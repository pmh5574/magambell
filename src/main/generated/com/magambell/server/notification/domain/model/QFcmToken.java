package com.magambell.server.notification.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFcmToken is a Querydsl query type for FcmToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFcmToken extends EntityPathBase<FcmToken> {

    private static final long serialVersionUID = -1367948953L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFcmToken fcmToken = new QFcmToken("fcmToken");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.magambell.server.store.domain.model.QStore store;

    public final StringPath token = createString("token");

    public final com.magambell.server.user.domain.model.QUser user;

    public QFcmToken(String variable) {
        this(FcmToken.class, forVariable(variable), INITS);
    }

    public QFcmToken(Path<? extends FcmToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFcmToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFcmToken(PathMetadata metadata, PathInits inits) {
        this(FcmToken.class, metadata, inits);
    }

    public QFcmToken(Class<? extends FcmToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.magambell.server.store.domain.model.QStore(forProperty("store"), inits.get("store")) : null;
        this.user = inits.isInitialized("user") ? new com.magambell.server.user.domain.model.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

