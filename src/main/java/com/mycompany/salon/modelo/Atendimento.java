/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author ThigoYure
 */
public class Atendimento {
    private Servico servico;
    private Atendente atendente;
    private Usuario cliente;
    private int id;
    private LocalTime horaInicio;
    private boolean confirmado;

    public Atendimento() {
    }

    public Atendimento(Servico servico, Atendente atendente, Usuario cliente, int id, LocalTime horaInicio, boolean confirmado) {
        this.servico = servico;
        this.atendente = atendente;
        this.cliente = cliente;
        this.id = id;
        this.horaInicio = horaInicio;
        this.confirmado = confirmado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.servico);
        hash = 11 * hash + Objects.hashCode(this.atendente);
        hash = 11 * hash + Objects.hashCode(this.cliente);
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.horaInicio);
        hash = 11 * hash + (this.confirmado ? 1 : 0);
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
        final Atendimento other = (Atendimento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.confirmado != other.confirmado) {
            return false;
        }
        if (!Objects.equals(this.servico, other.servico)) {
            return false;
        }
        if (!Objects.equals(this.atendente, other.atendente)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "servico=" + servico + ", atendente=" + atendente + ", cliente=" + cliente + ", id=" + id + ", horaInicio=" + horaInicio + ", confirmado=" + confirmado + '}';
    }

        
    
    
}
