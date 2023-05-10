package com.telcom.ups.data.info;


import com.telcom.ups.data.read.*;
import com.telcom.ups.data.read.DetalleComunicacionRead;
import com.telcom.ups.data.read.EmpresaRead;
import com.telcom.ups.data.read.MedicionTipoRead;
import com.telcom.ups.data.read.ReporteComunicacionRead;

import java.util.List;

public class DispositivoInfo {

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
    private String servidor = "35.188.219.65";
    private Integer puerto;
    private String topico;
    private String url;
    private DispositivoTipoInfoOnly dispositivoTipo;
    private ApplicationInfo application;
    private TarifaInfo tarifa;
    private ClienteInfoOnly cliente;
    private ZonaInfoOnly zona;
    private DetalleComunicacionRead detalleComunicacion;
    private ReporteComunicacionRead reporteComunicacion;
//    private PerfilDispositivoInfoOnly perfilDispositivo;
    private EmpresaRead empresa;
    private List<MedicionTipoRead> medicionTipos;

    public DispositivoInfo() {
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

    public DispositivoTipoInfoOnly getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(DispositivoTipoInfoOnly dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
    }

    public TarifaInfo getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaInfo tarifa) {
        this.tarifa = tarifa;
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

    public ApplicationInfo getApplication() {
        return application;
    }

    public void setApplication(ApplicationInfo application) {
        this.application = application;
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

    public DetalleComunicacionRead getDetalleComunicacion() {
        return detalleComunicacion;
    }

    public void setDetalleComunicacion(DetalleComunicacionRead detalleComunicacion) {
        this.detalleComunicacion = detalleComunicacion;
    }

    public ReporteComunicacionRead getReporteComunicacion() {
        return reporteComunicacion;
    }

    public void setReporteComunicacion(ReporteComunicacionRead reporteComunicacion) {
        this.reporteComunicacion = reporteComunicacion;
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

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
