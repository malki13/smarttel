package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RolDTO {

    @NotNull(message = " EL nombre DEL ROL NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL ROL NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL ROL ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descripcion DEL ROL NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DEL ROL NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DEL ROL ES REQUERIDO ")
    private String descripcion;

    public RolDTO() {
    }

    public RolDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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