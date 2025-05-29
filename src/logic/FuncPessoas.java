package logic;

import console.controllers.Comandos;
import console.controllers.MenuControls;
import data.Cliente;
import data.Funcionario;
import data.Loja;
import data.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class FuncPessoas<T extends Pessoa>  {
    protected abstract T criarEntidade(Loja loja) ;
    protected abstract T buscarPorNIF(Loja loja,int nif);
    protected abstract T buscarPorId(Loja loja, int id);
    protected abstract void listarPessoas(T entidade);
    protected abstract void listarTodasPessoas(Loja loja, boolean completo);

    public void inscrever(Loja loja) {
        T entidade = criarEntidade(loja);
        try {
            adicionarEntidade(loja, entidade);

            Comandos.setCorMenssagem("Inscrita com sucesso!", Comandos.setCorVerde());
        } catch (Exception e) {
            Comandos.setCorMenssagem("Falha ao inscrever: " + e.getMessage(), Comandos.setCorVermelho());
        }
    }

    public void consultar(Loja loja) {
        String option = Comandos.readString("Pretende listar todos (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) return;

        if (option.equalsIgnoreCase("S")) {
            listarTodasPessoas(loja, false);
        }

        T entidade;
        do {
            int id = Comandos.readInt("Introduza o ID pretendido ['0' para cancelar]", "Valor errado", false);
            if (id == 0) return;

            entidade = buscarPorId(loja, id);
            if (entidade != null) break;

            Comandos.mensagemErro("ID não encontrado!");
        } while (true);

        listarPessoas(entidade);
    }

    public abstract void atualizar(Loja loja);

    protected void adicionarEntidade(Loja loja, T entidade) {
        if (entidade instanceof Funcionario) {
            loja.increveFuncionario((Funcionario) entidade);
        } else if (entidade instanceof Cliente) {
            loja.increveCliente((Cliente) entidade);
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado: " + entidade.getClass().getName());
        }
    }
}