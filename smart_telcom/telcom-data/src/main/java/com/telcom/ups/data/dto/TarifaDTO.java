package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TarifaDTO {

    @NotNull(message = " EL nombreCategoria DE LA TARIFA NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombreCategoria DE LA TARIFA NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombreCategoria DE LA TARIFA ES REQUERIDO ")
    private String nombreCategoria;

    @NotNull(message = " EL rangoConsumo DE LA TARIFA NO DEBE SER NULO ")
    @NotEmpty(message = " EL rangoConsumo DE LA TARIFA NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL rangoConsumo DE LA TARIFA ES REQUERIDO ")
    private String rangoConsumo;

    private double cargoDisponibilidad;
    private double cargoVariable;

    public TarifaDTO() {
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
