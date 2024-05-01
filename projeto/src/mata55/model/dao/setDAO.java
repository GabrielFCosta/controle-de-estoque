package mata55.model.dao;

import mata55.model.bo.ProdutoBO;
import mata55.model.bo.PessoaBO;
import java.io.*;

/**
 * Classe setDAO faz o gerenciamento dos arquivos das bases de dados do sistema.
 * 1) Carrega os HashSets de pessoas (incluindo endereço da pessoa)
 * e de produtos (com Respectivos HashSets de compras e vendas aninhados) a partir
 * dos arquivos binários pessoas.bin e produtos.bin, respectivamente.
 * Se arquivos nao existirem ou ocorrer alguma exceção classe retorna HashSets vazios.
 * 2) Salva HashSets nos mesmos arquivos, pessoas.bin e produtos.bin, utilizados para carregamento.
 * @author Gabriel Gonçalves Faria Costa
 */
public class setDAO{

    //variáveis para carregamento dos arquivos.
    private ObjectInputStream objIN;
    private FileInputStream fileIN;
    /**
     * Construtor da classe.
     * define objIN e fileIN como null.
     */
    public setDAO(){
        objIN = null;
        fileIN = null;
    }
    /**
     * Carrega base de dados de pessoas, pesssoas.bin, no HashSet de Pessoas.
     * Utiliza métodos getFile(String filename) e getObject(FileInputStream file, String n)
     * internamente.
     * @return pessoas carregado ou vazio.
     */
    public PessoaBO loadPessoas(){
        PessoaBO pessoas = new PessoaBO();
        fileIN = getFile("pessoas.bin");
        if(fileIN != null){
            objIN = getObject(fileIN,"pessoas");
            if(objIN != null){
                try{
                    pessoas = (PessoaBO) objIN.readObject();
                    fileIN.close();
                    objIN.close();
                    System.out.printf("Set de pessoas carregado.\n");
                    return pessoas;
                }
                catch(IOException e){
                    System.out.printf("erro de entrada e saida: pessoas.\n");
                    return pessoas;
                }
                catch(ClassNotFoundException e){
                    System.out.printf("classe nao encontrada: PessoaBO\n");
                    try{
                        fileIN.close();
                        objIN.close();
                    }catch(IOException f){}
                    return pessoas;
                }
                catch(ClassCastException e){
                    System.out.printf("cast de classe incompativel: PessoaBO\n");
                    try{
                        fileIN.close();
                        objIN.close();
                    }catch(IOException f){}
                    return pessoas;
                }

            }
        }
        return pessoas;
    }
    /**
     * Carrega base de dados de produtos, produtos.bin, no HashSet de produtos.
     * Utiliza métodos getFile(String filename) e getObject(FileInputStream file, String n)
     * internamente.
     * @return produtos carregado ou vazio.
     */
    public ProdutoBO loadProdutos(){
        ProdutoBO produtos = new ProdutoBO();
        fileIN = getFile("produtos.bin");
        if(fileIN != null){
            objIN = getObject(fileIN,"produtos");
            if(objIN != null){
                try{
                    produtos = (ProdutoBO) objIN.readObject();
                    fileIN.close();
                    objIN.close();
                    System.out.printf("Set de produtos carregado.\n");
                    return produtos;
                }
                catch(IOException e){
                    System.out.printf("erro de entrada e saida: produtos.\n");
                    return produtos;
                }
                catch(ClassNotFoundException e){
                    System.out.printf("classe nao encontrada: ProdutoBO\n");
                    try{
                        fileIN.close();
                        objIN.close();
                    }catch(IOException f){}
                    return produtos;
                }
                catch(ClassCastException e){
                    System.out.printf("cast de classe incompativel: ProdutoBO\n");
                    try{
                        fileIN.close();
                        objIN.close();
                    }catch(IOException f){}
                    return produtos;
                }
            }
        }
        return produtos;

    }
    /**
     * Retorna objeto do tipo FileInputStream utilizado no carregamento de arquivos
     * @param filename String do nome do arquivo a ser carregado.
     * @return file ou null.
     */
    public FileInputStream getFile(String filename){
        try{
            FileInputStream file = new FileInputStream(filename);
            return file;
        }
        catch(FileNotFoundException e){
            System.out.printf("Arquivo nao encontrado: %s\n",filename);
            return null;
        }
        catch(SecurityException e){
            System.out.printf("Acesso negado: %s\n",filename);
            return null;
        }
    }
    /**
     * Retorna objeto do tipo ObjectInputStream utilizado no carregamento de arquivos
     * @param file objeto do tipo FileInputStream.
     * @param n String do nome do set a ser carregado.
     * @return objeto ou null.
     */
    public ObjectInputStream getObject(FileInputStream file, String n){
        try{
            ObjectInputStream objeto = new ObjectInputStream(file);
            return objeto;
        }
        catch(StreamCorruptedException e){
            System.out.printf("stream header incorreto: %s\n",n);
            return null;
        }
        catch(IOException e){
            System.out.printf("erro de entrada e saida: %s\n",n);
            return null;
        }
        catch(SecurityException e){
            System.out.printf("erro de seguran�a: %s\n",n);
            return null;
        }
    }
    /**
     * Retorna objeto do tipo FileOutputStream utilizado na gravação de arquivos
     * @param filename String do nome do arquivo a ser gravado.
     * @return file ou null.
     */
    public FileOutputStream getFileOut(String filename){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            return file;
        }
        catch(FileNotFoundException e){
                System.out.printf("Arquivo nao encontrado\n");
                return null;
        }
        catch(SecurityException e){
                System.out.printf("Acesso negado\n");
                return null;
        }
    }
    /**
     * Retorna objeto do tipo ObjectOutputStream utilizado na gravação de arquivos
     * @param file objeto do tipo FileOutputStream.
     * @return objeto ou null.
     */
    public ObjectOutputStream getObjectOut(FileOutputStream file){
        try{
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            return objeto;
        }
        catch(IOException e){
            System.out.printf("erro de entrada e saida\n");
            return null;
        }
        catch(SecurityException e){
            System.out.printf("erro de seguranca\n");
            return null;
        }
    }
    /**
     * Retorna true se arquivo gravado ou false se ocorreu alguma exceção.
     * @param obj objeto do tipo ObjectOutputStream.
     * @param pessoas Set de pessoas a ser gravado ou nulo.
     * @param produtos Set de produtos a ser gravado ou nulo.
     * @return true arquivo gravado, false se ocorreu alguma exceção.
     */
    public boolean escritaArquivo(ObjectOutputStream obj,PessoaBO pessoas, ProdutoBO produtos){
        try{
            //Um dos sets sera sempre nulo. Arquivo a ser escrito sera o do set não nulo.
            if(pessoas != null){
                obj.writeObject(pessoas);
            }
            else{
                obj.writeObject(produtos);
            }
            obj.close();
            return true;
        }
        catch(InvalidClassException e){
            System.out.printf("classe invalida.\n");
            return false;
        }
        catch(NotSerializableException e){
            System.out.printf("Objeto nao serializavel.\n");
            return false;
        }
        catch(IOException e){
            System.out.printf("Erro de entrada e saida.\n");
            return false;
        }
    }
    /**
     * Tenta gravar set em arquivo binario de acordo com parâmetros.
     * Utiliza internamente os métodos getFileOut(String filename), getObjectOut(FileOutputStream file)
     * e escritaArquivo(ObjectOutputStream obj,boolean param).
     * @param filename String do nome do arquivo a ser salvo.
     * @param pessoas Set de pessoas a ser gravado ou nulo.
     * @param produtos Set de produtos a ser gravado ou nulo.
     */
    public void salvaArquivo(String filename, PessoaBO pessoas, ProdutoBO produtos){

        //variáveis para gravação dos arquivos.
        ObjectOutputStream objOUT = null;
        FileOutputStream fileOUT = null;
        System.out.printf("Salvando arquivo %s...",filename);
        fileOUT = getFileOut(filename);
        if(fileOUT != null){
            objOUT = getObjectOut(fileOUT);
            if(objOUT != null){
                if(filename.equals("pessoas.bin")){
                    if(!(escritaArquivo(objOUT, pessoas, produtos))){
                        System.out.printf("Erro\n");
                    }
                    else System.out.printf("Ok\n");
                }
                if(filename.equals("produtos.bin")){
                    if(!(escritaArquivo(objOUT, pessoas, produtos))){
                        System.out.printf("Erro\n");
                    }
                    else System.out.printf("Ok\n");
                }
            }
            else System.out.printf("Erro: Objeto nulo.\n");
        }
        else System.out.printf("Erro: Arquivo nulo.\n");
    }
}
