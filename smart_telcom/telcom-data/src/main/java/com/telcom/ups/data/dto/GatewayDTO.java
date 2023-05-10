package com.telcom.ups.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GatewayDTO {


    @NotNull(message = " EL nombre DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL GATEWAY ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descripcion DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DEL GATEWAY ES REQUERIDO ")
    private String descripcion;

    @NotNull(message = " EL idGateway DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL idGateway DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL idGateway DEL GATEWAY ES REQUERIDO ")
    private String idGateway;

    @NotNull(message = " EL idNetworkServer DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL idNetworkServer DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL idNetworkServer DEL GATEWAY ES REQUERIDO ")
    private String idNetworkServer;

    @NotNull(message = " EL idServiceProfile DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL idServiceProfile DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL idServiceProfile DEL GATEWAY ES REQUERIDO ")
    private String idServiceProfile;

    @NotNull(message = " EL idGatewayProfile DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL idGatewayProfile DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL idGatewayProfile DEL GATEWAY ES REQUERIDO ")
    private String idGatewayProfile;

    @NotNull(message = " EL idOrganization DEL GATEWAY NO DEBE SER NULO ")
    @NotEmpty(message = " EL idOrganization DEL GATEWAY NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL idOrganization DEL GATEWAY ES REQUERIDO ")
    private String idOrganization;

    private Double altitude;
    private Double latitude;
    private Double longitude;
//    private Integer integracionId;

    public GatewayDTO() {
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

    public String getIdGateway() {
        return idGateway;
    }

    public void setIdGateway(String idGateway) {
        this.idGateway = idGateway;
    }

    public String getIdNetworkServer() {
        return idNetworkServer;
    }

    public void setIdNetworkServer(String idNetworkServer) {
        this.idNetworkServer = idNetworkServer;
    }

    public String getIdServiceProfile() {
        return idServiceProfile;
    }

    public void setIdServiceProfile(String idServiceProfile) {
        this.idServiceProfile = idServiceProfile;
    }

    public String getIdGatewayProfile() {
        return idGatewayProfile;
    }

    public void setIdGatewayProfile(String idGatewayProfile) {
        this.idGatewayProfile = idGatewayProfile;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

//    public Integer getIntegracionId() {
//        return integracionId;
//    }
//
//    public void setIntegracionId(Integer integracionId) {
//        this.integracionId = integracionId;
//    }
}
