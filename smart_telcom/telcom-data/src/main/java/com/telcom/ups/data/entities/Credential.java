package com.telcom.ups.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_Credentials")
public class Credential {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cre_iden", nullable = false)
    private Integer iden;

    @Column(name = "cre_codi", nullable = false, unique = true)
    private String codigo;

    @Column(name = "cre_token", nullable = false)
    private String token;

    public Credential() {
    }

    public Credential(Integer iden, String codigo, String token) {
        this.iden = iden;
        this.codigo = codigo;
        this.token = token;
    }

    public Credential(String codigo, String token) {
        this.codigo = codigo;
        this.token = token;
    }

    public Credential(String token) {
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
