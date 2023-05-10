package com.telcom.ups.data.read;

import com.telcom.ups.data.info.InterventorInfo;

public class EmpresaRead {

    private Integer iden;
    private Integer idorganizationas;
    private Integer numGateways;
    private Integer numDevices;
    private InterventorInfo interventor;

    public EmpresaRead() {
    }

    public EmpresaRead(Integer iden, Integer idorganizationas, Integer numGateways, Integer numDevices, InterventorInfo interventor) {
        this.iden = iden;
        this.idorganizationas = idorganizationas;
        this.numGateways = numGateways;
        this.numDevices = numDevices;
        this.interventor = interventor;
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public Integer getIdorganizationas() {
        return idorganizationas;
    }

    public void setIdorganizationas(Integer idorganizationas) {
        this.idorganizationas = idorganizationas;
    }

    public Integer getNumGateways() {
        return numGateways;
    }

    public void setNumGateways(Integer numGateways) {
        this.numGateways = numGateways;
    }

    public Integer getNumDevices() {
        return numDevices;
    }

    public void setNumDevices(Integer numDevices) {
        this.numDevices = numDevices;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }
}
