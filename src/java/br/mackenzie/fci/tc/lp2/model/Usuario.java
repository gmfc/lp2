/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.mackenzie.fci.tc.lp2.model;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class Usuario implements Serializable {
    
    private Integer idUsuario;
    private String nome;
    private String hashSenha;

    public Usuario(Integer idUsuario, String nome, String hashSenha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.hashSenha = hashSenha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }
    
    
    
}
