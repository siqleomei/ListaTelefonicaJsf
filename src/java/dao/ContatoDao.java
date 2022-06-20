/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Contato;

/**
 *
 * @author Usuario
 */
public class ContatoDao implements CrudDao<Contato> {

    @Override
    public void gravar(Contato contato) throws ErroSistema {
        Connection conn;
        PreparedStatement ps;
        try {
            conn = new Conexao().conectar();
            ps = conn.prepareStatement("INSERT INTO contatos (Nome, Telefone, Email) VALUES (?, ?, ?)");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new ErroSistema("Falha ao gravar novo contato");
        }
    }

    @Override
    public void editar(Contato contato) throws ErroSistema {
        Connection conn;
        PreparedStatement ps;
        try {
            conn = new Conexao().conectar();
            ps = conn.prepareStatement("UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new ErroSistema("Falha ao atualizar o contato");
        }
    }

    @Override
    public void apagar(Contato contato) throws ErroSistema {
        Connection conn;
        PreparedStatement ps;
        try {
            conn = new Conexao().conectar();
            ps = conn.prepareStatement("DELETE FROM contatos WHERE id = ?");
            ps.setInt(1, contato.getId());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new ErroSistema("Falha ao apagar o contato");
        }
    }

    @Override
    public List<Contato> buscar(String filtro) throws ErroSistema {
        List<Contato> result = null;
        Connection conn;
        PreparedStatement ps;
        try {
            conn = new Conexao().conectar();
            ps = conn.prepareCall("SELECT Id, Nome, Telefone, Email FROM contatos");
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                result = new ArrayList<>();
                do {
                    Contato contato = new Contato();
                    contato.setId(rs.getInt("id"));
                    contato.setNome(rs.getString("nome"));
                    contato.setTelefone(rs.getString("telefone"));
                    contato.setEmail(rs.getString("email"));
                    result.add(contato);
                } while (rs.next());
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new ErroSistema("Falha ao buscar os contatos");
        }

        return result;
    }

}
