package com.magambell.server.review.app.port.out;

import com.magambell.server.order.domain.model.OrderGoods;
import com.magambell.server.review.app.port.in.request.ReviewListServiceRequest;
import com.magambell.server.review.app.port.in.request.ReviewRatingAllServiceRequest;
import com.magambell.server.review.app.port.out.response.ReviewListDTO;
import com.magambell.server.review.app.port.out.response.ReviewRatingSummaryDTO;
import com.magambell.server.review.domain.model.Review;
import com.magambell.server.user.domain.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryPort {
    boolean existsOrderAndReview(OrderGoods orderGoods, User user);

    List<ReviewListDTO> getReviewList(ReviewListServiceRequest request, Pageable pageable);

    ReviewRatingSummaryDTO getReviewRatingAll(ReviewRatingAllServiceRequest request);

    List<ReviewListDTO> getReviewListByUser(User user, Pageable pageable);

    Review findByIdAndUserId(Long reviewId, Long userId);
}
