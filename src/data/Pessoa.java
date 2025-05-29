package data;
import java.io.Serializable;
import java.time.LocalDate;

public class Pessoa implements Serializable {
 private static int ultimo = 0;
    private int id;
    private String nome;
    private int nif;
    private LocalDate dataDeNascimento;
    private String endereco;
    private String email;
    private int telemovel;

    public Pessoa() {
        nome = "";
    }

    public Pessoa(int id,String nome, int nif, LocalDate nascimento, String email) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.dataDeNascimento = nascimento;
        this.endereco = "";
        this.email = email;
        this.telemovel = 0;
    }

    //-----------------------Getters & Setters---------------------------
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }
    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }
    public void setDataDeNascimento(LocalDate nascimento) {
        this.dataDeNascimento = nascimento;
    }

    public String getEndereco() { return endereco;}
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelemovel() {
        return telemovel;
    }
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    //----------------------------Miscellaneous--------------------------


    @Override
    public String toString() {
        return"id=" + id +
                ", nome='" + nome + '\'' +
                ", nif=" + nif +
                ", dataDeNascimento=" + dataDeNascimento +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", telemovel=" + telemovel + '\''
                ;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && this.getClass() == obj.getClass()){
            Pessoa other = (Pessoa) obj;
            return this.nome.equals(other.nome) && this.nif == other.nif && this.endereco.equals(other.endereco) && this.email.equals(other.email) && this.telemovel == other.telemovel && this.dataDeNascimento.equals(other.dataDeNascimento);
        }
        return  false;
    }
}