package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Reportes_Comunicacion")
public class ReporteComunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_com_iden", nullable = false)
    private Integer iden;

    @Column(name = "rep_com_mode_daily_schedule")
    private String ModeDailySchedule;

    @Column(name = "rep_com_mode_normal_sampler")
    private String ModeNormalSampler;

    @Column(name = "rep_com_mode_resume_suspend")
    private String ModeResumeSuspend;

    @Column(name = "rep_com_cut_off_day")
    private Integer cutOffDay;

    @Column(name = "rep_com_com_lora")
    private Integer comLora;

    @Column(name = "rep_com_retries")
    private Integer retries;

    @Column(name = "rep_com_time_asyn")
    private Integer timeAsyn;

    @Column(name = "rep_com_time_leak_detect")
    private Integer timeLeakDetect;

    @Column(name = "rep_com_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "rep_com_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    public ReporteComunicacion() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getModeDailySchedule() {
        return ModeDailySchedule;
    }

    public void setModeDailySchedule(String modeDailySchedule) {
        ModeDailySchedule = modeDailySchedule;
    }

    public String getModeNormalSampler() {
        return ModeNormalSampler;
    }

    public void setModeNormalSampler(String modeNormalSampler) {
        ModeNormalSampler = modeNormalSampler;
    }

    public String getModeResumeSuspend() {
        return ModeResumeSuspend;
    }

    public void setModeResumeSuspend(String modeResumeSuspend) {
        ModeResumeSuspend = modeResumeSuspend;
    }

    public Integer getCutOffDay() {
        return cutOffDay;
    }

    public void setCutOffDay(Integer cutOffDay) {
        this.cutOffDay = cutOffDay;
    }

    public Integer getComLora() {
        return comLora;
    }

    public void setComLora(Integer comLora) {
        this.comLora = comLora;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Integer getTimeAsyn() {
        return timeAsyn;
    }

    public void setTimeAsyn(Integer timeAsyn) {
        this.timeAsyn = timeAsyn;
    }

    public Integer getTimeLeakDetect() {
        return timeLeakDetect;
    }

    public void setTimeLeakDetect(Integer timeLeakDetect) {
        this.timeLeakDetect = timeLeakDetect;
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
