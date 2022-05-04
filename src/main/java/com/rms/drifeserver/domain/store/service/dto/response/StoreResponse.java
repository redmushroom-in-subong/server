package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponse {
    private Long storeId;
    private String storeName;
    private String phone;
    private String roadAddressName;
    private String storeDesc;
    private String snsAddress;

    public static StoreResponse of(Store store){
        return new StoreResponse(store.getId(), store.getStoreName(), store.getPhone(),
                store.getRoadAddressName(), store.getStoreDesc(), store.getSnsAddress());
    }
}
