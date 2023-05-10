package com.telcom.ups.data.dto;

public class ComunicacionDTO {

    private String estado;

    public ComunicacionDTO() {
    }

    public ComunicacionDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
