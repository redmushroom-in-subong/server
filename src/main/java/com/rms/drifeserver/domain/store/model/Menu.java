package com.rms.drifeserver.domain.store.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Menu {

    @Id @GeneratedValue
    //@Column(name = "")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    private String item;
    private Integer price;

    private Menu(Store store, String item, Integer price){
        this.store = store;
        this.item = item;
        this.price = price;
        store.getMenus().add(this);
    }

    public static Menu createMenu(Store store, String item, Integer price){
        Menu menu = new Menu(store, item, price);
        return menu;
    }
}
