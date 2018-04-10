/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.persistencia.AtendimentoDao;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ThigoYure
 */
public class BuscaServicosAtendente extends SimpleTagSupport {
    private String atendente;

    @Override
    public void doTag(){
        AtendimentoDao atendDao = new AtendimentoDao();
        getJspContext().setAttribute("HorariosPorAtendente", atendDao.readAgendaByAtendente(atendente));
    }
    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }
    
    
    
}
