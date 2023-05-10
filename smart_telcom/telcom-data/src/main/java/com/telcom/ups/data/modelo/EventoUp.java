package com.telcom.ups.data.modelo;

public class EventoUp {

    private String id;
    private String nombreDispositivo;
    private String applicationId;
    private Double frecuencia;
    private Integer fcnt;
    private Integer fport;
    private String rx_info;
    private String tx_info;

    public EventoUp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getFcnt() {
        return fcnt;
    }

    public void setFcnt(Integer fcnt) {
        this.fcnt = fcnt;
    }

    public Integer getFport() {
        return fport;
    }

    public void setFport(Integer fport) {
        this.fport = fport;
    }

    public String getRx_info() {
        return rx_info;
    }

    public void setRx_info(String rx_info) {
        this.rx_info = rx_info;
    }

    public String getTx_info() {
        return tx_info;
    }

    public void setTx_info(String tx_info) {
        this.tx_info = tx_info;
    }
}
