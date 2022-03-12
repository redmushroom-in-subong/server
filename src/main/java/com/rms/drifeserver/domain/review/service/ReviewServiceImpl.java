package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
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
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewKeywordTypeRepository reviewKeywordTypeRepository;

    @Transactional
    @Override
    public void addReview(AddReviewRequest request, Long userId, Long storeId) {

        User user = userRepository.getById(userId);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));

        Review review = request.toReview(user, store);

        List<ReviewKeywordType> reviewKeywordTypes = request.getKeywordIds().stream()
                .map((id) -> reviewKeywordTypeRepository.getById(id))
                .collect(Collectors.toList());
        review.addReviewKeyword(reviewKeywordTypes);

        reviewRepository.save(review);
        visitRepository.save(Visit.of(user, store));
    }

    @Override
    public ReviewDetailResponse getReviewDetail(Long storeId, Long reviewId) {

        return null;
    }

    @Override
    public ReviewDetailResponse updateReview(UpdateReviewRequest request, Long userId, Long storeId, Long reviewId) {
        return null;
    }

    @Override
    public void deleteReview(Long userId, Long storeId, Long reviewId) {

    }
}
