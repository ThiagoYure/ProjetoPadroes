/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.persistencia.AtendimentoDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThigoYure
 */
@Named
public class ConfirmarAtendimentoController implements Command,Serializable{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        int atendimentoId = Integer.parseInt(req.getParameter("idAtendimento"));
        AtendimentoDao atendimentoDao = new AtendimentoDao();
        if(atendimentoDao.updateConfirmado(atendimentoId)){
            try {
                res.sendRedirect(req.getContextPath() + "?msg=Confirmado com sucesso!!");
            } catch (IOException ex) {
                Logger.getLogger(ConfirmarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                res.sendRedirect(req.getContextPath() + "?msg=Falha ao confirmar. Tente novamente mais tarde!");
            } catch (IOException ex) {
                Logger.getLogger(ConfirmarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
