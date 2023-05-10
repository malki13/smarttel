package com.telcom.ups.data.info;

import com.telcom.ups.data.read.EstatusRead;

public class AdministradorInfo {

    private Integer iden;
    private InterventorInfo interventor;
    private EstatusRead estatus;

    public AdministradorInfo() {
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

    public EstatusRead getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusRead estatus) {
        this.estatus = estatus;
    }
}
