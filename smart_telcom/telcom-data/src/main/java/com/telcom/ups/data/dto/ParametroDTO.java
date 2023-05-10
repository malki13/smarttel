package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ParametroDTO {

    @NotNull(message = " EL nombre DEL PARAMETRO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre PARAMETRO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre PARAMETRO ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " EL descripcion DEL PARAMETRO NO DEBE SER NULO ")
    @NotEmpty(message = " EL descripcion PARAMETRO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL descripcion PARAMETRO ES REQUERIDO ")
    private String descripcion;

    public ParametroDTO() {
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
