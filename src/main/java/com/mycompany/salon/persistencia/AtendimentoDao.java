/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.salon.persistencia;

import com.mycompany.salon.modelo.Atendente;
import com.mycompany.salon.modelo.Atendimento;
import com.mycompany.salon.modelo.AtendimentoBuilder;
import com.mycompany.salon.modelo.Servico;
import com.mycompany.salon.modelo.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ThigoYure
 */
@Named
public class AtendimentoDao implements Serializable {

    public ArrayList<Atendimento> readHorariosByAtendente(String atendente, String data, String servico) {
        try (Connection con = ConFactory.getConnection()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataAgenda = LocalDate.parse(data, dtf);
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE atendente = ? and servico = ? and confirmado is null");
            st.setString(1, atendente);
            st.setString(2, servico);
            ResultSet r = st.executeQuery();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            AtendimentoBuilder builder = new AtendimentoBuilder();
            while (r.next()) {
                Atendente atendenteObj = atendenteDao.readByNome(r.getString("atendente"));
                Servico servicoDoAtendente = servicoDao.readByNome(servico);
                Atendimento atendimento = builder.comAtendente(atendenteObj).comServico(servicoDoAtendente).comId(r.getInt("id")).getAtendimento();
                LocalTime ultimoHorario = atendenteObj.getHoraInício();
                LocalTime auxiliar;
                while (ultimoHorario != atendenteObj.getHoraFim()) {
                    auxiliar = ultimoHorario;
                    ultimoHorario = auxiliar.plusMinutes(servicoDoAtendente.getTempoMedio());
                    PreparedStatement st2 = con.prepareStatement("SELECT * FROM atendimento WHERE (horainicio = ? or ? between horainicio and horafim) and data = ?");
                    st2.setTime(1, Time.valueOf(auxiliar));
                    st2.setTime(2, Time.valueOf(ultimoHorario));
                    st2.setDate(3, Date.valueOf(dataAgenda));
                    ResultSet rs = st2.executeQuery();
                    if (!rs.next()) {
                        Atendimento horario = atendimento.clone();
                        horario.setData(dataAgenda);
                        horario.setHoraInicio(auxiliar);
                        horario.setHoraFim(ultimoHorario);
                        retorno.add(horario);
                    }
                }
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
            ArrayList<Atendimento> retorno = new ArrayList<>();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
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

    public ArrayList<Atendimento> readHorariosByServico(String servico, String data) {
        try (Connection con = ConFactory.getConnection()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println(servico);
            LocalDate dataAgenda = LocalDate.parse(data, dtf);
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE servico = ? and confirmado is null");
            st.setString(1, servico);
            ResultSet r = st.executeQuery();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            AtendimentoBuilder builder = new AtendimentoBuilder();
            while (r.next()) {
                Atendente atendente = atendenteDao.readByNome(r.getString("atendente"));
                Servico servicoDoAtendente = servicoDao.readByNome(servico);
                Atendimento atendimento = builder.comAtendente(atendente).comServico(servicoDoAtendente).comId(r.getInt("id")).getAtendimento();
                LocalTime ultimoHorario = atendente.getHoraInício();
                LocalTime auxiliar;
                while (ultimoHorario != atendente.getHoraFim()) {
                    auxiliar = ultimoHorario;
                    ultimoHorario = auxiliar.plusMinutes(servicoDoAtendente.getTempoMedio());
                    System.out.println(auxiliar);
                    System.out.println(ultimoHorario);
                    PreparedStatement st2 = con.prepareStatement("SELECT * FROM atendimento WHERE (horainicio = ? or ? between horainicio and horafim) and data = ?");
                    st2.setTime(1, Time.valueOf(auxiliar));
                    st2.setTime(2, Time.valueOf(ultimoHorario));
                    st2.setDate(3, Date.valueOf(dataAgenda));
                    ResultSet rs = st2.executeQuery();
                    if (!rs.next()) {
                        System.out.println("oi");
                        Atendimento horario = atendimento.clone();
                        horario.setData(dataAgenda);
                        horario.setHoraInicio(auxiliar);
                        horario.setHoraFim(ultimoHorario);
                        retorno.add(horario);
                    }
                }
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Atendimento readAgendaById(int idAtendimento) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE id = ?");
            st.setInt(1, idAtendimento);
            ResultSet r = st.executeQuery();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            AtendimentoBuilder builder = new AtendimentoBuilder();
            if (r.next()) {
                Atendimento atendimento;
                Atendente atendente = atendenteDao.readByNome(r.getString("atendente"));
                Servico servico = servicoDao.readByNome(r.getString("servico"));
                int id = r.getInt("id");
                builder.comAtendente(atendente).comServico(servico).comId(id);
                atendimento = builder.getAtendimento();
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
                st.setDate(4, Date.valueOf(atendimento.getData()));
                st.setTime(5, Time.valueOf(atendimento.getHoraInicio()));
                st.setTime(6, Time.valueOf(atendimento.getHoraFim()));
                st.setBoolean(7, atendimento.isConfirmado());
                retorno = st.executeUpdate();
                st.close();
                con.close();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Atendimento readByDataHorarioAtendente(LocalDate data, LocalTime horaInicio, String nome) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento WHERE data = ? and (horainicio = ? or ? between horainicio and horafim) and atendente = ?");
            st.setString(1, data.toString());
            st.setTime(2, Time.valueOf(horaInicio));
            st.setTime(3, Time.valueOf(horaInicio));
            st.setString(4, nome);
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
            PreparedStatement st = con.prepareStatement("select att.cliente, count(att.cliente) from atendimento as att, servico as s where att.servico = s.nome and att.confirmado is true and att.data between ? and ? group by att.cliente order by count(att.cliente) desc");
            st.setDate(1, Date.valueOf(dataMaxima));
            st.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet r = st.executeQuery();
            ArrayList<Usuario> retorno = new ArrayList<>();
            UsuarioDao usuarioDao = new UsuarioDao();
            while (r.next()) {
                Usuario user = usuarioDao.readByEmail(r.getString("cliente"));
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
        System.out.println(dataMaxima);
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("select att.cliente, sum(s.preco) from atendimento as att, servico as s where att.servico = s.nome and att.confirmado is true and att.data between ? and ? group by att.cliente order by sum(s.preco) desc");
            st.setDate(1, Date.valueOf(dataMaxima));
            st.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet r = st.executeQuery();
            ArrayList<Usuario> retorno = new ArrayList<>();
            UsuarioDao usuarioDao = new UsuarioDao();
            while (r.next()) {
                Usuario user = usuarioDao.readByEmail(r.getString("cliente"));
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

    public ArrayList<Servico> readAgendaByAtendente(String atendente) {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento where atendente = ? and confirmado is null");
            st.setString(1, atendente);
            ResultSet r = st.executeQuery();
            ArrayList<Servico> retorno = new ArrayList<>();
            ServicoDao servicoDao = new ServicoDao();
            while (r.next()) {
                Servico servico = servicoDao.readByNome(r.getString("servico"));
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

    public ArrayList<Atendimento> readAtendimentosPendentes() {
        try (Connection con = ConFactory.getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM atendimento where confirmado is false");
            ResultSet r = st.executeQuery();
            ArrayList<Atendimento> retorno = new ArrayList<>();
            AtendenteDao atendenteDao = new AtendenteDao();
            ServicoDao servicoDao = new ServicoDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            while (r.next()) {
                if(r.getDate("data").before(Date.valueOf(LocalDate.now()))){
                    Atendimento atendimento;
                    AtendimentoBuilder builder = new AtendimentoBuilder();
                    Atendente atendente = atendenteDao.readByNome(r.getString("atendente"));
                    Servico servico = servicoDao.readByNome(r.getString("servico"));
                    Usuario usuario = usuarioDao.readByEmail(r.getString("cliente"));
                    builder.comAtendente(atendente).comCliente(usuario).comServico(servico).comId(r.getInt("id")).comData(r.getDate("data").toLocalDate()).comConfirmado(r.getBoolean("confirmado")).comHoraInicio(r.getTime("horainicio").toLocalTime()).comHoraFim(r.getTime("horafim").toLocalTime());
                    atendimento = builder.getAtendimento();
                    retorno.add(atendimento);
                }
            }
            st.close();
            con.close();
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AtendenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateConfirmado(int atendimentoId) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("update atendimento set confirmado = true where id = ?");
                st.setInt(1, atendimentoId);
                retorno = st.executeUpdate();
                st.close();
                con.close();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
