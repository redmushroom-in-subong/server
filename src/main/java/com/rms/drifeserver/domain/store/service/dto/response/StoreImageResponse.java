package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.image.model.ReviewImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreImageResponse {
    private Long imageId;
    private String imageUrl;

    public static StoreImageResponse of(ReviewImage reviewImage){
        return new StoreImageResponse(reviewImage.getId(), reviewImage.getImageUrl());
    }
}
