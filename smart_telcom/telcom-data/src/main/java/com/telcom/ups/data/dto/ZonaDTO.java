package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ZonaDTO {

    @NotNull(message = " EL nombre DE LA ZONA NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA ZONA NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA ZONA ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descServicio DE LA ZONA NO DEBE SER NULO ")
    @NotEmpty(message = " LA descServicio DE LA ZONA NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descServicio DE LA ZONA ES REQUERIDO ")
    private String descServicio;

    public ZonaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescServicio() {
        return descServicio;
    }

    public void setDescServicio(String descServicio) {
        this.descServicio = descServicio;
    }
}
