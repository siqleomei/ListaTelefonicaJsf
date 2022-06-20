/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import dao.CrudDao;
import exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CrudBean<E, D extends CrudDao> {

    private E entidade;
    private List<E> entidades;

    public abstract D getDao();

    public abstract E criarNovaEntidade();

    public void novo() {
        entidade = criarNovaEntidade();
    }
    
    public void salvar() {
        try {
            getDao().gravar(entidade);
            entidade = criarNovaEntidade();
            irParaBusca();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(E entidade) {
        try {
            getDao().editar(entidade);

        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(E entidade) {
        try {
            getDao().apagar(entidade);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<E> buscar() {
        try {
            entidades = getDao().buscar("");
            
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidades;
    }

    public E getEntidade() {
        return entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    public abstract String irParaCadastro();
    public abstract String irParaEdicao(E entidade);
    public abstract String irParaBusca();
    
    public boolean isTeste() {
        return true;
    };
}
