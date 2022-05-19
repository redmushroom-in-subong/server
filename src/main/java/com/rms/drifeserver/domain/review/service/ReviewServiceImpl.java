package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.like.dao.ReviewLikesRepository;
import com.rms.drifeserver.domain.like.model.ReviewLikes;
import com.rms.drifeserver.domain.review.dao.ReviewKeywordTypeRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeywordType;
import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ReviewKeywordTypeRepository reviewKeywordTypeRepository;
    private final ReviewLikesRepository reviewLikesRepository;
    private final VisitRepository visitRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public void addReview(AddReviewRequest request, Long storeId, User user) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));

        Review review = request.toReview(user, store);

        reviewRepository.save(review);
        visitRepository.save(Visit.of(user, store));
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewDetailResponse getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REVIEW));

        return ReviewDetailResponse.of(review, ReviewServiceUtils.getReviewCount(visitRepository, reviewRepository,
                reviewLikesRepository, review));
    }

    @Transactional
    @Override
    public ReviewDetailResponse updateReview(UpdateReviewRequest request, Long reviewId, User user) {
        Review review = reviewRepository.findByIdAndUser(reviewId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REVIEW));

        List<ReviewKeywordType> reviewKeywordTypes = reviewKeywordTypeRepository.findAllById(request.getKeywordIds());

        review.update(request.getContents(), reviewKeywordTypes, request.getImages());

        return ReviewDetailResponse.of(review, ReviewServiceUtils.getReviewCount(visitRepository, reviewRepository,
                reviewLikesRepository, review));
    }

    @Transactional
    @Override
    public void deleteReview(Long reviewId, User user) {
        Review review = reviewRepository.findByIdAndUser(reviewId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REVIEW));
        reviewRepository.delete(review);
    }

    @Transactional
    @Override
    public void toggleReviewLike(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REVIEW));
        ReviewLikes reviewLikes = reviewLikesRepository.findByUserAndReview(user, review);

        if (reviewLikes != null) {
            reviewLikesRepository.delete(reviewLikes);
        } else {
            reviewLikesRepository.save(ReviewLikes.of(user, review));
        }
    }
}
