package GUI_Frames;

// @author Márison Tamiarana

import Classes.Modelo_Tabela;
import Conexao.Conecta_Banco;
import Conexao.Controle_Saida_Outra;
import Conexao.Controle_Saida_Produto;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Data_Final_Inferior_Cons_Saida_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Nao_Existe_Saida_Aberto_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Nao_Existe_Saida_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Preencher_Datas_Cons_Saida_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Preencher_N_Saida_Cons_Saida_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Saida_Nao_Encontrada_Dev_Outra;
import GUI_Dialogs_Consulta_Ent_Saida.Inf_Selecione_Linha_Saida_Dev_Outra;

import Metodos.Pintar_Tabela_Padrao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;


public class Tela_Consulta_Saida_Devolucao_Outra_DL extends javax.swing.JDialog {
    
    Tela_Gerar_Devolucao_Outra ObjDevolucao = new Tela_Gerar_Devolucao_Outra();
    Conecta_Banco ObjConecta = new Conecta_Banco();
    Conecta_Banco ObjConecta_2 = new Conecta_Banco();
    Controle_Saida_Produto ObjControlSaida = new Controle_Saida_Produto();
    Controle_Saida_Outra ObjControlSaidaOutra = new Controle_Saida_Outra();
    
    //Instancia das dialogs
    private static Inf_Data_Final_Inferior_Cons_Saida_Dev_Outra ObjDataInferior;
    private static Inf_Preencher_N_Saida_Cons_Saida_Dev_Outra ObjPreencherSaida;
    private static Inf_Preencher_Datas_Cons_Saida_Dev_Outra ObjPreencherDatas;
    private static Inf_Saida_Nao_Encontrada_Dev_Outra ObjSaidaNaoEncontrada;
    private static Inf_Nao_Existe_Saida_Dev_Outra ObjNaoExisteSaida;
    private static Inf_Nao_Existe_Saida_Aberto_Dev_Outra ObjNaoExisteSaidaAberto;
    private static Inf_Selecione_Linha_Saida_Dev_Outra ObjSelecione;
    
    boolean DataMenor=false;

