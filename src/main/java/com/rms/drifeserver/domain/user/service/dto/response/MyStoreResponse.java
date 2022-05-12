package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyStoreResponse {
    private Long storeId;
    private String storeName;
    private String thumbnailImage;
    private Long regularCustomerCount;
    private int visitCount;
    private int reviewCount;
    private boolean isLike;
}
