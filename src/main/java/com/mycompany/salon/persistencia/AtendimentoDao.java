/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 */
public class AtendimentoDao {

    public AtendimentoDao() {
    }

    public ArrayList<Atendimento> readAgendaByAtendente(String atendente) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE atendente = ?");
            ResultSet r = st.executeQuery();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            while (r.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setAtendente(atendenteDao.readByNome(r.getString("atendente")));
                atendimento.setServico(servicoDao.readByNome(r.getString("servico")));
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horarioinicio")));
                atendimento.setHoraFim(LocalTime.parse("horafim"));
                retorno.add(atendimento);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object readByAtendente(String atendente) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE atendente = ?");
            st.setString(1, atendente);
            ResultSet r = st.executeQuery();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            while (r.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setAtendente(atendenteDao.readByNome(r.getString("atendente")));
                atendimento.setCliente(usuarioDao.readByEmail(r.getString("email")));
                atendimento.setServico(servicoDao.readByNome(r.getString("servico")));
                atendimento.setConfirmado(r.getBoolean("confirmado"));
                atendimento.setData(LocalDate.parse(r.getString("data")));
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraFim(LocalTime.parse(r.getString("horafim")));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horainicio")));
                retorno.add(atendimento);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object readByServico(String servico) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE servico = ?");
            st.setString(1, servico);
            ResultSet r = st.executeQuery();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            while (r.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setAtendente(atendenteDao.readByNome(r.getString("atendente")));
                atendimento.setCliente(usuarioDao.readByEmail(r.getString("email")));
                atendimento.setServico(servicoDao.readByNome(r.getString("servico")));
                atendimento.setConfirmado(r.getBoolean("confirmado"));
                atendimento.setData(LocalDate.parse(r.getString("data")));
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraFim(LocalTime.parse(r.getString("horafim")));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horainicio")));
                retorno.add(atendimento);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
