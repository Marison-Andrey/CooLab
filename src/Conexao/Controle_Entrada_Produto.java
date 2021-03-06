package Conexao;

//@author Márison Tamiarana

import Classes.Modelo_Entrada_Produto;
import Classes.Modelo_Lote_Estoque;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

 
public class Controle_Entrada_Produto {
    
    Conecta_Banco ObjConecta = new Conecta_Banco();
    Modelo_Entrada_Produto ObjModeloEntrada = new Modelo_Entrada_Produto();
    Modelo_Lote_Estoque ObjModLote = new Modelo_Lote_Estoque();
    Controle_Lote_Estoque ObjControleLote = new Controle_Lote_Estoque();
    
    public boolean Confirma_Entrada;
    public boolean Confirma_Entrada_Item;
    public boolean Confirma_Entrada_Estoque;
    public boolean Confirma_Entrada_Estoque_Lote;
    public boolean Confirma_Atualiza_Estoque;
    public boolean Confirma_Atualiza_Estoque_Lote;
    public boolean Confirma_Atualiza_Estoque_Lote_Novo;
    public boolean Confirma_Efetivar_Entrada;
    public boolean Confirma_Excluir_Entrada;
    public boolean Confirma_Excluir_Item;
    public boolean ControlaLote;
    public boolean Controle_Entrada = false;
    public boolean Verifica_Entrada_Sem_Itens;
    boolean Consulta_Lote_Novo;
    boolean Consulta_Lote;
    public long dt;
    
