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

public class HotelDAO implements GenericoDAO<Hotel> {

    @Override
    public void inserir(Hotel hotel) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO HOTELAPP.HOTEL (NOME,ID_CIDADE, ID_ESTADO) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hotel.getNome());
            preparedStatement.setInt(2, hotel.getCidade().getCodigo());
            preparedStatement.setInt(3, hotel.getEstado().getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }

    }

    @Override
    public void atualizar(Hotel hotel) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "UPDATE HOTELAPP.HOTEL SET NOME=?, ID_CIDADE=?, ID_ESTADO=? WHERE ID_HOTEL=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hotel.getNome());
            ps.setInt(2, hotel.getCidade().getCodigo());
            ps.setInt(3, hotel.getEstado().getCodigo());
            ps.setInt(4, hotel.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("alteração não realizada!");
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
            String sql = "DELETE FROM HOTELAPP.HOTEL WHERE ID_HOTEL = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
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
    public List<Hotel> listar() throws PersistenciaException {
        Connection connection = null;
        List<Hotel> hoteis = new ArrayList<Hotel>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT F.ID_HOTEL, F.NOME NOME_HOTEL, F.ID_CIDADE, C.NOME NOME_CIDADE, "
                    + "F.ID_ESTADO, D.NOME NOME_ESTADO "
                    + "FROM HOTELAPP.HOTEL F "
                    + "INNER JOIN HOTELAPP.CIDADE C "
                    + "ON F.ID_CIDADE = C.ID_CIDADE "
                    + "INNER JOIN HOTELAPP.ESTADO D "
                    + "ON F.ID_ESTADO = D.ID_ESTADO";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                hoteis.add(
                        new Hotel(result.getInt("id_hotel"), result.getString("NOME_HOTEL"),
                        new Cidade(result.getInt("id_cidade"), result.getString("NOME_CIDADE")),
                        new Estado(result.getInt("id_estado"), result.getString("NOME_ESTADO"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return hoteis;
    }

//    @Override
//    public Hotel buscarPorId(Integer id) throws PersistenciaException {
//        Connection connection = null;
//        Hotel hotel = null;
//        try {
//            connection = Conexao.getInstance().getConnection();
//
//            String sql = "SELECT f.ID_HOTEL, f.NOME, c.ID_CIDADE, c.NOME as cidade,"
//                    + "d.ID_ESTADO, d.NOME as estado "
//                    + "FROM HOTELAPP.HOTEL AS f INNER JOIN HOTELAPP.CIDADE AS c "
//                    + "ON f.ID_CIDADE = c.ID_CIDADE "
//                    + "INNER JOIN HOTELAPP.ESTADO AS d "
//                    + "ON F.ID_ESTADO = d.ID_ESTADO "
//                    + "WHERE ID_HOTEL = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet result = ps.executeQuery();
//            while (result.next()) {
//                hotel = new Hotel(result.getInt("id_hotel"), result.getString("nome"), 
//                        new Cidade(result.getInt("id_cidade"), result.getString("cidade")), 
//                        new Estado(result.getInt("id_estado"), result.getString("estado")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
//            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
//                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
//            }
//        }
//        return hotel;
//    }
        @Override
    public Hotel buscarPorId(Integer id) throws PersistenciaException {
        Connection connection = null;
        Hotel hotel = null;
        try {
            connection = Conexao.getInstance().getConnection();

            String sql = "SELECT F.ID_HOTEL, F.NOME NOME_HOTEL, F.ID_CIDADE, C.NOME NOME_CIDADE, "
                    + "F.ID_ESTADO, D.NOME NOME_ESTADO "
                    + "FROM HOTELAPP.HOTEL F "
                    + "INNER JOIN HOTELAPP.CIDADE C "
                    + "ON F.ID_CIDADE = C.ID_CIDADE "
                    + "INNER JOIN HOTELAPP.ESTADO D "
                    + "ON F.ID_ESTADO = D.ID_ESTADO "
                    + "WHERE ID_HOTEL = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                hotel = new Hotel(result.getInt("id_hotel"), result.getString("NOME_HOTEL"), 
                        new Cidade(result.getInt("id_cidade"), result.getString("NOME_CIDADE")), 
                        new Estado(result.getInt("id_estado"), result.getString("NOME_ESTADO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return hotel;
    }
    

//    public List<Hotel> buscarPorNome(String nome) throws PersistenciaException {
//        Connection connection = null;
//        List<Hotel> hoteis = new ArrayList<Hotel>();
//        try {
//            connection = Conexao.getInstance().getConnection();
//            String sql = "SELECT * FROM HOTELAPP.HOTEL WHERE NOME LIKE ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "%" + nome + "%");
//
//            ResultSet result = ps.executeQuery();
//            while (result.next()) {
//                Cidade cidade = new Cidade();
//                cidade = new CidadeDAO().buscarPorId(result.getInt("id_cidade"));
//                hoteis.add(new Hotel(result.getInt("id_hotel"), result.getString("nome"), cidade));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
//            throw new PersistenciaException("Não foi possível realizar a busca pelo nome");
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
//                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
//            }
//        }
//        return hoteis;
//    }
}
