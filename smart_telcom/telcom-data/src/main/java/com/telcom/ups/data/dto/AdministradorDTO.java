package com.telcom.ups.data.dto;

import com.telcom.ups.data.info.InterventorInfo;

import javax.validation.constraints.NotNull;

public class AdministradorDTO {

    private Integer iden;

    @NotNull
    private InterventorInfo interventor;

    @NotNull
    private EstatusDTO estatus;

    private EmpresaDTO empresa;

    public AdministradorDTO() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }

    public EstatusDTO getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusDTO estatus) {
        this.estatus = estatus;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }
}
