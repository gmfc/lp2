/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.dao;

import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import br.mackenzie.fci.tc.lp2.model.Usuario;
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
public class UsuarioDAO implements GenericoDAO<Usuario> {

    @Override
    public void inserir(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO USUARIO (IDUSUARIO,NOME,HASHSENHA) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, usuario.getIdUsuario());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getHashSenha());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Inserção não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "UPDATE USUARIO SET NOME=?, HASHSENHA=? WHERE IDUSUARIO=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setInt(2, usuario.hashCode());
            ps.setInt(3, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("alteração não realizada!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public void excluir(Integer id) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível excluir o registro!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
    }

    @Override
    public List<Usuario> listar() throws PersistenciaException {
        Connection connection = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM Usuario";
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(sql);

            while (result.next()) {
                usuarios.add(new Usuario(result.getInt("IDUSUARIO"), result.getString("NOME"), result.getString("HASHSENHA")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível listar todos!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorId(Integer id) throws PersistenciaException {
        Connection connection = null;
        Usuario usuario = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                usuario = new Usuario(result.getInt("IDUSUARIO"), result.getString("NOME"), result.getString("HASHSENHA"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return usuario;
    }
    
    public Usuario buscarPorNome(String nome) throws PersistenciaException {
        Connection connection = null;
        Usuario usuario = null;
        try {
            connection = Conexao.getInstance().getConnection();
            String sql = "SELECT * FROM USUARIO WHERE NOME = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                usuario = new Usuario(result.getInt("IDUSUARIO"), result.getString("NOME"), result.getString("HASHSENHA"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(br.mackenzie.fci.tc.lp2.dao.UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível realizar a busca pelo código!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Não foi possível fechar a conexão com  o banco de dados");
            }
        }
        return usuario;
    }

}
