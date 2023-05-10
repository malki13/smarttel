package com.telcom.ups.data.modelo;

import java.time.OffsetDateTime;

public class DataAlarma {

    private Integer idgen;
    private String iddb;
    private String nombreDispositivo;
    private OffsetDateTime fecha;
    private String applicationId;
    private Double frecuencia;
    private Integer fcnt;
    private Integer fport;
    private String pipeEmpty;
    private String flowOnBlocked;
    private String lowBat;
    private String comFail;
    private String pw1st;
    private String leakDetect;
    private String negativeFlow;
    private String service;
    private String sampler;
    private String daily;
    private String rtcc;
    private String alarmaDec;

    public DataAlarma() {
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

    public String getPipeEmpty() {
        return pipeEmpty;
    }

    public void setPipeEmpty(String pipeEmpty) {
        this.pipeEmpty = pipeEmpty;
    }

    public String getFlowOnBlocked() {
        return flowOnBlocked;
    }

    public void setFlowOnBlocked(String flowOnBlocked) {
        this.flowOnBlocked = flowOnBlocked;
    }

    public String getLowBat() {
        return lowBat;
    }

    public void setLowBat(String lowBat) {
        this.lowBat = lowBat;
    }

    public String getComFail() {
        return comFail;
    }

    public void setComFail(String comFail) {
        this.comFail = comFail;
    }

    public String getPw1st() {
        return pw1st;
    }

    public void setPw1st(String pw1st) {
        this.pw1st = pw1st;
    }

    public String getLeakDetect() {
        return leakDetect;
    }

    public void setLeakDetect(String leakDetect) {
        this.leakDetect = leakDetect;
    }

    public String getNegativeFlow() {
        return negativeFlow;
    }

    public void setNegativeFlow(String negativeFlow) {
        this.negativeFlow = negativeFlow;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSampler() {
        return sampler;
    }

    public void setSampler(String sampler) {
        this.sampler = sampler;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getRtcc() {
        return rtcc;
    }

    public void setRtcc(String rtcc) {
        this.rtcc = rtcc;
    }

    public String getAlarmaDec() {
        return alarmaDec;
    }

    public void setAlarmaDec(String alarmaDec) {
        this.alarmaDec = alarmaDec;
    }
}
