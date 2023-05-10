package com.telcom.ups.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Interventores_Tipo")
public class InterventorTipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "int_tip_iden",nullable = false)
    private Integer iden;

    @Column(name = "int_tip_nomb",nullable = false, unique = true, length = 40)
    private String nombre;

    @Column(name = "int_tip_idcl", length = 2)
    private String idclientesri;

    @Column(name = "int_tip_idpu", length = 2)
    private String idproveedorsri;

    @Column(name = "int_tip_idtc", length = 2)
    private String idtarcredito;

    @Column(name = "int_fecr", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "int_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "int_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;


    public InterventorTipo() {
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

    public String getIdclientesri() {
        return idclientesri;
    }

    public void setIdclientesri(String idclientesri) {
        this.idclientesri = idclientesri;
    }

    public String getIdproveedorsri() {
        return idproveedorsri;
    }

    public void setIdproveedorsri(String idproveedorsri) {
        this.idproveedorsri = idproveedorsri;
    }

    public String getIdtarcredito() {
        return idtarcredito;
    }

    public void setIdtarcredito(String idtarcredito) {
        this.idtarcredito = idtarcredito;
    }

    @JsonIgnore
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}