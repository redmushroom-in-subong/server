package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.service.dto.response.MyRecentStoreResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;

import java.util.List;
import java.util.stream.Stream;

public interface MyStoreService {
    List<MyStoreResponse> getMyFavoriteStores();
    Stream<MyRecentStoreResponse> getMyRecentStores();
    List<MyStoreResponse> getMyFrequentStores();
}
