package com.telcom.ups.data.dto;

public class DetalleComunicacionDTO {

    private Integer iden;
    private String service;
    private String sampler;
    private String frequency;
    private Integer cutDay;
    private String dailyTransmissions;
    private String updateTime;
    private Integer asyncSend;
    private Integer leakDetect;
    private Float latitud;
    private Float longitud;
    private Integer battery;
    private Short rssi;
    private String setPosition;
    private String setCNF;
    private String hora;
    private String estado;
    private Integer comando;
    private Integer parametro;

    public DetalleComunicacionDTO() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getCutDay() {
        return cutDay;
    }

    public void setCutDay(Integer cutDay) {
        this.cutDay = cutDay;
    }

    public String getDailyTransmissions() {
        return dailyTransmissions;
    }

    public void setDailyTransmissions(String dailyTransmissions) {
        this.dailyTransmissions = dailyTransmissions;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAsyncSend() {
        return asyncSend;
    }

    public void setAsyncSend(Integer asyncSend) {
        this.asyncSend = asyncSend;
    }

    public Integer getLeakDetect() {
        return leakDetect;
    }

    public void setLeakDetect(Integer leakDetect) {
        this.leakDetect = leakDetect;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Short getRssi() {
        return rssi;
    }

    public void setRssi(Short rssi) {
        this.rssi = rssi;
    }

    public String getSetPosition() {
        return setPosition;
    }

    public void setSetPosition(String setPosition) {
        this.setPosition = setPosition;
    }

    public String getSetCNF() {
        return setCNF;
    }

    public void setSetCNF(String setCNF) {
        this.setCNF = setCNF;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getComando() {
        return comando;
    }

    public void setComando(Integer comando) {
        this.comando = comando;
    }

    public Integer getParametro() {
        return parametro;
    }

    public void setParametro(Integer parametro) {
        this.parametro = parametro;
    }
}
