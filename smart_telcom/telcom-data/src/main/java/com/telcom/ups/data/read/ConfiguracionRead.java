package com.telcom.ups.data.read;

import java.util.Set;

public class ConfiguracionRead {

    private Integer iden;
    private String nombre;
    private String comando;
    private String descripcion;
    private Set<ParametroRead> parametros;

    public ConfiguracionRead() {
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

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<ParametroRead> getParametros() {
        return parametros;
    }

    public void setParametros(Set<ParametroRead> parametros) {
        this.parametros = parametros;
    }
}
