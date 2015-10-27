/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.fci.tc.lp2.dao;

import br.mackenzie.fci.tc.lp2.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author 1146322
 */
public interface GenericoDAO<E> {

    void inserir(E e) throws PersistenciaException;

    void atualizar(E e) throws PersistenciaException;

    void excluir(Integer id) throws PersistenciaException;

    List<E> listar() throws PersistenciaException;

    E buscarPorId(Integer id) throws PersistenciaException;
}
