package com.magambell.server.review.adapter;

import com.magambell.server.common.Response;
import com.magambell.server.common.security.CustomUserDetails;
import com.magambell.server.common.swagger.BaseResponse;
import com.magambell.server.review.adapter.in.web.RegisterReviewRequest;
import com.magambell.server.review.adapter.in.web.ReviewListRequest;
import com.magambell.server.review.adapter.in.web.ReviewMyRequest;
import com.magambell.server.review.adapter.in.web.ReviewRatingAllRequest;
import com.magambell.server.review.adapter.out.persistence.ReviewListResponse;
import com.magambell.server.review.adapter.out.persistence.ReviewRatingSummaryResponse;
import com.magambell.server.review.adapter.out.persistence.ReviewRegisterResponse;
import com.magambell.server.review.app.port.in.ReviewUseCase;
import com.magambell.server.review.app.port.in.request.DeleteReviewServiceRequest;
import com.magambell.server.review.app.port.out.response.ReviewListDTO;
import com.magambell.server.review.app.port.out.response.ReviewRatingSummaryDTO;
import com.magambell.server.review.app.port.out.response.ReviewRegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review", description = "Review API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewUseCase reviewUseCase;

    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "리뷰 등록")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReviewRegisterResponse.class))})
    @PostMapping("")
    public Response<ReviewRegisterResponse> registerReview(
            @RequestBody @Validated final RegisterReviewRequest request,
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
    ) {
        ReviewRegisterResponseDTO reviewRegisterResponseDTO = reviewUseCase.registerReview(request.toServiceRequest(),
                customUserDetails.userId());

        return new Response<>(reviewRegisterResponseDTO.toResponse());
    }

    @Operation(summary = "리뷰 리스트")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReviewListResponse.class))})
    @GetMapping("")
    public Response<ReviewListResponse> getReviewList(
            @ModelAttribute @Validated final ReviewListRequest request
    ) {
        List<ReviewListDTO> reviewList = reviewUseCase.getReviewList(request.toServiceRequest());
        return new Response<>(new ReviewListResponse(reviewList));
    }

    @Operation(summary = "리뷰 리스트 평점별 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReviewRatingSummaryResponse.class))})
    @GetMapping("/rating")
    public Response<ReviewRatingSummaryResponse> getReviewRatingAll(
            @ModelAttribute @Validated final ReviewRatingAllRequest request
    ) {
        ReviewRatingSummaryDTO reviewRatingAll = reviewUseCase.getReviewRatingAll(request.toServiceRequest());
        return new Response<>(reviewRatingAll.toResponse());
    }

    @Operation(summary = "내가 작성한 리뷰 목록")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReviewListResponse.class))})
    @GetMapping("/me")
    public Response<ReviewListResponse> getMyReviewList(
            @ModelAttribute @Validated final ReviewMyRequest request,
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
    ) {
        List<ReviewListDTO> reviewListByUser = reviewUseCase.getReviewListByUser(
                request.toServiceRequest(customUserDetails.userId()));
        return new Response<>(new ReviewListResponse(reviewListByUser));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "리뷰 삭제")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = BaseResponse.class))})
    @DeleteMapping("/{reviewId}")
    public Response<BaseResponse> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
    ) {
        reviewUseCase.deleteReview(new DeleteReviewServiceRequest(reviewId, customUserDetails.userId()));

        return new Response<>();
    }
}
