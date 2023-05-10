package com.telcom.ups.data.read;

public class MedicionTipoRead {

    private Integer iden;
    private String codigo;
    private String nombre;
    private String descripcion;

    public MedicionTipoRead() {
    }

    public MedicionTipoRead(Integer iden, String nombre, String descripcion) {
        this.iden = iden;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
