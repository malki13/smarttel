package com.telcom.ups.data.info;

import com.telcom.ups.data.dto.EstatusDTO;

public class UsuarioInfo {

    private Integer iden;
    private String nombre;
    private EstatusDTO estatus;
    private InterventorInfo interventor;

    public UsuarioInfo() {
    }

    public UsuarioInfo(Integer iden, String nombre, EstatusDTO estatus, InterventorInfo interventor) {
        this.iden = iden;
        this.nombre = nombre;
        this.estatus = estatus;
        this.interventor = interventor;
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstatusDTO getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusDTO estatus) {
        this.estatus = estatus;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }
}
