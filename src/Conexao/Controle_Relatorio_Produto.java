package Conexao;

//@author Márison Tamiarana

import static GUI_Frames.Tela_Principal.UserLogado;
import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Controle_Relatorio_Produto {
    
    Conecta_Banco ObjConecta = new Conecta_Banco();
    
    public void Relatorio_Produto_Todos(String situacao, String relat){
    try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select count(id_produto) as cont from produto "+situacao+"");
            ObjConecta.rs.first();
            int Cont = ObjConecta.rs.getInt("cont");
            
            ObjConecta.ExecutaSQL("select * from produto inner join categoria_produto "
            + "on produto.Categoria_Produto_id_categoria = categoria_produto.id_categoria "+situacao+" order by descricao");
            JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
            HashMap parametros = new HashMap();
            parametros.put("Usuario",UserLogado);
            parametros.put("Quant_Itens",Cont);
            parametros.put("Tipo_Relatorio", relat);
            String C = "C:\\Program Files (x86)\\SisCoE/Relat_Produto_Geral.jasper";
            //String C = "/Relatorios/Relat_Produtos_Todos.jasper";
            JasperPrint JPrint = JasperFillManager.fillReport(C,parametros, Relatorio);
            JasperViewer JView = new JasperViewer(JPrint, false);
            JView.setVisible(true);
            //Colocar titulo na janela
            JView.setTitle("Relatório De Produto");
            //Colocar icone na janela
            JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
            //mandar direto para a impressora;
            //true - abrir caixa de opção de impressora -- false manda direto para a padrao
            //JasperPrintManager.printReport(JPrint, true);
            ObjConecta.Desconecta();
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }
    }
    
    public void Relatorio_Produto_Entrada_Periodo(JDateChooser dinicial,JDateChooser dfinal, int id_prod ){
    try {
        ObjConecta.Conectar();
        
        String di = new SimpleDateFormat("yyyy-MM-dd").format(dinicial.getDate());
        String dti = new SimpleDateFormat("dd-MM-yyyy").format(dinicial.getDate());
        String df = new SimpleDateFormat("yyyy-MM-dd").format(dfinal.getDate());
        String dtf = new SimpleDateFormat("dd-MM-yyyy").format(dfinal.getDate());
        long DiasIntervalo = 0;
        //calcular dias entre as datas
        long Dias = (((dfinal.getDate().getTime() - dinicial.getDate().getTime()) + 3600000) / 86400000L);//Quantidade de dias entra as datas
        //metodo para contar a quantidade de entradas
        ObjConecta.ExecutaSQL("select count(id_entrada) as cont from entrada inner join entrada_itens on entrada.id_entrada=entrada_itens.entrada_id_entrada "
                + "where entrada_itens.produto_id_produto ="+id_prod+" and entrada.data_entrada between '" + di + "' and '" + df + "'");
        ObjConecta.rs.first();
        int Cont = ObjConecta.rs.getInt("cont");
        //metodo para soma a quantidade de estoque por iten
        ObjConecta.ExecutaSQL("select sum(quantidade) as soma from entrada inner join entrada_itens on entrada.id_entrada=entrada_itens.entrada_id_entrada "
                + "where entrada_itens.produto_id_produto = "+id_prod+" and entrada.data_entrada between '" + di + "' and '" + df + "'");
        ObjConecta.rs.first();
        double Soma = ObjConecta.rs.getDouble("soma");
        double Media =(Soma/Cont);
        
        //metodo para saber a primeira e a ultima data de entrada dentro do periodo;
        if(Soma != 0){
            if(di.equals(df)){}
            else{
                ObjConecta.ExecutaSQL("select * from entrada inner join entrada_itens on entrada.id_entrada=entrada_itens.entrada_id_entrada "
                        + "inner join produto on produto.id_produto=entrada_itens.produto_id_produto where produto.id_produto ="+id_prod+""
                        + " and entrada.data_entrada between '" + di + "' and '" + df + "'");
                ObjConecta.rs.first();
                int teste = ObjConecta.rs.getInt("id_entrada");
                Date dt1 = ObjConecta.rs.getDate("data_entrada");
                ObjConecta.rs.last();
                Date dt2 = ObjConecta.rs.getDate("data_entrada");
                DiasIntervalo = (((dt2.getTime() - dt1.getTime()) + 3600000) / 86400000L);//Quantidade de dias entra as datas
            }
        }
        //metodo para preencher o relatorio
        ObjConecta.ExecutaSQL("select * from entrada inner join entrada_itens on entrada.id_entrada=entrada_itens.entrada_id_entrada "
                + "inner join produto on produto.id_produto=entrada_itens.produto_id_produto where produto.id_produto ="+id_prod+""
                + " and entrada.data_entrada between '" + di + "' and '" + df + "'");
                
        JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
                        
        //passar parametros para o relátorio
        HashMap parametros = new HashMap();
        parametros.put("Data_Inicial", di);
        parametros.put("Data_Final", df);
        parametros.put("Usuario", UserLogado);
        parametros.put("DTI", dti);
        parametros.put("DTF", dtf);
        parametros.put("Cont", Cont);
        parametros.put("Soma",Soma);
        parametros.put("Dias",Dias);
        parametros.put("Media", Media);
        parametros.put("Intervalo",DiasIntervalo);
        JasperPrint JPrint = JasperFillManager.fillReport("C:\\Program Files (x86)\\SisCoE/Relat_Produto_Entrada_Periodo.jasper", parametros, Relatorio);
        JasperViewer JView = new JasperViewer(JPrint, false);
        JView.setVisible(true);
        //Colocar titulo na janela
        JView.setTitle("Relatório De Produto");
        //Colocar icone na janela
        JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
        ObjConecta.Desconecta();
    } catch (JRException | SQLException ex) {
        ObjConecta.Desconecta();
        JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório: " + ex);
            }
    }
    public void Relatorio_Produto_Categoria_Todas(){
    try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select count(id_produto) as cont from produto where situacao = 'ATIVO'");
            ObjConecta.rs.first();
            int Cont = ObjConecta.rs.getInt("cont");
            
            ObjConecta.ExecutaSQL("select*from categoria_produto inner join produto "
            + "on categoria_produto.id_categoria = produto.Categoria_Produto_id_categoria where produto.situacao ='Ativo' order by categoria");
            JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
            HashMap parametros = new HashMap();
            parametros.put("Usuario",UserLogado);
            parametros.put("Quant_Itens",Cont);
            JasperPrint JPrint = JasperFillManager.fillReport("C:\\Program Files (x86)\\SisCoE/Relat_Produtos_Categoria_Todas.jasper",parametros, Relatorio);
            JasperViewer JView = new JasperViewer(JPrint, false);
            JView.setVisible(true);
            //Colocar titulo na janela
            JView.setTitle("Relatório De Produto");
            //Colocar icone na janela
            JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
            //mandar direto para a impressora;
            //true - abrir caixa de opção de impressora -- false manda direto para a padrao
            //JasperPrintManager.printReport(JPrint, true);
            ObjConecta.Desconecta();
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }
    }
    
    public void Relatorio_Produto_Categoria_Seleciona(int cod){
    try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select count(id_produto) as cont from produto where Categoria_Produto_id_categoria = "+cod+" and situacao = 'ATIVO'");
            ObjConecta.rs.first();
            int Cont = ObjConecta.rs.getInt("cont");
            
            ObjConecta.ExecutaSQL("select*from categoria_produto inner join produto on categoria_produto.id_categoria = produto.Categoria_Produto_id_categoria "
                    + " where categoria_produto.id_categoria= "+cod+" and produto.situacao ='Ativo' order by categoria");
            JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
            HashMap parametros = new HashMap();
            parametros.put("Usuario",UserLogado);
            parametros.put("Quant_Itens",Cont);
            JasperPrint JPrint = JasperFillManager.fillReport("C:\\Program Files (x86)\\SisCoE/Relat_Produtos_Categoria_Seleciona.jasper",parametros, Relatorio);
            JasperViewer JView = new JasperViewer(JPrint, false);
            JView.setVisible(true);
            //Colocar titulo na janela
            JView.setTitle("Relatório De Produto");
            //Colocar icone na janela
            JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
            //mandar direto para a impressora;
            //true - abrir caixa de opção de impressora -- false manda direto para a padrao
            //JasperPrintManager.printReport(JPrint, true);
            ObjConecta.Desconecta();
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }
    }
    
    public void Relatorio_Produto_Estoque_Sintetico(){
    try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select count(id_produto) as cont from produto where produto.situacao = 'ATIVO' "
                    + " and (select sum(quantidade_estoque) from lote_estoque where produto.id_produto=lote_estoque.produto_id_produto)>0;");
            ObjConecta.rs.first();
            int Cont = ObjConecta.rs.getInt("cont");
            
            ObjConecta.ExecutaSQL("select distinct id_produto, descricao, quantidade_minima, unidade,categoria,"
                    + "(select sum(quantidade_estoque) from lote_estoque where produto.id_produto=lote_estoque.produto_id_produto) as estoque from produto "
                    + " inner join categoria_produto on produto.Categoria_Produto_id_categoria = categoria_produto.id_categoria where produto.situacao = 'ATIVO' "
                    + " and (select sum(quantidade_estoque) from lote_estoque where produto.id_produto=lote_estoque.produto_id_produto)>0 order by produto.descricao");
            JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
            HashMap parametros = new HashMap();
            parametros.put("Usuario",UserLogado);
            parametros.put("Quant_Itens",Cont);
            JasperPrint JPrint = JasperFillManager.fillReport("C:\\Program Files (x86)\\SisCoE/Relat_Estoque_Sintetico.jasper",parametros, Relatorio);
            JasperViewer JView = new JasperViewer(JPrint, false);
            JView.setVisible(true);
            //Colocar titulo na janela
            JView.setTitle("Relatório De Estoque");
            //Colocar icone na janela
            JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
            //mandar direto para a impressora;
            //true - abrir caixa de opção de impressora -- false manda direto para a padrao
            //JasperPrintManager.printReport(JPrint, true);
            ObjConecta.Desconecta();
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }
    }
    
    public void Relatorio_Produto_Estoque_Analitico(){
    try {
            ObjConecta.Conectar();
            ObjConecta.ExecutaSQL("select count(id_produto) as cont from produto where produto.situacao = 'ATIVO' "
                    + " and (select sum(quantidade_estoque) from lote_estoque where produto.id_produto=lote_estoque.produto_id_produto)>0;");
            ObjConecta.rs.first();
            int Cont = ObjConecta.rs.getInt("cont");
            
            ObjConecta.ExecutaSQL("select * from produto "
                    + " inner join lote_estoque on produto.id_produto=lote_estoque.produto_id_produto inner join categoria_produto "
                    + " on produto.Categoria_Produto_id_categoria = categoria_produto.id_categoria "
                    + " where produto.situacao = 'ATIVO'  and quantidade_estoque >0 order by descricao;");
            JRResultSetDataSource Relatorio = new JRResultSetDataSource(ObjConecta.rs);
            HashMap parametros = new HashMap();
            parametros.put("Usuario",UserLogado);
            parametros.put("Quant_Itens",Cont);
            JasperPrint JPrint = JasperFillManager.fillReport("C:\\Program Files (x86)\\SisCoE/Relat_Estoque_Analitico.jasper",parametros, Relatorio);
            JasperViewer JView = new JasperViewer(JPrint, false);
            JView.setVisible(true);
            //Colocar titulo na janela
            JView.setTitle("Relatório De Estoque");
            //Colocar icone na janela
            JView.setIconImage(new ImageIcon(getClass().getResource("/Icones_Gerais/Serviço 24x24.png")).getImage());
            //mandar direto para a impressora;
            //true - abrir caixa de opção de impressora -- false manda direto para a padrao
            //JasperPrintManager.printReport(JPrint, true);
            ObjConecta.Desconecta();
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro: "+ex);
        }
    }
}