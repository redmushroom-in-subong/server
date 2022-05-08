package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;

import java.util.List;

public interface MyStoreService {
    List<MyStoreResponse> getMyFavoriteStores();
    List<MyStoreResponse> getMyRecentStores();
    List<MyStoreResponse> getMyFrequentStores();
}
