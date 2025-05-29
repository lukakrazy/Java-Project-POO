package logic;

import console.controllers.Comandos;
import console.controllers.MenuControls;
import data.Cliente;
import data.Funcionario;
import data.Loja;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncClientes extends FuncPessoas<Cliente> {
    @Override
    public Cliente criarEntidade (Loja loja) {
        int nif;
        String nome = Comandos.readString("Nome                           ", "Opção inválida", false);
        do {
            Cliente entidade;
            nif = Comandos.readInt("NIF                            ", "Opção inválida. [Valores permitidos: 9]",9,false);
            entidade = buscarPorNIF(loja, nif);
            if (entidade == null) break;

            Comandos.mensagemErro("Já existe um cliente com esse NIF!");
        } while (true);
        LocalDate nascimento = Comandos.readDateNascimento("Data de nascimento [dd-mm-yyyy]", "Data inválida. Formato: dd-mm-yyyy");
        String endereco = Comandos.readString("Morada                         ", "Opção inválida", true);
        String email = Comandos.readEmail("Email                          ", "Opção inválida", false);
        int telemovel = Comandos.readInt("Telemóvel                      ", "Opção inválida. [Valores permitidos: 9]",9,true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = LocalDate.now().format(formatter);
        LocalDate data = Comandos.converterData(formattedDate);

        Cliente cliente = new Cliente(nome, nif, nascimento, email);

        cliente.setEndereco(endereco);
        cliente.setTelemovel(telemovel);
        cliente.setDataDeInscricao(data);
        Comandos.linhasVazias(2);

        return cliente;
    }
    @Override
    protected Cliente buscarPorId(Loja loja, int id) {
        return loja.getClienteById(id);
    }
    @Override
    protected Cliente buscarPorNIF(Loja loja, int nif) {
        return loja.getClienteByNIF(nif);
    }
    public void consultar(Loja loja) {
        String option = Comandos.readString("Pretende listar todos os clientes (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) {
            return;
        }

        if (option.equalsIgnoreCase("S")) {
            listarTodasPessoas(loja,false);
        }

        Cliente entidade = null;

        do {
            int id = Comandos.readInt("Introduz o id do cliente pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            entidade = buscarPorId(loja, id);
            if (entidade != null) break;

            Comandos.mensagemErro("Id do cliente não encontrado!");
        } while (true);

        listarPessoas(entidade);

    }
    @Override
    public void atualizar(Loja loja) {
        Cliente cliente = null;

        do {
            int id = Comandos.readInt("Introduz o id do cliente pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            cliente = loja.getClienteById(id);
            if (cliente != null) {
                break;
            }

            Comandos.mensagemErro("Id do cliente não encontrado!");
        } while (true);

        String options[] = new String[]{"Nome", "NIF", "Data de nascimento", "Morada", "Email", "Telemóvel", "", "Sair"};
        MenuControls.displayMenu("ATUALIZAR CLIENTE", options);

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
                    cliente.setNome(nome);

                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 2:
                    int nif = Comandos.readInt("NIF", "Opção inválida",9,false);
                    cliente.setNif(nif);

                    Comandos.menssagemSucesso("Alterações efetuadas");

                    break;
                case 3:
                    LocalDate dataNascimento = Comandos.readDateNascimento("Data de nascimento [yyyy-mm-dd]", "Opção inválida");
                    cliente.setDataDeNascimento(dataNascimento);

                    Comandos.menssagemSucesso("Alterações efetuadas");

                    break;
                case 4:
                    String endereco = Comandos.readString("Morada", "Opção inválida", false);
                    cliente.setEndereco(endereco);

                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 5:
                    String email = Comandos.readEmail("Email", "Opção inválida", false);
                    cliente.setEmail(email);

                    Comandos.menssagemSucesso("Alterações efetuadas");

                    break;
                case 6:
                    int telemovel = Comandos.readInt("Telemóvel", "Opção inválida",9,false);
                    cliente.setTelemovel(telemovel);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());

                    break;
            }
        }
    }
    @Override
    public void listarTodasPessoas(Loja loja, boolean completo) {
        String[] headers = completo ?
                new String[]{"Nome            ", "NIF      ", "Data de nascimento", "Morada                   ", "Email                      ", "Telemovel", "Data de inscrição", "Estado"}:
                new String[]{"ID", "Nome            ", "NIF      "};

        MenuControls.displayListHeader(headers);
        for (Cliente cliente : loja.getClientes()) {
            if (completo) {
                MenuControls.displayListContentRow(headers, new Object[]{
                        cliente.getNome(),
                        cliente.getNif(),
                        cliente.getDataDeNascimento(),
                        cliente.getEndereco(),
                        cliente.getEmail(),
                        cliente.getTelemovel(),
                        cliente.getDataDeInscricao(),
                        cliente.getEstado(),});
            } else {
                MenuControls.displayListContentRow(headers, new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getNif()});
            }


        }
        MenuControls.displayListFooter(headers);
    }
    @Override
    public void listarPessoas(Cliente cliente) {
        String[] headers = {"Nome            ","NIF      ", "Data de nascimento", "Morada                   ", "Email                   ", "Telemovel","Data de inscrição", "Estado"};

        MenuControls.displayListHeader(headers);
        MenuControls.displayListContentRow(headers, new Object[]{
                cliente.getNome(),
                cliente.getNif(),
                cliente.getDataDeNascimento(),
                cliente.getEndereco(),
                cliente.getEmail(),
                cliente.getTelemovel(),
                cliente.getDataDeInscricao(),
                cliente.getEstado(),
        });

        MenuControls.displayListFooter(headers);
    }

}
