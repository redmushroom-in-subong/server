package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewKeywordRepository;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewKeywordServiceImpl implements ReviewKeywordService {

    private final ReviewKeywordRepository reviewKeywordRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ReviewKeywordCountResponse> getReviewKeywordCountByStore(Store store) {
        return reviewKeywordRepository.getReviewKeywordCountByStore(store);
    }

}
