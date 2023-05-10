package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FormaPagoDTO {

    @NotNull(message = " EL nombre DE LA FORMA DE PAGO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA FORMA DE PAGO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA FORMA DE PAGO ES REQUERIDO ")
    private String nombre;

    public FormaPagoDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
