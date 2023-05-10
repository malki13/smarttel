package com.telcom.ups.data.read;

import com.telcom.ups.data.info.InterventorInfo;


public class TecnicoRead {

    private Integer iden;
    private InterventorInfo interventor;
    private EstatusRead estatus;

    public TecnicoRead() {
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
