package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.badge.event.UserBadgeEvent;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.review.service.RetrieveReviewsService;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;
    private final UserService userService;
    private final RetrieveReviewsService retrieveReviewsService;
    //TODO to publish event
    private final ApplicationEventPublisher eventPublisher;
    @GetMapping("/v1/store/reviews")
    public ApiResponse<ReviewsResponse> getReviews(@RequestParam(required = false) Long userId,
                                                   @RequestParam(required = false) Long keywordId,
                                                   @RequestParam Long storeId, Pageable pageable) {
        if (userId == null && keywordId == null) {
           return ApiResponse.success(retrieveReviewsService.getAllReviewsInStore(storeId, pageable));
        }
        else if (userId == null) {
            return ApiResponse.success(retrieveReviewsService.getAllReviewsInStoreWithKeywordId(storeId, keywordId, pageable));
        }
        else if (keywordId == null) {
            return ApiResponse.success(retrieveReviewsService.getAllReviewsInStoreWithUserId(storeId, userId, pageable));
        }
        return ApiResponse.error(ErrorCode.INVALID);
    }

    @PostMapping("/v1/store/reviews")
    public ApiResponse<Object> addReview(@Valid @RequestBody AddReviewRequest request) {
        User user = userService.getUserEntity();
        //TODO publish event
        eventPublisher.publishEvent(new UserBadgeEvent(this,user.getId()));

        reviewService.addReview(request, user);

        return ApiResponse.success(null);
    }

    @GetMapping("/v1/store/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> getReview(@PathVariable Long reviewId) {
        return ApiResponse.success(reviewService.getReviewDetail(reviewId));
    }

    @PutMapping("/v1/store/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> updateReview(@RequestBody UpdateReviewRequest request, @PathVariable Long reviewId) {
        User user = userService.getUserEntity();
        return ApiResponse.success(reviewService.updateReview(request, reviewId, user));
    }

    @DeleteMapping("/v1/store/reviews/{reviewId}")
    public ApiResponse<Object> deleteReview(@PathVariable Long reviewId) {
        User user = userService.getUserEntity();
        reviewService.deleteReview(reviewId, user);
        return ApiResponse.success(null);
    }

    @PostMapping("/v1/store/reviews/{reviewId}/like")
    public ApiResponse<Object> toggleReviewLike(@PathVariable Long reviewId) {
        User user = userService.getUserEntity();
        reviewService.toggleReviewLike(reviewId, user);
        return ApiResponse.success(null);
    }
}
