package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.like.dao.ReviewLikesRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewCounterResponse;
import com.rms.drifeserver.domain.review.service.dto.response.StoreReviewCountInfoResponse;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoArgsConstructor
public class ReviewServiceUtils {

    @NotNull
    public static ReviewCounterResponse getReviewCount(VisitRepository visitRepository, ReviewRepository reviewRepository,
                                                  ReviewLikesRepository reviewLikesRepository, Review review) {
        StoreReviewCountInfoResponse storeReviewCountInfo = getStoreReviewCountInfo(visitRepository, reviewRepository, review.getStore());
        Long reviewLikes = reviewLikesRepository.countByStoreAndReview(review.getStore(), review);
        Long myVisitCount = visitRepository.countByStoreAndUser(review.getStore(), review.getUser());
        Long myReviewCount = reviewRepository.countByStoreAndUser(review.getStore(), review.getUser());
        Boolean myIsLiked = Optional.ofNullable(reviewLikesRepository.findByUserAndReview(review.getUser(), review)).isPresent();

        return ReviewCounterResponse.of(storeReviewCountInfo, reviewLikes, myVisitCount, myReviewCount, myIsLiked);
    }

    @NotNull
    public static StoreReviewCountInfoResponse getStoreReviewCountInfo(VisitRepository visitRepository, ReviewRepository reviewRepository, Store store) {
        Long storeVisitCount = visitRepository.countByStore(store);
        Long storeReviewCount = reviewRepository.countByStore(store);
        Long storeCustomCount = visitRepository.countByStoreWithCustom(store.getId());

        return StoreReviewCountInfoResponse.of(storeVisitCount, storeReviewCount, storeCustomCount);
    }

}
