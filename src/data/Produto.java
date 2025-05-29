package data;
//import console.controllers.Ler;
import java.io.Serializable;
import java.time.LocalDate;

public class Produto implements Serializable {
    private static int ultimo=0;
    private int cod;
    private Categoria categoria;
    private String nome;
    private String descricao;
    private double preco;
    private double stock;
    private LocalDate validade;
    private boolean vendidoPorPeso;
    private boolean disponivel;
    private int iva;
    public Produto(Categoria categoria, String nome, String descricao, double preco, LocalDate validade, boolean vendidoPorPeso, int iva){
        this.cod = ++ultimo;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.stock = 0;
        this.validade = validade;
        this.vendidoPorPeso = vendidoPorPeso;
        this.disponivel = true;
        this.iva = iva;
    }
    public Produto(Categoria categoria, String nome, double preco, LocalDate validade, boolean vendidoPorPeso, int iva){
        this.cod = ++ultimo;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = "";
        this.preco = preco;
        this.stock = 0;
        this.validade = validade;
        this.vendidoPorPeso = vendidoPorPeso;
        this.disponivel = true;
        this.iva = iva;
    }

    public boolean isVendidoPorPeso() {
        return vendidoPorPeso;
    }
    public void setVendidoPorPeso(boolean vendidoPorPeso) {
        this.vendidoPorPeso = vendidoPorPeso;
    }

    public int getCod() {
        return cod;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getStock() {
        return stock;
    }
    public void setStock(double stock) {
        this.stock = stock;
    }

    public LocalDate getValidade() {
        return validade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;

    }

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        String tipoVenda = vendidoPorPeso ? " Peso (kg)" : " Unidades";
        String disp = disponivel ? "Disponível" : "Indisponível";
        return "Código: " + cod + "\n" + "Categoria: " + categoria.getNome() + "\n" + "Nome: " + nome + "\n" + "Preço: " + preco + "€" + "\n" + "Stock: " + stock + tipoVenda + "\n" + "Validade: " + validade + "\n" + disp + "\n";
    }
}
