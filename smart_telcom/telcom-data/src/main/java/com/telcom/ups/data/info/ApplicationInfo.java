package com.telcom.ups.data.info;

public class ApplicationInfo {

    private Integer iden;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String idServiceProfile;
    private String applicationId;

    public ApplicationInfo() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getIdServiceProfile() {
        return idServiceProfile;
    }

    public void setIdServiceProfile(String idServiceProfile) {
        this.idServiceProfile = idServiceProfile;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
