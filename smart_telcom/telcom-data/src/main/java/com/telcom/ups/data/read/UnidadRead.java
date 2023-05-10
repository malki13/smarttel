package com.telcom.ups.data.read;

public class UnidadRead {

    private Integer iden;
    private String nombre;

    public UnidadRead() {
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

    @Override
    public String toString() {
        return "UnidadRead{" +
                "iden=" + iden +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
