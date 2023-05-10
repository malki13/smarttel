package com.telcom.ups.data.read;

import com.telcom.ups.data.info.InterventorInfo;

import java.util.List;

public class UsuarioRead {

    private Integer iden;
    private String nombre;
    private String password;
    private EstatusRead estatus;
    private InterventorInfo interventor;
    private List<RolRead> roles;
    private EmpresaRead empresa;

    public UsuarioRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
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

    public EstatusRead getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusRead estatus) {
        this.estatus = estatus;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }

    public List<RolRead> getRoles() {
        return roles;
    }

    public void setRoles(List<RolRead> roles) {
        this.roles = roles;
    }

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }
}
