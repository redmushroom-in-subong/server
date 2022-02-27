package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewKeywordTypeRepository;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordTypeResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReviewKeywordTypeServiceImpl implements ReviewKeywordTypeService{
    private final ReviewKeywordTypeRepository reviewKeywordTypeRepository;

    @Override
    public List<ReviewKeywordTypeResponse> getReviewKeywordTypes() {
        return reviewKeywordTypeRepository.findAll()
                .stream()
                .map(ReviewKeywordTypeResponse::of)
                .collect(Collectors.toList());
    }
}
