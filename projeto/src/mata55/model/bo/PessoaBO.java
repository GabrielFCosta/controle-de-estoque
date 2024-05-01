package mata55.model.bo;

import mata55.model.vo.Pessoa;
import mata55.model.vo.Colecao;
import java.util.*;
import java.io.Serializable;

/**
 * Classe do HashSet de pessoas.
 * PessoaBO depende de Pessoa. Nenhuma classe em vo depende de PessoaBO.
 * Classe PessoaBO manipulada diretamente pela classe gerenciadora de arquivo setDAO.
 * @author Gabriel Gonçalves Faria Costa
 */
public class PessoaBO extends Colecao implements Serializable {

    private static final long serialVersionUID = -6197538490549734987L;
	
    private HashSet<Pessoa> stpessoa;
    /**
     * Construtor de Colecao inicia contador de registros com valor zero.
     * Instancia um novo HashSet de objetos Pessoa.
     */
    public PessoaBO() {
    	super();
        this.stpessoa = new HashSet<Pessoa>();
    }
    /**
     * Adiciona novas instâncias de pessoas ao HashSet
     * e incrementa contador.
     * Retorna true se nova pessoa adicionada ou false caso contrário.
     * @param nova pessoa a ser adicionada ao set
     * @return true ou false
     */
    public boolean add(Pessoa nova){
        if(stpessoa.add(nova)){
        	this.setSize();
            return true;
		}
        else return false;
    }
    /**
     * Busca pessoa pelo código único no set.
     * Retorna objeto do tipo Pessoa ou null.
     * @param codigo int
     * @return obj Pessoa ou null
     */
    public Pessoa getPessoa(int codigo){
        for(Pessoa obj : this.stpessoa){
            if(obj.getCodigo() == codigo){
                return obj;
            }
        }
        return null;
    }
    /**
     * Forma simplificada de getPessoa().
     * Busca pessoa pelo código único no set.
     * Retorna status do objeto.
     * @param codigo int
     * @return true ou false
     */
    public boolean checkPessoa(int codigo){
        for(Pessoa obj : this.stpessoa){
            if(obj.getCodigo() == codigo){
                return obj.getStatus();
            }
        }
        return false;
    }
    /**
     * Converte HashSet de pessoas em Arraylist.
     * Retorna ArrayList de pessoas ordenado por nome da pessoa.
     * @return lista ArrayList de objetos Pessoa.
     */
    public ArrayList<Pessoa> getArray(){
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>(stpessoa);
        Collections.sort(lista);
        return lista;
    }
    /**
     * Converte HashSet de pessoas em matriz a ser apresentada na JTable.
     * Retorna matriz de pessoas ordenado por código do fornecedor.
     * @return matriz de pessoas.
     */
    public String[][] getMatriz(){
        int i = 0;
        // quantifica numero de fornecedores ativos.
        for(Pessoa obj : this.stpessoa)
            if(obj.getStatus())
                i++;
        /*
         * declara tamanho da matriz com base na quantidade de fornecedores
         * ativos encontrados anteriormente.
         */
        String[][] matriz = new String[i][6];
        i = 0;
        // registra somente fornecedores ativos na matriz.
        for(Pessoa obj : this.getArray()){
            if(obj.getStatus()){
                matriz[i][0] = Integer.toString(obj.getCodigo());
                matriz[i][1] = obj.getNome();
                matriz[i][2] = obj.getSobrenome();
                matriz[i][3] = obj.getRazao();
                matriz[i][4] = obj.getEmail();
                matriz[i][5] = obj.getTelefone();
                i++;
            }
        }
        return matriz;
    }

}
