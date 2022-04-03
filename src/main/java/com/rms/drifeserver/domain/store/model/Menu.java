package com.rms.drifeserver.domain.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Menu {

    @Id @GeneratedValue
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

    public void updateItem(String item){
        this.item = item;
    }

    public void updatePrice(Integer price){
        this.price = price;
    }
}
