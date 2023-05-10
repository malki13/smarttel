package com.telcom.ups.data.read;

import com.telcom.ups.data.info.UsuarioInfo;

import java.time.LocalDateTime;

public class DispositivoQueueRead {

    private Integer iden;
    private String deveui;
    private String payload;
    private Integer fCnt;
    private Integer fPort;
    private LocalDateTime fecha;
    private UsuarioInfo usuario;

    public DispositivoQueueRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
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

    public UsuarioInfo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioInfo usuario) {
        this.usuario = usuario;
    }
}
