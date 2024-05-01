package mata55.model.vo;

import java.util.*;
import java.io.Serializable;

/**
 * Classe do HashSet de compras.
 * Compraset depende de Compra.
 * Classe Produto em vo depende de Compraset para compilação.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Compraset extends Colecao implements Serializable{

    private static final long serialVersionUID = 3591454851956500091L;

    private HashSet<Compra> stcompras;
    /**
     * Construtor de Colecao inicia contador de registros com valor zero.
     * Instancia um novo HashSet de objetos do tipo Compra.
     */
    Compraset() {
    	super();
        this.stcompras = new HashSet<Compra>();
    }
    /**
     * Adiciona novas instâncias de compras
     * e incrementa contador do HashSet.
     * Retorna true se nova compra adicionada ou false caso contrário.
     * @param nova compra a ser adicionada ao set.
     * @return true ou false.
     */
    public boolean add(Compra nova){
        if(stcompras.add(nova)) {
        	this.setSize();
        	return true;
        }
        else return false;
    }
    /**
     * Busca compra pelo código único no set.
     * Retorna objeto do tipo Compra ou null.
     * @param cod int.
     * @return obj Compra ou null.
     */
    public Compra getCompra(int cod){
        for(Compra obj : this.stcompras){
            if(obj.getCodigo() == cod){
                return obj;
            }
        }
        return null;
    }
    /**
     * Forma simplificada de getCompra().
     * Busca compra pelo código único no set.
     * retorna status do objeto se encontrado.
     * @param codigo int.
     * @return true ou false.
     */
    public boolean checkCompra(int codigo){
        for(Compra obj : this.stcompras){
            if(obj.getCodigo() == codigo){
                return obj.getStatus();
            }
        }
        return false;
    }
    /**
     * Converte HashSet de compras em Arraylist.
     * Retorna ArrayList de compras ordenado por código de transação.
     * @return lista ArrayList de compras.
     */
    public ArrayList<Compra> getArray(){
        ArrayList<Compra> lista = new ArrayList<Compra>(stcompras);
        Collections.sort(lista);
        return lista;
    }
    /**
     * Converte HashSet de compras em matriz a ser apresentada na JTable.
     * Retorna matriz de compras ordenado por código de compra.
     * @return matriz de compras.
     */
    public String[][] getMatriz(){
        int i = 0;
        for(Compra obj : this.stcompras){
            if(obj.getStatus())
                i++;
        }
        String[][] matriz = new String[i][9];
        i = 0;
        for(Compra obj : this.getArray()){
            if(obj.getStatus()){
                String  date = Integer.toString(obj.getDia());
                date = date.concat("/");
                date = date.concat(Integer.toString(obj.getMes()));
                date = date.concat("/");
                date = date.concat(Integer.toString(obj.getAno()));
                matriz[i][0] = Integer.toString(obj.getCodigo());
                matriz[i][1] = Integer.toString(obj.getFornecedor());
                matriz[i][2] = date;
                matriz[i][3] = obj.getMarca();
                matriz[i][4] = String.format ("%.2f",obj.getQuantidade());
                matriz[i][5] = String.format ("%.2f",obj.getPrecounitario());
                matriz[i][6] = String.format ("%.2f",obj.getTotal());
                matriz[i][7] = String.format ("%.2f",obj.getContador());
                matriz[i][8] = String.format ("%.2f",obj.getResultado());
                i++;
            }
        }
        return matriz;
    }

}
