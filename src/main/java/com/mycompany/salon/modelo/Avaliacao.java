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
public class Avaliacao {
    
    private int id;
    private Atendente atendente;
    private int tempoAtendimento;
    private int qualidadeAtendimento;
    private int qualidadeAmbiente;
    private int qualidadeServico;

    public Avaliacao() {
    }

    public Avaliacao(int id, Atendente atendente, int tempoAtendimento, int qualidadeAtendimento, int qualidadeAmbiente, int qualidadeServico) {
        this.id = id;
        this.atendente = atendente;
        this.tempoAtendimento = tempoAtendimento;
        this.qualidadeAtendimento = qualidadeAtendimento;
        this.qualidadeAmbiente = qualidadeAmbiente;
        this.qualidadeServico = qualidadeServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(int tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    public int getQualidadeAtendimento() {
        return qualidadeAtendimento;
    }

    public void setQualidadeAtendimento(int qualidadeAtendimento) {
        this.qualidadeAtendimento = qualidadeAtendimento;
    }

    public int getQualidadeAmbiente() {
        return qualidadeAmbiente;
    }

    public void setQualidadeAmbiente(int qualidadeAmbiente) {
        this.qualidadeAmbiente = qualidadeAmbiente;
    }

    public int getQualidadeServico() {
        return qualidadeServico;
    }

    public void setQualidadeServico(int qualidadeServico) {
        this.qualidadeServico = qualidadeServico;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.atendente);
        hash = 29 * hash + this.tempoAtendimento;
        hash = 29 * hash + this.qualidadeAtendimento;
        hash = 29 * hash + this.qualidadeAmbiente;
        hash = 29 * hash + this.qualidadeServico;
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
        final Avaliacao other = (Avaliacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tempoAtendimento != other.tempoAtendimento) {
            return false;
        }
        if (this.qualidadeAtendimento != other.qualidadeAtendimento) {
            return false;
        }
        if (this.qualidadeAmbiente != other.qualidadeAmbiente) {
            return false;
        }
        if (this.qualidadeServico != other.qualidadeServico) {
            return false;
        }
        if (!Objects.equals(this.atendente, other.atendente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + id + ", atendente=" + atendente + ", tempoAtendimento=" + tempoAtendimento + ", qualidadeAtendimento=" + qualidadeAtendimento + ", qualidadeAmbiente=" + qualidadeAmbiente + ", qualidadeServico=" + qualidadeServico + '}';
    }  
    
    
}
