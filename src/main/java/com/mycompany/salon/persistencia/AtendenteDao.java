/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 * @author AlexaLins
 */
public class AtendenteDao {

    public AtendenteDao() {
    }

    public Atendente cadastroAtendente(Atendente atendente) throws ClassNotFoundException {
        try (Connection con = ConFactory.getConnection()) {
            String sql = "INSERT INTO atendente (nome, horaInicio, horaFim) VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, atendente.getNome());
            stmt.setTime(2, Time.valueOf(atendente.getHoraInício()));
            stmt.setTime(3, Time.valueOf(atendente.getHoraFim()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Atendente> readAll() {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendente");
            ResultSet r = st.executeQuery();
            ArrayList<Atendente> retorno = new ArrayList<>();
            while (r.next()) {
                Atendente atendente = new Atendente();
                atendente.setNome(r.getString("nome"));
                atendente.setHoraInício(LocalTime.parse(r.getString("horainicio")));
                atendente.setHoraFim(LocalTime.parse(r.getString("horafim")));
                AtendimentoDao atendimentoDao = new AtendimentoDao();
                atendente.setAgenda(atendimentoDao.readAgendaByAtendente(atendente.getNome()));
                retorno.add(atendente);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    Atendente readByNome(String nome) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendente WHERE nome = ?");
            st.setString(1, nome);
            ResultSet r = st.executeQuery();
            AtendimentoDao atendimentoDao = new AtendimentoDao();
            if (r.next()) {
                Atendente atendente = new Atendente();
                atendente.setAgenda(atendimentoDao.readAgendaByAtendente(r.getString("nome")));
                atendente.setNome(r.getString("nome"));
                atendente.setHoraFim(LocalTime.parse(r.getString("horafim")));
                atendente.setHoraInício(LocalTime.parse(r.getString("horainicio")));
                return atendente;
            }
            st.close();
            con.close();
            return null;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
