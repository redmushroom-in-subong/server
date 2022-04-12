package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreDetailResponse {
    private Long storeId;
    private String storeDesc;
    private String snsAddress;

    public static StoreDetailResponse of(Store store){
        return new StoreDetailResponse(store.getId(), store.getStoreDesc(), store.getSnsAddress());
    }
}
