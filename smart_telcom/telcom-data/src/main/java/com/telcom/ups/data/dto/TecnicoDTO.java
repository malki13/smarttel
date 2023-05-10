package com.telcom.ups.data.dto;

import com.telcom.ups.data.info.InterventorInfo;

import javax.validation.constraints.NotNull;

public class TecnicoDTO {

    private Integer iden;

    @NotNull(message = " EL interventor DEL TECNICO NO DEBE SER NULO ")
    private InterventorInfo interventor;

    @NotNull(message = " EL estatus DEL TECNICO NO DEBE SER NULO ")
    private EstatusDTO estatus;

    private EmpresaDTO empresa;

    public TecnicoDTO() {
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
