package com.telcom.ups.data.dto;

import com.telcom.ups.data.read.FormaPagoRead;

import javax.validation.constraints.NotNull;

public class DetallePagoDTO {

    @NotNull(message = " La formaPago DEL DETALLE DE PAGO NO DEBE SER NULO ")
    private FormaPagoRead formaPago;

    @NotNull(message = " El valor DEL DETALLE DE PAGO NO DEBE SER NULO ")
    private Double valor;
    private String tiempo;
    private Integer plazo;

    public DetallePagoDTO() {
    }

    public FormaPagoRead getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPagoRead formaPago) {
        this.formaPago = formaPago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    @Override
    public String toString() {
        return "DetallePagoDTO{" +
                "formaPago=" + formaPago +
                ", valor=" + valor +
                ", tiempo=" + tiempo +
                ", plazo=" + plazo +
                '}';
    }
}
