package br.mackenzie.fci.tc.lp2.dao;

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

public class CidadeDAO implements GenericoDAO<Cidade> {

    @Override
    public void inserir(Cidade cidade) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO HOTELAPP.CIDADE (NOME) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void atualizar(Cidade cidade) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "UPDATE HOTELAPP.CIDADE SET NOME=? "
                    + "WHERE ID_CIDADE=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cidade.getNome());
            ps.setInt(2, cidade.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Atualização não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void excluir(Integer id) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM HOTELAPP.CIDADE WHERE ID_CIDADE = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
        }
    }

    @Override
    public List<Cidade> listar() throws PersistenciaException {
        Connection connection = null;
        List<Cidade> cidades = new ArrayList<Cidade>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.CIDADE";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                cidades.add(new Cidade(result.getInt("id_cidade"), result.getString("nome")));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        }
        return cidades;
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
                    + "WHERE F.ID_CIDADE = "+id;
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
    public Cidade buscarPorId(Integer id) throws PersistenciaException {
        Connection connection = null;
        Cidade cidade = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.CIDADE WHERE ID_CIDADE = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                cidade = new Cidade(result.getInt("id_cidade"), result.getString("nome"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return cidade;
    }

    public Cidade buscarPorNome(String nome) throws PersistenciaException {
        Connection connection = null;
        List<Cidade> cidades = new ArrayList<Cidade>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM HOTELAPP.CIDADE WHERE NOME LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                return new Cidade(resultSet.getInt("id_cidade"), resultSet.getString("nome"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo nome");
        }
        return new Cidade();
    }
}