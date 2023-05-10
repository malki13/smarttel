package com.telcom.ups.monolitico.util.chirpstack.modelo;

public class DeviceQueueItemClass {

    private String devEUI;
    private boolean confirmed;
    private Integer fCnt;
    private Integer fPort;
    private String data;

    public DeviceQueueItemClass() {
    }

    public String getDevEUI() {
        return devEUI;
    }

    public void setDevEUI(String devEUI) {
        this.devEUI = devEUI;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getfCnt() {
        return fCnt;
    }

    public void setfCnt(Integer fCnt) {
        this.fCnt = fCnt;
    }

    public Integer getfPort() {
        return fPort;
    }

    public void setfPort(Integer fPort) {
        this.fPort = fPort;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeviceQueueItem{" +
                "devEUI='" + devEUI + '\'' +
                ", confirmed=" + confirmed +
                ", fCnt=" + fCnt +
                ", fPort=" + fPort +
                ", data='" + data + '\'' +
                '}';
    }
}
