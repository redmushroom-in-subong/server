package com.rms.drifeserver.domain.store.service.dto.request;

import com.rms.drifeserver.domain.store.model.BusinessHours;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddBusinessHoursRequest {
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    public BusinessHours toBhours(Store store){
        return store.createBhours(this.mon, this.tue, this.wed, this.thu, this.fri, this.sat, this.sun);
    }
}
