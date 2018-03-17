/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ThigoYure
 */
public class AtendimentoBuilder {
    private final Atendimento atendimento;

    public AtendimentoBuilder() {
        atendimento = new Atendimento();
    }
    public AtendimentoBuilder comAtendente(Atendente atendente){
        atendimento.setAtendente(atendente);
        return this;
    }
    public AtendimentoBuilder comServico(Servico servico){
        atendimento.setServico(servico);
        return this;
    }
    public AtendimentoBuilder comCliente(Usuario cliente){
        atendimento.setCliente(cliente);
        return this;
    }
    public AtendimentoBuilder comId(int id){
        atendimento.setId(id);
        return this;
    }
    public AtendimentoBuilder comData(LocalDate data){
        atendimento.setData(data);
        return this;
    }
    public AtendimentoBuilder comHoraInicio(LocalTime horaInicio){
        atendimento.setHoraInicio(horaInicio);
        return this;
    }
    public AtendimentoBuilder comHoraFim(LocalTime horaFim){
        atendimento.setHoraFim(horaFim);
        return this;
    }
    public AtendimentoBuilder comConfirmado(boolean confirmado){
        atendimento.setConfirmado(confirmado);
        return this;
    }
    public Atendimento getAtendimento(){
        return atendimento;
    }
    
}
