package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public void addVisit(User user, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));

        visitRepository.save(Visit.of(user, store));
    }
}
