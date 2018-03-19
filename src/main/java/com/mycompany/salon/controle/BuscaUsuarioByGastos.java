/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.persistencia.AtendimentoDao;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ThigoYure
 */
public class BuscaUsuarioByGastos extends SimpleTagSupport {

    private String intervalo;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        AtendimentoDao atendimentoDao = new AtendimentoDao();
        getJspContext().setAttribute("UsuariosGastos", atendimentoDao.readByGastos(intervalo));
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }
    
}
