package com.telcom.ups.data.read;

public class ApplicationRead {

    private Integer iden;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String idServiceProfile;
    private String applicationId;
//    private PerfilServicioInfoOnly perfilServicio;
    private EmpresaRead empresa;
    private ProtocoloTipoRead protocoloTipo;

    public ApplicationRead() {
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

//    public PerfilServicioInfoOnly getPerfilServicio() {
//        return perfilServicio;
//    }
//
//    public void setPerfilServicio(PerfilServicioInfoOnly perfilServicio) {
//        this.perfilServicio = perfilServicio;
//    }

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }

    public ProtocoloTipoRead getProtocoloTipo() {
        return protocoloTipo;
    }

    public void setProtocoloTipo(ProtocoloTipoRead protocoloTipo) {
        this.protocoloTipo = protocoloTipo;
    }
}
