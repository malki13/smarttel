package com.telcom.ups.data.read;

import com.telcom.ups.data.info.DispositivoInfoFactura;

public class DetalleRead {

    private Integer iden;
    private String codigo;
    private String descripcion;
    private Integer cantidad;
    private Double impuesto;
    private Double descuento;
    private Double precio;
    private Double total;
    private DispositivoInfoFactura dispositivo;
    private UnidadRead unidad;

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public DispositivoInfoFactura getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoInfoFactura dispositivo) {
        this.dispositivo = dispositivo;
    }

    public UnidadRead getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadRead unidad) {
        this.unidad = unidad;
    }
}
