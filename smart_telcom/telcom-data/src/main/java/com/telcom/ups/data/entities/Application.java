package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Applications")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_iden", nullable = false)
    private Integer iden;

    @Column(name = "app_codi", nullable = false, length = 70, unique = true)
    private String codigo;

    @Column(name = "app_nomb", nullable = false, length = 150, unique = true)
    private String nombre;

    @Column(name = "app_desc", length = 150)
    private String descripcion;

    @Column(name = "app_service_profile_id", nullable = false)
    private String idServiceProfile;

    @Column(name = "app_application_id", nullable = false)
    private String idApplication;

    @Column(name = "app_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "app_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "app_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "app_emp_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "app_pti_id")
    private ProtocoloTipo protocoloTipo;

//    @ManyToOne
//    @JoinColumn(name = "app_pes_id")
//    private PerfilServicio perfilServicio;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<Dispositivo> medidors;

    public Application() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getIdServiceProfile() {
        return idServiceProfile;
    }

    public void setIdServiceProfile(String idServiceProfile) {
        this.idServiceProfile = idServiceProfile;
    }

    public String getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(String idApplication) {
        this.idApplication = idApplication;
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

    public List<Dispositivo> getMedidors() {
        return medidors;
    }

    public void setMedidors(List<Dispositivo> medidors) {
        this.medidors = medidors;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

//    public PerfilServicio getPerfilServicio() {
//        return perfilServicio;
//    }
//
//    public void setPerfilServicio(PerfilServicio perfilServicio) {
//        this.perfilServicio = perfilServicio;
//    }


    public ProtocoloTipo getProtocoloTipo() {
        return protocoloTipo;
    }

    public void setProtocoloTipo(ProtocoloTipo protocoloTipo) {
        this.protocoloTipo = protocoloTipo;
    }
}
