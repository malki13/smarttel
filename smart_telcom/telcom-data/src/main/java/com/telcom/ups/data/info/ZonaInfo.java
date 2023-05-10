package com.telcom.ups.data.info;

import com.telcom.ups.data.read.EmpresaRead;

public class ZonaInfo {

    private Integer iden;
    private String nombre;
    private String descServicio;
    private EmpresaRead empresa;
    private AdministradorInfo admin;
    private TecnicoInfo tecnico;

    public ZonaInfo() {
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

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }

    public AdministradorInfo getAdmin() {
        return admin;
    }

    public void setAdmin(AdministradorInfo admin) {
        this.admin = admin;
    }

    public TecnicoInfo getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoInfo tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescServicio() {
        return descServicio;
    }

    public void setDescServicio(String descServicio) {
        this.descServicio = descServicio;
    }
}
