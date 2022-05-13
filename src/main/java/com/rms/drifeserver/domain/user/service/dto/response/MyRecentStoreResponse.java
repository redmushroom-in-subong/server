package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyRecentStoreResponse {
    private Long storeId;
    private String storeName;
    private LocalDateTime visitedAt;
    private String thumbnailImage;
    private Long regularCustomerCount;
    private int visitCount;
    private int reviewCount;
    private boolean isLike;
}
