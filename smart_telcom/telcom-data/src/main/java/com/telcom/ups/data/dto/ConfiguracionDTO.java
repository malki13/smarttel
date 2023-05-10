package com.telcom.ups.data.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ConfiguracionDTO {


    @NotNull(message = " El nombre DE LA CONFIGURACION NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA CONFIGURACION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA CONFIGURACION ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " El comando DE LA CONFIGURACION NO DEBE SER NULO ")
    @NotEmpty(message = " EL comando DE LA CONFIGURACION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL comando DE LA CONFIGURACION ES REQUERIDO ")
    private String comando;

    @NotNull(message = " LA descripcion DE LA CONFIGURACION NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DE LA CONFIGURACION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DE LA CONFIGURACION ES REQUERIDO ")
    private String descripcion;

    public ConfiguracionDTO() {
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

}
