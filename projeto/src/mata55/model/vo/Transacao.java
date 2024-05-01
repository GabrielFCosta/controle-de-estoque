package mata55.model.vo;

import java.util.Calendar;
import java.io.Serializable;

/**
 * Contém atributos e métodos em comum entre classes de compra
 * e venda. Classe pai dessas duas outras classes. Implementa interfaces
 * serializable, necessária para gravação de objetos em arquivos binários,
 * e Comparable, necessária para ordenação do ArrayList.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Transacao  implements Serializable,Comparable<Transacao>{

    private static final long serialVersionUID = 8097771933328660365L;

    private int codigo;

    private int dia;

    private  int mes;

    private int ano;

    private double quantidade;

    private double precounitario;

    private double total;

    private boolean status;
    /**
     * Construtor da classe.
     * Define o status da transação como true.
     */
    Transacao() {
        this.status = true;
    }
    /**
     * Define código único da transação.
     * @param codigo int.
     */
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    /**
     * Define data da transação. A partir do método getInstance() da
     * classe Calendar obtém valores inteiros para o dia, mês e ano do
     * registro da transação no sistema.
     */
    public void setData() {
        Calendar data = Calendar.getInstance();
        dia = data.get(Calendar.DAY_OF_MONTH);
        mes = data.get(Calendar.MONTH)+1;
        ano = data.get(Calendar.YEAR);
    }
    /**
     * Define quantidade do produto negociada pela transação. Podendo ser
     * um valor unitário ou o peso total do produto, por isso o tipo double.
     * @param quantidade double.
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * Define o valor monetário de uma unidade do produto negociado pela
     * transação.
     * @param preco double.
     */
    public void setPrecounitario(double preco) {
        this.precounitario = preco;
    }
    /**
     * Define o valor monetário total da transação. quantidade x preço
     * unitário.
     * @param qtd double.
     * @param prc double.
     */
    public void setTotal(double qtd, double prc){
        total = qtd*prc;
    }
    /**
     * Define o estado da transação, se ativo ou inativo.
     * Funcionalidade de exclusão da listagem sem
     * remover objeto da base de dados.
     * @param status boolean.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * Retorna o código único da transação.
     * @return codigo int.
     */
    public int getCodigo(){
        return codigo;
    }
    /**
     * Retorna o dia do mês do registro da transação.
     * @return dia int.
     */
    public int getDia() {
        return dia;
    }
    /**
     * Retorna o mês do registro da transação.
     * @return mes int.
     */
    public int getMes() {
        return mes;
    }
    /**
     * Retorna o ano do registro da transação.
     * @return ano int.
     */
    public int getAno() {
        return ano;
    }
    /**
     * Retorna a quantidade do produto negociada pela transação.
     * @return quantidade double.
     */
    public double getQuantidade() {
        return quantidade;
    }
    /**
     * Retorna o preço unitário do produto negociado.
     * @return precounitario double.
     */
    public double getPrecounitario() {
        return precounitario;
    }
    /**
     * Retorna o valor total da transação como definido pelo método
     * setTotal().
     * @return total double.
     */
    public double getTotal(){
        return total;
    }
    /**
     * Retorna o estado da transação.
     * @return status boolean.
     */
    public boolean getStatus() {
        return status;
    }
    @Override // modficado método compareTo para ordenação por codigo.
    public int compareTo(Transacao outro) {
        if(this.getCodigo() < outro.getCodigo()) {
            return -1;
        }
        if(this.getCodigo() > outro.getCodigo()) {
            return 1;
        }
        return 0;
    }

}
