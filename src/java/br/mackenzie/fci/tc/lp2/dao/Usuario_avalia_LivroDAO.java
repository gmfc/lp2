/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.dao;

import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Livro;
import br.mackenzie.fci.tc.lp2.model.Usuario;
import br.mackenzie.fci.tc.lp2.model.Usuario_avalia_Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class Usuario_avalia_LivroDAO implements GenericoDAO<Usuario_avalia_Livro> {

    @Override
    public void inserir(Usuario_avalia_Livro ual) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            
            String sql = "INSERT INTO Usuario_avalia_Livro (idUsuario, idLivro, NOTA) VALUES ("
                    +ual.getIdUsuario().getIdUsuario()+","+
                    ual.getIdLivro().getIdLivro()+","+
                    ual.getNota()+")";
            System.out.println(">>>>>>>>>>>>>>>>>  " +sql);
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void atualizar(Usuario_avalia_Livro ual) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            // VER SE TA CERTO!
            String sql = "UPDATE Usuario_avalia_Livro SET NOTA=? WHERE idUsuario=? AND idLivro=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ual.getNota());
            ps.setInt(2, ual.getIdUsuario().getIdUsuario());
            ps.setInt(3, ual.getIdLivro().getIdLivro());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("alteração não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void excluir(Integer idUsuario) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM Usuario_avalia_Livro WHERE idUsuario=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public List<Usuario_avalia_Livro> listar() throws PersistenciaException {
        Connection connection = null;
        List<Usuario_avalia_Livro> uals = new ArrayList<Usuario_avalia_Livro>();
        List<Usuario> users = new ArrayList<Usuario>();
        List<Livro> livro = new ArrayList<Livro>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Usuario_avalia_Livro";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                Usuario u = new UsuarioDAO().buscarPorId(result.getInt("idUsuario"));
                Livro l = new LivroDAO().buscarPorId(result.getInt("idLivro"));
                Usuario_avalia_Livro ual = new Usuario_avalia_Livro(u, l, result.getInt("NOTA"));
                uals.add(ual);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return uals;
    }

    public List<Usuario_avalia_Livro> listarPorIdLivro(Integer idLivro) throws PersistenciaException {
        Connection connection = null;
        List<Usuario_avalia_Livro> uals = new ArrayList<Usuario_avalia_Livro>();
        List<Usuario> users = new ArrayList<Usuario>();
        List<Livro> livro = new ArrayList<Livro>();
        try {

            // COMOFAZ!!
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Usuario_avalia_Livro WHERE idLivro=" + idLivro;
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                Usuario u = new UsuarioDAO().buscarPorId(result.getInt("idUsuario"));
                Livro l = new LivroDAO().buscarPorId(result.getInt("idLivro"));
                Usuario_avalia_Livro ual = new Usuario_avalia_Livro(u, l, result.getInt("NOTA"));
                uals.add(ual);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_avalia_LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return uals;
    }

    @Override
    @Deprecated
    public Usuario_avalia_Livro buscarPorId(Integer livroId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
