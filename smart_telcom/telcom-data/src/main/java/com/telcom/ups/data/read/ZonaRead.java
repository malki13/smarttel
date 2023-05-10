package com.telcom.ups.data.read;

import com.telcom.ups.data.info.AdministradorInfo;
import com.telcom.ups.data.info.TecnicoInfo;

public class ZonaRead {

    private Integer iden;
    private String nombre;
    private String descServicio;
    private EmpresaRead empresa;
    private AdministradorInfo admin;
    private TecnicoInfo tecnico;

    public ZonaRead() {
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

    public String getDescServicio() {
        return descServicio;
    }

    public void setDescServicio(String descServicio) {
        this.descServicio = descServicio;
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
}
