/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ThigoYure
 */
public class Agenda {
    private Atendente atendente;
    private ArrayList<Servico> servicos = new ArrayList<>();

    public Agenda() {
    }

    public Agenda(Atendente atendente) {
        this.atendente = atendente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.atendente);
        hash = 61 * hash + Objects.hashCode(this.servicos);
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
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.atendente, other.atendente)) {
            return false;
        }
        if (!Objects.equals(this.servicos, other.servicos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "atendente=" + atendente + ", servicos=" + servicos + '}';
    }
    
    
}
