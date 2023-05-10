package com.telcom.ups.data.dto;

public class DispositivoDetalleDTO {

    private Integer iden;
    private Double alarma;
    private Double consumo;
    private Double rssi;
    private Double battery;
    private Double longitude;
    private Double latitude;
    //private EstatusDTO estatus;
    private DispositivoDTO dispositivo;

    public DispositivoDetalleDTO() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public Double getAlarma() {
        return alarma;
    }

    public void setAlarma(Double alarma) {
        this.alarma = alarma;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public Double getRssi() {
        return rssi;
    }

    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

//    public EstatusDTO getEstatus() {
//        return estatus;
//    }
//
//    public void setEstatus(EstatusDTO estatus) {
//        this.estatus = estatus;
//    }

    public DispositivoDTO getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoDTO dispositivo) {
        this.dispositivo = dispositivo;
    }
}
