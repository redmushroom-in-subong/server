package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyRecentStoreResponse {
    private Long visitId;
    private Long storeId;
    private String storeName;
    private String visitedAt;
    private String thumbnailImage;
    private long regularCustomerCount;
    private int visitCount;
    private boolean isLike;

    public MyRecentStoreResponse(Long visitId,Long storeId, String storeName, LocalDateTime visitedAt, String thumbnailImage, long regularCustomerCount, int visitCount, boolean isLike) {
        this.visitId = visitId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.visitedAt = visitedAt.toString().substring(0,10);
        this.thumbnailImage = thumbnailImage;
        this.regularCustomerCount = regularCustomerCount;
        this.visitCount = visitCount;
        this.isLike = isLike;
    }
}
