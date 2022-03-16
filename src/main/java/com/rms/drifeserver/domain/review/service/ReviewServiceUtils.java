package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewCounterResponse;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class ReviewServiceUtils {

    @NotNull
    static ReviewCounterResponse findCount(VisitRepository visitRepository, ReviewRepository reviewRepository, Review review) {

        long storeVisitCount = visitRepository.countByStore(review.getStore());
        long storeReviewCount = visitRepository.countByStoreWithCustom(review.getStore());
        long storeCustomCount = reviewRepository.countByStore(review.getStore());
        long myVisitCount = visitRepository.countByStoreAndUser(review.getStore(), review.getUser());
        long myReviewCount = reviewRepository.countByStoreAndUser(review.getStore(), review.getUser());
        String myStoreTier = "";

        return ReviewCounterResponse.of(storeVisitCount, storeReviewCount, storeCustomCount, myVisitCount, myReviewCount, myStoreTier);
    }

}
