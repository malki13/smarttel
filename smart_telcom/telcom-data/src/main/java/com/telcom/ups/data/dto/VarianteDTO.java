package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VarianteDTO {

    @NotNull(message = " LA data DE LA VARIANTE NO DEBE SER NULO ")
    @NotEmpty(message = " LA data DE LA VARIANTE NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA data DE LA VARIANTE ES REQUERIDO ")
    private String data;

    @NotNull(message = " EL nombre DE LA VARIANTE NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA VARIANTE NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA VARIANTE ES REQUERIDO ")
    private String nombre;

    public VarianteDTO() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
