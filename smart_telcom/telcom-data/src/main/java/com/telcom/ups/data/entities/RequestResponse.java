package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_RequestResponse")
public class RequestResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rer_iden", nullable = false)
    private Integer iden;

    @Column(name = "rer_url", length = 100)
    private String url;

    @Column(name = "rer_fecr", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "rer_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "rer_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "rer_itg_id")
    private Integracion integracion;

    public RequestResponse() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integracion getIntegracion() {
        return integracion;
    }

    public void setIntegracion(Integracion integracion) {
        this.integracion = integracion;
    }
}
