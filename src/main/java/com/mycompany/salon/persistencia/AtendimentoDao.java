/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
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
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE atendente = ? and cliente is null");
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

    public ArrayList<Atendimento> readByAtendente(String atendente) {
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

    public ArrayList<Atendimento> readByServico(String servico) {
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

    public Atendimento readById(int idAtendimento) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE id = ?");
            st.setInt(1, idAtendimento);
            ResultSet r = st.executeQuery();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            if (r.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setAtendente(atendenteDao.readByNome(r.getString("atendente")));
                atendimento.setCliente(usuarioDao.readByEmail(r.getString("email")));
                atendimento.setServico(servicoDao.readByNome(r.getString("servico")));
                atendimento.setConfirmado(r.getBoolean("confirmado"));
                atendimento.setData(LocalDate.parse(r.getString("data")));
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraFim(LocalTime.parse(r.getString("horafim")));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horainicio")));
                return atendimento;
            }
            st.close();
            con.close();
            return null;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean create(Atendimento atendimento) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO Atendimento (atendente,servico,cliente,data,horainicio,horafim,"
                        + "confirmado) VALUES(?,?,?,?,?,?,?)");
                st.setString(1, atendimento.getAtendente().getNome());
                st.setString(2, atendimento.getServico().getNome());
                st.setString(3, atendimento.getCliente().getEmail());
                st.setString(4, atendimento.getData().toString());
                st.setString(5, atendimento.getHoraInicio().toString());
                st.setString(6, atendimento.getHoraFim().toString());
                st.setBoolean(7, atendimento.isConfirmado());
                retorno = st.executeUpdate();
                st.close();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Atendimento readByDataHorarioAtendente(LocalDate data, LocalTime horaInicio, String nome) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE data = ? and horainicio = ? and atendente = ?");
            st.setString(1, data.toString());
            st.setString(2, horaInicio.toString());
            st.setString(3, nome);
            ResultSet r = st.executeQuery();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            if (r.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setAtendente(atendenteDao.readByNome(r.getString("atendente")));
                atendimento.setCliente(usuarioDao.readByEmail(r.getString("email")));
                atendimento.setServico(servicoDao.readByNome(r.getString("servico")));
                atendimento.setConfirmado(r.getBoolean("confirmado"));
                atendimento.setData(LocalDate.parse(r.getString("data")));
                atendimento.setId(r.getInt("id"));
                atendimento.setHoraFim(LocalTime.parse(r.getString("horafim")));
                atendimento.setHoraInicio(LocalTime.parse(r.getString("horainicio")));
                return atendimento;
            }
            st.close();
            con.close();
            return null;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Usuario> readByQuant(String intervalo) {
        int intervaloInt = Integer.parseInt(intervalo);
        LocalDate dataMaxima = LocalDate.now().minusMonths(intervaloInt);
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("select u.email, u.nome from atendimento at,usuario u where"
                    + "u.email = at.cliente and (at.data between ? and ?) order by (select sum(s.preco) from atendimento att, servico s where att.cliente = at.cliente and att.servico = s.nome ) asc");
            st.setDate(1, Date.valueOf(dataMaxima));
            st.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet r = st.executeQuery();
            ArrayList<Usuario> retorno = new ArrayList<>();
            while(r.next()){
                Usuario user = new Usuario();
                user.setEmail(r.getString("u.email"));
                user.setNome(r.getString("u.nome"));
                retorno.add(user);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Usuario> readByGastos(String intervalo) {
        int intervaloInt = Integer.parseInt(intervalo);
        LocalDate dataMaxima = LocalDate.now().minusMonths(intervaloInt);
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("select u.email, u.nome from atendimento at,usuario u where"
                    + "u.email = at.cliente and (at.data between ? and ?) order by (select count(cliente) from atendimento att where att.cliente = at.cliente ) asc");
            st.setDate(1, Date.valueOf(dataMaxima));
            st.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet r = st.executeQuery();
            ArrayList<Usuario> retorno = new ArrayList<>();
            while(r.next()){
                Usuario user = new Usuario();
                user.setEmail(r.getString("u.email"));
                user.setNome(r.getString("u.nome"));
                retorno.add(user);
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
