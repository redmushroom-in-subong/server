package com.rms.drifeserver.domain.store.model;

import com.rms.drifeserver.domain.like.model.StoreLikes;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store {

    @Id @GeneratedValue
    private Long id;

    private String phone;
    private String roadAddressName;
    private String x;
    private String y;
    private String regionCode;
    //가게이름
    private String storeName;
    //가게설명
    private String storeDesc;
    //가게 sns주소
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

    //좋아요
    @OneToMany(mappedBy = "store")
    private List<StoreLikes> likes = new ArrayList<>();

    //방문
    @OneToMany(mappedBy = "store")
    private List<Visit> visits = new ArrayList<>();
    
    //가게사진

    public BusinessHours createBhours(String mon, String tue, String wed, String thu,
                                       String fri, String sat, String sun){
        BusinessHours businessHours = new BusinessHours(this, mon, tue, wed, thu, fri, sat, sun);
        this.businessHours = businessHours;
        return businessHours;
    }

    public void updateDesc(String desc){
        this.storeDesc = desc;
    }

    public void updateSnsAdr(String snsAdr){
        this.snsAddress = snsAdr;
    }
}
