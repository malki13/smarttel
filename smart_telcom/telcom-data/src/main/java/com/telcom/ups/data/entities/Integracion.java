package com.telcom.ups.data.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Integraciones")
public class Integracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itg_iden", nullable = false)
    private Integer iden;

    @Column(name = "itg_nombre", nullable = false, unique = true, length = 70)
    private String nombre;

    @Column(name = "itg_codigo", unique = true, length = 70)
    private String codigo;

    @Column(name = "itg_activo", nullable = false)
    private boolean activo;

    @Column(name = "itg_servidor", length = 150)
    private String servidor;

    @Column(name = "itg_puerto")
    private Integer puerto;

    @Column(name = "itg_timeout")
    private Integer timeout;

    @Column(name = "itg_descripcion", length = 250)
    private String descripcion;

    @Column(name = "itg_username", length = 250)
    private String username;

    @Column(name = "itg_password", length = 250)
    private String password;

    @Column(name = "itg_baseurl", length = 250)
    private String baseurl;

    @Column(name = "itg_app_server_url", length = 250)
    private String appServerUrl;

    @Column(name = "itg_app_server_token", length = 250)
    private String appServerToken;

    @Column(name = "itg_qos")
    private Integer qos;

    @Column(name = "itg_fecr", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "itg_feac", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "itg_feeli", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name = "itg_prtipo_id", nullable = false)
    private ProtocoloTipo protocoloTipo;

    @OneToOne
    @JoinColumn(name = "itg_cred_tipo_id", nullable = false)
    private CredencialTipo credencialTipo;

    @OneToMany(mappedBy = "integracion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Topico> topicos;

    @OneToMany(mappedBy = "integracion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RequestResponse> requestResponses;


//    @OneToMany(mappedBy = "integracion", fetch = FetchType.LAZY)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<Gateway> gateways;

    public Integracion() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getAppServerUrl() {
        return appServerUrl;
    }

    public void setAppServerUrl(String appServerUrl) {
        this.appServerUrl = appServerUrl;
    }

    public String getAppServerToken() {
        return appServerToken;
    }

    public void setAppServerToken(String appServerToken) {
        this.appServerToken = appServerToken;
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

    public ProtocoloTipo getProtocoloTipo() {
        return protocoloTipo;
    }

    public void setProtocoloTipo(ProtocoloTipo protocoloTipo) {
        this.protocoloTipo = protocoloTipo;
    }

    public CredencialTipo getCredencialTipo() {
        return credencialTipo;
    }

    public void setCredencialTipo(CredencialTipo credencialTipo) {
        this.credencialTipo = credencialTipo;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public List<RequestResponse> getRequestResponses() {
        return requestResponses;
    }

    public void setRequestResponses(List<RequestResponse> requestResponses) {
        this.requestResponses = requestResponses;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

//    public List<Gateway> getGateways() {
//        return gateways;
//    }
//
//    public void setGateways(List<Gateway> gateways) {
//        this.gateways = gateways;
//    }
}
