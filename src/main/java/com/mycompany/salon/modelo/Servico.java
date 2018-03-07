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
public class Servico {
    private String nome;
    private int tempoMedio;
    private Float preco;

    public Servico() {
    }

    public Servico(String nome, int tempoMedio, Float preco) {
        this.nome = nome;
        this.tempoMedio = tempoMedio;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + this.tempoMedio;
        hash = 47 * hash + Objects.hashCode(this.preco);
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
        final Servico other = (Servico) obj;
        if (this.tempoMedio != other.tempoMedio) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Servico{" + "nome=" + nome + ", tempoMedio=" + tempoMedio + ", preco=" + preco + '}';
    }

    
    
    
}
