package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.store.model.Tier;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInStoreResponse {
    private Long storeId;
    private Long userId;
    private String myStoreTier;
    private Long myVisitCount;

    public static UserInStoreResponse of(Store store, User user, Long myVisitCount){
        return new UserInStoreResponse(store.getId(), user.getId(), Tier.getTier(myVisitCount), myVisitCount);
    }
}
