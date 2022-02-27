package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
}
