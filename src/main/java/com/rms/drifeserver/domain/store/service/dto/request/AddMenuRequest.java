package com.rms.drifeserver.domain.store.service.dto.request;

import com.rms.drifeserver.domain.store.model.Menu;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMenuRequest {
    private String item;
    private Integer price;

    public Menu toMenu(Store store){
        return Menu.createMenu(store, this.item, this.price);
    }
}
