package data;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private static int ultimo = 0;
    private String cargo;
    private double salario;
    private String estado;
    private LocalDate dataDeInscricao;
    private int codigoDeSeguranca;


    public static int getUltimo() {
        return ultimo;
    }

    public static void setUltimo(int ultimo) {
        Funcionario.ultimo = ultimo;
    }

    public Funcionario(String nome, int nif, LocalDate nascimento, String email, String cargo, double salario) {
        super(++ultimo,nome, nif, nascimento, email);
        this.cargo = cargo;
        this.salario = salario;
        this.dataDeInscricao = LocalDate.now();
        codigoDeSeguranca = 1234;
        estado = "Ativo";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setCodigoDeSeguranca(int codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }
    public int getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }
    public LocalDate getDataDeInscricao() {
        return dataDeInscricao;
    }

    public void setDataDeInscricao(LocalDate dataDeInscricao) {
        this.dataDeInscricao = dataDeInscricao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String toString() {
        return super.toString() + ", Cargo: " + cargo + ", Salário: " + salario;
    }

}
