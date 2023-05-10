package com.telcom.ups.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Configuraciones_Cadena")
public class ConfiguracionCadena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_cad_iden", nullable = false)
    private Integer iden;

    @Column(name = "con_cad_nomb", nullable = false, unique = true)
    private String nombre;

    @Column(name = "con_cad_data")
    private String data;

    @Column(name = "con_cad_data_hexadecimal")
    private String hexadecimal;

    @Column(name = "con_cad_data_base64")
    private String base64;

    @ManyToOne
    @JoinColumn(name = "con_cad_distip_id")
    private DispositivoTipo dispositivoTipo;

    @Column(name = "con_cad_fecr", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "con_cad_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "con_cad_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    public ConfiguracionCadena() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public DispositivoTipo getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(DispositivoTipo dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
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
