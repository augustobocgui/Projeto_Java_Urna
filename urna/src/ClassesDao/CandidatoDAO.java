/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Candidato;
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
public class CandidatoDAO {

    Connection conexao;
    private String sql;

    public CandidatoDAO() throws BancoException {
        conexao = (Connection) new FazerConexao().fazerConexaoBanco();
    }

    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = null;
    }

    public int gerarCodigoCandidato() throws BancoException {
        int codigo = 0;
        sql = "SELECT MAX(ID) as ID FROM CANDIDATO";
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

    public void adicionarCandidato(Candidato candidato) throws BancoException {
        sql = "INSERT INTO CANDIDATO (ID, NOME, PARTIDO, FOTO) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setInt(1, candidato.getCodigo());
            urna.setString(2, candidato.getNome());
            urna.setString(3, candidato.getPartido());
            urna.setString(4, candidato.getFoto());


            urna.execute(); // se não precisar resgatar dados
            urna.close();
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public Candidato carregarCandidatoPeloCodigo(String nome) throws BancoException {
        Candidato candidato = new Candidato();
        candidato.setNome("NULO");
        sql = "SELECT * FROM CANDIDATO WHERE id = ?";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setString(1, nome);
            ResultSet banco = urna.executeQuery();
            while (banco.next()) // só entra se existir 
            {
                candidato.setCodigo(banco.getInt("id"));
                candidato.setNome(banco.getString("nome"));
                candidato.setPartido(banco.getString("partido"));
                candidato.setFoto(banco.getString("foto"));

            }
            banco.close();
            urna.close();
            return candidato;
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public Candidato carregarCandidato(StringBuffer cursos) throws BancoException {
        Candidato regiao = new Candidato();
        regiao.setNome("NULO");
        sql = "SELECT * FROM CANDIDATO WHERE ID = ?";
        try {
            PreparedStatement urna = conexao.prepareStatement(sql);
            urna.setString(1, cursos.toString());
            ResultSet banco = urna.executeQuery();
            while (banco.next()) // só entra se existir 
            {
                regiao.setCodigo(banco.getInt("id"));
                regiao.setNome(banco.getString("nome"));
                regiao.setPartido(banco.getString("partido"));
                regiao.setFoto(banco.getString("foto"));
            }
            urna.close();
            banco.close();
            return regiao;
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public void atualizaDados(Candidato candidato) throws BancoException {
        sql = "UPDATE CANDIDATO SET NOME = ?, PARTIDO = ?, FOTO = ? WHERE CODIGO = ? ";
        try {
            PreparedStatement urnaAlterar = conexao.prepareStatement(sql);
            urnaAlterar.setString(1, candidato.getNome());
            urnaAlterar.setString(2, candidato.getPartido());
            urnaAlterar.setInt(3, candidato.getCodigo());

            urnaAlterar.execute();
            urnaAlterar.close();
        } catch (SQLException e) {
            throw new BancoException("" + e);
        }
    }

    public List<Candidato> pesquisaCandidato(String pes, String pesCpf) throws BancoException {
        char aspas = '"';
        String sql;
        if (pes.equals("") || pes == null) {// || pesCpf.equals("")) {
            sql = "Select * from candidato";
        } else {
            sql = "Select * from candidato where nome like " + aspas + "%" + pes + "%" + aspas + "and partido"
                    + " like" + aspas + "%" + pesCpf + "%" + aspas;
        }

        try {
            PreparedStatement cidadao = conexao.prepareStatement(sql);
            ResultSet banco = cidadao.executeQuery();

            List<Candidato> lista = new ArrayList<Candidato>();
            while (banco.next()) // só entra se existir 
            {
                Candidato democracia = new Candidato();
                democracia.setCodigo(banco.getInt("id"));
                democracia.setNome(banco.getString("nome"));
                democracia.setPartido(banco.getString("partido"));

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
