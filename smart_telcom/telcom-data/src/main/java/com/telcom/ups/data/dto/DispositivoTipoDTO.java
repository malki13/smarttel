package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DispositivoTipoDTO {

    @NotNull(message = " EL codigo DEL TIPO DE DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " EL codigo DEL TIPO DE DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL codigo DEL TIPO DE DISPOSITIVO ES REQUERIDO ")
    private String codigo;

    @NotNull(message = " EL nombre DEL TIPO DE DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL TIPO DE DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL TIPO DE DISPOSITIVO ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descripcion DEL TIPO DE DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DEL TIPO DE DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DEL TIPO DE DISPOSITIVO ES REQUERIDO ")
    private String descripcion;


    public DispositivoTipoDTO() {
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

}
