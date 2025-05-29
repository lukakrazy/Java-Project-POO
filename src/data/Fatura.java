package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
public class Fatura  implements Serializable {
    private Cliente cliente;
    private ArrayList<ProdutoQuantidade> produtos;
    private LocalDate dataVenda;
    private double total;
    private Funcionario funcionario;


    public Fatura(Cliente cliente, Funcionario funcionario, ArrayList<ProdutoQuantidade> produtos, double total) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.dataVenda = LocalDate.now();
        this.total = 0.0;
        this.funcionario = funcionario;
    }

//    public void adicionarProduto(Produto produto, int quantidade) {
//        if (produto.getStock() >= quantidade) {
//            produtos.add(produto);
//            produto.setStock(produto.getStock() - quantidade);
//            total += produto.getPreco() * quantidade;
//        } else {
//            System.out.println("Stock insuficiente para o produto " + produto.getNome());
//        }
//    }

    // Getters


    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public double getTotal() {
        return total;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        sb.append("Data da Venda: ").append(dataVenda).append("\n");
        sb.append("Funcionário: ").append(funcionario.getNome()).append("\n");
        sb.append("Produtos:\n");
        for (ProdutoQuantidade pq : produtos) {
            sb.append("- Produto: ").append(pq.getProduto().getNome())
            .append(", Quantidade: ").append(pq.getQuantidade())
            .append(", Preço: ").append(pq.getProduto().getPreco()).append("€\n");
        }
        sb.append("Total: ").append(total).append("€\n");
        return sb.toString();
    }
}
