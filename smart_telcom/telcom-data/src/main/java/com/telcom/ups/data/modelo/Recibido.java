package com.telcom.ups.data.modelo;

import java.util.Date;

public class Recibido {

    private int id;
    private Date fecha;
    private int cantidadRecibidos;

    public Recibido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidadRecibidos() {
        return cantidadRecibidos;
    }

    public void setCantidadRecibidos(int cantidadRecibidos) {
        this.cantidadRecibidos = cantidadRecibidos;
    }
}
