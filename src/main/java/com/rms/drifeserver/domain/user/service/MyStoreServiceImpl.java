package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.dao.MyStoreRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyRecentStoreResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MyStoreServiceImpl implements MyStoreService{
    final private MyStoreRepository myStoreRepository;
    final private UserService userService;
    @Override
    public List<MyStoreResponse> getMyFavoriteStores() {
        User user=userService.getUserEntity();
        return myStoreRepository.findFavoriteStore(user.getId());
    }
    @Override
    public Stream<MyRecentStoreResponse> getMyRecentStores() {
        User user=userService.getUserEntity();
        return myStoreRepository.findRecentStore(user.getId()).stream().sorted(Comparator.comparing(MyRecentStoreResponse::getVisitedAt).reversed());
    }

    @Override
    public List<MyStoreResponse> getMyFrequentStores() {
        User user=userService.getUserEntity();
        return myStoreRepository.findFrequentStore(user.getId());
    }
}
