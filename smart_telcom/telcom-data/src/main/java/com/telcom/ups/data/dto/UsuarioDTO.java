package com.telcom.ups.data.dto;

import com.telcom.ups.data.read.RolRead;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UsuarioDTO {

    @NotNull(message = " EL nombre DEL USUARIO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL USUARIO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL USUARIO ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " EL password DEL USUARIO NO DEBE SER NULO ")
//    @NotEmpty(message = " EL password DEL USUARIO NO DEBE ESTAR VACIO ")
//    @NotBlank(message = " EL password DEL USUARIO ES REQUERIDO ")
    private String password;

    private List<RolRead> roles;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String password, List<RolRead> roles) {
        this.nombre = nombre;
        this.password = password;
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolRead> getRoles() {
        return roles;
    }

    public void setRoles(List<RolRead> roles) {
        this.roles = roles;
    }
}
