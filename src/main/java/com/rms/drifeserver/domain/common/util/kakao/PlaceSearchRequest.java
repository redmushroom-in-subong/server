package com.rms.drifeserver.domain.common.util.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class PlaceSearchRequest {
    private String query;
    private String category;
    private String x;
    private String y;
    private Integer radius;
    private String sort;

    public PlaceSearchRequest(String query, String category, String x, String y, Integer radius, String sort) {
        this.query = query;
        this.category = category;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.sort = sort;
    }
    public PlaceSearchRequest(String query, String category, String sort) {
        this.query = query;
        this.category = category;
        this.sort = sort;
    }
    public PlaceSearchRequest(String query) {
        this.query = query;
    }
    public PlaceSearchRequest(String query, String category) {
        this.query = query;
        this.category = category;
    }
}
