/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Conexao {

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String driver = "jdbc:mysql://localhost:3306/eleicao";
            Connection con = DriverManager.getConnection(driver, "root", "root");
            return con;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,
                    "Não foi possível estabelecer conexão com o Mysql.\n"
                    + "Vefifique usuário, senha e base de dados se estão configurados corretamente!",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
}
