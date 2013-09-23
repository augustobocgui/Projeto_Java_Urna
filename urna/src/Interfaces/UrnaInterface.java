/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Classes.AePlayWave;
import Classes.Apuracao;
import Classes.Candidato;
import ClassesDao.ApuracaoDAO;
import ClassesDao.CandidatoDAO;
import Excessoes.BancoException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author
 */
public class UrnaInterface extends javax.swing.JDialog {

    int[] voto = new int[5];
    int aux = 0;
    int cont = 0;

    /**
     * Creates new form UrnaInterface
     */
    public UrnaInterface(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.requestFocus();


        jLFoto.setVisible(false);
        jLVotoEmBranco.setVisible(false);
        jLDetalhes.setVisible(false);
        jLDetalhes1.setVisible(false);
        jLDetalhes2.setVisible(false);

        setLocationRelativeTo(null);



        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {

                int selection = JOptionPane.showConfirmDialog(rootPane,
                        "Deseja finalizar sessão?",
                        "Urna Eletrônica", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (selection == JOptionPane.OK_OPTION) {
                    try {
                        Fechando();
                    } catch (BancoException ex) {
                        Logger.getLogger(UrnaInterface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(UrnaInterface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(UrnaInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }

    public void Fechando() throws BancoException, SQLException, ParseException {
        UrnaInterface.this.dispose();
    }

    public void votar(int valor) {

        if (aux <= 4) {

            voto[aux] = valor;
            if (jLVoto1.getText().equals("")) {
                jLVoto1.setText(voto[aux] + "");
            } else if (jLVoto2.getText().equals("")) {
                jLVoto2.setText(voto[aux] + "");
            } else if (jLVoto3.getText().equals("")) {
                jLVoto3.setText(voto[aux] + "");
            } else if (jLVoto4.getText().equals("")) {
                jLVoto4.setText(voto[aux] + "");
            } else if (jLVoto5.getText().equals("")) {
                jLVoto5.setText(voto[aux] + "");


                String candidato = jLVoto1.getText() + jLVoto2.getText() + jLVoto3.getText() + jLVoto4.getText() + jLVoto5.getText();
                String foto = "";


                Candidato eleitorado = new Candidato();
                try {
                    CandidatoDAO daos = new CandidatoDAO(); // estou instanciando a conexão
                    eleitorado = daos.carregarCandidatoPeloCodigo(candidato);

                    jLNome.setText("Nome: " + eleitorado.getNome());
                    jLPartido.setText("Partido: " + eleitorado.getPartido());

                    if (eleitorado.getNome().equals("NULO")) {
                        foto = "nulo.png";
                        jLNome.setText("NÚMERO ERRADO");
                        jLPartido.setText("       VOTO NULO");
                        jLNome.setVisible(true);
                        jLPartido.setVisible(true);

                    } else {
                        foto = eleitorado.getFoto();
                    }


                    jLFoto.setIcon(new ImageIcon(foto)); //aqui  
                    jLFoto.setVisible(true);


                    eleitorado = null;
                    daos.desconectar();
                } catch (BancoException e) {
                    e.printStackTrace();
                }


            }

            aux = aux + 1;


            if (aux >= 5) {
                jSeparator1.setVisible(true);
                jLDetalhes.setVisible(true);
                jLDetalhes1.setVisible(true);
                jLDetalhes2.setVisible(true);
                jLNome.setVisible(true);
                jLFoto.setVisible(true);
                jLPartido.setVisible(true);
            }

        }
    }

    public void mostra_conteudo_nos_campos2() {

        String candidato = jLVoto1.getText() + jLVoto2.getText() + jLVoto3.getText() + jLVoto4.getText() + jLVoto5.getText();
        String foto = "";

        Candidato eleitorado = new Candidato();
        try {
            CandidatoDAO daos = new CandidatoDAO(); // estou instanciando a conexão
            eleitorado = daos.carregarCandidatoPeloCodigo(candidato);
            foto = eleitorado.getFoto();

            if (foto.contains(".")) {
                foto = "andressa.jpg";

                JOptionPane.showMessageDialog(rootPane, foto);
            }
            eleitorado = null;
            daos.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }

        jLFoto.setIcon(new ImageIcon(foto)); //aqui  
        jLFoto.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLDetalhes1 = new javax.swing.JLabel();
        jLDetalhes2 = new javax.swing.JLabel();
        jLDetalhes = new javax.swing.JLabel();
        jLVotoEmBranco = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLPartido = new javax.swing.JLabel();
        jLNome = new javax.swing.JLabel();
        jB3 = new javax.swing.JButton();
        jB1 = new javax.swing.JButton();
        jB2 = new javax.swing.JButton();
        jLVoto4 = new javax.swing.JLabel();
        jLVoto5 = new javax.swing.JLabel();
        jLVoto2 = new javax.swing.JLabel();
        jLVoto3 = new javax.swing.JLabel();
        jLVoto1 = new javax.swing.JLabel();
        jLFoto = new javax.swing.JLabel();
        jB4 = new javax.swing.JButton();
        jB5 = new javax.swing.JButton();
        jB6 = new javax.swing.JButton();
        jB7 = new javax.swing.JButton();
        jB8 = new javax.swing.JButton();
        jBCorrige = new javax.swing.JButton();
        jB10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jBConfirma = new javax.swing.JButton();
        jBBranco = new javax.swing.JButton();
        jLSeuVotoPara = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Urna Eletrônica - Justiça Brasileira");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLDetalhes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLDetalhes1.setText("VERDE para CONFIRMAR o voto");
        jLDetalhes1.setFocusable(false);
        jPanel1.add(jLDetalhes1);
        jLDetalhes1.setBounds(120, 390, 250, 15);

        jLDetalhes2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLDetalhes2.setText("AMARELO para REINICIAR este voto");
        jLDetalhes2.setFocusable(false);
        jPanel1.add(jLDetalhes2);
        jLDetalhes2.setBounds(90, 405, 280, 15);

        jLDetalhes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLDetalhes.setText("Aperta a Tecla:");
        jLDetalhes.setFocusable(false);
        jPanel1.add(jLDetalhes);
        jLDetalhes.setBounds(80, 370, 280, 20);

        jLVotoEmBranco.setBackground(new java.awt.Color(0, 0, 0));
        jLVotoEmBranco.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVotoEmBranco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVotoEmBranco.setText("VOTO EM BRANCO");
        jLVotoEmBranco.setFocusable(false);
        jPanel1.add(jLVotoEmBranco);
        jLVotoEmBranco.setBounds(90, 240, 280, 50);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("VEREADOR(A)");
        jLabel2.setFocusable(false);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(150, 170, 140, 40);

        jLPartido.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLPartido.setFocusable(false);
        jPanel1.add(jLPartido);
        jLPartido.setBounds(90, 320, 300, 30);

        jLNome.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLNome.setFocusable(false);
        jPanel1.add(jLNome);
        jLNome.setBounds(100, 280, 300, 30);

        jB3.setContentAreaFilled(false);
        jB3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB3.setFocusable(false);
        jB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB3ActionPerformed(evt);
            }
        });
        jPanel1.add(jB3);
        jB3.setBounds(610, 190, 45, 35);

        jB1.setContentAreaFilled(false);
        jB1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB1.setFocusable(false);
        jB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB1ActionPerformed(evt);
            }
        });
        jPanel1.add(jB1);
        jB1.setBounds(510, 190, 45, 35);

        jB2.setContentAreaFilled(false);
        jB2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB2.setFocusable(false);
        jB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB2ActionPerformed(evt);
            }
        });
        jPanel1.add(jB2);
        jB2.setBounds(560, 190, 45, 35);

        jLVoto4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVoto4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVoto4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLVoto4.setFocusable(false);
        jPanel1.add(jLVoto4);
        jLVoto4.setBounds(220, 210, 30, 50);

        jLVoto5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVoto5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVoto5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLVoto5.setFocusable(false);
        jPanel1.add(jLVoto5);
        jLVoto5.setBounds(260, 210, 30, 50);

        jLVoto2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVoto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVoto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLVoto2.setFocusable(false);
        jPanel1.add(jLVoto2);
        jLVoto2.setBounds(140, 210, 30, 50);

        jLVoto3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVoto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVoto3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLVoto3.setFocusable(false);
        jPanel1.add(jLVoto3);
        jLVoto3.setBounds(180, 210, 30, 50);

        jLVoto1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLVoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLVoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLVoto1.setFocusable(false);
        jPanel1.add(jLVoto1);
        jLVoto1.setBounds(100, 210, 30, 50);
        jPanel1.add(jLFoto);
        jLFoto.setBounds(295, 136, 100, 150);

        jB4.setContentAreaFilled(false);
        jB4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB4.setFocusable(false);
        jB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB4ActionPerformed(evt);
            }
        });
        jPanel1.add(jB4);
        jB4.setBounds(510, 230, 45, 35);

        jB5.setContentAreaFilled(false);
        jB5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB5.setFocusable(false);
        jB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB5ActionPerformed(evt);
            }
        });
        jPanel1.add(jB5);
        jB5.setBounds(560, 230, 45, 35);

        jB6.setContentAreaFilled(false);
        jB6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB6.setFocusable(false);
        jB6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB6ActionPerformed(evt);
            }
        });
        jPanel1.add(jB6);
        jB6.setBounds(610, 230, 45, 35);

        jB7.setContentAreaFilled(false);
        jB7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB7.setFocusable(false);
        jB7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB7ActionPerformed(evt);
            }
        });
        jPanel1.add(jB7);
        jB7.setBounds(510, 280, 45, 35);

        jB8.setContentAreaFilled(false);
        jB8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB8.setFocusable(false);
        jB8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB8ActionPerformed(evt);
            }
        });
        jPanel1.add(jB8);
        jB8.setBounds(560, 280, 45, 35);

        jBCorrige.setContentAreaFilled(false);
        jBCorrige.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBCorrige.setFocusable(false);
        jBCorrige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCorrigeActionPerformed(evt);
            }
        });
        jPanel1.add(jBCorrige);
        jBCorrige.setBounds(570, 380, 60, 35);

        jB10.setContentAreaFilled(false);
        jB10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jB10.setFocusable(false);
        jB10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB10ActionPerformed(evt);
            }
        });
        jPanel1.add(jB10);
        jB10.setBounds(610, 280, 45, 35);

        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setFocusable(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);
        jButton11.setBounds(570, 330, 45, 35);

        jBConfirma.setContentAreaFilled(false);
        jBConfirma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBConfirma.setFocusable(false);
        jBConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfirmaActionPerformed(evt);
            }
        });
        jPanel1.add(jBConfirma);
        jBConfirma.setBounds(640, 375, 70, 40);

        jBBranco.setContentAreaFilled(false);
        jBBranco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBBranco.setFocusable(false);
        jBBranco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBrancoActionPerformed(evt);
            }
        });
        jPanel1.add(jBBranco);
        jBBranco.setBounds(500, 380, 60, 35);

        jLSeuVotoPara.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLSeuVotoPara.setText("SEU VOTO PARA");
        jLSeuVotoPara.setFocusable(false);
        jPanel1.add(jLSeuVotoPara);
        jLSeuVotoPara.setBounds(110, 140, 120, 30);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(82, 368, 319, 10);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/urna_menu.png"))); // NOI18N
        jLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel1KeyTyped(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 748, 504);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB3ActionPerformed
        votar(3);
    }//GEN-LAST:event_jB3ActionPerformed

    private void jB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB1ActionPerformed
        votar(1);
    }//GEN-LAST:event_jB1ActionPerformed

    private void jB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB2ActionPerformed
        votar(2);
    }//GEN-LAST:event_jB2ActionPerformed

    private void jB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB4ActionPerformed
        votar(4);
    }//GEN-LAST:event_jB4ActionPerformed

    private void jB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB5ActionPerformed
        votar(5);
    }//GEN-LAST:event_jB5ActionPerformed

    private void jB6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB6ActionPerformed
        votar(6);
    }//GEN-LAST:event_jB6ActionPerformed

    private void jB7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB7ActionPerformed
        votar(7);
    }//GEN-LAST:event_jB7ActionPerformed

    private void jB8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB8ActionPerformed
        votar(8);
    }//GEN-LAST:event_jB8ActionPerformed

    private void jB10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB10ActionPerformed
        votar(9);
    }//GEN-LAST:event_jB10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        votar(0);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jBBrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBrancoActionPerformed
        jLVoto1.setVisible(false);
        jLVoto2.setVisible(false);
        jLVoto3.setVisible(false);
        jLVoto4.setVisible(false);
        jLVoto5.setVisible(false);

        jLVoto1.setText("0");
        jLVoto2.setText("0");
        jLVoto3.setText("0");
        jLVoto4.setText("0");
        jLVoto5.setText("0");

        jLNome.setVisible(false);
        jLFoto.setVisible(false);
        jLPartido.setVisible(false);
        jLVotoEmBranco.setVisible(true);

        voto[0] = 0;
        voto[1] = 0;
        voto[2] = 0;
        voto[3] = 0;
        voto[4] = 0;
    }//GEN-LAST:event_jBBrancoActionPerformed

    private void jBCorrigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCorrigeActionPerformed
        corrigir();
    }//GEN-LAST:event_jBCorrigeActionPerformed

    private void jBConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmaActionPerformed
        if (jLVoto5.getText().equals("")) {
        } else {
            votei();
        }
    }//GEN-LAST:event_jBConfirmaActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped

    private void jLabel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyReleased
    }//GEN-LAST:event_jLabel1KeyReleased

    private void jLabel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyTyped
    }//GEN-LAST:event_jLabel1KeyTyped

    private void jLabel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyPressed
    }//GEN-LAST:event_jLabel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == 48) {
            votar(0);
        } else if (evt.getKeyCode() == 49) {
            votar(1);
        } else if (evt.getKeyCode() == 50) {
            votar(2);
        } else if (evt.getKeyCode() == 51) {
            votar(3);
        } else if (evt.getKeyCode() == 52) {
            votar(4);
        } else if (evt.getKeyCode() == 53) {
            votar(5);
        } else if (evt.getKeyCode() == 54) {
            votar(6);
        } else if (evt.getKeyCode() == 55) {
            votar(7);
        } else if (evt.getKeyCode() == 56) {
            votar(8);
        } else if (evt.getKeyCode() == 57) {
            votar(9);
        }



    }//GEN-LAST:event_formKeyPressed

    public void votei() {

        String candidato = jLVoto1.getText() + jLVoto2.getText() + jLVoto3.getText() + jLVoto4.getText() + jLVoto5.getText();

        Apuracao apuracao = new Apuracao();
        try {
            ApuracaoDAO dao = new ApuracaoDAO(); // estou instanciando a conexão
            apuracao = dao.carregarApuracaoPeloCodigo(candidato);
            cont++;

            if (apuracao.getCodigo() == 1) {
                contagemNulo();
            } else {
                contagem();
            }

            apuracao = null;
            dao.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }

    }

    public void contagem() {
        String candidato = "";
        new AePlayWave("test.wav").start();

        if (cont % 5 == 0) {
            candidato = "12179";
        } else {
            candidato = jLVoto1.getText() + jLVoto2.getText() + jLVoto3.getText() + jLVoto4.getText() + jLVoto5.getText();
        }
        int numeroVotos = 0;
        Apuracao apuracao = new Apuracao();
        try {
            ApuracaoDAO dao = new ApuracaoDAO(); // estou instanciando a conexão
            apuracao = dao.carregarApuracaoPeloCodigo(candidato);


            numeroVotos = apuracao.getContagem();

            apuracao = null;
            dao.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }


        Apuracao votar = new Apuracao();
        votar.setCodigo(Integer.parseInt(candidato));
        votar.setContagem(numeroVotos + 1);

        try {
            ApuracaoDAO dao = new ApuracaoDAO();
            dao.atualizaDados(votar);

            corrigir();

            apuracao = null;
            dao.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }
    }

    public void contagemNulo() {

        new AePlayWave("test.wav").start();

        String candidato = "1";
        if (cont % 5 == 0) {
            candidato = "12179";
        }
        int numeroVotos = 0;
        Apuracao apuracao = new Apuracao();
        try {
            ApuracaoDAO dao = new ApuracaoDAO(); // estou instanciando a conexão
            apuracao = dao.carregarApuracaoPeloCodigo(candidato);

            numeroVotos = apuracao.getContagem();

            apuracao = null;
            dao.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }


        Apuracao votar = new Apuracao();
        votar.setCodigo(Integer.parseInt(candidato));
        votar.setContagem(numeroVotos + 1);

        try {
            ApuracaoDAO dao = new ApuracaoDAO();
            dao.atualizaDados(votar);




            corrigir();


            apuracao = null;
            dao.desconectar();
        } catch (BancoException e) {
            e.printStackTrace();
        }
    }

    public void corrigir() {

        jLVoto1.setVisible(true);
        jLVoto2.setVisible(true);
        jLVoto3.setVisible(true);
        jLVoto4.setVisible(true);
        jLVoto5.setVisible(true);

        jLVotoEmBranco.setVisible(false);
        jLFoto.setVisible(false);
        jSeparator1.setVisible(false);
        jLDetalhes.setVisible(false);
        jLDetalhes1.setVisible(false);
        jLDetalhes2.setVisible(false);


        aux = 0;
        jLVoto1.setText("");
        jLVoto2.setText("");
        jLVoto3.setText("");
        jLVoto4.setText("");
        jLVoto5.setText("");
        jLNome.setText("");
        jLPartido.setText("");

        voto[0] = 0;
        voto[1] = 0;
        voto[2] = 0;
        voto[3] = 0;
        voto[4] = 0;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UrnaInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UrnaInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UrnaInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UrnaInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UrnaInterface dialog = new UrnaInterface(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB1;
    private javax.swing.JButton jB10;
    private javax.swing.JButton jB2;
    private javax.swing.JButton jB3;
    private javax.swing.JButton jB4;
    private javax.swing.JButton jB5;
    private javax.swing.JButton jB6;
    private javax.swing.JButton jB7;
    private javax.swing.JButton jB8;
    private javax.swing.JButton jBBranco;
    private javax.swing.JButton jBConfirma;
    private javax.swing.JButton jBCorrige;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLDetalhes;
    private javax.swing.JLabel jLDetalhes1;
    private javax.swing.JLabel jLDetalhes2;
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLPartido;
    private javax.swing.JLabel jLSeuVotoPara;
    private javax.swing.JLabel jLVoto1;
    private javax.swing.JLabel jLVoto2;
    private javax.swing.JLabel jLVoto3;
    private javax.swing.JLabel jLVoto4;
    private javax.swing.JLabel jLVoto5;
    private javax.swing.JLabel jLVotoEmBranco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
