package data;

import java.io.Serializable;

public class ProdutoQuantidade  implements Serializable {
    private Produto produto;
    private int quantidade;

    public ProdutoQuantidade(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}