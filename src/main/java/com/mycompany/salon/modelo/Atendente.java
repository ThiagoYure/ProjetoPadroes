/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ThigoYure
 */
public class Atendente {
    private String nome;
    private LocalTime horaInício;
    private LocalTime horaFim;
    private ArrayList<Atendimento> agenda;

    public Atendente() {
    }

    public Atendente(String nome, LocalTime horaInício, LocalTime horaFim, ArrayList<Atendimento> agenda) {
        this.nome = nome;
        this.horaInício = horaInício;
        this.horaFim = horaFim;
        this.agenda = agenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHoraInício() {
        return horaInício;
    }

    public void setHoraInício(LocalTime horaInício) {
        this.horaInício = horaInício;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public ArrayList<Atendimento> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<Atendimento> agenda) {
        this.agenda = agenda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.horaInício);
        hash = 61 * hash + Objects.hashCode(this.horaFim);
        hash = 61 * hash + Objects.hashCode(this.agenda);
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
        final Atendente other = (Atendente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.horaInício, other.horaInício)) {
            return false;
        }
        if (!Objects.equals(this.horaFim, other.horaFim)) {
            return false;
        }
        if (!Objects.equals(this.agenda, other.agenda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendente{" + "nome=" + nome + ", horaIn\u00edcio=" + horaInício + ", horaFim=" + horaFim + ", agenda=" + agenda + '}';
    }
    
}
