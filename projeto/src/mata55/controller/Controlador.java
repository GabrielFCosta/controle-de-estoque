package mata55.controller;

import mata55.model.dao.setDAO;
import mata55.model.bo.ProdutoBO;
import mata55.model.bo.PessoaBO;
import mata55.model.vo.Pessoa;
import mata55.model.vo.Endereco;
import mata55.model.vo.Produto;
import mata55.model.vo.Compra;
import mata55.model.vo.Revenda;

/**
 * Classe do controlador.
 * @author Gabriel Gonçalves Faria Costa
 */
public class Controlador{

    private setDAO DAO;
    private PessoaBO pessoas; //set de pessoas.
    private ProdutoBO produtos; //set de sets aninhados de produtos, compras e vendas.

    //variáveis globais auxiliares para cadastros.
    private Pessoa PSaux;
    private Produto PDaux;
    private Compra CPaux;
    private Revenda VDaux;

    public Controlador(){
    	DAO = new setDAO();
    }
    
    /**
     * Retorna preço unitário de uma compra.
     * Utilizado em RevendaCAD para definir preço de revenda
     * com base em margem de lucro.
     * @param prod código do produto
     * @param comp código da compra
     * @return produtos.getProduto(prod).getStcompras().getCompra(comp).getPrecounitario()
     */
    public double getPrecoCompra(int prod,int comp) {
    	return produtos.getProduto(prod).getStcompras().getCompra(comp).getPrecounitario();
    }
    /**
     * Retorna dados completos do fornecedor.
     * Utilizado na consulta do fornecedor.
     * @param fornecedor código do fornecedor
     * @return null se código não encontrado
     */
    public String getString(int fornecedor) {
    	PSaux = pessoas.getPessoa(fornecedor);
    	if(PSaux != null) {
    		String status = "Código: "+fornecedor+" - Status: ";
    		if(PSaux.getStatus())
    			status += "Ativo\n";
    		else status += "Cancelado\n";
    		String nome = "Nome: "+PSaux.getNome()+" - Sobrenome: "+PSaux.getSobrenome()+"\n";
    		String contato = "Telefone: "+PSaux.getTelefone()+" - E-mail: "+PSaux.getEmail()+"\n";
    		String razao = "Razão Social: "+PSaux.getRazao()+"\n";
    		String end1 = "Endereço:\n"+"Logradouro: "+PSaux.getEndereco().getLogradouro()+" - Número: "+PSaux.getEndereco().getNumero()+"\n";
    		String end2 = "Bairro: "+PSaux.getEndereco().getBairro()+" - Cidade: "+PSaux.getEndereco().getCidade()+"\n";
    		String end3 = "Estado: "+PSaux.getEndereco().getEstado()+" - País: "+PSaux.getEndereco().getPais()+"\n";
    		String end4 = "CEP: "+PSaux.getEndereco().getCodpostal()+"\n";
    		return status+nome+contato+razao+end1+end2+end3+end4;
    	}
    	return null;
    }
    /**
     * Cadastro de revenda.
     * @param prod código do produto
     * @param comp código do lote de compra
     * @param qt quantidade revendida
     * @param pc preço unitário de revenda
     * @return true se revenda registrada
     */
    public boolean cadVenda(int prod, int comp, double qt, double pc) {
    	PDaux = produtos.getProduto(prod);
    	CPaux = PDaux.getStcompras().getCompra(comp);
    	if(qt <= CPaux.getContador()){ //verifica se quantidade menor ou igual a quantidade disponível no lote.
    		VDaux = new Revenda();
            VDaux.setData();
            // getsize() retorna contador do Hashset e não o seu tamanho.
            VDaux.setCodigo(CPaux.getStvendas().getSize()+1);
            VDaux.setQuantidade(qt);
            VDaux.setPrecounitario(pc);
            VDaux.setTotal(qt,pc);
            /*
             * Adiciona Revenda ao set de vendas da compra.
             * Decrementa contadores da compra e do produto.
             * Atualiza resultados da compra e do produto.
             */
            if(CPaux.getStvendas().add(VDaux)){
                CPaux.setContador(qt,false);
                PDaux.setContador(qt,false);
                CPaux.setResultado(VDaux.getTotal());
                PDaux.setResultado(VDaux.getTotal());
                return true;
            }  
    	}
    	return false;
    }
    
