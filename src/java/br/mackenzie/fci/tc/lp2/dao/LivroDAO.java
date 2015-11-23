/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.dao;

import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Livro;
import br.mackenzie.fci.tc.lp2.model.Usuario;
import com.sun.istack.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Suelen
 */
public class LivroDAO implements GenericoDAO<Livro> {

    @Override
    public void inserir(Livro livro) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO Livro (titulo, genero, autor, editora, publicacao, preco) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getGenero());
            preparedStatement.setString(3, livro.getAutor());
            preparedStatement.setString(4, livro.getEditora());
            preparedStatement.setDate(5, (Date) livro.getPublicacao());
            preparedStatement.setDouble(6, livro.getPreco());
            connection.close();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void atualizar(Livro livro) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "UPDATE Livro SET titulo=? WHERE idLivro=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getIdLivro());
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("alteração não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void excluir(Integer id) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM Livro WHERE idLivro = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public List<Livro> listar() throws PersistenciaException {
        Connection connection = null;
        List<Livro> livros = new ArrayList<Livro>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Livro";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                livros.add(new Livro(result.getInt("idLivro"), result.getString("titulo"), result.getString("genero"), result.getString("autor"), result.getString("editora"), result.getDate("publicacao"), result.getDouble("preco")));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return livros;
    }

    @Override
    public Livro buscarPorId(Integer id) throws PersistenciaException {
        Connection connection = null;
        Livro livro = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Livro WHERE idLivro = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livro = new Livro(result.getInt("idLivro"), result.getString("titulo"), result.getString("genero"), result.getString("autor"), result.getString("editora"), result.getDate("publicacao"), result.getDouble("preco"));
            }
            connection.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return livro;
    }
    
    
    public Livro buscarPorNome(String nome) throws PersistenciaException {
        Connection connection = null;
        Livro livro = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Livro WHERE titulo = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livro = new Livro(result.getInt("idLivro"), result.getString("titulo"), result.getString("genero"), result.getString("autor"), result.getString("editora"), result.getDate("prblicacao"), result.getDouble("preco"));
            }
            connection.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return livro;
    }

}
