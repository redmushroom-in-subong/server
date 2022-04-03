package com.rms.drifeserver.domain.store.service.dto.response;

import com.rms.drifeserver.domain.store.model.BusinessHours;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessHoursResponse {
    private Long bhoursId;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    public static BusinessHoursResponse of(BusinessHours bhours){
        return new BusinessHoursResponse(bhours.getId(), bhours.getMon(), bhours.getTue(), bhours.getWed(),
                bhours.getThu(), bhours.getFri(), bhours.getSat(), bhours.getSun());
    }
}