    public void Inserir_Entrada(Modelo_Entrada_Produto ObjModeloEntrada, String Data){
        ObjConecta.Conectar();
        
        String sql = "insert into entrada (data_entrada,descricao_entrada, situacao_entrada, observacao_entrada)values(?,?,?,?)";  
            try {                
                try(PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql))
                {
                    {
                        stmt.setString(1, Data);
                        stmt.setString(2, ObjModeloEntrada.getDescricao());
                        stmt.setString(3, "ABERTO");
                        stmt.setString(4, ObjModeloEntrada.getObservacao());
                        
                    }
                    stmt.execute();
                    stmt.close();
                    
                }
                Confirma_Entrada = true;
                //metodo para pegar o ultimo id inserido
                ObjConecta.ExecutaSQL("select LAST_INSERT_ID()");//busca o id gerado
                ObjConecta.rs.first();
                ObjModeloEntrada.setId_entrada(ObjConecta.rs.getInt(1));//seta o ultimo id inserido
                
            } catch (SQLException ex) {
                Confirma_Entrada = false;
                JOptionPane.showMessageDialog(null, "Erro ao gerar a entrada no banco! \n"
                        + ex, "Informação Do Banco De Dados", JOptionPane.INFORMATION_MESSAGE);
            }
        ObjConecta.Desconecta();
    }
    
    public void Inserir_Entrada_Itens(int Id_Prod, int Id_Entrada, double Quant, String Lote, String Data_Val, double Preco){
        ObjConecta.Conectar();
        String sql = "insert into entrada_itens (Produto_id_produto, Entrada_id_entrada, quantidade, lote, data_validade, preco, data_entrada_produto)"
                + "values(?,?,?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setInt   (1,  Id_Prod);
                        stmt.setInt   (2,  Id_Entrada);
                        stmt.setDouble(3,  Quant);
                        stmt.setString(4,  Lote);
                        stmt.setString(5,  Data_Val);
                        stmt.setDouble(6,  Preco);
                        stmt.setString(7, new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));//data atual do sistema
                    }
                    stmt.execute();
                    stmt.close();
                }
                Confirma_Entrada_Item = true;
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                Confirma_Entrada_Item = false;
                JOptionPane.showMessageDialog(null,"Erro ao dar entrada de itens no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                    }        
        ObjConecta.Desconecta();
    }
    
    public void Inserir_Estoque_Lote(int id_prod, double quant, String lote, String data_val){
        try {
            ObjConecta.Conectar();
            String sql = "insert into lote_estoque (produto_id_produto, quantidade_estoque, numero_lote, data_validade_lote)"
                + "values(?,?,?,?)";
            
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setInt   (1,  id_prod);
                        stmt.setDouble(2,  quant);
                        stmt.setString(3,  lote);
                        stmt.setString(4,  data_val);
                    }
                    stmt.execute();
                    stmt.close();
                }
                Confirma_Entrada_Estoque_Lote = true;
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                Confirma_Entrada_Estoque_Lote = false;
                JOptionPane.showMessageDialog(null,"Erro ao inserir lote e estoque no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                    }        
        ObjConecta.Desconecta();
    }
    
    public void Inserir_Estoque(int id_prod, double quant){
        try {
            ObjConecta.Conectar();
            String sql = "insert into lote_estoque (produto_id_produto, quantidade_estoque)"
                + "values(?,?)";
            
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setInt   (1,  id_prod);
                        stmt.setDouble(2,  quant);
                    }
                    stmt.execute();
                    stmt.close();
                }
                Confirma_Entrada_Estoque = true;
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                Confirma_Entrada_Estoque = false;
                JOptionPane.showMessageDialog(null,"Erro ao inserir o estoque no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                    }        
        ObjConecta.Desconecta();
    }
        
   public void Atualiza_Estoque(Modelo_Lote_Estoque ObjModLote, int id_prod, double quant){
        ObjConecta.Conectar();        
        String sql = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_prod+" and numero_lote is null";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setDouble(1, ObjModLote.getQuantidade_estoque()+quant);
                    }
                    stmt.execute();
                    stmt.close();
                }           
                    Confirma_Atualiza_Estoque = true;      
                } catch (SQLException ex) {
                    Confirma_Atualiza_Estoque = false;
                    JOptionPane.showMessageDialog(null,"Erro ao atualzar o estoque no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                        }        
        ObjConecta.Desconecta();
    }
   public void Atualiza_Estoque_Lote(Modelo_Lote_Estoque ObjModLote, int id_prod, double quant, String lote, String validade){
        ObjConecta.Conectar();        
        String sql = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_prod+" and numero_lote= '"+lote+"' and data_validade_lote= '"+validade+"' ";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    
                    {
                        stmt.setDouble(1, ObjModLote.getQuantidade_estoque()+quant);
                    }
                    stmt.execute();
                    stmt.close();
                }           
                    Confirma_Atualiza_Estoque_Lote = true;      
                } catch (SQLException ex) {
                    Confirma_Atualiza_Estoque_Lote = false;
                    JOptionPane.showMessageDialog(null,"Erro ao atualzar o estoque no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                        }        
        ObjConecta.Desconecta();
    }
   public void Atualiza_Estoque_Lote_Novo( int id_prod, double quant, String lote, String data_val){
        ObjConecta.Conectar();        
        String sql = "update lote_estoque set quantidade_estoque =?, numero_lote=?, data_validade_lote=? "
                + "where produto_id_produto="+id_prod+" and numero_lote='NOVO'";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    
                    {
                        stmt.setDouble(1, quant);
                        stmt.setString(2, lote);
                        stmt.setString(3, data_val);
                        
                    }
                    stmt.execute();
                    stmt.close();
                }           
                    Confirma_Atualiza_Estoque_Lote_Novo = true;      
                } catch (SQLException ex) {
                    Confirma_Atualiza_Estoque_Lote_Novo = false;
                    JOptionPane.showMessageDialog(null,"Erro ao atualzar o estoque NOVO no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                        }        
        ObjConecta.Desconecta();
    }
            
    public Modelo_Entrada_Produto Media_Produto(Modelo_Entrada_Produto ObjModeloEntradaProd, int id){
        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -3); //diminuir datas - inicio para 90 dias;
            String data_atual = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String data_inicio = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            
            
                ObjConecta.Conectar();
                ObjConecta.ExecutaSQL("select avg(quantidade) as media from entrada inner join entrada_itens on "
                        + "entrada.id_entrada=entrada_itens.entrada_id_entrada "
                        + "where entrada.data_entrada between "+"'"+data_inicio+"'"+" and "+"'"+data_atual+"'"+" and produto_id_produto=" + id + "");
                ObjConecta.rs.first();
                ObjModeloEntradaProd.setMedia(ObjConecta.rs.getInt("media"));
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                ObjConecta.Desconecta();
                JOptionPane.showMessageDialog(null,"Erro ao procurar a media de 90 dias do produto no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }
        return ObjModeloEntradaProd;
    }

public Modelo_Entrada_Produto Media_Prod_Mes_Entrada(Modelo_Entrada_Produto ObjModeloEntradaProd, int id){
        try {
            //Conecta_Banco ObjConecta = new Conecta_Banco();
            ObjConecta.Conectar();
            
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -3); //diminuir datas - inicio para 90 dias;
            Date dt_atual = Calendar.getInstance().getTime();
            Date dt_inicio = c.getTime();
            String data_atual = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String data_inicio = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            
            ObjConecta.ExecutaSQL("select * from entrada inner join entrada_itens on"
              + " entrada.id_entrada=entrada_itens.entrada_id_entrada where produto_id_produto="+id+" and situacao_entrada !='CANCELADA'");
            ObjConecta.rs.first();
            Date dt1 = ObjConecta.rs.getDate("data_entrada");
            ObjConecta.rs.last();
            Date dt2 = ObjConecta.rs.getDate("data_entrada");
            ObjConecta.Desconecta();
            
            dt = (((dt_atual.getTime() - dt1.getTime()) + 3600000) / 86400000L);//Quantidade de dias entra as datas
                        
                ObjConecta.Conectar();
                ObjConecta.ExecutaSQL("select sum(quantidade) as media from entrada inner join entrada_itens on "
                        + "entrada.id_entrada=entrada_itens.entrada_id_entrada "
                        + "where entrada.data_entrada between "+"'"+data_inicio+"'"+" and "+"'"+data_atual+"'"+" and produto_id_produto=" + id + " "
                        + " and situacao_entrada !='CANCELADA'");
                ObjConecta.rs.first();
                float soma = ObjConecta.rs.getInt("media");
                ObjModeloEntradaProd.setTotal(soma);
                ObjModeloEntradaProd.setData_entrada(new SimpleDateFormat("dd-MM-yyyy").format(dt2.getTime()));
                if(dt_inicio.before(dt1)){               
                    if (dt <= 30) {
                        float resultado = soma;
                        ObjModeloEntradaProd.setMedia(resultado); }
                    if (dt > 30 && dt <= 60) {
                        float resultado = soma / 2;
                        ObjModeloEntradaProd.setMedia(resultado); }
                    if (dt > 60) {
                        float resultado = soma / 3;
                        ObjModeloEntradaProd.setMedia(resultado); }           
                }else{
                    float resultado = soma / 3;
                    ObjModeloEntradaProd.setMedia(resultado); }              
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                ObjConecta.Desconecta();
                
                JOptionPane.showMessageDialog(null,"Erro ao procurar a media de 90 dias do produto no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }
        return ObjModeloEntradaProd;
    }

public void Controla_Lote(int id_prod){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select* from produto where id_produto="+id_prod+"");
            ObjConecta.rs.first();
            String Situacao = ObjConecta.rs.getString("solicita_lote");
            
            ControlaLote = Situacao.equalsIgnoreCase("SIM");
            ObjConecta.Desconecta();
                       
        } catch (SQLException ex) {
            ControlaLote = false;
            JOptionPane.showMessageDialog(null,"Erro ao procurar o controle de estoque do produto no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            ObjConecta.Desconecta();
        }
    }


public void Inseri_Atualiza_Lote_Estoque(int id_prod, double quant, String lote, String data_val){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select* from lote_estoque where produto_id_produto="+id_prod+"");
            ObjConecta.rs.first();
            String Lote = ObjConecta.rs.getString("numero_lote");
            
                if(Lote == null){//Produto com estoque e sem lote
                    ObjControleLote.Quantidade_Estoque(ObjModLote,id_prod);//buscar a quantidade existente do estoque sem lote
                    //Atualiza o estoque
                    Atualiza_Estoque(ObjModLote, id_prod, quant);

                }else{//produto com lote - nova inserção ou atualizaçao
                    try{
                        if(Lote.equalsIgnoreCase("NOVO")){
                            Atualiza_Estoque_Lote_Novo(id_prod, quant, lote, data_val);
                        }
                        else{
                            ObjControleLote.Quantidade_Estoque_Lote(ObjModLote,id_prod, lote, data_val);//buscar a quantidade existente do estoque com lote
                            if(ObjControleLote.Controla_Lote == true){                                
                                Atualiza_Estoque_Lote(ObjModLote, id_prod, quant, lote, data_val);//Atualiza o estoque
                            }else{
                                Inserir_Estoque_Lote(id_prod, quant, lote, data_val);//Inseri novo
                            }
                        }
                    }catch(Exception ex) {//inserir estoque de produto com novo lote
                        Inserir_Estoque_Lote(id_prod, quant, lote, data_val);}
                }
            ObjConecta.Desconecta();
        } catch (SQLException ex) {//inserir estoque de produto sem estoque cadastrados
            Controla_Lote(id_prod);
                if(ControlaLote== true){
                    Inserir_Estoque_Lote(id_prod, quant, lote, data_val);  
                }else{
                    Inserir_Estoque(id_prod, quant);
                }
                ObjConecta.Desconecta();
            }
    }

public void Inseri_Atualiza_Estoque(int id_prod, double quant){    
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select* from lote_estoque where produto_id_produto="+id_prod+" and numero_lote is null");
            ObjConecta.rs.first();
            String Lote = ObjConecta.rs.getString("numero_lote");
            if(Lote == null){//Produto com estoque e sem lote
                    ObjControleLote.Quantidade_Estoque(ObjModLote,id_prod);//buscar a quantidade existente do estoque sem lote
                    //Atualiza o estoque
                    Atualiza_Estoque(ObjModLote, id_prod, quant);
            }else{
                Inserir_Estoque(id_prod, quant);
            }
        } catch (SQLException ex) {
            Inserir_Estoque(id_prod, quant);
        }
}

