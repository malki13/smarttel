package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Dispositivos_Tipos")
public class DispositivoTipo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "dis_tip_iden",nullable = false)
    private Integer iden;

    @Column(name = "dis_tip_codi",nullable = false, unique = true)
    private String codigo;

    @Column(name = "dis_tip_nomb",nullable = false, unique = true, length = 70)
    private String nombre;

    @Column(name = "dis_tip_desc")
    private String descripcion;

    @Column(name = "dis_tip_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "dis_tip_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "dis_tip_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "dispositivoTipo", fetch = FetchType.LAZY)
    List<Configuracion> configuracions;

    @OneToMany(mappedBy = "dispositivoTipo", fetch = FetchType.LAZY)
    List<ConfiguracionCadena> cadenas;

    public DispositivoTipo() {
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

    public List<Configuracion> getConfiguracions() {
        return configuracions;
    }

    public void setConfiguracions(List<Configuracion> configuracions) {
        this.configuracions = configuracions;
    }

    public List<ConfiguracionCadena> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<ConfiguracionCadena> cadenas) {
        this.cadenas = cadenas;
    }
}
