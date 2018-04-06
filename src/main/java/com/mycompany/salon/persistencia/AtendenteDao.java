/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;

/**
 *
 * @author ThigoYure
 * @author AlexaLins
 */
@Named
public class AtendenteDao implements Serializable{

    public Atendente cadastroAtendente(Atendente atendente) throws ClassNotFoundException {
        try (Connection con = ConFactory.getConnection()) {
            String sql = "INSERT INTO atendente (nome, horaInicio, horaFim) VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, atendente.getNome());
            stmt.setTime(2, Time.valueOf(atendente.getHoraInício()));
            stmt.setTime(3, Time.valueOf(atendente.getHoraFim()));
            stmt.executeUpdate();
            stmt.close();
            con.close();
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

    public Atendente readByNome(String nome) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendente WHERE nome = ?");
            st.setString(1, nome);
            ResultSet r = st.executeQuery();
            Atendente atendente = new Atendente();
            if (r.next()) {
                atendente.setNome(r.getString("nome"));
                atendente.setHoraFim(r.getTime("horafim").toLocalTime());
                atendente.setHoraInício(r.getTime("horainicio").toLocalTime());
                st.close();
                con.close();
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
