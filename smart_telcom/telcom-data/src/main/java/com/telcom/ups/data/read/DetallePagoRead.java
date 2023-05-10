package com.telcom.ups.data.read;



public class DetallePagoRead {

    private Integer iden;
    private FormaPagoRead formaPago;
    private Double valor;
    private String tiempo;
    private Integer plazo;

    public DetallePagoRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
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
}
