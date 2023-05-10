package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ApplicationDTO {

    private Integer iden;

    @NotNull(message = " El codigo DE LA APPLICATION NO DEBE SER NULO ")
    @NotEmpty(message = " EL codigo DE LA APPLICATION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL codigo DE LA APPLICATION ES REQUERIDO ")
    private String codigo;

    @NotNull(message = " El nombre DE LA APPLICATION NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DE LA APPLICATION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DE LA APPLICATION ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descripcion DE LA APPLICATION NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DE LA APPLICATION NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DE LA APPLICATION ES REQUERIDO ")
    private String descripcion;

//    @NotNull(message = " El idServiceProfile DE LA APPLICATION NO DEBE SER NULO ")
//    @NotEmpty(message = " EL idServiceProfile DE LA APPLICATION NO DEBE ESTAR VACIO ")
//    @NotBlank(message = " EL idServiceProfile DE LA APPLICATION ES REQUERIDO ")
    private String idServiceProfile;

//    @NotNull(message = " El applicationId DE LA APPLICATION NO DEBE SER NULO ")
//    @NotEmpty(message = " EL applicationId DE LA APPLICATION NO DEBE ESTAR VACIO ")
//    @NotBlank(message = " EL applicationId DE LA APPLICATION ES REQUERIDO ")
    private String applicationId;
    //private Integer perfilServicioId;
    private Integer empresaId;
    private Integer protocoloTipoId;

    public ApplicationDTO() {
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

//    public Integer getPerfilServicioId() {
//        return perfilServicioId;
//    }
//
//    public void setPerfilServicioId(Integer perfilServicioId) {
//        this.perfilServicioId = perfilServicioId;
//    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getProtocoloTipoId() {
        return protocoloTipoId;
    }

    public void setProtocoloTipoId(Integer protocoloTipoId) {
        this.protocoloTipoId = protocoloTipoId;
    }
}
