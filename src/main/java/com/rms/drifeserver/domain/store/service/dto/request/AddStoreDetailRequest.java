package com.rms.drifeserver.domain.store.service.dto.request;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddStoreDetailRequest {
    private String storeDesc;
    private String snsAddress;

}
