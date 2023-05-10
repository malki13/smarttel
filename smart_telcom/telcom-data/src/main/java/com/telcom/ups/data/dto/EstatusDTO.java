package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EstatusDTO {

    @NotNull(message = " EL codigo DEL ESTATUS NO DEBE SER NULO ")
    @NotEmpty(message = " EL codigo DEL ESTATUS NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL codigo DEL ESTATUS ES REQUERIDO ")
    private String codigo;

    @NotNull(message = " EL nombre DEL ESTATUS NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL ESTATUS NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL ESTATUS ES REQUERIDO ")
    private String nombre;

    public EstatusDTO() {
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
}
