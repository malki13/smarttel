package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Alarma {

    private int id;
    private Date fecha;
    private int tuberiaVacia;
    private int cierreValvula;
    private int bateriaBaja;
    private int falloComunicacion;
    private int potenciaBaja;
    private int deteccionFuga;
    private int flujoInvertido;
    private int modoServicio;
    private int modoMuestreo;
    private int modoDiario;
    private int relojDesigulado;
    private int alarmaDec;

    public Alarma() {
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

    public int getTuberiaVacia() {
        return tuberiaVacia;
    }

    public void setTuberiaVacia(int tuberiaVacia) {
        this.tuberiaVacia = tuberiaVacia;
    }

    public int getCierreValvula() {
        return cierreValvula;
    }

    public void setCierreValvula(int cierreValvula) {
        this.cierreValvula = cierreValvula;
    }

    public int getBateriaBaja() {
        return bateriaBaja;
    }

    public void setBateriaBaja(int bateriaBaja) {
        this.bateriaBaja = bateriaBaja;
    }

    public int getFalloComunicacion() {
        return falloComunicacion;
    }

    public void setFalloComunicacion(int falloComunicacion) {
        this.falloComunicacion = falloComunicacion;
    }

    public int getPotenciaBaja() {
        return potenciaBaja;
    }

    public void setPotenciaBaja(int potenciaBaja) {
        this.potenciaBaja = potenciaBaja;
    }

    public int getDeteccionFuga() {
        return deteccionFuga;
    }

    public void setDeteccionFuga(int deteccionFuga) {
        this.deteccionFuga = deteccionFuga;
    }

    public int getFlujoInvertido() {
        return flujoInvertido;
    }

    public void setFlujoInvertido(int flujoInvertido) {
        this.flujoInvertido = flujoInvertido;
    }

    public int getModoServicio() {
        return modoServicio;
    }

    public void setModoServicio(int modoServicio) {
        this.modoServicio = modoServicio;
    }

    public int getModoMuestreo() {
        return modoMuestreo;
    }

    public void setModoMuestreo(int modoMuestreo) {
        this.modoMuestreo = modoMuestreo;
    }

    public int getModoDiario() {
        return modoDiario;
    }

    public void setModoDiario(int modoDiario) {
        this.modoDiario = modoDiario;
    }

    public int getRelojDesigulado() {
        return relojDesigulado;
    }

    public void setRelojDesigulado(int relojDesigulado) {
        this.relojDesigulado = relojDesigulado;
    }

    public int getAlarmaDec() {
        return alarmaDec;
    }

    public void setAlarmaDec(int alarmaDec) {
        this.alarmaDec = alarmaDec;
    }
}
