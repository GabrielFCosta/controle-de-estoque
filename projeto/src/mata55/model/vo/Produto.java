package mata55.model.vo;

import java.io.Serializable;

/**
 * Cadastro de produtos. Contém informações sobre o nome e tipo de produto
 * e um set de compras próprio para registro de lotes de compras do
 * respectivo produto.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Produto  implements Serializable, Comparable<Produto> {

    private static final long serialVersionUID = 6085622107356238124L;

    private int codigo;

    private Compraset stcompras;

    private String nome;

    private String descricao;

    private String medida;

    private double resultado;

    private double contador;

    private boolean status;
    /**
     * Construtor da classe.
     * Inicia o Compraset do produto.
     * Inicia contador e resultado com valor zero.
     * Define o status do produto como true.
     */
    public Produto() {
        this.stcompras = new Compraset();
        this.resultado = 0;
        this.contador = 0;
        this.status = true;
    }
    /**
     * Define código único do produto.
     * @param codigo int.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /**
     * Define nome do produto.
     * @param nome String.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Define descrição ou variedade do produto
     * @param descricao String.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * Define medida de unidade do produto.
     * @param medida String.
     */
    public void setMedida(String medida) {
        this.medida = medida;
    }
    /**
     * Define balanço entre o total de compras e vendas para aquele produto.
     * Ou o somatório dos balanços de todas as compras do produto.
     * @param resultado double (R$).
     */
    public void setResultado(double resultado){
        this.resultado += resultado;
    }
    /**
     * Define o contador do produto. quantidade disponível para
     * revenda.
     * @param qtd double quantidade.
     * @param param booleano false para decremento.
     */
    public void setContador(double qtd, boolean param){
        if(param == true)
            contador += qtd;
        else contador -= qtd;
    }
    /**
     * Define o estado do produto, se ativo ou inativo.
     * Funcionalidade de exclusão do produto da listagem sem
     * remover objeto da base de dados.
     * @param status boolean.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * Retorna código único do produto.
     * @return codigo int.
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Retorna o nome do produto.
     * @return nome String.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Retorna descrição do produto como inserida pelo usuário.
     * @return descricao String.
     */
    public String getDescricao() {
        return descricao;
    }
     /**
     * Retorna medida de unidade do produto.
     * @return medida String.
     */
    public String getMedida() {
        return medida;
    }
    /**
     * Retorna o set de compras do produto.
     * @return stcompras Compraset;
     */
    public Compraset getStcompras(){
        return stcompras;
    }
    /**
     * Retorna o resultado financeiro do produto.
     * @return resultado double;
     */
    public double getResultado(){
        return resultado;
    }
    /**
     * Retorna contador do produto.
     * @return contador double;
     */
    public double getContador(){
        return contador;
    }
    /**
     * Retorna o estado do produto.
     * @return status boolean;
     */
    public boolean getStatus() {
        return status;
    }
    @Override // modficado método compareTo para ordenação por código.
    public int compareTo(Produto outro) {
        if(this.getCodigo() < outro.getCodigo()) {
            return -1;
        }
        if(this.getCodigo() > outro.getCodigo()) {
            return 1;
        }
        return 0;
    }

}
