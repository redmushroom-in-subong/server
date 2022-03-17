package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewCounterResponse;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class ReviewServiceUtils {

    @NotNull
    public static ReviewCounterResponse findCount(VisitRepository visitRepository, ReviewRepository reviewRepository, Review review) {

        long storeVisitCount = visitRepository.countByStore(review.getStore());
        long storeReviewCount = visitRepository.countByStoreWithCustom(review.getStore());
        long storeCustomCount = reviewRepository.countByStore(review.getStore());
        long myVisitCount = visitRepository.countByStoreAndUser(review.getStore(), review.getUser());
        long myReviewCount = reviewRepository.countByStoreAndUser(review.getStore(), review.getUser());
        String myStoreTier = getMyStoreTierByCount(myVisitCount);

        return ReviewCounterResponse.of(storeVisitCount, storeReviewCount, storeCustomCount, myVisitCount, myReviewCount, myStoreTier);
    }

    private static String getMyStoreTierByCount(long count) {

        if (count >= 20) {
            return "소울메이트";
        } else if (count >= 10) {
            return "절친한 사이";
        } else if (count >= 5) {
            return "친한 사이";
        } else if (count >= 1) {
            return "어색한 사이";
        }
        return "모르는 사이";
    }

}
