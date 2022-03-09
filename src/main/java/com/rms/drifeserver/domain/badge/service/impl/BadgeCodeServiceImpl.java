package com.rms.drifeserver.domain.badge.service.impl;

import com.rms.drifeserver.domain.badge.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badge.model.BadgeCode;
import com.rms.drifeserver.domain.badge.service.BadgeCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void addBadgeCode(BadgeCode badgeCode){
        badgeCodeRepository.save(badgeCode);
    }
    @Override
    public void removeBadgeCode(Long id){
        badgeCodeRepository.deleteById(id);
    }
}
