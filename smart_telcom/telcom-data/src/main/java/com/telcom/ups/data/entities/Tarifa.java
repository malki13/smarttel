package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Tarifas")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tar_iden")
    private Integer iden;

    @Column(name = "tar_nomb", nullable = false, length = 120, unique = true)
    private String nombreCategoria;

    @Column(name = "tar_rang", nullable = false, length = 120)
    private String rangoConsumo;

    @Column(name = "tar_carg_disp", nullable = false)
    private double cargoDisponibilidad;

    @Column(name = "tar_carg_var", nullable = false)
    private double cargoVariable;

    @ManyToOne
    @JoinColumn(name = "tar_emp_id")
    private Empresa empresa;

    @Column(name = "tar_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "tar_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "tar_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    public Tarifa() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getRangoConsumo() {
        return rangoConsumo;
    }

    public void setRangoConsumo(String rangoConsumo) {
        this.rangoConsumo = rangoConsumo;
    }

    public double getCargoDisponibilidad() {
        return cargoDisponibilidad;
    }

    public void setCargoDisponibilidad(double cargoDisponibilidad) {
        this.cargoDisponibilidad = cargoDisponibilidad;
    }

    public double getCargoVariable() {
        return cargoVariable;
    }

    public void setCargoVariable(double cargoVariable) {
        this.cargoVariable = cargoVariable;
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
}
