package com.telcom.ups.data.read;

import java.time.LocalDateTime;
import java.util.List;

public class FacturaRead {

    private Integer iden;
    private LocalDateTime fechaEmision;
    private String tarifa;
    private String descripcion;
    private Integer secuencial;
    private String claveacceso;
    private String deveui;
    private LocalDateTime fechaAutorizacion;
    private String numReferencia;
    private ClienteRead cliente;
    private EmpresaRead empresa;
    private Double subtotal;
    private Double iva;
    private Double total;
    private List<DetalleRead> detalles;
    private List<DetallePagoRead> pagos;
    private List<AnexoRead> anexos;

    public FacturaRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
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

    public ClienteRead getCliente() {
        return cliente;
    }

    public void setCliente(ClienteRead cliente) {
        this.cliente = cliente;
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

    public List<DetalleRead> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRead> detalles) {
        this.detalles = detalles;
    }

    public List<DetallePagoRead> getPagos() {
        return pagos;
    }

    public void setPagos(List<DetallePagoRead> pagos) {
        this.pagos = pagos;
    }

    public List<AnexoRead> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<AnexoRead> anexos) {
        this.anexos = anexos;
    }

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }
}
