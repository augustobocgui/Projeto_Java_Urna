/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Apuracao;
import Excessoes.BancoException;
import Interfaces.FazerConexao;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class ApuracaoDAO {

    Connection conexao;
    private String sql;

    public ApuracaoDAO() throws BancoException {
        conexao = (Connection) new FazerConexao().fazerConexaoBanco();
    }

    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ApuracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = null;
    }

    public int gerarCodigoApuracao() throws BancoException {
        int codigo = 0;
        sql = "SELECT MAX(ID) as ID FROM APURACAO";
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet urna = stm.executeQuery(); // se tiver que puxar algum dado do banco
            while (urna.next()) {
                codigo = urna.getInt("id"); // puxo o valor da tabela alunos codigo do banco de dados
            }
            stm.close();
            urna.close();
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
        return codigo + 1;
    }

    public void adicionarApuracao(Apuracao localidade) throws BancoException {
        sql = "INSERT INTO APURACAO (ID, CONTAGEM) VALUES (?, ?)";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setInt(1, localidade.getCodigo());
            urna.setInt(2, localidade.getContagem());


            urna.execute(); // se não precisar resgatar dados
            urna.close();
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public Apuracao carregarApuracaoPeloCodigo(String nome) throws BancoException {
        Apuracao localidade = new Apuracao();
        localidade.setCodigo(1);
        sql = "SELECT * FROM APURACAO WHERE id = ?";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setString(1, nome);
            ResultSet banco = urna.executeQuery();
            while (banco.next()) // só entra se existir 
            {
                localidade.setCodigo(banco.getInt("id"));
                localidade.setContagem(banco.getInt("contagem"));

            }
            banco.close();
            urna.close();
            return localidade;
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public Apuracao carregarApuracao(StringBuffer cursos) throws BancoException {
        Apuracao regiao = new Apuracao();
        regiao.setCodigo(1);
        sql = "SELECT * FROM APURACAO WHERE ID = ?";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setString(1, cursos.toString());
            ResultSet banco = urna.executeQuery();
            while (banco.next()) // só entra se existir 
            {
                regiao.setCodigo(banco.getInt("id"));
                regiao.setContagem(banco.getInt("contagem"));
            }
            urna.close();
            banco.close();
            return regiao;
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public void atualizaDados(Apuracao local) throws BancoException {
        sql = "UPDATE APURACAO SET CONTAGEM = ? WHERE ID = ? ";
        try {
            PreparedStatement urnaAlterar = conexao.prepareStatement(sql);
            urnaAlterar.setInt(1, local.getContagem());
            urnaAlterar.setInt(2, local.getCodigo());

            urnaAlterar.execute();
            urnaAlterar.close();
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public List<Apuracao> pesquisaApuracao(String pes, String pesCpf) throws BancoException {
        char aspas = '"';
        String sql;
        if (pes.equals("") || pes == null) {// || pesCpf.equals("")) {
            sql = "Select * from apuracao";
        } else {
            sql = "Select * from apuracao where nome like " + aspas + "%" + pes + "%" + aspas + "and contagem"
                    + " like" + aspas + "%" + pesCpf + "%" + aspas;
        }

        try {
            PreparedStatement cidadao = conexao.prepareStatement(sql);
            ResultSet banco = cidadao.executeQuery();

            List<Apuracao> lista = new ArrayList<Apuracao>();
            while (banco.next()) // só entra se existir 
            {
                Apuracao democracia = new Apuracao();
                democracia.setCodigo(banco.getInt("id"));
                democracia.setContagem(banco.getInt("contagem"));

                lista.add(democracia);
            }
            banco.close();
            cidadao.close();
            return lista;
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }
}
