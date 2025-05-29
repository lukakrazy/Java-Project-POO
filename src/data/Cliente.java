package data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Cliente extends Pessoa {
    private static int ultimo = 0;
    private String estado;
    private LocalDate dataDeInscricao;


    public Cliente(String nome, int nif, LocalDate nascimento, String email) {
        super(++ultimo,nome, nif, nascimento, email);
        this.dataDeInscricao = LocalDate.now();
        estado = "Ativo";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDataDeInscricao() {
        return dataDeInscricao;
    }

    public void setDataDeInscricao(LocalDate dataDeInscricao) {
        this.dataDeInscricao = dataDeInscricao;
    }

    public static int getUltimo() {
        return ultimo;
    }

    public static void setUltimo(int ultimo) {
        Cliente.ultimo = ultimo;
    }

    @Override
    public String toString() {
        return "Cliente{"+
                super.toString() +
                "estado='" + estado + '\'' +
                ", dataDeInscricao=" + dataDeInscricao + '\'' +
                '}';
    }

    public ArrayList<Venda> getVendas(Loja loja) {
        ArrayList<Venda> vendasCliente = new ArrayList<>();
        for (Venda venda : loja.getVendas()) {
            if (venda.getCliente().equals(this)) {
                vendasCliente.add(venda);
            }
        }
        return vendasCliente;
    }
}
