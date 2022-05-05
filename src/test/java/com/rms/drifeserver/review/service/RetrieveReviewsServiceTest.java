package com.rms.drifeserver.review.service;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.RetrieveReviewsService;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RetrieveReviewsServiceTest{

    @Autowired
    private RetrieveReviewsService retrieveReviewsService;
    private Pageable pageable;

    @Test
    void 해당_가게의_리뷰들을_가져온다() {
        //given

        //when
        ReviewsResponse reviews = retrieveReviewsService.getAllReviewsInStore(1L, pageable);
        //then
        Assertions.assertThat(reviews.getResults()).hasSize(4);
    }

    @Test
    void 해당_가게의_해당_사용자가_작성한_리뷰들을_가져온다() {
        //given

        //when
        ReviewsResponse reviews = retrieveReviewsService.getAllReviewsInStoreWithUserId(1L, 16L, pageable);
        //then
        Assertions.assertThat(reviews.getResults()).hasSize(4);
    }

    @Test
    void 해당_가게의_해당_키워드의_리뷰들을_가져온다() {
        //given

        //when
        ReviewsResponse reviews = retrieveReviewsService.getAllReviewsInStoreWithKeywordId(1L, 1L, pageable);
        //then
        Assertions.assertThat(reviews.getResults()).hasSize(2);
    }
}