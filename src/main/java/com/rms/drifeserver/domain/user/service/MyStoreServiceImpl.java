package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.dao.MyStoreRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<MyStoreResponse> getMyRecentStores() {
        User user=userService.getUserEntity();
        return myStoreRepository.findRecentStore(user.getId());
    }

    //TODO 미완
    @Override
    public List<MyStoreResponse> getMyFrequentStores() {
        User user=userService.getUserEntity();
        return myStoreRepository.findFrequentStore(user.getId());
    }
}
