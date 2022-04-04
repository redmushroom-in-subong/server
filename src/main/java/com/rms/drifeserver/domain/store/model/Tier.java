package com.rms.drifeserver.domain.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tier {
    SOULMATE("소울메이트"),
    BF("절친한사이"),
    CLOSE("친한사이"),
    AWKWARD("어색한사이"),
    STRANGER("모르는사이");

    final private String name;
    private Tier(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
/*

    //한글명 파라미터로 받아서 enum 형식으로 반환
    public static Tier nameOf(String name) {
        for (Tier status : Tier.values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }
*/

    public static String getTier(Long visitCnt){
        if(visitCnt>=30) return SOULMATE.getName();
        else if(visitCnt>=20) return BF.getName();
        else if(visitCnt>=10) return CLOSE.getName();
        else if(visitCnt>=5) return AWKWARD.getName();
        else return STRANGER.getName();
    }
}
