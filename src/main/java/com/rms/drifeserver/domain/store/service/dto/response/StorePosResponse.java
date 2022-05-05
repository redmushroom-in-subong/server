package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StorePosResponse {
    private Long id;
    private String x;
    private String y;

    public static StorePosResponse of(Store store){
        return new StorePosResponse(store.getId(), store.getX(), store.getY());
    }
}