public void Inseri_Atualiza_Lote_Estoque_(int id_prod, double quant, String lote, String data_val){
    Consulta_Lote_novo(id_prod);
    if(Consulta_Lote_Novo == true){
        Atualiza_Estoque_Lote_Novo(id_prod, quant, lote, data_val);
        Consulta_Lote_Novo = false;
    }else{
        Consulta_Lote(id_prod, lote, data_val);
        if(Consulta_Lote == true){
            ObjControleLote.Quantidade_Estoque_Lote(ObjModLote, id_prod, lote, data_val);//buscar a quantidade existente do estoque com lote
            if (ObjControleLote.Controla_Lote == true) {
                Atualiza_Estoque_Lote(ObjModLote, id_prod, quant, lote, data_val);//Atualiza o estoque
                ObjControleLote.Controla_Lote = false;
            } else {
                Inserir_Estoque_Lote(id_prod, quant, lote, data_val);//Inseri novo
            }
        Consulta_Lote = false;
        }else{
            Inserir_Estoque_Lote(id_prod, quant, lote, data_val);//Inseri novo
        }
    }
}
void Consulta_Lote_novo(int id_prod){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from lote_estoque where produto_id_produto="+id_prod+" and numero_lote='NOVO'");
            ObjConecta.rs.first();
            String Lote = ObjConecta.rs.getString("numero_lote");
            Consulta_Lote_Novo = Lote.equalsIgnoreCase("NOVO");
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Consulta_Lote_Novo = false;
            ObjConecta.Desconecta();
        }
}
void Consulta_Lote(int id_prod,String lote, String data_val){
    try {
        ObjConecta.Conectar();
        ObjConecta.ExecutaSQL("select * from lote_estoque where produto_id_produto="+id_prod+" and numero_lote='"+lote+"' and data_validade_lote='"+data_val+"'");
        ObjConecta.rs.first();
        String Lote = ObjConecta.rs.getString("numero_lote");
        Consulta_Lote = Lote.equalsIgnoreCase(lote);
        ObjConecta.Desconecta();
    } catch (SQLException ex) {
        Consulta_Lote = false;
        ObjConecta.Desconecta();
    }
}


