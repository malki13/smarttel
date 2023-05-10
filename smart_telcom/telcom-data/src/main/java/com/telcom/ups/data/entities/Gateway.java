package com.telcom.ups.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Gateways")
public class Gateway implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gat_iden", nullable = false)
    private Integer iden;

    @Column(name = "gat_nomb", nullable = false, length = 150)
    @NotBlank(message = "El nombre del gateway es requerido")
    private String nombre;

    @Column(name = "gat_desc", length = 150)
    private String descripcion;

    @Column(name = "gat_id", nullable = false, length = 70, unique = true)
    private String idGateway;

    @Column(name = "gat_network_server_id", nullable = false)
    private String idNetworkServer;

    @Column(name = "gat_service_profile_id", nullable = false)
    private String idServiceProfile;

    @Column(name = "gat_gateway_profile_id", nullable = false)
    private String idGatewayProfile;

    @Column(name = "gat_gateway_organization_id", nullable = false)
    private String idOrganization;

    @Column(name = "gat_altitude")
    private Double altitude;

    @Column(name = "gat_latitude")
    private Double latitude;

    @Column(name = "gat_longitude")
    private Double longitude;

    @Column(name = "gat_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "gat_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "gat_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "gat_emp_id")
    private Empresa empresa;

//    @ManyToOne
//    @JoinColumn(name = "gat_itg_id")
//    private Integracion integracion;

    public Gateway() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

//    public Integracion getIntegracion() {
//        return integracion;
//    }
//
//    public void setIntegracion(Integracion integracion) {
//        this.integracion = integracion;
//    }
}
