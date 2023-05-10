package com.telcom.ups.data.read;
import java.util.List;

public class IntegracionRead {

    private Integer iden;
    private String nombre;
    private String codigo;
    private boolean activo;
    private String servidor;
    private Integer puerto;
    private Integer timeout;
    private String descripcion;
    private String username;
    private String password;
    private String baseurl;
    private String appServerUrl;
    private String appServerToken;
    private Integer qos;
    private ProtocoloTipoRead protocoloTipo;
    private CredencialTipoRead credencialTipo;
    private List<TopicoRead> topicos;
    private List<RequestResponseRead> urls;

    public IntegracionRead() {
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

    public ProtocoloTipoRead getProtocoloTipo() {
        return protocoloTipo;
    }

    public void setProtocoloTipo(ProtocoloTipoRead protocoloTipo) {
        this.protocoloTipo = protocoloTipo;
    }

    public CredencialTipoRead getCredencialTipo() {
        return credencialTipo;
    }

    public void setCredencialTipo(CredencialTipoRead credencialTipo) {
        this.credencialTipo = credencialTipo;
    }

    public List<TopicoRead> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<TopicoRead> topicos) {
        this.topicos = topicos;
    }

    public List<RequestResponseRead> getUrls() {
        return urls;
    }

    public void setUrls(List<RequestResponseRead> urls) {
        this.urls = urls;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }
}
