/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Conexao {
    private final String bd = "biblioteca"; // mudar o nome da base para cada projeto
    private final String usuario = "root";
    private final String senha = "";

    public Connection conectar() {
        Connection conexao = null;
        try { //tratamento de exceção            
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + this.bd,
                    this.usuario, this.senha);
        } catch (ClassNotFoundException e1) {
            System.out.println("Erro com o driver: " + e1.getMessage());
        } catch (SQLException e2) {
            System.out.println("Erro na tentativa de conexão: " + e2.getMessage());
        }
        return conexao;
    }
}