    public Tela_Consulta_Saida_Devolucao_Outra_DL(Tela_Gerar_Devolucao_Outra parent, boolean modal) {
        this.ObjDevolucao = parent;
        this.setModal(modal);
        initComponents();
        setResizable(false);
        setSize(740,530);
        setLocationRelativeTo(ObjDevolucao);
        Preencher_CB_Pesquisa();   
        BT_Adicionar.setEnabled(false);
        Setar_Atalho_BT();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JD_Inicial = new com.toedter.calendar.JDateChooser();
        JD_Final = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JCB_Tipo_Pesquisa = new javax.swing.JComboBox();
        BT_Consultar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTB_Saidas = new javax.swing.JTable();
        BT_Sair = new javax.swing.JButton();
        BT_Adicionar = new javax.swing.JButton();
        JL_Quant_Itens1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Saída - Outras Saídas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Tipo De Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        JD_Inicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        JD_Final.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("De:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Até:");

        JCB_Tipo_Pesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCB_Tipo_Pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCB_Tipo_PesquisaActionPerformed(evt);
            }
        });

        BT_Consultar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BT_Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones_Gerais/Search_24x24.png"))); // NOI18N
        BT_Consultar.setText("Consultar (F3)");
        BT_Consultar.setToolTipText("Clique Para Consultar A Saída");
        BT_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCB_Tipo_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JD_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JD_Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BT_Consultar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BT_Consultar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JCB_Tipo_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JD_Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JD_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTB_Saidas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTB_Saidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTB_Saidas.setToolTipText("Selecione Uma Linha E Clique No Botão Adicionar ou Clique Duas Vezes Para Mais Informações");
        JTB_Saidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTB_SaidasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTB_Saidas);

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

        JL_Quant_Itens1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JL_Quant_Itens1.setText("Esc - Sair | F3 - Consultar | F4 - Adicionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JL_Quant_Itens1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BT_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BT_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BT_Sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BT_Adicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(JL_Quant_Itens1))
                .addContainerGap())
        );

        setBounds(0, 0, 747, 539);
    }// </editor-fold>//GEN-END:initComponents

    private void JCB_Tipo_PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCB_Tipo_PesquisaActionPerformed
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==0){
            BT_Consultar.setEnabled(false);
            JD_Final.setEnabled(false);
            JD_Inicial.setEnabled(false);
            JD_Final.setDate(null);
            JD_Inicial.setDate(null);
            Limpar_Tabela_Saida();
            BT_Adicionar.setEnabled(false);
        }
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==1){
            BT_Consultar.setEnabled(!false);
            JD_Final.setEnabled(false);
            JD_Inicial.setEnabled(false);
            JD_Final.setDate(null);
            JD_Inicial.setDate(null);
            Limpar_Tabela_Saida();
            BT_Adicionar.setEnabled(false);
        }
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==2){
            BT_Consultar.setEnabled(!false);
            JD_Final.setEnabled(false);
            JD_Inicial.setEnabled(false);
            JD_Final.setDate(null);
            JD_Inicial.setDate(null);
            Limpar_Tabela_Saida();
            BT_Adicionar.setEnabled(false);
        }
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==3){
            BT_Consultar.setEnabled(!false);
            JD_Final.setEnabled(!false);
            JD_Inicial.setEnabled(!false);
            JD_Final.setDate(null);
            JD_Inicial.setDate(null);
            Limpar_Tabela_Saida();
            BT_Adicionar.setEnabled(false);
        }
        
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==4){
            BT_Consultar.setEnabled(!false);
            JD_Final.setEnabled(false);
            JD_Inicial.setEnabled(false);
            JD_Final.setDate(null);
            JD_Inicial.setDate(null);
            Limpar_Tabela_Saida();
            BT_Adicionar.setEnabled(false);
        }
    }//GEN-LAST:event_JCB_Tipo_PesquisaActionPerformed

    private void BT_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_ConsultarActionPerformed
        Testar_Campos();
    }//GEN-LAST:event_BT_ConsultarActionPerformed

    private void JTB_SaidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTB_SaidasMouseClicked
        if (evt.getClickCount() == 2) {
            Object Num_Saida = JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 0);
            Object Data = JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 1);
            try {
                ObjConecta.Conectar();
                ObjConecta.ExecutaSQL("select * from saida_outra where id_saida_outra="+Num_Saida+"");
                ObjConecta.rs.first();
                String Tipo = ObjConecta.rs.getString("tipo_outra");
                String Observacao = ObjConecta.rs.getString("observacao");
                String Situacao = ObjConecta.rs.getString("situacao");
                
                JOptionPane.showMessageDialog(rootPane,"Número Da Saída: "+ Num_Saida+"   Data: "+Data+
                    "\nTipo: "+Tipo + "\nSituação: "+Situacao+ "\nObservação: "+Observacao,
                    "Descrição Da Saída",JOptionPane.INFORMATION_MESSAGE);
                ObjConecta.Desconecta();

            }catch (SQLException ex) {
                ObjConecta.Desconecta();
            }
        }
    }//GEN-LAST:event_JTB_SaidasMouseClicked

    private void BT_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SairActionPerformed
        dispose();
    }//GEN-LAST:event_BT_SairActionPerformed

    private void BT_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_AdicionarActionPerformed
        int linha_selecionada = JTB_Saidas.getSelectedRow();
        if (linha_selecionada >= 0)
        {
            Object resultado = (JTB_Saidas.getValueAt(JTB_Saidas.getSelectedRow(), 0));
            ObjDevolucao.Setar_Campo_Id_Saida(resultado);
            ObjDevolucao.Testar_Campos_Pesquisa();
            dispose();            
        }else{
            Mostrar_Selecione_Uma_Linha();
        }
    }//GEN-LAST:event_BT_AdicionarActionPerformed

    public void Testar_Campos(){
    try{
        Limpar_Tabela_Saida();        
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==1){
            ObjControlSaidaOutra.Consulta_Saida_Todas();
                if(ObjControlSaidaOutra.Controle_Saida == true){
                    Preencher_Tabela_Saida("select * from saida_outra order by id_saida_outra desc");
                    BT_Adicionar.setEnabled(!false);
                    ObjControlSaidaOutra.Controle_Saida=false;
                }else{
                    Mostra_Nao_Existe_Saida();
                    Limpar_Tabela_Saida();
                    BT_Adicionar.setEnabled(false);
                    ObjControlSaidaOutra.Controle_Saida=false;
                    
                }
        }

        if(JCB_Tipo_Pesquisa.getSelectedIndex()==2){
            ObjControlSaidaOutra.Consulta_Saida_Todas();
                if(ObjControlSaidaOutra.Controle_Saida == true){
                    try{
                        ObjControlSaidaOutra.Consulta_Saida_Ultimas_30();
                        if(ObjControlSaidaOutra.Controle_Saida == true){
                            Calendar c = Calendar.getInstance();
                            c.add(Calendar.MONTH, -1); //diminuir datas - inicio para 30 dias;
                            String df = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                            String di = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                            Preencher_Tabela_Saida("select * from saida_outra where data_saida_outra between '"+di+"' and '"+df+"' order by id_saida_outra desc");
                            BT_Adicionar.setEnabled(!false);
                            ObjControlSaidaOutra.Controle_Saida=false;
                        }else{
                            Mostrar_Saida_Nao_Encontrada();
                            ObjControlSaidaOutra.Controle_Saida=false;
                        }
                        }catch(Exception  ex){}
                        ObjControlSaidaOutra.Controle_Saida=false;
                }else{
                    Mostra_Nao_Existe_Saida();
                    Limpar_Tabela_Saida();
                    BT_Adicionar.setEnabled(false);
                    ObjControlSaidaOutra.Controle_Saida=false;
                }
        }
        
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==3){
            if(JD_Inicial.getDate()==null || JD_Final.getDate()==null){
                Mostrar_Preencher_Datas();
            }else{
                Verifica_Datas();
                if(DataMenor == true){
                    Mostrar_Data_Inferior();
                    DataMenor = false;
                }else{
                    ObjControlSaidaOutra.Consulta_Saida_Todas();
                    if(ObjControlSaidaOutra.Controle_Saida == true){
                        try{
                            ObjControlSaidaOutra.Consulta_Saida_Intervalo(JD_Inicial, JD_Final);
                            if(ObjControlSaidaOutra.Controle_Saida == true){
                                String di = new SimpleDateFormat("yyyy-MM-dd").format(JD_Inicial.getDate());
                                String df = new SimpleDateFormat("yyyy-MM-dd").format(JD_Final.getDate());
                                Preencher_Tabela_Saida("select * from saida_outra where data_saida_outra between '"+di+"' and '"+df+"' order by id_saida_outra desc");
                                BT_Adicionar.setEnabled(!false);
                                ObjControlSaidaOutra.Controle_Saida=false;
                            }else{
                                Mostrar_Saida_Nao_Encontrada();
                            }
                        }catch(Exception ex){}
                            ObjControlSaidaOutra.Controle_Saida=false;
                    }else{
                        Mostra_Nao_Existe_Saida();
                        Limpar_Tabela_Saida();
                        BT_Adicionar.setEnabled(false);
                    }
                }
            }
        }
                        
        if(JCB_Tipo_Pesquisa.getSelectedIndex()==4){
            ObjControlSaidaOutra.Consulta_Saida_Alterada("ABERTO");
                if(ObjControlSaidaOutra.Controle_Saida == true){
                    Preencher_Tabela_Saida("select * from saida_outra where situacao = 'ABERTO' order by id_saida_outra desc");
                    BT_Adicionar.setEnabled(!false);
                    ObjControlSaidaOutra.Controle_Saida=false;
                }else{
                    Mostra_Nao_Existe_Saida_Aberto();
                    Limpar_Tabela_Saida();
                    BT_Adicionar.setEnabled(false);
                    ObjControlSaidaOutra.Controle_Saida=false;
                    
                }
        }
        }catch(NumberFormatException | HeadlessException ex){}
    }
    
    public void Verifica_Datas(){//verifica se a data inicial é inferior a inicial
        long dt1 = JD_Inicial.getDate().getTime();
        long dt2 = JD_Final.getDate().getTime();

        if (dt2==dt1) {
            DataMenor=!true;
        } else {    
            if (dt2 > dt1) {
                DataMenor=!true;
            } else {
                DataMenor=true;
            }
        }  
    }
    
    public final void Preencher_CB_Pesquisa(){
        JCB_Tipo_Pesquisa.removeAllItems();
        JCB_Tipo_Pesquisa.addItem(" ");
        JCB_Tipo_Pesquisa.addItem("TODOS");
        JCB_Tipo_Pesquisa.addItem("ÚLTIMOS 30 DIAS");
        JCB_Tipo_Pesquisa.addItem("POR PERÍODO");
        JCB_Tipo_Pesquisa.addItem("EM ABERTO");
    }
    
     public final void Limpar_Tabela_Saida() {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{};//Seta os indices da tabela
        Modelo_Tabela tabela = new Modelo_Tabela(dados, Colunas);
        JTB_Saidas.setModel(tabela);
    }
    
    public final void Preencher_Tabela_Saida(String SQL) {
        ArrayList dados = new ArrayList();

        String[] Colunas = new String[]{"Nº Saída", "Data Saída", "Descrição","Situação"};//Seta os indices da tabela
        ObjConecta.Conectar();
        ObjConecta.ExecutaSQL(SQL);
        try {
            ObjConecta.rs.first();           
            do {                
                String data_saida = String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(ObjConecta.rs.getDate("data_saida_outra")));
                                                        
                dados.add(new Object[]{ObjConecta.rs.getInt("id_saida_outra"), data_saida, ObjConecta.rs.getString("tipo_outra"),
                ObjConecta.rs.getString("situacao") });
            
            } while (ObjConecta.rs.next());
            
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            ObjConecta.Desconecta();
        }
        Modelo_Tabela tabela = new Modelo_Tabela(dados, Colunas);
        JTB_Saidas.setModel(tabela);
        JTB_Saidas.setDefaultRenderer(Object.class, new Pintar_Tabela_Padrao());
        JTB_Saidas.getColumnModel().getColumn(0).setPreferredWidth(100);//Tamanho da coluna
        JTB_Saidas.getColumnModel().getColumn(0).setResizable(false);//Redimensionavel        
        JTB_Saidas.getColumnModel().getColumn(1).setPreferredWidth(100);
        JTB_Saidas.getColumnModel().getColumn(1).setResizable(false);
        JTB_Saidas.getColumnModel().getColumn(2).setPreferredWidth(300);
        JTB_Saidas.getColumnModel().getColumn(2).setResizable(false);
        JTB_Saidas.getColumnModel().getColumn(3).setPreferredWidth(150);
        JTB_Saidas.getColumnModel().getColumn(3).setResizable(false);
        JTB_Saidas.getTableHeader().setReorderingAllowed(false);//Reordenar alocação
        JTB_Saidas.setAutoResizeMode(JTB_Saidas.AUTO_RESIZE_ALL_COLUMNS);//Tabela Redimensionavel(Todas colunas)
        JTB_Saidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Seleciona uma unica linha da tabela
    }
    
    public void Mostrar_Preencher_Datas(){
        ObjPreencherDatas = new Inf_Preencher_Datas_Cons_Saida_Dev_Outra(this, true);
        ObjPreencherDatas.setVisible(true);
    }
    public void Mostrar_Data_Inferior(){
        ObjDataInferior = new Inf_Data_Final_Inferior_Cons_Saida_Dev_Outra(this, true);
        ObjDataInferior.setVisible(true);
    }
    public void Mostrar_Preencher_N_Saida(){
        ObjPreencherSaida = new Inf_Preencher_N_Saida_Cons_Saida_Dev_Outra(this, true);
        ObjPreencherSaida.setVisible(true);
    }
    public void Mostrar_Saida_Nao_Encontrada(){
       ObjSaidaNaoEncontrada = new Inf_Saida_Nao_Encontrada_Dev_Outra(this, true);
       ObjSaidaNaoEncontrada.setVisible(true);
    }
    public void Mostra_Nao_Existe_Saida_Aberto(){
       ObjNaoExisteSaidaAberto = new Inf_Nao_Existe_Saida_Aberto_Dev_Outra(this, true);
       ObjNaoExisteSaidaAberto.setVisible(true);
    }
    void Mostra_Nao_Existe_Saida(){
       ObjNaoExisteSaida = new Inf_Nao_Existe_Saida_Dev_Outra(this, true);
       ObjNaoExisteSaida.setVisible(true);
    }
    void Mostrar_Selecione_Uma_Linha(){
        ObjSelecione = new Inf_Selecione_Linha_Saida_Dev_Outra(this, true);
        ObjSelecione.setVisible(true);
    }
    
    public final void Setar_Atalho_BT(){
        //metodo para pegar a tecla pressionada
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Esc");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        
        InputMap inputMap4 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap4.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),"Consultar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap4);
        
        InputMap inputMap3 = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"Adicionar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap3);
                
        //método para executar
         this.getRootPane().getActionMap().put("Consultar", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Consultar.doClick();
        }
        });        
        this.getRootPane().getActionMap().put("Esc", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Sair.doClick();
        }
        });
         this.getRootPane().getActionMap().put("Adicionar", new AbstractAction(){
        private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent arg0) {
        BT_Adicionar.doClick();
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela_Consulta_Saida_Devolucao_Outra_DL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Consulta_Saida_Devolucao_Outra_DL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Consulta_Saida_Devolucao_Outra_DL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Consulta_Saida_Devolucao_Outra_DL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela_Consulta_Saida_Devolucao_Outra_DL dialog = new Tela_Consulta_Saida_Devolucao_Outra_DL(new Tela_Gerar_Devolucao_Outra(), true);
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
    private javax.swing.JButton BT_Consultar;
    private javax.swing.JButton BT_Sair;
    private javax.swing.JComboBox JCB_Tipo_Pesquisa;
    private com.toedter.calendar.JDateChooser JD_Final;
    private com.toedter.calendar.JDateChooser JD_Inicial;
    private javax.swing.JLabel JL_Quant_Itens1;
    private javax.swing.JTable JTB_Saidas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
