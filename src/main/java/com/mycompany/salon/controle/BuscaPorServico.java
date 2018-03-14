/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.persistencia.AtendimentoDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ThigoYure
 */
public class BuscaPorServico extends SimpleTagSupport {

    private String servico;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        AtendimentoDao atendDao = new AtendimentoDao();
        try {
            getJspContext().setAttribute("AtendimentoPorServico", atendDao.readByServico(servico));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscaPorServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setServico(String servico) {
        this.servico = servico;
    }
    
}
