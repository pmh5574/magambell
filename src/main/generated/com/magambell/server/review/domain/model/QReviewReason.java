package com.magambell.server.review.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewReason is a Querydsl query type for ReviewReason
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewReason extends EntityPathBase<ReviewReason> {

    private static final long serialVersionUID = -1040247993L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewReason reviewReason = new QReviewReason("reviewReason");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QReview review;

    public final EnumPath<com.magambell.server.review.domain.enums.SatisfactionReason> satisfactionReason = createEnum("satisfactionReason", com.magambell.server.review.domain.enums.SatisfactionReason.class);

    public QReviewReason(String variable) {
        this(ReviewReason.class, forVariable(variable), INITS);
    }

    public QReviewReason(Path<? extends ReviewReason> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewReason(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewReason(PathMetadata metadata, PathInits inits) {
        this(ReviewReason.class, metadata, inits);
    }

    public QReviewReason(Class<? extends ReviewReason> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

