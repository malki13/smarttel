package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaDTO {

    @NotNull(message = " LA fechaEmision DE LA FACTURA NO DEBE SER NULO ")
    private LocalDateTime fechaEmision;

    @NotNull(message = " LA tarifa DE LA FACTURA NO DEBE SER NULO ")
    @NotEmpty(message = " LA tarifa DE LA FACTURA NO DEBE ESTAR VACIA ")
    @NotBlank(message = " LA tarifa DE LA FACTURA NO DEBE ESTAR VACIA ")
    private String tarifa;

    @NotNull(message = " LA descripcion DE LA FACTURA NO DEBE SER NULO ")
    private String descripcion;

    @NotNull(message = " EL secuencial DE LA FACTURA NO DEBE SER NULO [EN CASO DE NO TENER SECUENCIAL DE DEBE UN ENVIAR UN 0] ")
    private Integer secuencial;

    @NotNull(message = " LA claveacceso DE LA FACTURA NO DEBE SER NULO [EN CASO DE NO TENER SECUENCIAL DE DEBE UN ENVIAR UNA CADENA DE 0000000000000] ")
    @NotEmpty(message = " LA claveacceso DE LA FACTURA NO DEBE ESTAR VACIO [EN CASO DE NO TENER SECUENCIAL DE DEBE UN ENVIAR UNA CADENA DE 0000000000000] ")
    @NotBlank(message = " LA claveacceso DE LA FACTURA ES REQUERIDO [EN CASO DE NO TENER SECUENCIAL DE DEBE UN ENVIAR UNA CADENA DE 0000000000000] ")
    private String claveacceso;

    private String deveui;

    private LocalDateTime fechaAutorizacion;

    private String numReferencia;

    @NotNull(message = " EL subtotal DE LA FACTURA NO DEBE SER NULO ")
    private Double subtotal;

    @NotNull(message = " EL iva DE LA FACTURA NO DEBE SER NULO ")
    private Double iva;

    @NotNull(message = " EL total DE LA FACTURA NO DEBE SER NULO ")
    private Double total;

    @NotNull(message = " LOS DETALLES SON REQUERIDOS PARA CREAR UN FACTURA ")
    private List<DetalleDTO> detalles;

    @NotNull(message = " LOS PAGOS SON REQUERIDOS PARA CREAR UN FACTURA ")
    private List<DetallePagoDTO> pagos;

    @NotNull(message = " LOS ANEXOS SON REQUERIDOS PARA CREAR UN FACTURA [EN CASO DE QUE LA FACTURA NO CONTENGA ANEXOS, SE DEBE ENVIAR EL ITERABLE DE ANEXOS VACIO] ")
    private List<AnexoDTO> anexos;

    public FacturaDTO() {
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }

    public String getClaveacceso() {
        return claveacceso;
    }

    public void setClaveacceso(String claveacceso) {
        this.claveacceso = claveacceso;
    }

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
    }

    public LocalDateTime getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(LocalDateTime fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }

    public List<DetallePagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<DetallePagoDTO> pagos) {
        this.pagos = pagos;
    }

    public List<AnexoDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<AnexoDTO> anexos) {
        this.anexos = anexos;
    }

    @Override
    public String toString() {
        return "FacturaDTO{" +
                "fechaEmision=" + fechaEmision +
                ", descripcion='" + descripcion + '\'' +
                ", secuencial=" + secuencial +
                ", claveacceso='" + claveacceso + '\'' +
                ", fechaAutorizacion=" + fechaAutorizacion +
                ", numReferencia='" + numReferencia + '\'' +
                ", subtotal=" + subtotal +
                ", iva=" + iva +
                ", total=" + total +
                ", detalles=" + detalles +
                ", pagos=" + pagos +
                ", anexos=" + anexos +
                '}';
    }
}
