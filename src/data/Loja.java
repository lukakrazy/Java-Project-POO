package data;

import console.controllers.Comandos;
import console.controllers.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Loja implements Serializable {

    private String nome;
    private String morada;
    private int telefone;
    private String dataFundacao;
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Venda> vendas = new ArrayList<>();
    private ArrayList<Categoria> categorias = new ArrayList<>();

    //---------------------------Constructors---------------------------
    public Loja(String nome, String dataFundacao, String morada) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.morada = morada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", telefone=" + telefone +
                ", dataFundacao='" + dataFundacao + '\'' +
                ", funcionarios=" + funcionarios +
                ", clientes=" + clientes +
                ", produtos=" + produtos +
                ", categorias=" + categorias +
                ", vendas=" + vendas +
                '}';
    }


    //_______________________ METODO PESSOAS _______________________
    public static <T extends Pessoa> void inscrever(ArrayList<T> lista, T entidade) {
        if (!lista.contains(entidade)) {
            lista.add(entidade);
        } else {
            Comandos.mensagemErro("Erro ao adicionar a entidade.");
        }
    }

    public static <T extends Pessoa> T getPessoaById(ArrayList<T> lista, int id) {
        for (T entidade : lista) {
            if (entidade.getId() == id) {
                return entidade;
            }
        }
        return null;
    }

    public static <T extends Pessoa> T getPessoaByNIF(ArrayList<T> lista, int nif) {
        for (T entidade : lista) {
            if (entidade.getNif() == nif) {
                return entidade;
            }
        }
        return null;
    }
    //_______________________ METODOS VENDAS _______________________

    public  void registarVenda(Venda venda){
        if (!vendas.contains(venda)){
            vendas.add(venda);
        }else{
            Comandos.mensagemErro("Erro");
        }
    }
    public Venda getVendaById(int id) {
        for (int i = 0; i < vendas.size(); i++) {
            if (vendas.get(i).getId() == id) {
                return vendas.get(i);
            }
        }
        return null;
    }
    public void eliminarVenda(Venda venda){
        if (vendas.contains(venda)){
            vendas.remove(venda);
        }else{
            Comandos.mensagemErro("Venda não encontrada");
        }
    }

    //_______________________ METODOS FUNCIONARIO _______________________
    public void increveFuncionario(Funcionario funcionario) {
        inscrever(funcionarios, funcionario);
    }

    public Funcionario getFuncionariosById(int id) {
        return  getPessoaById(funcionarios, id);
    }
    public Funcionario getFuncionariosByNIF(int nif) {

        return getPessoaByNIF(funcionarios, nif);
    }
    //_______________________ METODOS CLIENTE _______________________
    public void increveCliente(Cliente cliente) {
        inscrever(clientes, cliente);
    }

    public Cliente getClienteById(int id) {

        return getPessoaById(clientes, id);
    }
    public Cliente getClienteByNIF(int nif) {

        return getPessoaByNIF(clientes, nif);
    }

    //_______________________ METODOS CATEGORIAS _______________________
    public void criarCat(Categoria categoria) {
        if (!categorias.contains(categoria)){
            categorias.add(categoria);

        }else{
            Comandos.mensagemErro("Erro");
        }
    }
    public Categoria getCategoriaById(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    public void removerCat(Categoria categorias) {
        this.categorias.remove(categorias);
    }

    //_______________________ METODOS PRODUTOS _______________________
    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)){
            produtos.add(p);
        }else{
            Comandos.mensagemErro("Erro");
        }
    }
    public ArrayList<Produto> getProdutoByCategoria(String texto) {
        ArrayList<Produto> produtosArray = new ArrayList<>();
        for (Produto produto : produtos) {
            Categoria categoria = produto.getCategoria();
            if (categoria != null && categoria.getNome().equals(texto)) {
                produtosArray.add(produto);
            }
        }
        return produtosArray;
    }

    public Produto getProdutoById(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCod() == id) {
                return produtos.get(i);
            }
        }
        return null;
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }


    //-----------------Getters-------------------
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Venda> getVendas() { return vendas; }
}
