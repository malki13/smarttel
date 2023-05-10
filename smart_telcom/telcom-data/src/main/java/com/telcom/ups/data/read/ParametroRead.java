package com.telcom.ups.data.read;

import java.util.List;

public class ParametroRead {

    private Integer iden;
    private String nombre;
    private String descripcion;
    private List<VarianteRead> variantes;

    public ParametroRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<VarianteRead> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<VarianteRead> variantes) {
        this.variantes = variantes;
    }
}
