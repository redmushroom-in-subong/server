package com.rms.drifeserver.domain.badgecode.service.impl;

import com.rms.drifeserver.domain.badgecode.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.CONFLICT_BADGE_NAME;

@Service
@RequiredArgsConstructor
public class BadgeCodeServiceImpl implements BadgeCodeService {
    final private BadgeCodeRepository badgeCodeRepository;
    @Override
    public List<BadgeCode> findAll(){
        return badgeCodeRepository.findAll();
    }
    @Override
    public Optional<BadgeCode> findById(Long id){
        return badgeCodeRepository.findById(id);
    }
    @Override
    @Transactional
    public void addBadgeCode(AddBadgeCodeRequest addBadgeCodeRequest){
        BadgeCode badgeCode=new BadgeCode();
        badgeCode.setBadgeName(addBadgeCodeRequest.getBadgeName());
        badgeCode.setReviewCount(addBadgeCodeRequest.getReviewCount());
    }
    @Override
    public void removeBadgeCode(Long id){
        badgeCodeRepository.deleteById(id);
    }

}
