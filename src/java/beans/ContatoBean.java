/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import dao.ContatoDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.Contato;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class ContatoBean extends CrudBean<Contato, ContatoDao> implements Serializable{

    private ContatoDao contatoDao;
    @Override
    public ContatoDao getDao() {
        if (contatoDao == null) {
           contatoDao = new ContatoDao();
        }
        return contatoDao;
    }

    @Override
    public Contato criarNovaEntidade() {
        return new Contato();
    }

    @Override
    public String irParaCadastro() {
        return "cadastro-contato.xhtml?faces-redirect=true";
    }

    @Override
    public String irParaEdicao() {
        return "editar-contato.xhtml?faces-redirect=true";
    }

    @Override
    public String irParaBusca() {
        return "lista-contato.xhtml?faces-redirect=true";
    }
}
