package mata55.model.vo;

import java.util.*;
import java.io.Serializable;

/**
 * Classe do HashSet de revendas.
 * Revendaset depende de Revenda.
 * Classe Compra em vo depende de Revendaset para compilação.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Revendaset extends Colecao implements Serializable {

    private static final long serialVersionUID = -5562866532644913823L;

    private HashSet<Revenda> stvendas;
    /**
     * Construtor de Colecao inicia contador de registros com valor zero.
     * Instancia um novo HashSet de objetos Revenda.
     */
    Revendaset() {
    	super();
        this.stvendas = new HashSet<Revenda>();
    }
    /**
     * Adiciona novas instâncias de revendas
     * e incrementa contador do HashSet.
     * Retorna true se nova revenda adicionada ou false caso contrário.
     * @param nova Revenda a ser adicionada ao set.
     * @return true ou false.
     */
    public boolean add(Revenda nova){
        if(stvendas.add(nova)) {
        	this.setSize();
        	return true;
        }
        else return false;
    }
    /**
     * Busca revenda pelo código no set.
     * Retorna objeto do tipo Revenda ou null.
     * @param cod int.
     * @return obj Revenda ou null.
     */
    public Revenda getVenda(int cod){
        for(Revenda obj : this.stvendas){
            if(obj.getCodigo() == cod){
                return obj;
            }
        }
        return null;
    }
    /**
     * Forma simplificada de getVenda().
     * Busca revenda pelo código no set.
     * Retorna status se objeto encontrado.
     * @param codigo int.
     * @return true ou false.
     */
    public boolean checkVenda(int codigo){
        for(Revenda obj : this.stvendas){
            if(obj.getCodigo() == codigo){
                return obj.getStatus();
            }
        }
        return false;
    }
    /**
     * Converte HashSet de revendas em Arraylist.
     * Retorna ArrayList de revendas ordenado por código de transação.
     * @return lista ArrayList de revenda.
     */
    public ArrayList<Revenda> getArray(){
        ArrayList<Revenda> lista = new ArrayList<Revenda>(stvendas);
        Collections.sort(lista);
        return lista;
    }
    /**
     * Converte HashSet de revendas em matriz a ser apresentada na JTable.
     * Retorna matriz de revendas ordenada por código de revenda.
     * @return matriz de revendas.
     */
    public String[][] getMatriz(){
        int i = 0;
        for(Revenda obj : this.stvendas){
            if(obj.getStatus())
                i++;
        }
        String[][] matriz = new String[i][5];
        i = 0;
        for(Revenda obj : this.getArray()){
            if(obj.getStatus()){
                String  date = Integer.toString(obj.getDia());
                date = date.concat("/");
                date = date.concat(Integer.toString(obj.getMes()));
                date = date.concat("/");
                date = date.concat(Integer.toString(obj.getAno()));
                matriz[i][0] = Integer.toString(obj.getCodigo());
                matriz[i][1] = date;
                matriz[i][2] = String.format ("%.2f",obj.getQuantidade());
                matriz[i][3] = String.format ("%.2f",obj.getPrecounitario());
                matriz[i][4] = String.format ("%.2f",obj.getTotal());
                i++;
            }
        }
        return matriz;
    }

}
