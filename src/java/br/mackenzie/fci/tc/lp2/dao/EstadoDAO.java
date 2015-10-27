/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.dao;

/**
 *
 * @author 31319238
 */

import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Cidade;
import br.mackenzie.fci.tc.lp2.model.Estado;
import br.mackenzie.fci.tc.lp2.model.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoDAO implements GenericoDAO<Estado> {
    
    @Override
    public void inserir(Estado estado) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO HOTELAPP.ESTADO (NOME) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, estado.getNome());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void atualizar(Estado estado) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "UPDATE HOTELAPP.ESTADO SET NOME=? "
                    + "WHERE ID_ESTADO=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, estado.getNome());
            ps.setInt(2, estado.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Atualização não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void excluir(Integer id) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM HOTELAPP.ESTADO WHERE ID_ESTADO = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
        }
    }

    @Override
    public List<Estado> listar() throws PersistenciaException {
        Connection connection = null;
        List<Estado> estados = new ArrayList<Estado>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.ESTADO";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                estados.add(new Estado(result.getInt("id_estado"), result.getString("nome")));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        }
        return estados;
    }
    
    public List<Hotel> listarHoteis(Integer id) throws PersistenciaException {
        Connection connection = null;
        List<Hotel> hoteis = new ArrayList<>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT F.ID_HOTEL, F.NOME NOME_HOTEL, F.ID_CIDADE, C.NOME NOME_CIDADE, "
                    + "F.ID_ESTADO, D.NOME NOME_ESTADO "
                    + "FROM HOTELAPP.HOTEL F "
                    + "INNER JOIN HOTELAPP.CIDADE C "
                    + "ON F.ID_CIDADE = C.ID_CIDADE "
                    + "INNER JOIN HOTELAPP.ESTADO D "
                    + "ON F.ID_ESTADO = D.ID_ESTADO "
                    + "WHERE F.ID_ESTADO = "+id;
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);
            while (result.next()) {
                hoteis.add(
                        new Hotel(result.getInt("id_hotel"), result.getString("NOME_HOTEL"),
                        new Cidade(result.getInt("id_cidade"), result.getString("NOME_CIDADE")),
                        new Estado(result.getInt("id_estado"), result.getString("NOME_ESTADO"))));
            }
            return hoteis;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos os hoteis!");
        }
    }

    @Override
    public Estado buscarPorId(Integer id) throws PersistenciaException {
        Connection connection = null;
        Estado estado = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.ESTADO WHERE ID_ESTADO = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                estado = new Estado(result.getInt("id_estado"), result.getString("nome"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return estado;
    }

    public Estado buscarPorNome(String nome) throws PersistenciaException {
        Connection connection = null;
        List<Estado> estados = new ArrayList<Estado>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.ESTADO WHERE NOME LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                return new Estado(resultSet.getInt("id_estado"), resultSet.getString("nome"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo nome");
        }
        return new Estado();
    }
}
