/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import exception.ErroSistema;
import java.util.List;

/**
 *
 * @author Usuario
 */
    public interface CrudDao<E> {
    
    void gravar(E element) throws ErroSistema;
    void editar(E element) throws ErroSistema;
    void apagar(E element) throws ErroSistema;
    List<E> buscar(String filtro) throws ErroSistema;
}
