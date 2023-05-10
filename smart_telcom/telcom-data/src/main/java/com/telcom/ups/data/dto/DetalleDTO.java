package com.telcom.ups.data.dto;

import com.telcom.ups.data.read.UnidadRead;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DetalleDTO {

    @NotNull(message = " EL codigo DEL DETALLE NO DEBE SER NULO ")
    @NotEmpty(message = " EL codigo DEL DETALLE NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL codigo DEL DETALLE ES REQUERIDO ")
    private String codigo;

    @NotNull(message = " LA descripcion DEL DETALLE NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DEL DETALLE NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DEL DETALLE ES REQUERIDO ")
    private String descripcion;

    @NotNull(message = " LA cantidad DEL DETALLE NO DEBE SER NULO ")
    private Integer cantidad;

    @NotNull(message = " EL impuesto DEL DETALLE NO DEBE SER NULO [En caso que el detalle no tenta impuesto se debe enviar un valor de 0] ")
    private Double impuesto;

    @NotNull(message = " El descuento DEL DETALLE NO DEBE SER NULO [En caso que el detalle no tenga descuento se debe enviar un valor de 0] ")
    private Double descuento;

    @NotNull(message = " El precio DEL DETALLE NO DEBE SER NULO ")
    private Double precio;

    @NotNull(message = " El total DEL DETALLE NO DEBE SER NULO ")
    private Double total;

    @NotNull(message = " El dispositivoId DEL DETALLE NO DEBE SER NULO ")
    private Integer dispositivoId;

    @NotNull(message = " La unidad DEL DETALLE NO DEBE SER NULO ")
    private UnidadRead unidad;

    public DetalleDTO() {
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

    public Integer getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Integer dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public UnidadRead getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadRead unidad) {
        this.unidad = unidad;
    }

    @Override
    public String toString() {
        return "DetalleDTO{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", impuesto=" + impuesto +
                ", descuento=" + descuento +
                ", precio=" + precio +
                ", total=" + total +
                ", dispositivoId=" + dispositivoId +
                ", unidad=" + unidad +
                '}';
    }
}
