/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.util.Objects;

/**
 *
 * @author ThigoYure
 */
public class Usuario {

    private String email;
    private String nome;
    private String telefone;
    private String fotoPerfil;

    public Usuario() {
    }

    public Usuario(String email, String nome, String telefone, String fotoPerfil) {
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.fotoPerfil = fotoPerfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.telefone);
        hash = 67 * hash + Objects.hashCode(this.fotoPerfil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.fotoPerfil, other.fotoPerfil)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nome=" + nome + ", telefone=" + telefone + ", fotoPerfil=" + fotoPerfil + '}';
    }

}
