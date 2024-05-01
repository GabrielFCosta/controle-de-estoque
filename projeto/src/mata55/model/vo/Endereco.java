package mata55.model.vo;

import java.io.Serializable;

/**
 * Complementa cadastro de pessoa com dados do endereço.
 * Instanciado dentro de classe Pessoa.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Endereco implements Serializable {

    private static final long serialVersionUID = -6044642008725246083L;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private String codpostal;
    /**
     * Construtor da classe.
     */
    public Endereco() {}
    /**
     * Define logradouro.
     * @param logradouro String.
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    /**
     * Define número do imóvel.
     * @param numero String.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Define bairro.
     * @param bairro String.
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    /**
     * Define município.
     * @param cidade String.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    /**
     * Define estado ou UF.
     * @param estado String.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Define país.
     * @param pais String.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    /**
     * Define código postal.
     * @param codpostal String.
     */
    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }
    /**
     * Retorna logradouro.
     * @return logradouro String.
     */
    public String getLogradouro() {
        return logradouro;
    }
    /**
     * Retorna número do imóvel.
     * @return numero String.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Retorna bairro.
     * @return bairro String.
     */
    public String getBairro() {
        return bairro;
    }
    /**
     * Retorna município.
     * @return cidade String.
     */
    public String getCidade() {
        return cidade;
    }
    /**
     * Retorna estado ou UF.
     * @return estado String.
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Retorna país.
     * @return pais String.
     */
    public String getPais() {
        return pais;
    }
    /**
     * Retorna código postal.
     * @return codpostal String.
     */
    public String getCodpostal() {
        return codpostal;
    }
}
