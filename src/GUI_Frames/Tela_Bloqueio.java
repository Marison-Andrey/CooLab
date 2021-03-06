package GUI_Frames;

//@author Márison Tamiarana

import Conexao.Conecta_Banco;
import Conexao.Controle_Log;
import Conexao.Controle_Usuario;
import GUI_Dialogs_Principal.Inf_Senha_Invalida_Bloqueio;
import GUI_Dialogs_Principal.Logoff_Bloqueio;
import GUI_Dialogs_Principal.Logout_User_Bloqueio;
import static GUI_Frames.Tela_Principal.CodLogado;
import Metodos.ComboBoxRenderer;
import Metodos.Formatacao;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;



public class Tela_Bloqueio extends javax.swing.JDialog {

    Conecta_Banco ObjConecta = new Conecta_Banco();
    Controle_Usuario ObjControlUser = new Controle_Usuario();
    Formatacao ObjFormat = new Formatacao();
    private static Logoff_Bloqueio ObjLogoff;
    private static Tela_Principal TP;
    private static Inf_Senha_Invalida_Bloqueio ObjSenhaInvalida;
    private static Logout_User_Bloqueio ObjLogout;
    ComboBoxRenderer renderer = new ComboBoxRenderer();
    
        
    public Tela_Bloqueio(Tela_Principal parent, boolean modal) {
        this.TP = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(TP);
        setResizable(false);
        setSize(490,240);
        Setar_Atalho_BT();
        JPF_LG_Senha.setDocument(ObjFormat.new Format_Geral(50));
        
        
//        JCB_Encerrar.setRenderer(renderer);
//        //JCB_Encerrar.setMaximumRowCount(2);
//        ImageIcon img = new ImageIcon(getClass().getResource("/Icones_Gerais/power-button 75X75.png")); 
//        ImageIcon img2 = new ImageIcon(getClass().getResource("/Icones_Gerais/Log Out_75X75.png")); 
//        
//        JCB_Encerrar.addItem(img);
//        JCB_Encerrar.addItem(img2);
        
        
        
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BT_Ok = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JPF_LG_Senha = new javax.swing.JPasswordField();
        BT_Encerrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bloqueio De Sistema");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        BT_Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Ok.png"))); // NOI18N
        BT_Ok.setMnemonic('o');
        BT_Ok.setToolTipText("Clique Para Confirma A Senha Ou Pressione Alt + O");
        BT_Ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Ok Press.png"))); // NOI18N
        BT_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_OkActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Cadeado - Login.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Sistema Bloqueado!");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Senha*:");

        JPF_LG_Senha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        BT_Encerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Sair 24X24.png"))); // NOI18N
        BT_Encerrar.setToolTipText("Encerrar O Sistema");
        BT_Encerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EncerrarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Logout Usuario 24x24.png"))); // NOI18N
        jButton2.setToolTipText("Logoff De Usuário");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Insira a senha do usuário que está logado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BT_Ok)
                                .addGap(18, 18, 18)
                                .addComponent(BT_Encerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(JPF_LG_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(JPF_LG_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BT_Ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BT_Encerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(479, 206));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BT_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_OkActionPerformed
             ObjControlUser.Controle_Acesso_Bloqueio(TP.UserLogado, JPF_LG_Senha);
           if(ObjControlUser.ControleAcessoBloqueio==true){
                dispose();
                new Controle_Log().Registrar_Log("desbloqueio do sistema",CodLogado);   
                ObjControlUser.ControleAcessoBloqueio=false;
           }else{
               Mostrar_Senha_Invalida();
           }
       
       
    }//GEN-LAST:event_BT_OkActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Mostrar_Logoff();
    }//GEN-LAST:event_formWindowClosing

    private void BT_EncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_EncerrarActionPerformed
        Mostrar_Logoff();
    }//GEN-LAST:event_BT_EncerrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Mostrar_Logout();
    }//GEN-LAST:event_jButton2ActionPerformed

    void Mostrar_Senha_Invalida(){
        ObjSenhaInvalida = new Inf_Senha_Invalida_Bloqueio(this, true);
        ObjSenhaInvalida.setVisible(true);
    }
    public void Mostrar_Logoff(){
        ObjLogoff = new Logoff_Bloqueio(this, true);
        ObjLogoff.setVisible(true);
    }
    public void Mostrar_Logout(){
        ObjLogout = new Logout_User_Bloqueio(this, true);
        ObjLogout.setVisible(true);
    }
    
     public final void Setar_Atalho_BT(){
        //metodo para pegar a tecla pressionada
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Tecla Esc");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        
        InputMap inputMap2 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"Tecla Enter");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap2);
        //método para executar
        this.getRootPane().getActionMap().put("Tecla Enter", new AbstractAction(){
        private static final long serialVersionUID = 2L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Ok.doClick();
    }
    });
        this.getRootPane().getActionMap().put("Tecla Esc", new AbstractAction(){
        private static final long serialVersionUID = 2L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Encerrar.doClick();
    }
    });
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Bloqueio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela_Bloqueio dialog = new Tela_Bloqueio((Tela_Principal) new javax.swing.JFrame(), true);
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
    
//       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Encerrar;
    private javax.swing.JButton BT_Ok;
    private javax.swing.JPasswordField JPF_LG_Senha;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

   
}

