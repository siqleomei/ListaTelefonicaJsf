/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import dao.ContatoDao;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Contato;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "contatoBean")
@SessionScoped
public class ContatoBean extends CrudBean<Contato, ContatoDao> implements Serializable {

    private static final long serialVersionUID = 1L;
    private ContatoDao contatoDao;
    private String conteudoEmail;

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
        return "cadastro.xhtml";
    }

    @Override
    public String irParaEdicao(Contato contato) {
        setEntidade(contato);
        return "edita.xhtml";
    }

    @Override
    public String irParaBusca() {
        return "index.xhtml?faces-redirect=true";
    }
    
    public String irParaEmail(Contato contato) {
        setEntidade(contato);
        return "email.xhtml";
    }

    @Override
    public List<Contato> getEntidades() {
        if (super.getEntidades() == null) {
            buscar();
        }
        return super.getEntidades();
    }

    public String enviarEmail() {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email@gmail.com", "senha");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(((Contato) getEntidade()).getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com Projeto JSF");//Assunto
            message.setText(conteudoEmail);
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            return irParaBusca();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the conteudoEmail
     */
    public String getConteudoEmail() {
        return conteudoEmail;
    }

    /**
     * @param conteudoEmail the conteudoEmail to set
     */
    public void setConteudoEmail(String conteudoEmail) {
        this.conteudoEmail = conteudoEmail;
    }
    
    
}
