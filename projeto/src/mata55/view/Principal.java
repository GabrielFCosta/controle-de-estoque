package mata55.view;


import mata55.controller.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe do menu principal e listagens de tabelas.
 * Contém o método main ao final!! 
 * @author Gabriel Gonçalves Faria Costa
 */
public class Principal extends JFrame implements ActionListener{

    private static final long serialVersionUID = -270215131800000256L;
    
    private static Controlador control;
    private static int cod1;
    private static int cod2;
    private static int cod3;
    
    // Telas de listagens e cadastros.
    private static Principal fornlist = null;
    private static Principal prodlist = null;
    private static Principal complist = null;
    private static Principal vendlist = null;
    private static PessoaCAD cadpessoa = null;
    private static ProdutoCAD cadproduto = null;
    private static CompraCAD cadcompra = null;
    private static RevendaCAD cadvenda = null;
    
    // Botões do menu prinipal.
    private static JButton fornreg = new JButton("Registrar Fornecedor");
    private static JButton prodreg = new JButton("Registrar Produto");
    private static JButton compreg = new JButton("Registrar Compra de Produto");
    private static JButton vendreg = new JButton("Registrar Revenda de Compra");
    private static JButton fornrem = new JButton("Cancelar Fornecedor");
    private static JButton prodrem = new JButton("Cancelar Produto");
    private static JButton comprem = new JButton("Cancelar Compra de Produto");
    private static JButton vendrem = new JButton("Cancelar Revenda de Compra");
    private static JButton forn = new JButton("Listar fornecedores");
    private static JButton prod = new JButton("Listar Produtos");
    private static JButton comp = new JButton("Listar Compras por Produto");
    private static JButton vend = new JButton("Listar Revendas por Compra");
    private static JButton forncons = new JButton("Consultar");

