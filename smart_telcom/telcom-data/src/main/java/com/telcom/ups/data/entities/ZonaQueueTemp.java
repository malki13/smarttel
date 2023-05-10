package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Zonas_Queue_Temp", uniqueConstraints = @UniqueConstraint(columnNames = {"quet_zon_deveui", "quet_zon_id"}, name = "uniqueDeveuiZonaTemp"))
public class ZonaQueueTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quet_zon_iden", nullable = false)
    private Integer iden;

    @Column(name = "quet_zon_deveui", nullable = false, unique = true)
    private String deveui;

    @Column(name = "quet_zon_payload", nullable = false)
    private String payload;

    @Column(name = "quet_zon_fcnt", nullable = false)
    private Integer fCnt;

    @Column(name = "quet_zon_fport", nullable = false)
    private Integer fPort;

    @Column(name = "quet_zon_fech", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "quet_zon_cont", nullable = false)
    private Integer cont;

    @Column(name = "quet_zon_id", nullable = false)
    private Integer zonaId;

    @Column(name = "quet_zon_fecr", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "quet_zon_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    public ZonaQueueTemp() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getfCnt() {
        return fCnt;
    }

    public void setfCnt(Integer fCnt) {
        this.fCnt = fCnt;
    }

    public Integer getfPort() {
        return fPort;
    }

    public void setfPort(Integer fPort) {
        this.fPort = fPort;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
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
}
