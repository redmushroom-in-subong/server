package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.*;

import java.time.LocalDateTime;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyStoreResponse {
    private Long storeId;
    private String storeName;
    private String visitedAt;
    private String thumbnailImage;
    private int regularCustomerCount;
    private int visitCount;
    private boolean isLike;

    public MyStoreResponse(Long storeId, String storeName, LocalDateTime visitedAt, String thumbnailImage, int regularCustomerCount, int visitCount, boolean isLike) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.visitedAt = visitedAt.toString().substring(0,10);
        this.thumbnailImage = thumbnailImage;
        this.regularCustomerCount = regularCustomerCount;
        this.visitCount = visitCount;
        this.isLike = isLike;
    }

    public MyStoreResponse(Long storeId, String storeName, String thumbnailImage, int regularCustomerCount, int visitCount, boolean isLike) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.thumbnailImage = thumbnailImage;
        this.regularCustomerCount = regularCustomerCount;
        this.visitCount = visitCount;
        this.isLike = isLike;
    }
}
