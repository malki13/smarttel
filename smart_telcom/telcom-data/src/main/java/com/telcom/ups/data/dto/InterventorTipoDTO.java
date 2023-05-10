package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InterventorTipoDTO {

    @NotNull(message = " EL nombre DEL TIPO DE INTERVENTOR NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL TIPO DE INTERVENTOR NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL TIPO DE INTERVENTOR ES REQUERIDO ")
    private String nombre;

    private String idclientesri;
    private String idproveedorsri;
    private String idtarcredito;

    public InterventorTipoDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdclientesri() {
        return idclientesri;
    }

    public void setIdclientesri(String idclientesri) {
        this.idclientesri = idclientesri;
    }

    public String getIdproveedorsri() {
        return idproveedorsri;
    }

    public void setIdproveedorsri(String idproveedorsri) {
        this.idproveedorsri = idproveedorsri;
    }

    public String getIdtarcredito() {
        return idtarcredito;
    }

    public void setIdtarcredito(String idtarcredito) {
        this.idtarcredito = idtarcredito;
    }
}

