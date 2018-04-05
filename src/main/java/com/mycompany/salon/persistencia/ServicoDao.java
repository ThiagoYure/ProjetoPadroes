/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Servico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 * @author AlexaLins
 */
public class ServicoDao implements Serializable{

    public ServicoDao() {
    }

    public Servico cadastroServico(Servico servico) throws ClassNotFoundException {
        try (Connection con = ConFactory.getConnection()) {
            String sql = "INSERT INTO servico (nome, tempoMedio, preco) VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, servico.getNome());
            stmt.setInt(2, servico.getTempoMedio());
            stmt.setFloat(3, servico.getPreco());
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Servico> readAll() {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM servico");
            ResultSet r = st.executeQuery();
            ArrayList<Servico> retorno = new ArrayList<>();
            while (r.next()) {
                Servico servico = new Servico();
                servico.setNome(r.getString("nome"));
                servico.setPreco(r.getFloat("preco"));
                servico.setTempoMedio(r.getInt("tempomedio"));
                retorno.add(servico);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Servico readByNome(String nome) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM servico WHERE nome = ?");
            st.setString(1, nome);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Servico servico = new Servico();
                servico.setNome(r.getString("nome"));
                servico.setPreco(r.getFloat("preco"));
                servico.setTempoMedio(r.getInt("tempoMedio"));
                st.close();
                con.close();
                return servico;
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
