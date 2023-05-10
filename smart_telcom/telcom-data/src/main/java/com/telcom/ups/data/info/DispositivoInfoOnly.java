package com.telcom.ups.data.info;

import com.telcom.ups.data.read.DetalleComunicacionRead;
import com.telcom.ups.data.read.ReporteComunicacionRead;

public class DispositivoInfoOnly {

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
    private String topico;
    private String url;
    private DetalleComunicacionRead detalleComunicacion;
    private ReporteComunicacionRead reporteComunicacion;

    public DispositivoInfoOnly() {
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

    public String getDeveuiEmpresa() {
        return deveuiEmpresa;
    }

    public void setDeveuiEmpresa(String deveuiEmpresa) {
        this.deveuiEmpresa = deveuiEmpresa;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
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
}
