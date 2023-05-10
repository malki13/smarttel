package com.telcom.ups.data.info;

import java.util.List;

public class DispositivoTipoInfo {

    private Integer iden;
    private String codigo;
    private String nombre;
    private String descripcion;
    private List<ConfiguracionInfo> configuraciones;
    private List<ConfiguracionCadenaInfo> cadenas;

    public DispositivoTipoInfo() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ConfiguracionInfo> getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(List<ConfiguracionInfo> configuraciones) {
        this.configuraciones = configuraciones;
    }

    public List<ConfiguracionCadenaInfo> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<ConfiguracionCadenaInfo> cadenas) {
        this.cadenas = cadenas;
    }
}
