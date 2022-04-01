package com.rms.drifeserver.domain.store.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BusinessHours {

    @Id
    @GeneratedValue
    //@Column(name = "")
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

}
