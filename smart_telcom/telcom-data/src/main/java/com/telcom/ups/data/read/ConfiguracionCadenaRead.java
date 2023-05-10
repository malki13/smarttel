package com.telcom.ups.data.read;

import com.telcom.ups.data.dto.DispositivoTipoDTO;

public class ConfiguracionCadenaRead {

    private Integer iden;
    private String nombre;
    private String data;
    private String hexadecimal;
    private String base64;
    private DispositivoTipoDTO dispositivoTipo;

    public ConfiguracionCadenaRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public DispositivoTipoDTO getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(DispositivoTipoDTO dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
    }
}
