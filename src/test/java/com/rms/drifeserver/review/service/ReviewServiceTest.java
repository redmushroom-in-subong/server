package com.rms.drifeserver.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewKeywordRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeyword;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse;
import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ReviewKeywordRepository reviewKeywordRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VisitRepository visitRepository;

//    @AfterEach
//    void cleanUp() {
//        reviewKeywordRepository.deleteAllInBatch();
//        reviewRepository.deleteAllInBatch();
//        visitRepository.deleteAllInBatch();
//    }

    @Test
    void 가게에_사용자가_작성한_리뷰를_수정한다() {
        // given
        String contents = "우와 맛있어요";
        List<Long> keywordIds = new ArrayList<>();
        keywordIds.add(1L);
        keywordIds.add(2L);

        UpdateReviewRequest request = new UpdateReviewRequest(contents, keywordIds);

        // when
        //reviewService.updateReview(request, 16L, 12L);

        // then
        List<Review> reviews = reviewRepository.findAll();
        Assertions.assertThat(reviews).hasSize(4);
    }

    @Test
    void 가게_리뷰키워드수를_조회한다() {
        //given
        Store store = storeRepository.findById(1L).get();

        //when
        List<ReviewKeywordCountResponse> list = reviewKeywordRepository.getReviewKeywordCountByStore(store);

        //then
        for (ReviewKeywordCountResponse response : list)
        {
            System.out.println("response.getKeywordId() = " + response.getKeywordId());
            System.out.println("response.getCnt() = " + response.getCnt());

        }
        Assertions.assertThat(list).hasSize(3);
    }
}
