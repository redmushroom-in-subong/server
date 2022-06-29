package com.rms.drifeserver.integrationTest.review.service;

import com.rms.drifeserver.domain.oauth.entity.ProviderType;
import com.rms.drifeserver.domain.oauth.entity.RoleType;
import com.rms.drifeserver.domain.review.dao.ReviewKeywordTypeRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.integrationTest.SetupStoreIntegrationTest;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.image.dao.ReviewImageRepository;
import com.rms.drifeserver.domain.review.dao.ReviewKeywordRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
public class ReviewServiceTest extends SetupStoreIntegrationTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewKeywordRepository reviewKeywordRepository;

    @Autowired
    private ReviewKeywordTypeRepository reviewKeywordTypeRepository;

    @Autowired
    private ReviewImageRepository reviewImageRepository;

    @Autowired
    private VisitRepository visitRepository;

    @AfterEach
    void cleanUp() {
        reviewKeywordRepository.deleteAll();
        reviewKeywordTypeRepository.deleteAll();
        reviewImageRepository.deleteAll();
        reviewRepository.deleteAll();
        visitRepository.deleteAll();
        userRepository.deleteAll();
        storeRepository.deleteAll();
    }

    public static void assertReview(Review review, Long storeId, String contents, Long userId) {
        assertAll(
                () -> assertThat(review.getStore().getId()).isEqualTo(storeId),
                () -> assertThat(review.getUser().getId()).isEqualTo(userId),
                () -> assertThat(review.getContents()).isEqualTo(contents)
        );
    }

    @Nested
    class AddReviewTest {

        @Test
        void 가게에_새로운_리뷰를_작성합니다() {
            // given
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            AddReviewRequest request = AddReviewRequest.testBuilder()
                    .storeId(store.getId())
                    .contents(contents)
                    .keywordIds(keywordIds)
                    .imageUrls(imageUrls)
                    .build();

            //when
            reviewService.addReview(request, user);

            //then
            List<Review> reviews = reviewRepository.findAll();
            assertAll(
                    () -> assertThat(reviews).hasSize(1),
                    () -> assertThat(reviews.get(0).getReviewKeywords()).hasSize(2),
                    () -> assertThat(reviews.get(0).getReviewImages()).hasSize(2),
                    () -> assertReview(reviews.get(0), store.getId(), contents, userId)
            );
        }

        @Test
        void 가게에_새로운_리뷰를_작성할때_가게가_없는경우_NOTFOUND_에러가_발생합니다() {
            // given
            Long notFoundStoreId = -1L;

            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            AddReviewRequest request = AddReviewRequest.testBuilder()
                    .storeId(notFoundStoreId)
                    .contents(contents)
                    .keywordIds(keywordIds)
                    .imageUrls(imageUrls)
                    .build();

            // when & then
            assertThatThrownBy(() -> reviewService.addReview(request, user)).isInstanceOf(BaseException.class);
        }
    }
    @Nested
    class UpdateStoreReviewTest {

        @Test
        void 내가_작성한_리뷰를_수정합니다() {
            // given
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            String updatedContents = "정말 맛있어요";
            List<Long> updatedKeywordIds = new ArrayList<>(Arrays.asList(2L));
            List<String> updatedImageUrls = new ArrayList<>(Arrays.asList("test1.png"));

            Review review = Review.of(user, store, contents, keywordIds, imageUrls);
            reviewRepository.save(review);

            UpdateReviewRequest request = UpdateReviewRequest.testBuilder()
                    .contents(updatedContents)
                    .keywordIds(updatedKeywordIds)
                    .imageUrls(updatedImageUrls)
                    .build();

            // when
            reviewService.updateReview(request, review.getId(), user);

            // then
            List<Review> reviews = reviewRepository.findAll();
            assertAll(
                    () -> assertThat(reviews).hasSize(1),
                    () -> assertThat(reviews.get(0).getReviewKeywords()).hasSize(1),
                    () -> assertThat(reviews.get(0).getReviewImages()).hasSize(1),
                    () -> assertReview(reviews.get(0), store.getId(), updatedContents, userId)
            );
        }

        @Test
        void 존재하지_않는_리뷰를_수정하는경우_NOTFOUND_에러가_발생합니다() {
            // given
            Long notFoundReviewId = -1L;
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            UpdateReviewRequest request = UpdateReviewRequest.testBuilder()
                    .contents(contents)
                    .keywordIds(keywordIds)
                    .imageUrls(imageUrls)
                    .build();

            // when & then
            assertThatThrownBy(() -> reviewService.updateReview(request, notFoundReviewId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_리뷰를_수정하는_경우_NOT_FOUND_에러가_발생한다() {
            // given
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            String updatedContents = "정말 맛있어요";
            List<Long> updatedKeywordIds = new ArrayList<>(Arrays.asList(2L));
            List<String> updatedImageUrls = new ArrayList<>(Arrays.asList("test1.png"));

            Review review = Review.of(user, store, contents, keywordIds, imageUrls);
            reviewRepository.save(review);

            UpdateReviewRequest request = UpdateReviewRequest.testBuilder()
                    .contents(updatedContents)
                    .keywordIds(updatedKeywordIds)
                    .imageUrls(updatedImageUrls)
                    .build();

            // when & then
            User diffUser = new User(
                    "diff-social-id",
                    "diff-name",
                    "diff-email",
                    null,
                    "Y",
                    "diff-img",
                    ProviderType.LOCAL,
                    RoleType.USER,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            userRepository.save(diffUser);

            assertThatThrownBy(() -> reviewService.updateReview(request, review.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }
    }

    @Nested
    class DeleteStoreReviewTest {

        @Test
        void 내가_작성한_리뷰를_삭제합니다() {
            // given
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            Review review = Review.of(user, store, contents, keywordIds, imageUrls);
            reviewRepository.save(review);

            // when
            reviewService.deleteReview(review.getId(), user);

            // then
            List<Review> reviews = reviewRepository.findAll();
            assertThat(reviews).hasSize(0);
        }

        @Test
        void 없는_리뷰를_삭제하려하면_NOT_FOUND_에러가_발생한다() {
            // given
            Long notFoundReviewId = -1L;

            // when & then
            assertThatThrownBy(() -> reviewService.deleteReview(notFoundReviewId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_리뷰를_삭제하려하면_NOT_FOUND에러가_발생한다() {
            // given
            String contents = "우와 맛있어요";
            List<Long> keywordIds = new ArrayList<>(Arrays.asList(1L,3L));
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            Review review = Review.of(user, store, contents, keywordIds, imageUrls);
            reviewRepository.save(review);

            // when & then
            User diffUser = new User(
                    "diff-social-id",
                    "diff-name",
                    "diff-email",
                    null,
                    "Y",
                    "diff-img",
                    ProviderType.LOCAL,
                    RoleType.USER,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            userRepository.save(diffUser);

            assertThatThrownBy(() -> reviewService.deleteReview(review.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }

    }
}
