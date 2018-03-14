/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 */
public class AtendimentoDao {

    public AtendimentoDao() {
    }

    public Atendimento readAtendimento(String servico, String atendente, String horario, String data) throws SQLException, ClassNotFoundException {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE servico=? and atendente=? and horario=? and data=?");
            st.setString(1, servico);
            st.setString(2, atendente);
            st.setString(3, horario);
            st.setString(4, data);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Atendimento atendimento = new Atendimento();
                ServicoDao servicoDao = new ServicoDao();
                Servico sevicoObj = servicoDao.readOne(r.getString("servico"));
                AtendenteDao atendenteDao = new AtendenteDao();
                Atendente atendenteObj = atendenteDao.readOne(r.getString("atendente"));
                UsuarioDao usuarioDao = new UsuarioDao();
                Usuario usuarioObj = usuarioDao.readOne(r.getString("cliente"));
                atendimento.setAtendente(atendenteObj);
                atendimento.setCliente(usuarioObj);
                atendimento.setServico(sevicoObj);
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horarioinicio")));
                atendimento.setConfirmado(r.getBoolean("confirmado"));
                atendimento.setData(LocalDate.parse(r.getString("data")));
                st.close();
                con.close();
                return atendimento;
            }
        }
        return null;
    }

    public boolean create(String atendente, String servico, String horario, String cliente, String data) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO atendimento (atendente,servico,cliente,horainicio,data) VALUES(?,?,?,?,?)");
                st.setString(1, atendente);
                st.setString(2, servico);
                st.setString(3, cliente);
                st.setString(4, horario);
                st.setString(5, data);
                retorno = st.executeUpdate();
                st.close();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
