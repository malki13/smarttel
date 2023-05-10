package com.telcom.ups.data.read;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReporteComunicacionRead {

    private Integer iden;
    private String ModeDailySchedule;
    private String ModeNormalSampler;
    private String ModeResumeSuspend;
    private Integer cutOffDay;
    private Integer comLora;
    private Integer retries;
    private Integer timeAsyn;
    private Integer timeLeakDetect;
    private LocalDateTime updatedAt;

    public ReporteComunicacionRead() {
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "America/Guayaquil")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
