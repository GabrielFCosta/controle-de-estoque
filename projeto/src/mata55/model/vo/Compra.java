package mata55.model.vo;

/**
 * Classe de compra de um produto. Contém informações adicionais relativas
 * ao tipo de transação de compra para revenda.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Compra extends Transacao{

    private static final long serialVersionUID = -8050340679834575872L;

    private int fornecedor;

    private double contador;

    private double resultado;

    private String marca;

    private Revendaset stvendas;
    /**
     * Instancia uma transação e um set de revendas para a compra.
     * Define o código do fornecedor por parâmetro.
     * Inicia contador e resultado em zero.
     * @param fornecedor Pessoa.
     */
    public Compra(int fornecedor) {
        super();
        this.stvendas = new Revendaset();
        this.fornecedor = fornecedor;
        this.contador = 0;
        this.resultado = 0;
    }
    /**
     * Define fornecedor do produto. código referente ao código de uma
     * pessoa armazenada em instancia de PessoaBO que representa
     * de quem o usuário adquiriu o produto.
     * @param fornecedor int.
     */
    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }
    /**
     * Define o contador da compra. quantidade do produto dispnível para
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
     * Calcula e define o resultado entre o valor total da compra e o valor
     * das revendas do lote de compra.
     * @param resultado double.
     */
    public void setResultado(double resultado){
        this.resultado += resultado;
    }
    /**
     * Define informações adiconais sobre a compra a critério do usuário.
     * Pode ser utilizada para definir a marca do produto.
     * obs. medida do produto é definida por atributo da classe Produto.
     * @param marca String.
     */
    public void setMarca(String marca){
        this.marca = marca;
    }
    /**
     * Retorna código do fornecedor.
     * @return fornecedor int.
     */
    public int getFornecedor() {
        return fornecedor;
    }
    /**
     * Retorna contador da compra.
     * @return contador double.
     */
    public double getContador(){
        return contador;
    }
    /**
     * Retorna o balanço entre o valor total da compra e o que já foi
     * revendido.
     * @return resultado double.
     */
    public double getResultado(){
        return resultado;
    }
    /**
     * Retorna informações adicionais sobre a compra como inseridas
     * pelo usuário.
     * @return marca String.
     */
    public String getMarca(){
        return marca;
    }
    /**
     * Retorna o HashSet das revendas do lote de compra.
     * @return stvendas Revendaset.
     */
    public Revendaset getStvendas(){
        return stvendas;
    }
}
