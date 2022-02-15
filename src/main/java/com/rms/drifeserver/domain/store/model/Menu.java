package com.rms.drifeserver.domain.store.model;

import javax.persistence.*;

@Entity
public class Menu {

    @Id @GeneratedValue
    //@Column(name = "")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    private String item;
    private Integer price;
}
