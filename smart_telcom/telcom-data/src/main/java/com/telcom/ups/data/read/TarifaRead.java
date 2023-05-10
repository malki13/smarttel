package com.telcom.ups.data.read;

public class TarifaRead {

    private Integer iden;
    private String nombreCategoria;
    private String rangoConsumo;
    private double cargoDisponibilidad;
    private double cargoVariable;
    private EmpresaRead empresa;

    public TarifaRead() {
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

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }
}
