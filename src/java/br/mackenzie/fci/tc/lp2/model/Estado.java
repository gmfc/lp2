/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.model;

import java.io.Serializable;

/**
 *
 * @author 31319238
 */
public class Estado implements Serializable{
    
    private String nome;
    private int codigo;

    public Estado() {
    }

    public Estado(int codigo, String nome){
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
}