    /**
     * Cadastro de lote de compra de um produto.
     * @param prod código do produto
     * @param forn código do fornecedor
     * @param qt quantidade comprada
     * @param dc observações sobre compra
     * @param pc preço unitário 
     * @return true se cadastrado
     */
    public boolean cadCompra(int prod,int forn,double qt,String dc,double pc) {
    	PDaux = produtos.getProduto(prod);
    	PSaux = pessoas.getPessoa(forn);
    	CPaux = new Compra(PSaux.getCodigo());
        CPaux.setData();
        // getsize() retorna contador do Hashset e não o seu tamanho.
        CPaux.setCodigo(PDaux.getStcompras().getSize()+1);
        CPaux.setMarca(dc);
        CPaux.setQuantidade(qt);
        CPaux.setContador(qt,true);
        CPaux.setPrecounitario(pc);
        CPaux.setTotal(qt,pc);
        CPaux.setResultado(-(CPaux.getTotal()));
        /*
         * Adiciona compra ao set de compras do produto.
         * Atualiza reesultado e contador do produto.
         */
        if(PDaux.getStcompras().add(CPaux)){
            PDaux.setResultado(CPaux.getResultado());
            PDaux.setContador(CPaux.getQuantidade(),true);
            return true;
        }
        return false;
    }
    /**
     * Cadastro de Fornecedores.
     * @param nm nome
     * @param sm sobrenome
     * @param tl telefone
     * @param em e-mail
     * @param rz razão social (se empresa)
     * @param lg logradouro (endereço)
     * @param no número (endereço)
     * @param br bairro (endereço)
     * @param cd cidade (endereço)
     * @param uf estado (endereço)
     * @param ps país (endereço)
     * @param cp cep (endereço)
     * @return pessoas.add(PSaux) true se cadastrado
     */
    public boolean cadPessoa(String nm,String sm,String tl,String em,String rz,String lg,String no,String br,String cd,String uf,String ps,String cp){
    	PSaux = new Pessoa();
    	// getsize() retorna contador do Hashset e não o seu tamanho. 
        PSaux.setCodigo(pessoas.getSize()+1);
        PSaux.setNome(nm);
        PSaux.setSobrenome(sm);
        PSaux.setTelefone(tl);
        PSaux.setEmail(em);
        PSaux.setRazao(rz);
        Endereco novo = new Endereco();
        novo.setLogradouro(lg);
        novo.setNumero(no);
        novo.setBairro(br);
        novo.setCidade(cd);
        novo.setEstado(uf);
        novo.setPais(ps);
        novo.setCodpostal(cp);
        PSaux.setEndereco(novo);
        return pessoas.add(PSaux);
    }
    /**
     * Cadastro de Produtos.
     * @param nome do produto
     * @param descricao descrição do produto
     * @param medida unidade de medida do produto
     * @return produtos.add(PDaux) true se cadastrado
     */
    public boolean cadProduto(String nome, String descricao, String medida){
        PDaux = new Produto();
        // getsize() retorna contador do Hashset e não o seu tamanho. 
        PDaux.setCodigo(produtos.getSize()+1);
        PDaux.setNome(nome);
        PDaux.setDescricao(descricao);
        PDaux.setMedida(medida);
        return produtos.add(PDaux);
    }
    /**
     * Retorna matriz de fornecedores ativos (sem endereços) do HashSet
     * de pessoas.
     * @return pessoas.getMatriz()
     */
    public String[][] getVetorPessoas(){
        return pessoas.getMatriz();
    }
    /**
     * Retorna matriz de produtos ativos do HashSet de produtos.
     * @return produtos.getMatriz()
     */
    public String[][] getVetorProdutos(){
        return produtos.getMatriz();
    }
    /**
     * Retorna matriz de compras ativas de um determinado produto definido
     * por parâmetro.
     * @param codigo int código do produto
     * @return produtos.getProduto(codigo).getStcompras().getMatriz()
     */
    public String[][] getVetorCompras(int codigo){
        return produtos.getProduto(codigo).getStcompras().getMatriz();
    }
    /**
     * Retorna matriz de revendas ativas de um determinado lote de compra
     * de um produto, ambos definidos por parâmetro.
     * @param codigo int código do produto
     * @param codCompra int código da compra
     * @return produtos.getProduto(codigo).getStcompras().getCompra(codCompra).getStvendas().getMatriz()
     */
    public String[][] getVetorRevendas(int codigo, int codCompra){
        return produtos.getProduto(codigo).getStcompras().getCompra(codCompra).getStvendas().getMatriz();
    }
    /**
     * Retorna estado (true ou false) do fornecedor definido por parâmetro.
     * @param codigo int código da pessoa
     * @return pessoas.checkPessoa(codigo)
     */
    public boolean checkCodFornecedor(int codigo){
        return pessoas.checkPessoa(codigo);
    }
    /**
     * Retorna estado (true ou false) do produto definido por parâmetro.
     * @param codigo int código do produto
     * @return produtos.checkProduto(codigo)
     */
    public boolean checkCodProduto(int codigo){
        return produtos.checkProduto(codigo);
    }
    /**
     * Retorna estado (true ou false) da compra definida por parâmetro.
     * @param codigo int código do produto
     * @param codCompra int código da compra
     * @return produtos.getProduto(codigo).getStcompras().checkCompra(codCompra)
     */
    public boolean checkCodCompra(int codigo, int codCompra){
        return produtos.getProduto(codigo).getStcompras().checkCompra(codCompra);
    }
    /**
     * Retorna estado (true ou false) da revenda definida por parâmetro.
     * @param codigo int código do produto
     * @param codCompra int código da compra
     * @param codVenda int código da revenda
     * @return produtos.getProduto(codigo).getStcompras().getCompra(codCompra).getStvendas().checkVenda(codVenda)
     */
    public boolean checkCodRevenda(int codigo,int codCompra,int codVenda){
        return produtos.getProduto(codigo).getStcompras().getCompra(codCompra).getStvendas().checkVenda(codVenda);
    }
    /**
     * Retorna nome do produto de acordo com código do parâmetro.
     * @param codigo int
     * @return produtos.getProduto(codigo).getNome() String
     */
    public String getNomeProduto(int codigo){
        return produtos.getProduto(codigo).getNome();
    }
    /**
     * Desativa pessoa da listagem de fornecedores.
     * @param codigo int da pessoa
     */
    public void removePessoa(int codigo){
        PSaux = pessoas.getPessoa(codigo);
        PSaux.setStatus(false);
    }
    /**
     * Desativa produto da listagem de produtos.
     * @param codigo int do produto
     */
    public void removeProduto(int codigo){
        PDaux = produtos.getProduto(codigo);
        PDaux.setStatus(false);
    }
    /**
     * Decrementa contador do produto com o contador da compra.
     * Desativa a compra e atualiza balanço do produto com balanço negativo da compra.
     * @param codigo int do produto
     * @param codCompra int da compra a ser desativada
     */
    public void removeCompra(int codigo, int codCompra){
        PDaux = produtos.getProduto(codigo);
        CPaux = PDaux.getStcompras().getCompra(codCompra);
        PDaux.setContador(CPaux.getContador(),false);
        PDaux.setResultado(-CPaux.getResultado());
        CPaux.setStatus(false);
    }
    /**
     * Incrementa contadores da respectiva compra e do produto com a quantidade da revenda.
     * Desativa a revenda e atualiza balanços da compra e produto com total negativo da revenda.
     * @param codigo int do produto
     * @param codCompra int da compra
     * @param codVenda int da revenda a ser desativada
     */
    public void removeRevenda(int codigo, int codCompra, int codVenda){
        PDaux = produtos.getProduto(codigo);
        CPaux = PDaux.getStcompras().getCompra(codCompra);
        VDaux = CPaux.getStvendas().getVenda(codVenda);
        double valor = VDaux.getQuantidade();
        PDaux.setContador(valor,true);
        CPaux.setContador(valor,true);
        valor = -VDaux.getTotal();
        PDaux.setResultado(valor);
        CPaux.setResultado(valor);
        VDaux.setStatus(false);
    }
    /**
     * Grava arquivo produtos.bin.
     */
    public void gravaProdutos(){
        if(produtos.getSize() > 0){
            DAO.salvaArquivo("produtos.bin",null,produtos);
        }
    }
    /**
     * Grava arquivo pessoas.bin.
     */
    public void gravaPessoas(){
        if(pessoas.getSize() > 0){
            DAO.salvaArquivo("pessoas.bin",pessoas,null);
        }
    }
    /**
     * Carrega HashSet de pessoas com conteúdo do arquivo pessoas.bin.
     */
    public void carregaPessoas() {
        pessoas = DAO.loadPessoas();
    }
    /**
     * Carrega HashSet de produtos com conteúdo do arquivo produtos.bin.
     */
    public void carregaProdutos() {
    	 produtos = DAO.loadProdutos();
    }
   
}
