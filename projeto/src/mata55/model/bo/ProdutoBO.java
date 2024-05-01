package mata55.model.bo;

import mata55.model.vo.Produto;
import mata55.model.vo.Colecao;
import java.util.*;
import java.io.Serializable;

/**
 * Classe do HashSet de produtos.
 * ProdutoBO depende de Produto. Nenhuma classe em vo depende de ProdutoBO.
 * Classe ProdutoBO manipulada diretamente pela classe gerenciadora de arquivo setDAO.
 * @author Gabriel Gonçalves Faria Costa
 */
public class ProdutoBO extends Colecao implements Serializable {

    private static final long serialVersionUID = 2825166481975374756L;

    private HashSet<Produto> stproduto;
    /**
     * Construtor de Colecao inicia contador de registros com valor zero.
     * Instancia um novo HashSet de objetos Produto.
     */
    public ProdutoBO() {
    	super();
        this.stproduto = new HashSet<Produto>();
    }
    /**
     * Adiciona novas instâncias de produtos
     * e incrementa contador do HashSet de produtos.
     * Retorna true se novo produto adicionado ou false caso contrário.
     * @param novo produto a ser adicionado ao set
     * @return true ou false
     */
    public boolean add(Produto novo){
        if(stproduto.add(novo)) {
        	this.setSize();
            return true;
        }
        else return false;
    }
    /**
     * Busca produto pelo código único no set.
     * Retorna objeto do tipo Produto ou null.
     * @param codigo int
     * @return obj Produto ou null
     */
    public Produto getProduto(int codigo){
        for(Produto obj : this.stproduto){
            if(obj.getCodigo() == codigo){
                return obj;
            }
        }
        return null;
    }
    /**
     * Forma simplificada de getProduto().
     * Busca produto pelo código único no set.
     * Retorna status do objeto.
     * @param codigo int
     * @return true ou false
     */
    public boolean checkProduto(int codigo){
        for(Produto obj : this.stproduto){
            if(obj.getCodigo() == codigo){
                return obj.getStatus();
            }
        }
        return false;
    }
    /**
     * Converte HashSet de produtos em Arraylist.
     * Retorna ArrayList de produtos ordenado por nome do produto.
     * @return lista ArrayList de Produto
     */
    public ArrayList<Produto> getArray(){
        ArrayList<Produto> lista = new ArrayList<Produto>(stproduto);
        Collections.sort(lista);
        return lista;
    }
    /**
     * Converte HashSet de produtos em matriz a ser apresentada na JTable.
     * Retorna matriz de produtos ordenado por código do produto.
     * @return matriz de Produtos
     */
    public String[][] getMatriz(){
        int i = 0;
        // quantifica n�mero de produtos ativos.
        for(Produto obj : this.stproduto)
            if(obj.getStatus())
                i++;
        /*
         * declara tamanho da matriz com base na quantidade de produtos
         * ativos encontrados anteriormente.
         */
        String[][] matriz = new String[i][6];
        i = 0;
        // registra somente produtos ativos na matriz.
        for(Produto obj : this.getArray()){
            if(obj.getStatus()){
                matriz[i][0] = Integer.toString(obj.getCodigo());
                matriz[i][1] = obj.getNome();
                matriz[i][2] = obj.getDescricao();
                matriz[i][3] = obj.getMedida();
                matriz[i][4] = String.format ("%.2f",obj.getContador());
                matriz[i][5] = String.format ("%.2f",obj.getResultado());
                i++;
            }
        }
        return matriz;
    }

}
