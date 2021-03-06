package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MyReviewResponse {
    private Long reviewId;
    private Long storeId;
    private String storeName;
    private String thumbnailImage;
    private String context;
}
