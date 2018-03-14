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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ThigoYure
 */
public class BuscaPorAtendente extends SimpleTagSupport {

    private String atendente;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        AtendimentoDao atendDao = new AtendimentoDao();
        try {
            getJspContext().setAttribute("AtendimentoPorAtendente", atendDao.readByAtendente(atendente));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscaPorAtendente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }
    
}
