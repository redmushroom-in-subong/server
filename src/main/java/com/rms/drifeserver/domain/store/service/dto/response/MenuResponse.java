package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuResponse {
    private Long menuId;
    private String item;
    private Integer price;

    public static MenuResponse of(Menu menu){
        return new MenuResponse(menu.getId(), menu.getItem(), menu.getPrice());
    }
}
