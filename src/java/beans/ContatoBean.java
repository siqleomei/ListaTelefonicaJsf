/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import dao.ContatoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.Contato;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "contatoBean")
@SessionScoped
public class ContatoBean extends CrudBean<Contato, ContatoDao> implements Serializable{
    private static final long serialVersionUID = 1L;
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
        novo();
        return "cadastro.xhtml?faces-redirect=true";
    }

    @Override
    public String irParaEdicao(Contato contato) {
        setEntidade(contato);
        return "/edita.xhtml?faces-redirect=true";
    }

    @Override
    public String irParaBusca() {
        return "index.xhtml?faces-redirect=true";
    }

    @Override
    public List<Contato> getEntidades() {
        if (super.getEntidades() == null)
            buscar();
        return super.getEntidades();
    }
    
    
}
