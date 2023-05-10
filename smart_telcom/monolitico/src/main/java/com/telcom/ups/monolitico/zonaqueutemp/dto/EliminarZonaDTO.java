package com.telcom.ups.monolitico.zonaqueutemp.dto;

public class EliminarZonaDTO {

    private Integer zonaId;
    private String tokenCS;

    public EliminarZonaDTO() {
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
    }

    public String getTokenCS() {
        return tokenCS;
    }

    public void setTokenCS(String tokenCS) {
        this.tokenCS = tokenCS;
    }
}
