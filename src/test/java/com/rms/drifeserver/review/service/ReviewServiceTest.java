package com.rms.drifeserver.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewKeywordRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeyword;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewKeywordRepository reviewKeywordRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VisitRepository visitRepository;

    @AfterEach
    void cleanUp() {
        reviewKeywordRepository.deleteAllInBatch();
        reviewRepository.deleteAllInBatch();
        visitRepository.deleteAllInBatch();
    }

    @Test
    void 가게에_새로운_리뷰를_작성한다() {
        //given
        String contents = "또 오고싶어용";
        List<Long> keywordIds = new ArrayList<>();
        keywordIds.add(1L);
        keywordIds.add(3L);
        AddReviewRequest request = new AddReviewRequest(contents, keywordIds);

        //when
        reviewService.addReview(request, 16L, 1L);

        //then
        List<Review> reviews = reviewRepository.findAll();
        Assertions.assertThat(reviews).hasSize(1);
    }

    @Test
    void 가게에_사용자가_작성한_리뷰를_수정한다() {
        // given
        String contents = "우와 맛있어요";
        List<Long> keywordIds = new ArrayList<>();
        keywordIds.add(1L);
        keywordIds.add(2L);

        UpdateReviewRequest request = new UpdateReviewRequest(contents, keywordIds);

        // when
        reviewService.updateReview(request, 16L, 12L);

        // then
        List<Review> reviews = reviewRepository.findAll();
        Assertions.assertThat(reviews).hasSize(1);
    }
}
