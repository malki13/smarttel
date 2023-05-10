package com.telcom.ups.data.read;

public class GatewayRead {

    private Integer iden;
    private String nombre;
    private String descripcion;
    private String idGateway;
    private String idNetworkServer;
    private String idServiceProfile;
    private String idGatewayProfile;
    private String idOrganization;
    private Double altitude;
    private Double latitude;
    private Double longitude;

    public GatewayRead() {
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

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
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

}
