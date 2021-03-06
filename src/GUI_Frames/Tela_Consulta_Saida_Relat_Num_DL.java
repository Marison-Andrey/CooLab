package GUI_Frames;

import Classes.Modelo_Entrada_Produto;
import Conexao.Conecta_Banco;
import Classes.Modelo_Tabela;
import Conexao.Controle_Entrada_Produto;
import Metodos.Formatacao;
import Metodos.Pintar_Tabela_Padrao;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// @author Márison Tamiarana
 
public class Tela_Consulta_Saida_Relat_Num_DL extends javax.swing.JDialog {

    Conecta_Banco ObjConecta = new Conecta_Banco();
    Conecta_Banco ObjConecta_2 = new Conecta_Banco();
    Tela_Relat_Saida_Num ObjSaidaNum = new Tela_Relat_Saida_Num();
    Tela_Saida_Produto ObjSaida = new Tela_Saida_Produto();
    Formatacao ObjFormat = new Formatacao();
    Controle_Entrada_Produto ObjControleEntrada = new Controle_Entrada_Produto();
    Modelo_Entrada_Produto ObjModEntrada = new Modelo_Entrada_Produto();
       
    int Cod;
    String data;//variavel para receber a data atual para comparar a validade
    
    
    public Tela_Consulta_Saida_Relat_Num_DL(Tela_Relat_Saida_Num parent, boolean modal) {
        this.ObjSaidaNum = parent;
        this.setModal(modal);
        
        initComponents();
        
        setResizable(false);
        setSize(815,530);
        setLocationRelativeTo(ObjSaidaNum);
        JTF_Pesquisa.setDocument(ObjFormat.new Format_Geral(50));
        Preencher_CB_Intervalo_Tempo();
        Preenche_Tabela_Saida(1);
        Contar_Saidas();
        FiltrarNome();
        Setar_Atalho_BT();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTB_Saidas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        JTF_Pesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BT_Limpar = new javax.swing.JButton();
        BT_Sair = new javax.swing.JButton();
        BT_Adicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JL_Saidas = new javax.swing.JLabel();
        JL_Atalhos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JCB_Intervalo_Tempo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Saída");
        setName("Consulta Produto"); // NOI18N

        JTB_Saidas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTB_Saidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTB_Saidas.setToolTipText("Clique Duas Vezes Em Uma Linha Para Adicionar  Ou Selecione Uma Linha E Clique No Botão Adicionar (Alt+O)");
        JTB_Saidas.getTableHeader().setReorderingAllowed(false);
        JTB_Saidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTB_SaidasMouseClicked(evt);
            }
        });
        JTB_Saidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTB_SaidasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTB_Saidas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        JTF_Pesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTF_Pesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTF_PesquisaFocusGained(evt);
            }
        });
        JTF_Pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTF_PesquisaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tipo:");

        BT_Limpar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BT_Limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Remove_24x24.png"))); // NOI18N
        BT_Limpar.setText("(F8)");
        BT_Limpar.setToolTipText("Clique Para Limpar O Campo De Pesquisa De Descrição");
        BT_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_LimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(JTF_Pesquisa)
                .addGap(18, 18, 18)
                .addComponent(BT_Limpar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BT_Limpar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JTF_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BT_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Sair.png"))); // NOI18N
        BT_Sair.setMnemonic('r');
        BT_Sair.setToolTipText("Clique Para Sair Ou Pressione Alt + R");
        BT_Sair.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Sair press.png"))); // NOI18N
        BT_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SairActionPerformed(evt);
            }
        });

        BT_Adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Adicionar.png"))); // NOI18N
        BT_Adicionar.setMnemonic('o');
        BT_Adicionar.setToolTipText("Selecione Uma Linha E Clique Para Adicionar Ou Pressione Alt + O");
        BT_Adicionar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Bt Adicionar Press.png"))); // NOI18N
        BT_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_AdicionarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Saídas:");

        JL_Saidas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        JL_Atalhos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JL_Atalhos.setText("Esc - Sair | F4/Enter - Adicionar | F8 - Limpar");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Intervalo De Tempo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Período:");

        JCB_Intervalo_Tempo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCB_Intervalo_Tempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCB_Intervalo_TempoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(JCB_Intervalo_Tempo, 0, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JCB_Intervalo_Tempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JL_Saidas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JL_Atalhos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(BT_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BT_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BT_Sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Atalhos)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JL_Saidas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(BT_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(435, 435, 435))
        );

        setSize(new java.awt.Dimension(746, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JTB_SaidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTB_SaidasMouseClicked
        if (evt.getClickCount() == 2) {
            Object id = (JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 0));
            dispose();
            ObjSaidaNum.Setar_Campo_Num_Saida(String.valueOf(id));
            
        }

    }//GEN-LAST:event_JTB_SaidasMouseClicked

    private void JTF_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTF_PesquisaActionPerformed
        int quant_itens = JTB_Saidas.getRowCount();
            if(quant_itens>0){
            JTB_Saidas.setRowSelectionInterval(0, 0);
            JTB_Saidas.requestFocus();
        }
       
    }//GEN-LAST:event_JTF_PesquisaActionPerformed

    private void BT_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SairActionPerformed
        dispose();
        //Sair_Sem_Salvar();
    }//GEN-LAST:event_BT_SairActionPerformed

    private void BT_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_AdicionarActionPerformed
        int linha_selecionada = JTB_Saidas.getSelectedRow();
        if (linha_selecionada >= 0)
        {
            Object id = (JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 0));
            dispose();
            ObjSaidaNum.Setar_Campo_Num_Saida(String.valueOf(id));     
        }
        
    }//GEN-LAST:event_BT_AdicionarActionPerformed

    private void BT_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_LimparActionPerformed
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 0){
            JTF_Pesquisa.setText("");
            Preenche_Tabela_Saida(1);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 1){
            JTF_Pesquisa.setText("");
            Preenche_Tabela_Saida(2);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 2){
            JTF_Pesquisa.setText("");
            Preenche_Tabela_Saida(3);
            Contar_Saidas();
        }
    }//GEN-LAST:event_BT_LimparActionPerformed

    private void JTF_PesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTF_PesquisaFocusGained
        PesquisarNome();
    }//GEN-LAST:event_JTF_PesquisaFocusGained

    private void JTB_SaidasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTB_SaidasKeyPressed
        int linha_selecionada = JTB_Saidas.getSelectedRow();
        if (linha_selecionada >= 0)
        {
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                Object id = (JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 0));
                dispose();
                ObjSaidaNum.Setar_Campo_Num_Saida(String.valueOf(id));
            }
        }
         
    }//GEN-LAST:event_JTB_SaidasKeyPressed

    private void JCB_Intervalo_TempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCB_Intervalo_TempoActionPerformed
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 0){
            Preenche_Tabela_Saida(1);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 1){
            Preenche_Tabela_Saida(2);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 2){
            Preenche_Tabela_Saida(3);
            Contar_Saidas();
        }
    }//GEN-LAST:event_JCB_Intervalo_TempoActionPerformed
    
    public final void Setar_Pesquisa(String pesquisa){
        JTF_Pesquisa.setText(pesquisa);
    }
        
    final void Preenche_Tabela_Saida(int mes){
        try {
            ObjControleEntrada.Consulta_Entrada_Periodo(mes);
            if(ObjControleEntrada.Controle_Entrada == true){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, -mes); //diminuir datas - inicio para 30 dias;
                String df = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String di = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                Preencher_Tabela("select * from saida where "
                        + " tipo like '%" + JTF_Pesquisa.getText().toUpperCase() + "%' "
                        + " and data_saida between '"+di+"' and '"+df+"' order by id_saida desc");
                ObjControleEntrada.Controle_Entrada = false;
            }else{
                Limpar_Tabela_Entrada();
                ObjControleEntrada.Controle_Entrada = false;
            }
        } catch (Exception ex) {  
            Limpar_Tabela_Entrada();
            ObjControleEntrada.Controle_Entrada = false;
        }
        
    }
 
    final void Preencher_CB_Intervalo_Tempo(){
        JCB_Intervalo_Tempo.removeAllItems();
        JCB_Intervalo_Tempo.addItem("Últimos 30 Dias");
        JCB_Intervalo_Tempo.addItem("Últimos 60 Dias");
        JCB_Intervalo_Tempo.addItem("Últimos 90 Dias");
}
    
 public void PesquisarNome() {

        JTF_Pesquisa.getDocument().addDocumentListener(new DocumentListener() {
            //ATUALIZA A CADA LETRA REMOVIDA================================================
            @Override
            public void removeUpdate(DocumentEvent e) {
                FiltrarNome();
            }
            //ATUALIZA A CADA LETRA INSERIDA================================================
            @Override
            public void insertUpdate(DocumentEvent e) {
                FiltrarNome();
            }
            //ATUALIZA A CADA LETRA ATUALIZADA OU TROCADA===================================
            @Override
            public void changedUpdate(DocumentEvent e) {
                FiltrarNome();
            }
        });
    }
    
    public final void FiltrarNome() {
          if(JCB_Intervalo_Tempo.getSelectedIndex() == 0){
            Preenche_Tabela_Saida(1);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 1){
            Preenche_Tabela_Saida(2);
            Contar_Saidas();
        }
        if(JCB_Intervalo_Tempo.getSelectedIndex() == 2){
            Preenche_Tabela_Saida(3);
            Contar_Saidas();
        }
    }
    
    final void Contar_Saidas(){
        JL_Saidas.setText(String.valueOf(JTB_Saidas.getRowCount()));
    }
 
  
 public final void Preencher_Tabela(String SQL) {
        ArrayList dados = new ArrayList();

        String[] Colunas = new String[]{"Nº Saída", "Data Saída", "Descrição","Turma","Situação"};//Seta os indices da tabela
        ObjConecta.Conectar();
        ObjConecta_2.Conectar();
        ObjConecta.ExecutaSQL(SQL);
        try {
            ObjConecta.rs.first();           
            do {                
                String data_Entrada = String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(ObjConecta.rs.getDate("data_saida")));
                
                ObjConecta_2.ExecutaSQL("select concat(semestre,abrev_curso,turno,ano_turma,'.',semestre_vestibular,' ',turma) as turmas "
                                + "from curso inner join turma on curso.id_curso = turma.curso_id_curso inner join saida on id_turma = saida.turma_id_turma "
                                + "where saida.id_saida="+ObjConecta.rs.getInt("id_saida")+"");
                ObjConecta_2.rs.first();
                String Turma = ObjConecta_2.rs.getString("turmas");
                        
                dados.add(new Object[]{ObjConecta.rs.getInt("id_saida"), data_Entrada, ObjConecta.rs.getString("tipo"),
                    Turma, ObjConecta.rs.getString("situacao") });
            
            } while (ObjConecta.rs.next());
            
            ObjConecta.Desconecta();
            ObjConecta_2.Desconecta();
        } catch (SQLException ex) {
            ObjConecta.Desconecta();
            ObjConecta_2.Desconecta();
        }
        Modelo_Tabela tabela = new Modelo_Tabela(dados, Colunas);
        JTB_Saidas.setModel(tabela);
        JTB_Saidas.setDefaultRenderer(Object.class, new Pintar_Tabela_Padrao());
        JTB_Saidas.getColumnModel().getColumn(0).setPreferredWidth(100);//Tamanho da coluna
        JTB_Saidas.getColumnModel().getColumn(0).setResizable(false);//Redimensionavel        
        JTB_Saidas.getColumnModel().getColumn(1).setPreferredWidth(100);
        JTB_Saidas.getColumnModel().getColumn(1).setResizable(false);
        JTB_Saidas.getColumnModel().getColumn(2).setPreferredWidth(380);
        JTB_Saidas.getColumnModel().getColumn(2).setResizable(false);
        JTB_Saidas.getColumnModel().getColumn(3).setPreferredWidth(120);
        JTB_Saidas.getColumnModel().getColumn(3).setResizable(false);
        JTB_Saidas.getColumnModel().getColumn(4).setPreferredWidth(120);
        JTB_Saidas.getColumnModel().getColumn(4).setResizable(false);
        JTB_Saidas.getTableHeader().setReorderingAllowed(false);//Reordenar alocação
        JTB_Saidas.setAutoResizeMode(JTB_Saidas.AUTO_RESIZE_ALL_COLUMNS);//Tabela Redimensionavel(Todas colunas)
        JTB_Saidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Seleciona uma unica linha da tabela
        
    }
 public final void Limpar_Tabela_Entrada() {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{};//Seta os indices da tabela
        Modelo_Tabela tabela = new Modelo_Tabela(dados, Colunas);
        JTB_Saidas.setModel(tabela);
    }
 

 public final void Setar_Atalho_BT(){
        //metodo para pegar a tecla pressionada
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Esc");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        
        InputMap inputMap2 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"Adicionar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap2);
        
        InputMap inputMap3 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0),"Limpar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap3);        
                      
        //método para executar
         this.getRootPane().getActionMap().put("Limpar", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Limpar.doClick();
        JTF_Pesquisa.requestFocus();
        }
        });
        this.getRootPane().getActionMap().put("Adicionar", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Adicionar.doClick();
        }
        });
        this.getRootPane().getActionMap().put("Esc", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Sair.doClick();
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
            java.util.logging.Logger.getLogger(Tela_Consulta_Saida_Relat_Num_DL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela_Consulta_Saida_Relat_Num_DL dialog = new Tela_Consulta_Saida_Relat_Num_DL(new Tela_Relat_Saida_Num(), true);
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
    private javax.swing.JButton BT_Adicionar;
    private javax.swing.JButton BT_Limpar;
    private javax.swing.JButton BT_Sair;
    private javax.swing.JComboBox JCB_Intervalo_Tempo;
    private javax.swing.JLabel JL_Atalhos;
    private javax.swing.JLabel JL_Saidas;
    private javax.swing.JTable JTB_Saidas;
    private javax.swing.JTextField JTF_Pesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
