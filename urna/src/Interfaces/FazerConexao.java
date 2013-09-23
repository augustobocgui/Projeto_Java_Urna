/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Excessoes.BancoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class FazerConexao {

    public Connection fazerConexaoBanco() throws BancoException {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/eleicao", "root", "root");
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null,
                    "Não foi possível estabelecer conexão com o Mysql.\n"
                    + "Vefifique usuário, senha e base de dados se estão configurados corretamente!",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            throw new BancoException("Erro ao fazer conexão: " + e);
        }
    }
}
