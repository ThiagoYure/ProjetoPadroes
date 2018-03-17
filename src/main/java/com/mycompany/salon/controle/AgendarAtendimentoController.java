/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.controle;

import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.Usuario;
import com.mycompany.salon.persistencia.AtendimentoDao;
import com.mycompany.salon.persistencia.UsuarioDao;
import java.io.IOException;
import java.time.LocalDate;
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
        int idAtendimento = Integer.parseInt(req.getParameter("opcao"));
        AtendimentoDao atendimentoDao = new AtendimentoDao();
        UsuarioDao userDao = new UsuarioDao();
        Atendimento agenda = atendimentoDao.readById(idAtendimento);
        Atendimento atendimento = agenda.clone();
        Usuario user = userDao.readByEmail(req.getParameter("cliente"));
        atendimento.setCliente(user);
        atendimento.setConfirmado(false);
        atendimento.setData(LocalDate.parse(req.getParameter("data")));
        if (req.getParameter("opcao").equals("") || req.getParameter("cliente").equals("") || req.getParameter("data").equals("")) {
            try {
                res.sendRedirect(req.getContextPath() + "?msg=Preencha os campos vazios.");
            } catch (IOException ex) {
                Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (atendimentoDao.readByDataHorarioAtendente(atendimento.getData(),atendimento.getHoraInicio(),atendimento.getAtendente().getNome()).equals(null)) {
            try {
                res.sendRedirect(req.getContextPath()+"?msg= Ja existe um atendimento marcado para esse horario.");
            } catch (IOException ex) {
                Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (atendimentoDao.create(atendimento)) {
                try {
                    res.sendRedirect(req.getContextPath() + "?msg=Horario marcado com sucesso.");
                } catch (IOException ex) {
                    Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    res.sendRedirect(req.getContextPath() + "?msg=Nao foi possivel marcar o horario.");
                } catch (IOException ex) {
                    Logger.getLogger(AgendarAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
