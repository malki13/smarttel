package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Join {

    private int id;
    private Date fecha;
    private int cantidadJoin;

    public Join() {
    }

    public Join(int id, Date fecha, int cantidadJoin) {
        this.id = id;
        this.fecha = fecha;
        this.cantidadJoin = cantidadJoin;
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

    public int getCantidadJoin() {
        return cantidadJoin;
    }

    public void setCantidadJoin(int cantidadJoin) {
        this.cantidadJoin = cantidadJoin;
    }
}
