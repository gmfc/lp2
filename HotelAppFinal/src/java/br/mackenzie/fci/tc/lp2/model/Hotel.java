/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.model;

import java.io.Serializable;

/**
 *
 * @author 1146322
 */
public class Hotel implements Serializable {

    private Integer codigo;
    private String nome;
    private Cidade cidade;
    private Estado estado;

    public Hotel() {
    }

    public Hotel(Integer codigo, String nome, Cidade cidade, Estado estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
