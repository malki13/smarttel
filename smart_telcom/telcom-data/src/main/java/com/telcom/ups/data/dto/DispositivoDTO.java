package com.telcom.ups.data.dto;

import com.telcom.ups.data.read.MedicionTipoRead;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DispositivoDTO {

    @NotNull(message = " EL nombre DEL DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " EL nombre DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL nombre DEL DISPOSITIVO ES REQUERIDO ")
    private String nombre;

    @NotNull(message = " LA descripcion DEL DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " LA descripcion DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " LA descripcion DEL DISPOSITIVO ES REQUERIDO ")
    private String descripcion;

    @NotNull(message = " EL deveui DEL DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " EL deveui DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL deveui DEL DISPOSITIVO ES REQUERIDO ")
    private String deveui;

    @NotNull(message = " EL deveuiEmpresa DEL DISPOSITIVO NO DEBE SER NULO ")
    @NotEmpty(message = " EL deveuiEmpresa DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
    @NotBlank(message = " EL deveuiEmpresa DEL DISPOSITIVO ES REQUERIDO ")
    private String deveuiEmpresa;

//    @NotNull(message = " EL idDeviceProfile DEL DISPOSITIVO NO DEBE SER NULO ")
//    @NotEmpty(message = " EL idDeviceProfile DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
//    @NotBlank(message = " EL idDeviceProfile DEL DISPOSITIVO ES REQUERIDO ")
    private String idDeviceProfile;

//    @NotNull(message = " EL idApplication DEL DISPOSITIVO NO DEBE SER NULO ")
//    @NotEmpty(message = " EL idApplication DEL DISPOSITIVO NO DEBE ESTAR VACIO ")
//    @NotBlank(message = " EL idApplication DEL DISPOSITIVO ES REQUERIDO ")
    private String idApplication;

    private Double altitude;
    private Double latitude;
    private Double longitude;
    private Integer dispositivoTipoId;
    private Integer applicationId;
    private Integer tarifaId;
    private Integer clienteId;
    private Integer zonaId;
    //private Integer perfilDispositivoId;
    private Integer empresaId;
    private List<MedicionTipoRead> medicionTipos;

    public DispositivoDTO() {
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

    public Integer getDispositivoTipoId() {
        return dispositivoTipoId;
    }

    public void setDispositivoTipoId(Integer dispositivoTipoId) {
        this.dispositivoTipoId = dispositivoTipoId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getTarifaId() {
        return tarifaId;
    }

    public void setTarifaId(Integer tarifaId) {
        this.tarifaId = tarifaId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
    }

//    public Integer getPerfilDispositivoId() {
//        return perfilDispositivoId;
//    }
//
//    public void setPerfilDispositivoId(Integer perfilDispositivoId) {
//        this.perfilDispositivoId = perfilDispositivoId;
//    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public List<MedicionTipoRead> getMedicionTipos() {
        return medicionTipos;
    }

    public void setMedicionTipos(List<MedicionTipoRead> medicionTipos) {
        this.medicionTipos = medicionTipos;
    }
}
