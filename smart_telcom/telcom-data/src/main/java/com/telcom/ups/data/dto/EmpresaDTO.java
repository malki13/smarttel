package com.telcom.ups.data.dto;

import javax.validation.constraints.NotNull;

public class EmpresaDTO {

    @NotNull(message = " EL idorganizationas DE LA EMPRESA NO DEBE SER NULO ")
    private Integer idorganizationas;

    @NotNull(message = " EL numGateways DE LA EMPRESA NO DEBE SER NULO ")
    private Integer numGateways;

    @NotNull(message = " EL numDevices DE LA EMPRESA NO DEBE SER NULO ")
    private Integer numDevices;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Integer idorganizationas, Integer numGateways, Integer numDevices) {
        this.idorganizationas = idorganizationas;
        this.numGateways = numGateways;
        this.numDevices = numDevices;
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
}
