package com.telcom.ups.data.modelo;

import java.time.OffsetDateTime;

public class Data {

    private Integer idgen;
    private String iddb;
    private String nombreDispositivo;
    private OffsetDateTime fecha;
    private String applicationId;
    private Double frecuencia;
    private Integer fcnt;
    private Integer fport;
    private String data;

    public Data() {
    }

    public Integer getIdgen() {
        return idgen;
    }

    public void setIdgen(Integer idgen) {
        this.idgen = idgen;
    }

    public String getIddb() {
        return iddb;
    }

    public void setIddb(String iddb) {
        this.iddb = iddb;
    }

    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
