package com.telcom.ups.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Dispositivos")
public class Dispositivo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_iden", nullable = false)
    private Integer iden;

    @Column(name = "dis_nomb", nullable = false, length = 150)
    @NotBlank(message = "El nombre del medidor es requerido")
    private String nombre;

    @Column(name = "dis_desc", length = 150)
    private String descripcion;

    @Column(name = "dis_deveui", length = 150, unique = true)
    private String deveui;

    @Column(name = "dis_deveui_empresa", length = 150, unique = true)
    private String deveuiEmpresa;

    @Column(name = "dis_device_profile_id", nullable = false)
    private String idDeviceProfile;

    @Column(name = "dis_application_id", nullable = false)
    private String idApplication;

    @Column(name = "dis_longitude")
    private Double longitude;

    @Column(name = "dis_latitude")
    private Double latitude;

    @Column(name = "dis_altitude")
    private Double altitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dis_distip_iden")
    private DispositivoTipo dispositivoTipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dis_detcom_iden")
    private DetalleComunicacion detalleComunicacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dis_repcom_iden")
    private ReporteComunicacion reporteComunicacion;

    @ManyToOne
    @JoinColumn(name = "dis_app_id")
    private Application application;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dis_tar_id")
    private Tarifa tarifa;

    @ManyToOne
    @JoinColumn(name = "dis_cli_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "dis_zon_id")
    private Zona zona;

//    @ManyToOne
//    @JoinColumn(name = "dis_ped_id")
//    private PerfilDispositivo perfilDispositivo;

    @ManyToOne
    @JoinColumn(name = "dis_emp_id")
    private Empresa empresa;

    @Column(name = "dis_topico", length = 150)
    private String topico;

    @Column(name = "dis_url", length = 150)
    private String url;
    @JoinTable(
            name = "tb_Dispositivos_Mediciones",
            joinColumns = @JoinColumn(name = "dim_dis_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "dim_med_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE
    })
    private List<MedicionTipo> medicionTipos;

    @Column(name = "dis_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "dis_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "dis_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;


    public Dispositivo() {
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

    public String getIdDeviceProfile() {
        return idDeviceProfile;
    }

    public String getDeveuiEmpresa() {
        return deveuiEmpresa;
    }

    public void setDeveuiEmpresa(String deveuiEmpresa) {
        this.deveuiEmpresa = deveuiEmpresa;
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

    public DispositivoTipo getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(DispositivoTipo dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
    }

    public DetalleComunicacion getDetalleComunicacion() {
        return detalleComunicacion;
    }

    public void setDetalleComunicacion(DetalleComunicacion detalleComunicacion) {
        this.detalleComunicacion = detalleComunicacion;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public ReporteComunicacion getReporteComunicacion() {
        return reporteComunicacion;
    }

    public void setReporteComunicacion(ReporteComunicacion reporteComunicacion) {
        this.reporteComunicacion = reporteComunicacion;
    }

//    public PerfilDispositivo getPerfilDispositivo() {
//        return perfilDispositivo;
//    }
//
//    public void setPerfilDispositivo(PerfilDispositivo perfilDispositivo) {
//        this.perfilDispositivo = perfilDispositivo;
//    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<MedicionTipo> getMedicionTipos() {
        return medicionTipos;
    }

    public void setMedicionTipos(List<MedicionTipo> medicionTipos) {
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
}
