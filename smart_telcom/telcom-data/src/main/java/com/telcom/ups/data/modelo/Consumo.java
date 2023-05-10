package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Consumo {

    private int id;
    private Date fecha;
    private Double dm3;
    private Double m3;

    public Consumo() {
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

    public Double getDm3() {
        return dm3;
    }

    public void setDm3(Double dm3) {
        this.dm3 = dm3;
    }

    public Double getM3() {
        return m3;
    }

    public void setM3(Double m3) {
        this.m3 = m3;
    }
}
