package com.telcom.ups.data.read;

public class AnexoRead {

    private Integer iden;
    private String nombre;
    private FacturaRead factura;

    public AnexoRead() {
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

    public FacturaRead getFactura() {
        return factura;
    }

    public void setFactura(FacturaRead factura) {
        this.factura = factura;
    }
}
