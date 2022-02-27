package com.rms.drifeserver.domain.store.model;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    @Id @GeneratedValue
    private Long id;

    //카카오 장소 아이디
    private String kakaoPlaceId;

    private String storeDesc;
    private String snsAddress;

    //메뉴
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    //영업시간 - 주 테이블에 외래키 (DB랑 다름)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BHOURS_ID")
    private BusinessHours businessHours;

    //리뷰
    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();

    //방문
    @OneToMany(mappedBy = "store")
    private List<Visit> visits = new ArrayList<>();
    
    //가게사진
}
