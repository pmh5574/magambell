package com.magambell.server.review.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1561508643L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.magambell.server.common.QBaseTimeEntity _super = new com.magambell.server.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.magambell.server.order.domain.model.QOrder order;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final ListPath<ReviewImage, QReviewImage> reviewImages = this.<ReviewImage, QReviewImage>createList("reviewImages", ReviewImage.class, QReviewImage.class, PathInits.DIRECT2);

    public final ListPath<ReviewReason, QReviewReason> reviewReasons = this.<ReviewReason, QReviewReason>createList("reviewReasons", ReviewReason.class, QReviewReason.class, PathInits.DIRECT2);

    public final com.magambell.server.user.domain.model.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.magambell.server.order.domain.model.QOrder(forProperty("order"), inits.get("order")) : null;
        this.user = inits.isInitialized("user") ? new com.magambell.server.user.domain.model.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

