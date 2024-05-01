package mata55.model.vo;

import java.io.Serializable;

/**
 * Atributos básicos do cadastro de pessoa.
 * Classe Pessoa corresponde aos fornecedores instanciados na classe de compra.
 * Elemento que compõe o HashSet PessoaBO.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Pessoa implements Serializable,Comparable<Pessoa> {

    private static final long serialVersionUID = 8874186786783365493L;

    private int codigo;

    private String nome;

    private String sobrenome;

    private String razao;

    private Endereco endereco;

    private String telefone;

    private String email;

    private boolean status;
    /**
     * Construtor da classe.
     * define o status da pessoa como true.
     */
    public Pessoa() {
        this.status = true;
    }
    /**
     * Define código único da pessoa.
     * @param codigo int.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /**
     * Define nome da pessoa.
     * @param nome String.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Define sobrenome da pessoa.
     * @param sobrenome String.
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    /**
     * Define razão social.
     * @param razao String.
     */
    public void setRazao(String razao){
        this.razao = razao;
    }
    /**
     * Define endereço.
     * @param endereco Endereco.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    /**
     * Define número de telefone.
     * @param telefone String.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    /**
     * Define email.
     * @param email String.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Define o estado da pessoa, se ativo ou inativo.
     * Funcionalidade de exclusão da listagem sem
     * remover objeto da base de dados.
     * @param status boolean.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * Retorna nome.
     * @return nome String.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Retorna sobrenome.
     * @return sobrenome String.
     */
    public String getSobrenome() {
        return sobrenome;
    }
    /**
     * Retorna razão social.
     * @return razao String.
     */
    public String getRazao() {
        return razao;
    }
    /**
     * Retorna objeto endereço.
     * @return endereco Endereco.
     */
    public Endereco getEndereco() {
        return endereco;
    }
    /**
     * Retorna número de telefone.
     * @return telefone String.
     */
    public String getTelefone() {
        return telefone;
    }
    /**
     * Retorna email.
     * @return email String.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Retorna código único da pessoa.
     * @return codigo int.
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Retorna o estado da pessoa.
     * @return status boolean.
     */
    public boolean getStatus() {
        return status;
    }
    @Override // modficado método compareTo para ordenação por código.
    public int compareTo(Pessoa outro) {
        if(this.getCodigo() < outro.getCodigo()) {
            return -1;
        }
        if(this.getCodigo() > outro.getCodigo()) {
            return 1;
        }
        return 0;
    }
}
