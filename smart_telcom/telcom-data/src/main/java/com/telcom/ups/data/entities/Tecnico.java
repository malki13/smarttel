package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_Tecnicos", uniqueConstraints = @UniqueConstraint(columnNames = {"tec_int_id", "tec_emp_id"}, name = "uniqueTecnicoEmpresa"))
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tec_iden")
    private Integer iden;

    @OneToOne
    @JoinColumn(name = "tec_int_id", nullable = false)
    private Interventor interventor;

    @OneToOne
    @JoinColumn(name = "tec_stat_id")
    private Estatus estatus;

    @ManyToOne
    @JoinColumn(name = "tec_emp_id")
    private Empresa empresa;

    @Column(name = "tec_fecr", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "tec_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "tec_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    public Tecnico() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public Interventor getInterventor() {
        return interventor;
    }

    public void setInterventor(Interventor interventor) {
        this.interventor = interventor;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
}
