package com.telcom.ups.data.read;

import com.telcom.ups.data.info.*;
import com.telcom.ups.data.info.*;

import java.util.List;

public class DispositivoRead {

    private Integer iden;
    private String nombre;
    private String descripcion;
    private String deveui;
    private String deveuiEmpresa;
    private String idDeviceProfile;
    private String idApplication;
    private Double altitude;
    private Double latitude;
    private Double longitude;
    private DispositivoTipoInfoOnly dispositivoTipo;
    private ApplicationInfo application;
    private TarifaInfo tarifa;
    private ClienteInfoOnly cliente;
    private ZonaInfoOnly zona;
//    private PerfilDispositivoInfoOnly perfilDispositivo;
    private EmpresaRead empresa;
    private List<MedicionTipoRead> medicionTipos;

    public DispositivoRead() {
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

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
    }

    public String getDeveuiEmpresa() {
        return deveuiEmpresa;
    }

    public void setDeveuiEmpresa(String deveuiEmpresa) {
        this.deveuiEmpresa = deveuiEmpresa;
    }

    public String getIdDeviceProfile() {
        return idDeviceProfile;
    }

    public void setIdDeviceProfile(String idDeviceProfile) {
        this.idDeviceProfile = idDeviceProfile;
    }

    public String getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(String idApplication) {
        this.idApplication = idApplication;
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

    public DispositivoTipoInfoOnly getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(DispositivoTipoInfoOnly dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
    }

    public ApplicationInfo getApplication() {
        return application;
    }

    public void setApplication(ApplicationInfo application) {
        this.application = application;
    }

    public TarifaInfo getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaInfo tarifa) {
        this.tarifa = tarifa;
    }

    public ClienteInfoOnly getCliente() {
        return cliente;
    }

    public void setCliente(ClienteInfoOnly cliente) {
        this.cliente = cliente;
    }

    public ZonaInfoOnly getZona() {
        return zona;
    }

    public void setZona(ZonaInfoOnly zona) {
        this.zona = zona;
    }

//    public PerfilDispositivoInfoOnly getPerfilDispositivo() {
//        return perfilDispositivo;
//    }
//
//    public void setPerfilDispositivo(PerfilDispositivoInfoOnly perfilDispositivo) {
//        this.perfilDispositivo = perfilDispositivo;
//    }

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }

    public List<MedicionTipoRead> getMedicionTipos() {
        return medicionTipos;
    }

    public void setMedicionTipos(List<MedicionTipoRead> medicionTipos) {
        this.medicionTipos = medicionTipos;
    }
}
