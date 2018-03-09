/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThigoYure
 */
public class AgendarAtendimentoController implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        String atendente = req.getParameter("atendente");
        String servico = req.getParameter("servico");
        LocalTime horario = LocalTime.parse(req.getParameter("horario"));
        String cliente = req.getParameter("cliente");
        AtendimentoDao dao = new AtendimentoDao();
        if (atendente.equals("") || servico.equals("") || cliente.equals("") || horario.equals(req.getParameter("horario"))) {
            try {
                res.sendError(1, "Campos vazios!");
            } catch (IOException ex) {
                Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dao.readAtendimento(servico, atendente, horario).equals(null)) {
            if(dao.create(atendente,servico,horario,cliente)){
                try {
                    res.sendRedirect(req.getContextPath());
                } catch (IOException ex) {
                    Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    res.sendError(2, "Falha ao criar.");
                } catch (IOException ex) {
                    Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
