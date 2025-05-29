package logic;

import console.controllers.Comandos;
import console.controllers.MenuControls;
import data.Cliente;
import data.Funcionario;
import data.Loja;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncFuncionarios extends FuncPessoas<Funcionario> {
    @Override
    protected Funcionario criarEntidade(Loja loja) {
        int nif;
        String nome = Comandos.readString("Nome                           ", "Opção inválida", false);
        do {
            Funcionario entidade;
            nif = Comandos.readInt("NIF                            ", "Opção inválida. [Valores permitidos: 9]",9,false);
            entidade = buscarPorNIF(loja, nif);
            if (entidade == null) break;

            Comandos.mensagemErro("Já existe um funcionário com esse NIF!");
        } while (true);        LocalDate nascimento = Comandos.readDateNascimento("Data de nascimento [dd-mm-yyyy]", "Data inválida. Formato: dd-mm-yyyy");
        String email = Comandos.readEmail("Email                          ", "Opção inválida", false);
        String cargo = Comandos.readString("Cargo                          ", "Opção inválida", false);
        double salario = Comandos.readDouble("Salário                        ", "Opção inválida", false);
        String endereco = Comandos.readString("Morada                         ", "Opção inválida", true);
        int telemovel = Comandos.readInt("Telemóvel                      ", "Opção inválida. [Valores permitidos: 9]",9,true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = LocalDate.now().format(formatter);
        LocalDate data = Comandos.converterData(formattedDate);

        Funcionario funcionario = new Funcionario(nome, nif, nascimento, email,cargo,salario);

        funcionario.setEndereco(endereco);
        funcionario.setTelemovel(telemovel);
        funcionario.setDataDeInscricao(data);
        Comandos.linhasVazias(2);
        return funcionario;
    }
    @Override
    protected Funcionario buscarPorId(Loja loja, int id) {
        return loja.getFuncionariosById(id);
    }
    @Override
    protected Funcionario buscarPorNIF(Loja loja, int nif) {
        return loja.getFuncionariosByNIF(nif);
    }
    @Override
    public void consultar(Loja loja) {
        String option = Comandos.readString("Pretende listar todos os funcionários (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) {
            return;
        }

        if (option.equalsIgnoreCase("S")) {
            listarTodasPessoas(loja,false);
        }

        Funcionario entidade = null;

        do {
            int id = Comandos.readInt("Introduz o id do funcionario pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            entidade = buscarPorId(loja, id);
            if (entidade != null) break;

            Comandos.mensagemErro("Id do funcionario não encontrado!");
        } while (true);

        listarPessoas(entidade);

    }
    @Override
    public void atualizar(Loja loja) {
        Funcionario funcionario = null;

        do {
            int id = Comandos.readInt("Introduz o id do funcionario pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            funcionario = loja.getFuncionariosById(id);
            if (funcionario != null) {
                break;
            }

            Comandos.mensagemErro("Id do funcionario não encontrado!");
        } while (true);

        String options[] = new String[]{"Nome","Salario","Cargo", "NIF", "Data de nascimento", "Morada", "Email", "Telemóvel", "", "Sair"};
        MenuControls.displayMenu("ATUALIZAR FUNCIONARIO", options);

        int optionCount = 0;
        for (String option : options) {
            if (!option.isEmpty()) {
                optionCount++;
            }
        }
        optionCount--;

        while (true) {
            int escolha = Comandos.readInt("Escolha uma das opções","Opção inválida!",0, optionCount,false);
            switch (escolha) {
                case 0:
                    return;
                case 1:
                    String nome = Comandos.readString("Nome", "Opção inválida", false);
                    funcionario.setNome(nome);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 2:
                    double salario = Comandos.readDouble("Telemóvel", "Opção inválida",false);
                    funcionario.setSalario(salario);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 3:
                    String cargo = Comandos.readString("Telemóvel", "Opção inválida",false);
                    funcionario.setCargo(cargo);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 4:
                    int nif = Comandos.readInt("NIF", "Opção inválida",9,false);
                    funcionario.setNif(nif);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 5:
                    LocalDate dataNascimento = Comandos.readDateNascimento("Data de nascimento [yyyy-mm-dd]", "Opção inválida");
                    funcionario.setDataDeNascimento(dataNascimento);
                    Comandos.menssagemSucesso("Alterações efetuadas");

                    break;
                case 6:
                    String endereco = Comandos.readString("Morada", "Opção inválida", false);
                    funcionario.setEndereco(endereco);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 7:
                    String email = Comandos.readEmail("Email", "Opção inválida", false);
                    funcionario.setEmail(email);
                    Comandos.menssagemSucesso("Alterações efetuadas");

                    break;
                case 8:
                    int telemovel = Comandos.readInt("Telemóvel", "Opção inválida",9,false);
                    funcionario.setTelemovel(telemovel);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;

            }
        }
    }
    @Override
    public void listarTodasPessoas(Loja loja, boolean completo) {
        String[] headers = completo?
             new String[]{"Nome            ", "Salario", "Cargo        ", "NIF      ", "Data de nascimento", "Morada                   ", "Email                      ", "Telemovel", "Data de inscrição", "Estado"}:
             new String[]{"ID", "Nome            ", "NIF      "};

        MenuControls.displayListHeader(headers);

        for (Funcionario funcionario  : loja.getFuncionarios()) {
            if (completo) {
                MenuControls.displayListContentRow(headers, new Object[]{
                        funcionario.getNome(),
                        funcionario.getSalario(),
                        funcionario.getCargo() ,
                        funcionario.getNif(),
                        funcionario.getDataDeNascimento(),
                        funcionario.getEndereco(),
                        funcionario.getEmail(),
                        funcionario.getTelemovel(),
                        funcionario.getDataDeInscricao(),
                        funcionario.getEstado(),
                });
            } else {
                MenuControls.displayListContentRow(headers, new Object[]{
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getNif()
                });
            }

        }
        MenuControls.displayListFooter(headers);
    }

    @Override
    protected void listarPessoas(Funcionario funcionario) {
        String[] headers = {"Nome            ","Salario","Cargo        ","NIF      ", "Data de nascimento", "Morada                   ", "Email                   ", "Telemovel","Data de inscrição", "Estado"};

        MenuControls.displayListHeader(headers);
        MenuControls.displayListContentRow(headers, new Object[]{
                funcionario.getNome(),
                funcionario.getSalario(),
                funcionario.getCargo() ,
                funcionario.getNif(),
                funcionario.getDataDeNascimento(),
                funcionario.getEndereco(),
                funcionario.getEmail(),
                funcionario.getTelemovel(),
                funcionario.getDataDeInscricao(),
                funcionario.getEstado(),
        });

        MenuControls.displayListFooter(headers);
    }

}
