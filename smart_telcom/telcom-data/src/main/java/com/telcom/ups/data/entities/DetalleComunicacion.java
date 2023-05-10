package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Detalles_Comunicacion")
public class DetalleComunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_com_iden", nullable = false)
    private Integer iden;

    @Column(name = "det_com_service")
    private String service;

    @Column(name = "det_com_sampling")
    private String sampler;

    @Column(name = "det_com_frequency")
    private String frequency;

    @Column(name = "det_com_cut_day")
    private Integer cutDay;

    @Column(name = "det_com_daily_transmissions")
    private String dailyTransmissions;

    @Column(name = "det_com_update_time")
    private String updateTime;

    @Column(name = "det_com_async_send")
    private Integer asyncSend;

    @Column(name = "det_com_leak_detect")
    private Integer leakDetect;

    @Column(name = "det_com_latitud")
    private Float latitud;

    @Column(name = "det_com_longitud")
    private Float longitud;

    @Column(name = "det_com_battery")
    private Integer battery;

    @Column(name = "det_com_rssi")
    private Short rssi;

    @Column(name = "det_com_set_position")
    private String setPosition;

    @Column(name = "det_com_set_cnf")
    private String setCNF;

    @Column(name = "det_com_hora")
    private String hora;

    @Column(name = "det_com_estado")
    private String estado;

    @Column(name = "det_com_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "det_com_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    public DetalleComunicacion() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
