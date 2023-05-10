package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthPasswordDTO {

    @NotNull(message = " El id DEL USUARIO PARA ACTUALIZAR EL PASSWORD NO DEBE SER NULO ")
    Integer id;

    @NotNull(message = " El password NO DEBE SER NULO ")
    @NotEmpty(message = " EL password NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL password ES REQUERIDO ")
    String password;

    public AuthPasswordDTO() {
    }

    public AuthPasswordDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
