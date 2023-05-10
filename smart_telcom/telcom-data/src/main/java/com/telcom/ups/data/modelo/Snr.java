package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Snr {

    private int id;
    private Date fecha;
    private double snr;

    public Snr() {
    }

    public Snr(int id, Date fecha, double snr) {
        this.id = id;
        this.fecha = fecha;
        this.snr = snr;
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

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
    }
}
