package com.rms.drifeserver.domain.badgecode.service;

import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;
import com.rms.drifeserver.domain.common.exception.BaseException;

import java.util.List;
import java.util.Optional;

public interface BadgeCodeService {
    Optional<BadgeCode> findById(Long id)throws Exception;
    void addBadgeCode(AddBadgeCodeRequest addBadgeCodeRequest)throws Exception, BaseException;
    void removeBadgeCode(Long id)throws Exception;
    List<BadgeCode> findAll()throws Exception;
}