public void Consulta_Entrada_Id(int id_entrada){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("Select * from entrada where id_entrada = "+id_entrada+"");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
    public void Consulta_Entrada_Id_Ativo(int id_entrada){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("Select * from entrada where id_entrada = "+id_entrada+" and situacao_entrada != 'CANCELADA'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
    
     public void Consulta_Entrada_Todas(){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("Select * from entrada");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
             ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
     public void Consulta_Entrada_Periodo(int mes){
         try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -mes); //diminuir datas - inicio para 30 dias;
            String df = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String di = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada where data_entrada between '"+di+"' and '"+df+"'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
     public void Consulta_Entrada_Por_Periodo(JDateChooser dt_inicial, JDateChooser dt_final){
         try {
            String di = new SimpleDateFormat("yyyy-MM-dd").format(dt_inicial.getDate());
            String df = new SimpleDateFormat("yyyy-MM-dd").format(dt_final.getDate());
            
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada where data_entrada between '"+di+"' and '"+df+"'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
    public void Consulta_Entrada_Alteradas(String situacao){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("Select * from entrada where situacao_entrada = '"+situacao+"'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
             ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
    public void Consulta_Entrada_Alteradas_Por_Periodo(JDateChooser dt_inicial, JDateChooser dt_final, String situacao){
         try {
            String di = new SimpleDateFormat("yyyy-MM-dd").format(dt_inicial.getDate());
            String df = new SimpleDateFormat("yyyy-MM-dd").format(dt_final.getDate());
            
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada where data_entrada between '"+di+"' and '"+df+"' and situacao_entrada = '"+situacao+"'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
    
    public void Consulta_Iten_Entrada(int id_prod){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada inner join entrada_itens on"
                    + " entrada.id_entrada=entrada_itens.entrada_id_entrada where produto_id_produto="+id_prod+" and situacao_entrada !='CANCELADA'");
            ObjConecta.rs.first();
            int id = ObjConecta.rs.getInt("id_entrada");
            Controle_Entrada = true;
            ObjConecta.Desconecta();
        } catch (SQLException ex) {
            Controle_Entrada = false;
            ObjConecta.Desconecta();
        }
    }
     
    public void Atualiza_Situacao_Entrada(int id, String situacao){
        ObjConecta.Conectar();
        String sql = "update entrada set situacao_entrada =? where id_entrada="+id+"";
        
        try {
            try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                stmt.setString(1,situacao);
                stmt.execute();
                stmt.close();
                Confirma_Efetivar_Entrada = true;
            }
        } catch (SQLException ex) 
            {
                ObjConecta.Desconecta();
                Confirma_Efetivar_Entrada = false;
                JOptionPane.showMessageDialog(null,"Erro ao atualizar a entrada no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }        
        ObjConecta.Desconecta();        
    }
    
    public void Atualiza_Data_Alteracao_Entrada(int id){
        ObjConecta.Conectar();
        String sql = "update entrada set data_alteracao_entrada =? where id_entrada="+id+"";
        
        try {
            try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                stmt.setString(1,new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));
                stmt.execute();
                stmt.close();
                Confirma_Efetivar_Entrada = true;
            }
        } catch (SQLException ex) 
            {
                ObjConecta.Desconecta();
                Confirma_Efetivar_Entrada = false;
                JOptionPane.showMessageDialog(null,"Erro ao atualizar a entrada no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }        
        ObjConecta.Desconecta();
        
    }
    public void Verifica_Entrada_Sem_Itens(int id_entrada){    
        try{
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada_itens where entrada_id_entrada = "+id_entrada+"");
            ObjConecta.rs.first();
            int cod = ObjConecta.rs.getInt("produto_id_produto");
            Verifica_Entrada_Sem_Itens = true;                
        }catch(SQLException ex){
            ObjConecta.Desconecta();
            Verifica_Entrada_Sem_Itens = false;
        }
        ObjConecta.Desconecta();  
    }
    
    public void Excluir_Entrada(int id){
        ObjConecta.Conectar();
        String sql = "delete from entrada where id_entrada="+id+"";
        
        try {
            try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                stmt.execute();
                stmt.close();
                Confirma_Excluir_Entrada = true;
            }
        } catch (SQLException ex) 
            {
                ObjConecta.Desconecta();
                Confirma_Excluir_Entrada = false;
                JOptionPane.showMessageDialog(null,"Erro ao excluir a entrada do banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }        
        ObjConecta.Desconecta();        
    }
    
    public Modelo_Entrada_Produto Consulta_Entrada(Modelo_Entrada_Produto ObjModEntrada, String id_entrada){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("Select * from entrada where id_entrada ="+id_entrada +"");
            ObjConecta.rs.first();
            ObjModEntrada.setId_entrada(ObjConecta.rs.getInt("id_entrada"));
            ObjModEntrada.setDescricao(ObjConecta.rs.getString("descricao_entrada"));
            ObjModEntrada.setData_entrada(ObjConecta.rs.getString("data_entrada"));
            ObjModEntrada.setObservacao(ObjConecta.rs.getString("observacao_entrada"));
            ObjModEntrada.setSituacao(ObjConecta.rs.getString("situacao_entrada"));
            
             ObjConecta.Desconecta();
        } catch (SQLException ex) {            
            ObjConecta.Desconecta();
        }
        return ObjModEntrada;
    }
    
    public Modelo_Entrada_Produto Consulta_Ultima_Entrada(Modelo_Entrada_Produto ObjModProduto, String id){
        try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select * from entrada inner join entrada_itens on"
                    + " entrada.id_entrada=entrada_itens.entrada_id_entrada where produto_id_produto="+id+" and situacao_entrada !='CANCELADA'");
            ObjConecta.rs.last();
            Date dt2 = ObjConecta.rs.getDate("data_entrada");
            ObjModProduto.setData_entrada(new SimpleDateFormat("dd-MM-yyyy").format(dt2.getTime()));
            ObjConecta.Desconecta();
            
        } catch (SQLException ex) { }       
        return ObjModProduto;
    }
    
    ////////////////////////////////////////Cancelamento//////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
        
    public void Atualiza_Estoque_Lote_Produto_Cancela(int id_prod, double quant, String lote, String data_val){
        try {
            ObjControleLote.Quantidade_Estoque_Lote(ObjModLote,id_prod, lote, data_val);//buscar a quantidade existente do estoque com lote
            //Atualiza o estoque
            Atualiza_Estoque_Lote_Cancela(ObjModLote, id_prod, quant, lote, data_val);//Atualiza o estoque
                               
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao executar atualizar o estoque com lote no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void Atualiza_Estoque_Produto_Cancela(int id_prod, double quant){
        try {
            ObjControleLote.Quantidade_Estoque(ObjModLote,id_prod);//buscar a quantidade existente do estoque sem lote
            //Atualiza o estoque
            Atualiza_Estoque_Cancela(ObjModLote, id_prod, quant);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao executar o atualizar de estoque no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void Atualiza_Estoque_Cancela(Modelo_Lote_Estoque ObjModLote, int id_prod, double quant){
        ObjConecta.Conectar();        
        String sql = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_prod+" and numero_lote is null";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setDouble(1, ObjModLote.getQuantidade_estoque()-quant);
                    }
                    stmt.execute();
                    stmt.close();
                }           
                    Confirma_Atualiza_Estoque = true;      
                } catch (SQLException ex) {
                    Confirma_Atualiza_Estoque = false;
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar o estoque no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                        }        
        ObjConecta.Desconecta();
    }
   public void Atualiza_Estoque_Lote_Cancela(Modelo_Lote_Estoque ObjModLote, int id_prod, double quant, String lote, String validade){
        ObjConecta.Conectar();        
        String sql = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_prod+" and numero_lote='"+lote+"' and data_validade_lote='"+validade+"' ";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    
                    {
                        stmt.setDouble(1, ObjModLote.getQuantidade_estoque()-quant);
                    }
                    stmt.execute();
                    stmt.close();
                }           
                    Confirma_Atualiza_Estoque_Lote = true;      
                } catch (SQLException ex) {
                    Confirma_Atualiza_Estoque_Lote = false;
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar o estoque com lote no banco! \n"
                        +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                        }        
        ObjConecta.Desconecta();
    }
   
   public void Inserir_Entrada_Itens_Cancelamento(int Id_Prod, int Id_Entrada, double Quant, String Lote, String Data_Val){
        ObjConecta.Conectar();
        String sql = "insert into entrada_itens_cancelados (Produto_id_produto, Entrada_id_entrada, quantidade, lote, data_validade, data_cancelamento_produto)"
                + " values(?,?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setInt   (1,  Id_Prod);
                        stmt.setInt   (2,  Id_Entrada);
                        stmt.setDouble(3,  Quant);
                        stmt.setString(4,  Lote);
                        stmt.setString(5,  Data_Val);
                        stmt.setString(6, new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));//data atual do sistema
                    }
                    stmt.execute();
                    stmt.close();
                }
                Confirma_Entrada_Item = true;
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                ObjConecta.Desconecta();
                Confirma_Entrada_Item = false;
                JOptionPane.showMessageDialog(null,"Erro ao dar entrada de itens cancelados no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                    }        
        ObjConecta.Desconecta();
    }
   
   public void Inserir_Motivo_Entrada_Cancelada(int Id_Entrada, String motivo){
       ObjConecta.Conectar();
        String sql = "insert into motivo_entrada_cancelada (motivo_entrada_cancelada, entrada_id_entrada, data_cancelamento)"
                + " values(?,?,?)";
            try {
                try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql)) {
                    {
                        stmt.setString(1,  motivo);
                        stmt.setInt   (2,  Id_Entrada);
                        stmt.setString(3, new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));//data atual do sistema
                    }
                    stmt.execute();
                    stmt.close();
                }
                ObjConecta.Desconecta();
            } catch (SQLException ex) {
                ObjConecta.Desconecta();
                JOptionPane.showMessageDialog(null,"Erro ao salvar o motivo cancelamento no banco! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
                    }        
        ObjConecta.Desconecta();
   }
   
        
    public void Excluir_Item_Entrada_Efetivada_Lote_Atualiza_Estoque(Modelo_Lote_Estoque ObjModLote,String id_produto, String id_entrada, String lote, String validade, double quant){
        ObjConecta.Conectar();        
        String sql = "delete from entrada_itens where produto_id_produto = "+id_produto+" and entrada_id_entrada = "+id_entrada+" "
                + " and lote = '"+lote+"' and data_validade = '"+validade+"' ";   
        
        String sql2 = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_produto+" and numero_lote='"+lote+"' and data_validade_lote='"+validade+"' ";
        
        try{
            try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql);
                 PreparedStatement stmt2 = ObjConecta.conn.prepareStatement(sql2)) {
                {
                    stmt2.setDouble(1, ObjModLote.getQuantidade_estoque()-quant);
                }
                stmt.execute();
                stmt2.execute();
                
                stmt.close();
                stmt2.close();
                Confirma_Excluir_Item = true;
            }
        }catch(SQLException ex){
            Confirma_Excluir_Item = false;
            JOptionPane.showMessageDialog(null,"Erro ao excluir e atualizar o estoque do item da entrada! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }        
        ObjConecta.Desconecta();
    }
    public void Excluir_Item_Entrada_Efetivada_Atualiza_Estoque(Modelo_Lote_Estoque ObjModLote,String id_produto, String id_entrada, double quant){
        ObjConecta.Conectar();        
        String sql = "delete from entrada_itens where produto_id_produto = "+id_produto+" and entrada_id_entrada = "+id_entrada+" ";   
        
        String sql2 = "update lote_estoque set quantidade_estoque =? where produto_id_produto="+id_produto+" and numero_lote is null";
        
        try{
            try (PreparedStatement stmt = ObjConecta.conn.prepareStatement(sql);
                 PreparedStatement stmt2 = ObjConecta.conn.prepareStatement(sql2)) {
                {
                    stmt2.setDouble(1, ObjModLote.getQuantidade_estoque()-quant);
                }
                stmt.execute();
                stmt2.execute();
                
                stmt.close();
                stmt2.close();
                Confirma_Excluir_Item = true;
            }
        }catch(SQLException ex){
            Confirma_Excluir_Item = false;
            JOptionPane.showMessageDialog(null,"Erro ao excluir e atualizar o estoque do item da entrada! \n"
                    +ex,"Informação Do Banco De Dados",JOptionPane.INFORMATION_MESSAGE);
            }        
        ObjConecta.Desconecta();
    }
      
}
