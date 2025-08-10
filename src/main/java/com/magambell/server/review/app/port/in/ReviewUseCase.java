package com.magambell.server.review.app.port.in;

import com.magambell.server.review.app.port.in.request.DeleteReviewServiceRequest;
import com.magambell.server.review.app.port.in.request.RegisterReviewServiceRequest;
import com.magambell.server.review.app.port.in.request.ReviewListServiceRequest;
import com.magambell.server.review.app.port.in.request.ReviewMyServiceRequest;
import com.magambell.server.review.app.port.in.request.ReviewRatingAllServiceRequest;
import com.magambell.server.review.app.port.out.response.ReviewListDTO;
import com.magambell.server.review.app.port.out.response.ReviewRatingSummaryDTO;
import com.magambell.server.review.app.port.out.response.ReviewRegisterResponseDTO;
import java.util.List;

public interface ReviewUseCase {
    ReviewRegisterResponseDTO registerReview(RegisterReviewServiceRequest request, Long userId);

    List<ReviewListDTO> getReviewList(ReviewListServiceRequest request);

    ReviewRatingSummaryDTO getReviewRatingAll(ReviewRatingAllServiceRequest request);

    List<ReviewListDTO> getReviewListByUser(ReviewMyServiceRequest request);

    void deleteReview(DeleteReviewServiceRequest request);
}
