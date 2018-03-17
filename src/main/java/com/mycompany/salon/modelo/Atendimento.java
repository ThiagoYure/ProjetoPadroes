/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.time.LocalDate;
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
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean confirmado;

    public Atendimento() {
    }
    public Atendimento(Atendimento atendimento){
        this.servico = atendimento.getServico();
        this.atendente = atendimento.getAtendente();
        this.cliente = atendimento.getCliente();
        this.id = atendimento.getId();
        this.data = atendimento.getData();
        this.horaInicio = atendimento.getHoraInicio();
        this.horaFim = atendimento.getHoraFim();
        this.confirmado = atendimento.isConfirmado();
    }
    public Atendimento(Servico servico, Atendente atendente, Usuario cliente, int id, LocalDate data, LocalTime horaInicio, boolean confirmado) {
        this.servico = servico;
        this.atendente = atendente;
        this.cliente = cliente;
        this.id = id;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaInicio.plusMinutes(servico.getTempoMedio());
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.servico);
        hash = 61 * hash + Objects.hashCode(this.atendente);
        hash = 61 * hash + Objects.hashCode(this.cliente);
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.data);
        hash = 61 * hash + Objects.hashCode(this.horaInicio);
        hash = 61 * hash + Objects.hashCode(this.horaFim);
        hash = 61 * hash + (this.confirmado ? 1 : 0);
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
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        if (!Objects.equals(this.horaFim, other.horaFim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "servico=" + servico + ", atendente=" + atendente + ", cliente=" + cliente + ", id=" + id + ", data=" + data + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", confirmado=" + confirmado + '}';
    }
    
    public Atendimento clone(){
        return (new Atendimento(this));
    }
    
    

   
}
