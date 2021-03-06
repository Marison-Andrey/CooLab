package GUI_Frames;

// @author Márison Tamiarana

import Conexao.Controle_Log;
import Conexao.Controle_Relatorio_Saidas;
import Conexao.Controle_Saida_Produto;
import GUI_Dialogs_Relatorios.Inf_Preencher_N_Saida_Relat_Saida;
import GUI_Dialogs_Relatorios.Inf_Saida_Nao_Encontrada_Relat_Saida_Num;
import static GUI_Frames.Tela_Principal.CodLogado;
import Metodos.Formatacao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

 
public class Tela_Relat_Saida_Num extends javax.swing.JInternalFrame {
    
    public static Tela_Relat_Saida_Num Obj;
    
    public void Open_Tela(){
        if(Obj==null){
            Obj = new Tela_Relat_Saida_Num();
            Tela_Principal.getPainel().add(Obj);
            Obj.setVisible(true);
            Obj.setPosicao();
        }
        try {  
            if(Obj.isIcon()) // se for um icon    
               Obj.setIcon(false); // tira desse estado    
            else // senão (não está iconizada)    
               Obj.toFront(); // trás para frente das outras    
         } catch (PropertyVetoException e) {}  
    }
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }  
    
    private static Tela_Principal TP;
    private static Inf_Preencher_N_Saida_Relat_Saida ObjPreencherNumSaida;
    private static Tela_Consulta_Saida_Relat_Num_DL ObjConsultaSaida;
    private static Inf_Saida_Nao_Encontrada_Relat_Saida_Num DLSaidaNaoEncontrada;
    
    Controle_Relatorio_Saidas ObjRelatSaida = new Controle_Relatorio_Saidas();
    Controle_Saida_Produto ObjControleSaida = new Controle_Saida_Produto();
    Formatacao ObjFormat = new Formatacao();

    public Tela_Relat_Saida_Num() {
        initComponents();
        JTF_Num_Saida.setDocument(ObjFormat.new Format_Apenas_Numero(10));
        Setar_Atalho_BT();  
        JTF_Num_Saida.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JTF_Num_Saida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BT_Pesquisar = new javax.swing.JButton();
        JL_Info = new javax.swing.JLabel();
        BT_Relatorio = new javax.swing.JButton();
        BT_Sair = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        setIconifiable(true);
        setTitle("17 - Número Da Saída");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Relatorio - Entrada 24x24.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Tipo De Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        JTF_Num_Saida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTF_Num_Saida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTF_Num_SaidaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nº Saída*:");

        BT_Pesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BT_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Pesquisar.png"))); // NOI18N
        BT_Pesquisar.setMnemonic('p');
        BT_Pesquisar.setToolTipText("Clique Para Pesquisar Ou Pressione Alt + P");
        BT_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_PesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JTF_Num_Saida)
                .addGap(18, 18, 18)
                .addComponent(BT_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BT_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(JTF_Num_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JL_Info.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JL_Info.setText("Esc - Sair | F2 - Consultar | F3 - Pesquisar");

        BT_Relatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Consultar.png"))); // NOI18N
        BT_Relatorio.setMnemonic('n');
        BT_Relatorio.setToolTipText("Clique Para Consultar Ou Pressione Alt + N");
        BT_Relatorio.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Consultar Press.png"))); // NOI18N
        BT_Relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_RelatorioActionPerformed(evt);
            }
        });

        BT_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Sair.png"))); // NOI18N
        BT_Sair.setMnemonic('r');
        BT_Sair.setToolTipText("Clique Para Sair Ou Pressione Alt + R");
        BT_Sair.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Sair press.png"))); // NOI18N
        BT_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Info)
                        .addGap(0, 88, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BT_Relatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BT_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_Info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BT_Relatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_RelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_RelatorioActionPerformed
        Testar_Campos();
    }//GEN-LAST:event_BT_RelatorioActionPerformed

    private void BT_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SairActionPerformed
        dispose();
    }//GEN-LAST:event_BT_SairActionPerformed

    private void JTF_Num_SaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTF_Num_SaidaActionPerformed
        Testar_Campos();
    }//GEN-LAST:event_JTF_Num_SaidaActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        Obj = null;
    }//GEN-LAST:event_formInternalFrameClosed

    private void BT_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_PesquisarActionPerformed
        Mostrar_Consulta_Saida();

    }//GEN-LAST:event_BT_PesquisarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        JTF_Num_Saida.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

     public void Testar_Campos(){
        if(JTF_Num_Saida.getText().equalsIgnoreCase("")){
            Mostrar_Preencher_N_Saida();
        }else{
            ObjControleSaida.Consulta_Saida_Id(Integer.parseInt(JTF_Num_Saida.getText()));//verifica se exite a saida
            if(ObjControleSaida.Controle_Saida == true){
                ObjRelatSaida.Relatorio_Saida_Num(JTF_Num_Saida.getText().trim());
                new Controle_Log().Registrar_Log("Gerou o relatório de saída por numero id: "+JTF_Num_Saida.getText(), CodLogado);
                ObjControleSaida.Controle_Saida = false;
            }else{
                Mostrar_Entrada_Nao_Encontrada();
                JTF_Num_Saida.setText("");
            }
        }
    }
     
      public void Mostrar_Preencher_N_Saida(){
        ObjPreencherNumSaida = new Inf_Preencher_N_Saida_Relat_Saida(this, true);
        ObjPreencherNumSaida.setVisible(true);
    }
      void Mostrar_Consulta_Saida(){
          ObjConsultaSaida = new Tela_Consulta_Saida_Relat_Num_DL(this, true);
          ObjConsultaSaida.setVisible(true);
      }
      void Mostrar_Entrada_Nao_Encontrada(){
          DLSaidaNaoEncontrada = new Inf_Saida_Nao_Encontrada_Relat_Saida_Num(this, true);
          DLSaidaNaoEncontrada.setVisible(true);
      }
      public void Setar_Campo_Num_Saida(String num_saida){
          JTF_Num_Saida.setText(num_saida);
      }
      
    public final void Setar_Atalho_BT(){
        //metodo para pegar a tecla pressionada
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Esc");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        
        //metodo para pegar a tecla pressionada
        InputMap inputMap2 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),"Procurar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap2);
        
        InputMap inputMap3 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),"Relatorio");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap3);
        
        this.getRootPane().getActionMap().put("Esc", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Sair.doClick();
        }
        });
        
        this.getRootPane().getActionMap().put("Relatorio", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Relatorio.doClick();
        }
        });
        
        this.getRootPane().getActionMap().put("Procurar", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Pesquisar.doClick();
        }
        });
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Pesquisar;
    private javax.swing.JButton BT_Relatorio;
    private javax.swing.JButton BT_Sair;
    private javax.swing.JLabel JL_Info;
    private javax.swing.JTextField JTF_Num_Saida;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
