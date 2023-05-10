package com.telcom.ups.data.info;

import com.telcom.ups.data.read.EstatusRead;

import java.util.List;

public class ClienteInfo {

    private Integer iden;
    private InterventorInfo interventor;
    private EstatusRead estatus;
    private List<DispositivoInfoFactura> dispositivos;

    public ClienteInfo() {
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

    public List<DispositivoInfoFactura> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoInfoFactura> dispositivos) {
        this.dispositivos = dispositivos;
    }
}
