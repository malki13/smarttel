package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UnidadDTO {

    @NotNull(message = " EL nombre DE LA UNIDAD NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA UNIDAD NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA UNIDAD ES REQUERIDO ")
    private String nombre;

    public UnidadDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
