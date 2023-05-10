package com.telcom.ups.data.dto;

public class ReporteComunicacionDTO {

    private Integer iden;
    private String modeDailySchedule;
    private String modeNormalSampler;
    private String modeResumeSuspend;
    private Integer cutOffDay;
    private Integer comLora;
    private Integer retries;
    private Integer timeAsyn;
    private Integer timeLeakDetect;
    private Integer comando;
    private Integer parametro;

    public ReporteComunicacionDTO() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getModeDailySchedule() {
        return modeDailySchedule;
    }

    public void setModeDailySchedule(String modeDailySchedule) {
        this.modeDailySchedule = modeDailySchedule;
    }

    public String getModeNormalSampler() {
        return modeNormalSampler;
    }

    public void setModeNormalSampler(String modeNormalSampler) {
        this.modeNormalSampler = modeNormalSampler;
    }

    public String getModeResumeSuspend() {
        return modeResumeSuspend;
    }

    public void setModeResumeSuspend(String modeResumeSuspend) {
        this.modeResumeSuspend = modeResumeSuspend;
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

    @Override
    public String toString() {
        return "ReporteComunicacionDTO{" +
                "iden=" + iden +
                ", modeDailySchedule='" + modeDailySchedule + '\'' +
                ", modeNormalSampler='" + modeNormalSampler + '\'' +
                ", modeResumeSuspend='" + modeResumeSuspend + '\'' +
                ", cutOffDay=" + cutOffDay +
                ", comLora=" + comLora +
                ", retries=" + retries +
                ", timeAsyn=" + timeAsyn +
                ", timeLeakDetect=" + timeLeakDetect +
                ", comando=" + comando +
                ", parametro=" + parametro +
                '}';
    }
}
