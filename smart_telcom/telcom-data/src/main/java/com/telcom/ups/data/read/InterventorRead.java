package com.telcom.ups.data.read;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InterventorRead {

    private Integer iden;
    private String codigo;
    private InterventorTipoRead interventorTipo;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String telefono;
    private String referencia;
    private Date fechaNacimiento;
    private String nombreImagen;
    private String imagen;

    public InterventorRead() {
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

    public InterventorTipoRead getInterventorTipo() {
        return interventorTipo;
    }

    public void setInterventorTipo(InterventorTipoRead interventorTipo) {
        this.interventorTipo = interventorTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
