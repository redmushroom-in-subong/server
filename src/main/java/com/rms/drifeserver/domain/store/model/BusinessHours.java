package com.rms.drifeserver.domain.store.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BusinessHours {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "businessHours", fetch = FetchType.LAZY)
    private Store store;

    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    protected BusinessHours(Store store, String mon, String tue, String wed, String thu,
                          String fri, String sat, String sun) {
        this.store = store;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

}
