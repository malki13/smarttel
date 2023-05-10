package com.telcom.ups.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_iden")
    private Integer iden;

    @Column(name = "usu_nick", nullable = false, unique = true, length = 250)
    private String nombre;

    @Column(name = "usu_pass", nullable = false, length = 150)
    private String password;

    @OneToOne
    @JoinColumn(name = "usu_stat")
    private Estatus estatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_int")
    private Interventor interventor;

    @JoinTable(
            name = "tb_Usuarios_Roles",
            joinColumns = @JoinColumn(name = "uro_usu_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "uro_rol_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE
    })
    private List<Rol> rols;

    @ManyToOne
    @JoinColumn(name = "usu_emp_id")
    private Empresa empresa;

    @Column(name = "usu_fecr", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "usu_feac", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @Column(name = "usu_feeli", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime deletedAt;

    public Usuario() {
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Interventor getInterventor() {
        return interventor;
    }

    public void setInterventor(Interventor interventor) {
        this.interventor = interventor;
    }

    @JsonIgnore
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
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

    public void addRol(Rol rol) {
        boolean existe = this.rols.contains(rol);
        if (!existe) this.rols.add(rol);
    }

    public void removeRol(Rol rol) {
        Rol rolExiste = this.rols.stream().filter(r -> r.getIden() == rol.getIden()).findFirst().orElse(null);
        if (rolExiste != null) {
            this.rols.remove(rolExiste);
        }
    }
}
