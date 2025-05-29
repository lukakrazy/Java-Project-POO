//erros a resolver: o metodo criar fatura e no calcular valor total
// nao está a ter a opção de somar o preço de várias quantidades do mesmo produto - corrigido?? fiz uma classe que busca o produto e a devida quantidade
// criar classe auxiliar que tenha produto e quantidade?



package data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venda implements Serializable {
    private static int ultimo = 0;
    private int id;
    private LocalDate dataVenda;
    private Funcionario funcionario;
    private Cliente cliente;
    private double valorTotal;
    private double valorTotalIVA;
    private Fatura fatura;

    private ArrayList<ProdutoQuantidade> produtos;

    public Venda(Funcionario funcionario, Cliente cliente, ArrayList<ProdutoQuantidade> produtos){
        this.id = ++ultimo;
        this.dataVenda = LocalDate.now();;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorTotal = calcularValorTotal();
        this.valorTotalIVA = calcularValorTotalIVA();
        this.fatura = null;
    }


    public Venda(Funcionario funcionario, ArrayList<ProdutoQuantidade> produtos) {
        this.id = ++ultimo;
        this.dataVenda = LocalDate.now();
        this.funcionario = funcionario;
        this.cliente = null;
        this.produtos = produtos;
        this.valorTotal = calcularValorTotal();
        this.valorTotalIVA = calcularValorTotalIVA();
        this.fatura = null;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public ArrayList<Produto> getProduto() {
        ArrayList<Produto> produtos = new ArrayList<>();
        for (ProdutoQuantidade pq : this.produtos) {
            produtos.add(pq.getProduto());
        }
        return produtos;
    }

    public void setProdutos(ArrayList<ProdutoQuantidade> produtos) {
        this.produtos = produtos;
        this.valorTotal = calcularValorTotal();
        this.valorTotalIVA = calcularValorTotalIVA();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorTotalIVA() {
        return valorTotalIVA;
    }

    public void setValorTotalIVA(double valorTotalIVA) {
        this.valorTotalIVA = valorTotalIVA;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Fatura getFatura() {
        return fatura;
    }
    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    private double calcularValorTotal() {
        double total = 0;
        for (ProdutoQuantidade produto : produtos) {
            total += produto.getProduto().getPreco() * produto.getQuantidade();
        }
        return total;
    }
    private double calcularValorTotalIVA() {
        double totalIVA = 0;
        for (ProdutoQuantidade produto : produtos) {
            double total = produto.getProduto().getPreco() * produto.getQuantidade();
            totalIVA += total * (produto.getProduto().getIva() / 100.0);
        }
        return totalIVA;
    }


    public Fatura criarFatura(Funcionario funcionario) {
        this.fatura = new Fatura(this.cliente, funcionario,this.produtos,this.valorTotal);
        return fatura;
    }

    public void exibirDetalhes() {
        System.out.println("ID da Venda: " + id);
        System.out.println("Data da Venda: " + dataVenda);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Produtos Vendidos:");
        for (ProdutoQuantidade pq : produtos) {
            System.out.println("- Produto: " + pq.getProduto().getNome() + ", Quantidade: " + pq.getQuantidade() + ", Preço: " + pq.getProduto().getPreco());
        }
        System.out.println("Valor Total: " + valorTotal);
        if (fatura != null) {
            System.out.println("Fatura: " + fatura);
        }
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", funcionario=" + funcionario +
                ", cliente=" + cliente +
                ", valorTotal=" + valorTotal +
                ", fatura=" + fatura +
                ", produtos=" + produtos +
                '}';
    }


}
