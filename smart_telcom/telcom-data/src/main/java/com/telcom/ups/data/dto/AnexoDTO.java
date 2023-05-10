package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnexoDTO {

    @NotNull(message = " El nombre DEL ANEXO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL ANEXO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL ANEXO ES REQUERIDO ")
    private String nombre;

    public AnexoDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "AnexoDTO{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
