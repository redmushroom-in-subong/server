package com.rms.drifeserver.domain.user.service.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

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

    public MyRecentStoreResponse(Long visitId,Long storeId, String storeName, String visitedAt, String thumbnailImage, long regularCustomerCount, int visitCount, boolean isLike) {
        this.visitId = visitId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.visitedAt = visitedAt;
        this.thumbnailImage = thumbnailImage;
        this.regularCustomerCount = regularCustomerCount;
        this.visitCount = visitCount;
        this.isLike = isLike;
    }

    static public MyRecentStoreResponse of(Long visitId,Long storeId, String storeName, String visitedAt,String thumbnailImage, long regularCustomerCount, int visitCount, boolean isLike) {
        return new MyRecentStoreResponse( visitId,storeId,  storeName, visitedAt, thumbnailImage ,regularCustomerCount,  visitCount,  isLike);
    }
    static public MyRecentStoreResponse of(Map<String,Object> resultMap){
        return new MyRecentStoreResponse(
                Long.parseLong(resultMap.get("visitedId").toString()),
               Long.parseLong(resultMap.get("storeId").toString()),
                resultMap.get("storeName").toString(),
                resultMap.get("visitedAt").toString(),
                resultMap.get("thumbnailImage").toString(),
                Long.parseLong(resultMap.get("regularCustomerCount").toString()),
                Integer.parseInt(resultMap.get("visitCount").toString()),
                Boolean.parseBoolean(resultMap.get("isLike").toString())
        );
    }
}
