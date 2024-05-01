
package mata55.model.vo;

import java.io.Serializable;

/**
 * Classe Colecao reformulada  para conter atributos e métodos
 * para geração dos códigos únicos dos registros dos HashSets de 
 * pessoas, produtos, compras e revendas.
 * Colecao implementa Serializable pois atributo size precisa 
 * ser gravado em arquivo (e recuperado) juntamente com os registros.
 * Código gerado a partir de um valor de referência diferente do tamanho atual
 * do HashSet possibilita exclusão física de registros do mesmo sem causar conflitos
 * de códigos com registros adicionados posteriormente.  
 * @author Gabriel Gonçalves Faria Costa
 */
public class Colecao implements Serializable  {
	
	private static final long serialVersionUID = -1385144836304371968L;
	
	/**
	 * size mantém um contador de todos os registros adicionados
	 * ao HashSet. Códigos únicos são gerados a partir desse valor.
	 * O valor desse atributo nunca é decrementado, mesmo se um registro
	 * for excluído do set.
	 */
    private int size;
    

	/**
	 * Construtor inicia size com valor zero.
	 */
	public Colecao() {
		size = 0;
	}
	 /**
     * Incrementa contador de registros.
     * Utilizado toda vez que um novo registro é adicionado.
     */
    public void setSize(){
    	size++;
    }
    /**
     * Retorna contador de registros.
     * @return size int.
     */
    public int getSize(){
    	 return size;
    }

}
