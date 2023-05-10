package com.telcom.ups.data.info;

public class TarifaInfo {

    private Integer iden;
    private String nombreCategoria;
    private String rangoConsumo;
    private double cargoDisponibilidad;
    private double cargoVariable;

    public TarifaInfo() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getRangoConsumo() {
        return rangoConsumo;
    }

    public void setRangoConsumo(String rangoConsumo) {
        this.rangoConsumo = rangoConsumo;
    }

    public double getCargoDisponibilidad() {
        return cargoDisponibilidad;
    }

    public void setCargoDisponibilidad(double cargoDisponibilidad) {
        this.cargoDisponibilidad = cargoDisponibilidad;
    }

    public double getCargoVariable() {
        return cargoVariable;
    }

    public void setCargoVariable(double cargoVariable) {
        this.cargoVariable = cargoVariable;
    }
}
