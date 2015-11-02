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
public class Usuario_avalia_Livro implements Serializable {
    
    private Usuario idUsuario;
    private Livro idLivro;
    private Integer nota;

    public Usuario_avalia_Livro(Usuario idUsuario, Livro idLivro, Integer nota) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.nota = nota;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Livro getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Livro idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
    
    
    
}
