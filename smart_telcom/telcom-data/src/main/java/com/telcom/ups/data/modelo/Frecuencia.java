package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Frecuencia {

    private int id;
    private Date fecha;
    private int f902300;
    private int f902500;
    private int f902700;
    private int f902900;
    private int f903100;
    private int f903300;
    private int f903500;
    private int f903700;

    public Frecuencia() {
    }

    public Frecuencia(int id, Date fecha, int f902300, int f902500, int f902700, int f902900, int f903100, int f903300, int f903500, int f903700) {
        this.id = id;
        this.fecha = fecha;
        this.f902300 = f902300;
        this.f902500 = f902500;
        this.f902700 = f902700;
        this.f902900 = f902900;
        this.f903100 = f903100;
        this.f903300 = f903300;
        this.f903500 = f903500;
        this.f903700 = f903700;
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

    public int getF902300() {
        return f902300;
    }

    public void setF902300(int f902300) {
        this.f902300 = f902300;
    }

    public int getF902500() {
        return f902500;
    }

    public void setF902500(int f902500) {
        this.f902500 = f902500;
    }

    public int getF902700() {
        return f902700;
    }

    public void setF902700(int f902700) {
        this.f902700 = f902700;
    }

    public int getF902900() {
        return f902900;
    }

    public void setF902900(int f902900) {
        this.f902900 = f902900;
    }

    public int getF903100() {
        return f903100;
    }

    public void setF903100(int f903100) {
        this.f903100 = f903100;
    }

    public int getF903300() {
        return f903300;
    }

    public void setF903300(int f903300) {
        this.f903300 = f903300;
    }

    public int getF903500() {
        return f903500;
    }

    public void setF903500(int f903500) {
        this.f903500 = f903500;
    }

    public int getF903700() {
        return f903700;
    }

    public void setF903700(int f903700) {
        this.f903700 = f903700;
    }
}