    public Principal(char op){
    	super();
    	JTabbedPane tab = new JTabbedPane();
        if(op == '0'){
            this.setSize(800,150);
            this.setResizable(true);
            this.setTitle("Programa de Controle de Estoque");
            JPanel fornecedores = new JPanel();
            JPanel produtos = new JPanel();
            JPanel compras = new JPanel();
            JPanel revendas = new JPanel();

            fornreg.addActionListener(this);
            fornrem.addActionListener(this);
            forn.addActionListener(this);
            forncons.addActionListener(this);
            fornecedores.add(fornreg);
            fornecedores.add(fornrem);
            fornecedores.add(forn);
            fornecedores.add(forncons);

            prodreg.addActionListener(this);
            prodrem.addActionListener(this);
            prod.addActionListener(this);
            produtos.add(prodreg);
            produtos.add(prodrem);
            produtos.add(prod);

            compreg.addActionListener(this);
            comprem.addActionListener(this);
            comp.addActionListener(this);
            compras.add(compreg);
            compras.add(comprem);
            compras.add(comp);

            vendreg.addActionListener(this);
            vendrem.addActionListener(this);
            vend.addActionListener(this);
            revendas.add(vendreg);
            revendas.add(vendrem);
            revendas.add(vend);

            tab.addTab("Fornecedores", fornecedores);
            tab.addTab("Produtos", produtos);
            tab.addTab("Compras", compras);
            tab.addTab("Revendas", revendas);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else{
            this.setSize(600,600);
            JPanel painel = new JPanel();

            if(op == 'p'){
                this.setTitle("Listagem de Produtos");
                String[][] matriz = control.getVetorProdutos();
                String[] colunas = {"Código","Nome","Descrição","Unidade","Qtd. Diponível","Saldo (R$)"};
                painel.add(getScrollPane(matriz,colunas));
                tab.addTab("Produtos", painel);
            }

            if(op == 'f'){
                this.setTitle("Listagem de Fornecedores");
                String[][] matriz = control.getVetorPessoas();
                String[] colunas = {"Código","Nome","Sobrenome","Razão Social","E-mail","Telefone"};
                painel.add(getScrollPane(matriz,colunas));
                tab.addTab("Fornecedores", painel);
            }

            if(op == 'c'){
                this.setTitle("Listagem de Compras");
                String[][] matriz = control.getVetorCompras(cod1);
                String[] colunas = {"Cód. Compra","Fornecedor","Data","Descrição","Qtd. inicial","Preço unit. (R$)", "Total (R$)","Qtd. Diponível","Saldo (R$)"};
                painel.add(getScrollPane(matriz,colunas));
                tab.addTab("Compras por Produto: "+control.getNomeProduto(cod1)+" - Código: "+cod1, painel);
            }
            if(op == 'v'){
                this.setTitle("Listagem de Revendas");
                String[][] matriz = control.getVetorRevendas(cod1,cod2);
                String[] colunas = {"Cód. Revenda","Data","Quantidade","Preço unit. (R$)", "Total (R$)"};
                painel.add(getScrollPane(matriz,colunas));
                tab.addTab("Revendas por Produto: "+control.getNomeProduto(cod1)+" - Código: "+cod1+" - Lote de Compra: "+cod2, painel);
            }
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        this.add(tab,BorderLayout.CENTER);
        this.setVisible(true);

    }
    
    /**
     * Retorna tabela preenchida.
     * @param matriz dados a serem apresentados.
     * @param colunas rótulos das colunas da tabela.
     * @return scrollbar.
     */
    public JScrollPane getScrollPane(String[][] matriz, String[] colunas){
        JTable tabela = new JTable(matriz,colunas);
        tabela.setEnabled(false);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollbar = new JScrollPane(tabela);
        return scrollbar;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // apresenta listagem de produtos.
        if(e.getSource() == prod){
        	control.carregaProdutos();
        	this.getInstance('p');
        }
        // apresenta listagem de fornecedores.
        if(e.getSource() == forn){
        	control.carregaPessoas();
        	this.getInstance('f');
        }
        if(e.getSource() == comp || e.getSource() == prodrem || e.getSource() == vend || e.getSource() == comprem || e.getSource() == vendrem){
            this.setcodigo('p');
            if(cod1 > 0){
            	control.carregaProdutos();
                if(control.checkCodProduto(cod1)){
                    // apresenta listagem de compras de determinado produto.
                    if(e.getSource() == comp){
                    	this.getInstance('c');
                    }
                    // cancela determinado produto. i.e. desativa produto da listagem.
                    else if(e.getSource() == prodrem){
                        control.removeProduto(cod1);
                        control.gravaProdutos();
                        JOptionPane.showMessageDialog(null,"Produto cancelado.");
                        control.carregaProdutos();
                        this.getInstance('p');
                    }
                    else{
                        this.setcodCompra(cod1);
                        if(cod2 > 0){
                            if(control.checkCodCompra(cod1,cod2)){
                                // apresenta listagem de revendas de um determinado lote de compra.
                                if(e.getSource() == vend){
                                	this.getInstance('r');
                                }
                                // cancela determinada compra. i.e. desativa compra da listagem.
                                else if(e.getSource() == comprem){
                                    control.removeCompra(cod1,cod2);
                                    control.gravaProdutos();
                                    JOptionPane.showMessageDialog(null,"Compra cancelada.");
                                    control.carregaProdutos();
                                    this.getInstance('c');
                                }
                                // cancela determinada revenda. i.e. desativa revenda da listagem.
                                else{
                                    this.setcodVenda();
                                    if(cod3 > 0){
                                        if(control.checkCodRevenda(cod1,cod2,cod3)){
                                            control.removeRevenda(cod1,cod2,cod3);
                                            control.gravaProdutos();
                                            JOptionPane.showMessageDialog(null,"Revenda cancelada.");
                                            control.carregaProdutos();
                                            this.getInstance('r');
                                        }
                                        else JOptionPane.showMessageDialog(null,"Código de revenda não encontrado.");
                                    }
                                }
                            }
                            else JOptionPane.showMessageDialog(null,"Código da compra não encontrado.");
                        }
                    }
                }
                else JOptionPane.showMessageDialog(null,"Código do produto não encontrado.");
            }
        }
        /*
         * cancela determinado fornecedor. i.e. desativa fornecedor da listagem.
         * consulta fornecedor por Código.
         */
        if(e.getSource() == fornrem || e.getSource() == forncons){
            this.setcodigo('f');
            if(cod1 > 0){
            	control.carregaPessoas();
            	// consulta de dados do fornecedor
            	if(e.getSource() == forncons) {
            		String text = control.getString(cod1);
            		if(text != null) {
            			JOptionPane.showMessageDialog(null,text,"Consulta de fornecdor",JOptionPane.INFORMATION_MESSAGE);
            		}
            		else JOptionPane.showMessageDialog(null,"Código do fornecedor não encontrado.");
            	}
            	else {
            		// cancela fornecedor
            		if(control.checkCodFornecedor(cod1)){
            			control.removePessoa(cod1);
            			control.gravaPessoas();
            			JOptionPane.showMessageDialog(null,"Fornecedor cancelado.");
            			control.carregaPessoas();
            			this.getInstance('f');
            		}
            		else JOptionPane.showMessageDialog(null,"Código do fornecedor não encontrado.");
            	}
            }
        }
        // cadastro de fornecedores.
        if(e.getSource() == fornreg){
        	try{
        		cadpessoa.dispose();
            }catch(NullPointerException a){
            }finally{
                cadpessoa = new PessoaCAD();
            }
        }
        // cadastro de produtos.
        if(e.getSource() == prodreg){
        	try{
        		cadproduto.dispose();
            }catch(NullPointerException a){
            }finally{
                cadproduto = new ProdutoCAD();
            }
        }
        // cadastro de compras e revendas.
        if(e.getSource() == compreg || e.getSource() == vendreg){
            this.setcodigo('p');
            cod3 = cod1; //cod3 recebe Código do produto.
            if(cod3 > 0){
            	control.carregaProdutos();
                if(control.checkCodProduto(cod3)){
                	// cadastro de compras.
                	if(e.getSource() == compreg) {
                		this.setcodigo('f'); //cod1 é o Código do fornecedor.
                		if(cod1 > 0){
                			control.carregaPessoas();
                			if(control.checkCodFornecedor(cod1)){
                				try{
                					cadcompra.dispose();
                				}catch(NullPointerException a){
                				}finally{
                					cadcompra = new CompraCAD(cod3,cod1);
                				}
                			}
                			else JOptionPane.showMessageDialog(null,"Código do fornecedor não encontrado.");
                		}
                	}
                	// cadastro de revendas.
                	else {
                		this.setcodCompra(cod3); //cod2 é o Código da compra.
                		if(cod2 > 0) {
                			if(control.checkCodCompra(cod3,cod2)) {
                				try{
                					cadvenda.dispose();
                				}catch(NullPointerException a){
                				}finally{
                					cadvenda = new RevendaCAD(cod3,cod2);
                				}
                			}
                			else JOptionPane.showMessageDialog(null,"Código da compra não encontrado.");
                		}	
                	}
                }
                else JOptionPane.showMessageDialog(null,"Código do produto não encontrado.");
            }
        }
    }
    /**
     * Instancia listagens de acordo com parâmetro.
     * @param frame listagem a ser instanciada.
     */
    public void getInstance(char frame) {
    	try{
    		switch(frame) {
    			case 'f' :	fornlist.dispose();
    						break;
    			case 'p' :	prodlist.dispose();
    						break;
    			case 'c' :	complist.dispose();
    						break;
    			case 'r' :	vendlist.dispose();
							break;
    			default: break;
    		}
        }catch(NullPointerException a){
        }finally{
        	switch(frame) {
        		case 'f' :	fornlist = new Principal('f');
        					break;
        		case 'p' :	prodlist = new Principal('p');
        					break;
        		case 'c' :	complist = new Principal('c');
        					break;
        		case 'r' :	vendlist = new Principal('v');
							break;
        		default: break;
        	}
        }
    }
    /**
     * Estabelece valor da variável global cod1
     * Utilizada para identificar código do produto ou fornecedor.
     * @param param define diálogos do produto ou fornecedor.
     */
    public void setcodigo(char param){
        cod1 = 0;
        String msg1 = null, msg2 = null;
        if(param == 'p'){
            msg1  = "Código do Produto:";
            msg2 = "Código do Produto Inválido.";
        }
        if(param == 'f'){
            msg1 = "Código do Fornecedor:";
            msg2 = "Código do Fornecedor Inválido.";
        }
        String aux = JOptionPane.showInputDialog(msg1);
        if(aux != null && aux.length() > 0){
            try{
                cod1 = Integer.parseInt(aux);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,msg2);
            }
        }
    }
    /**
     * Estabelece valor da variável global cod2
     * Utilizada para identificar código de compra.
     * @param prod código do produto.
     */
    public void setcodCompra(int prod){
        cod2 = 0;
        String aux = JOptionPane.showInputDialog("Produto: "+control.getNomeProduto(prod)+" - Código: "+prod+"\nCódigo da Compra:");
        if(aux != null && aux.length() > 0){
            try{
                cod2 = Integer.parseInt(aux);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Código da Compra Inválido.");
            }
        }
    }
    /**
     * Estabelece valor da valor da variável global cod3
     * Utilizada para identificar Código de revenda.
     */
    public void setcodVenda(){
        cod3 = 0;
        String aux = JOptionPane.showInputDialog("Produto: "+control.getNomeProduto(cod1)+" - Código: "+cod1+"\nCódigo da Compra: "+cod2+"\nCódigo de Revenda:");
        if(aux != null && aux.length() > 0){
            try{
                cod3 = Integer.parseInt(aux);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Código de Revenda inválido.");
            }
        }
    }
    
    public static void main(String[] args) {
		System.out.printf("Programa de Controle de Estoque\n");
		System.out.printf("-------------------------------\n");
		control = new Controlador();
		new Principal('0');
	}

}