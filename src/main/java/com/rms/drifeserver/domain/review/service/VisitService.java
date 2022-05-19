package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.user.model.User;

public interface VisitService {
    void addVisit(User user, Long storeId);
}
