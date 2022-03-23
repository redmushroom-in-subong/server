package com.rms.drifeserver.domain.badgecode.service.impl;

import com.rms.drifeserver.domain.badgecode.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadgeCodeServiceImpl implements BadgeCodeService {
    final private BadgeCodeRepository badgeCodeRepository;
    @Override
    public List<BadgeCode> findAll(){
        return badgeCodeRepository.findAll();
    }
    @Override
    public Optional<BadgeCode> findBadgeCodeById(Long id){
        return badgeCodeRepository.findById(id);
    }
    @Override
    @Transactional
    public void addBadgeCode(AddBadgeCodeRequest addBadgeCodeRequest){
        BadgeCode badgeCode=new BadgeCode();
        badgeCode.setBadgeName(addBadgeCodeRequest.getBadgeName());
        badgeCode.setReviewCount(addBadgeCodeRequest.getReviewCount());
        badgeCodeRepository.save(badgeCode);
    }
    @Override
    public void removeBadgeCode(Long id){
        badgeCodeRepository.deleteById(id);
    }

}
