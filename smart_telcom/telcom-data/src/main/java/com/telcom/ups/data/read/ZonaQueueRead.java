package com.telcom.ups.data.read;

import com.telcom.ups.data.info.UsuarioInfo;
import com.telcom.ups.data.info.ZonaInfoOnly;

import java.time.LocalDateTime;

public class ZonaQueueRead {

    private Integer iden;
    private String payload;
    private Integer fCnt;
    private Integer fPort;
    private LocalDateTime fecha;
    private ZonaInfoOnly zona;
    private UsuarioInfo usuario;

    public ZonaQueueRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ZonaInfoOnly getZona() {
        return zona;
    }

    public void setZona(ZonaInfoOnly zona) {
        this.zona = zona;
    }

    public UsuarioInfo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioInfo usuario) {
        this.usuario = usuario;
    }
}
