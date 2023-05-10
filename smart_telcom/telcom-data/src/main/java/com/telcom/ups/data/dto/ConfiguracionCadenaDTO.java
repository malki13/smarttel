package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConfiguracionCadenaDTO {

    @NotNull(message = " El nombre DE LA CADENA DE CONFIGURACION NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA CADENA DE CONFIGURACION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA CADENA DE CONFIGURACION ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA data DE LA CADENA DE CONFIGURACION NO DEBE SER NULO ")
    @NotEmpty(message = " LA data DE LA CADENA DE CONFIGURACION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA data DE LA CADENA DE CONFIGURACION ES REQUERIDO ")
    private String data;

    public ConfiguracionCadenaDTO() {
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
}
