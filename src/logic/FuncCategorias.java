package logic;
import console.controllers.Comandos;
import console.controllers.Ler;
import console.controllers.MenuControls;
import data.Categoria;
import data.Cliente;
import data.Loja;

import java.time.LocalDate;

public class FuncCategorias {

    public static void criarCategoria(Loja loja) {
        String nome = Comandos.readString("Nome                           ", "Opção inválida", false);
        String descricao = Comandos.readString("Descrição                      ", "Opção inválida", false);
        Categoria c = new Categoria(nome, descricao);

        try {
            loja.criarCat(c);
            Comandos.menssagemSucesso("Categoria criada!");
        } catch (Exception e) {
            Comandos.mensagemErro("Falha ao criar categoria: " + e.getMessage());
        }
    }

    public static void atualizarCategoria(Loja loja) {
        Categoria categorias = null;
        do{
            int id = Comandos.readInt("Introduz o id da caregoria pretendida ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            categorias = loja.getCategoriaById(id);
            if (categorias != null) {
                break;
            }

            Comandos.mensagemErro("Id da categoria não encontrada!");
        } while (true);

        String options[] = new String[]{"Nome", "Descrição", "", "Sair"};
        MenuControls.displayMenu("ATUALIZAR CATEGORIA", options);

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
                    categorias.setNome(nome);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
                case 2:
                    String descricao = Comandos.readString("Descrição", "Opção inválida", false);
                    categorias.setDescricao(descricao);
                    Comandos.menssagemSucesso("Alterações efetuadas");
                    break;
            }
        }
    }

    public static void listarTodasCategorias(Loja loja) {
        String[] headers = new String[]{"ID", "Nome                     ", "Descrição                                                                                          "} ;

        MenuControls.displayListHeader(headers);
        for (Categoria categoria : loja.getCategorias()) {
            MenuControls.displayListContentRow(headers, new Object[]{
                    categoria.getId(),
                    categoria.getNome(),
                    categoria.getDescricao()});
        }
        MenuControls.displayListFooter(headers);
    }


    public static void removerCategoria(Loja loja) {
        Categoria categorias = null;
        do {
            int id = Comandos.readInt("Introduz o id da categoria pretendida ['0' para cancelar]", "Valor errado", false);
            if (id == 0) {
                return;
            }

            categorias = loja.getCategoriaById(id);
            if (categorias != null) {
                break;
            }

            Comandos.mensagemErro("Id da categoria não encontrada!");
        } while (true);

        try {
            loja.removerCat(categorias);
            Comandos.menssagemSucesso("Categoria removida!");
        } catch (Exception e) {
            Comandos.mensagemErro("Falha ao remover categoria: " + e.getMessage());
        }
    }
}
/*
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        if (categorias.isEmpty()) {
            System.out.println("Não existem categorias para listar!");
            return;
        }
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println(categorias.get(i).toString());
        }
    }

    public void removerCategoria(ArrayList<Categoria> categorias) {
        boolean existe = false;
        System.out.print("Qual o id da categoria que deseja remover? ");
        int id = Ler.umInt();
        if (categorias.isEmpty()) {
            System.out.println("Não existem categorias para remover!");
            return;
        }
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == id) {
                existe = true;
                categorias.remove(i);
                System.out.println("Categoria removida com sucesso.");
            }
        }
        if (!existe) {
            System.out.println("Categoria não encontrada.");
        }
    }

//    public static void main(String[] args) {
//        ArrayList<Categoria> categorias = new ArrayList<>();
//        FuncCategorias fc = new FuncCategorias();
//
//        criarCategoria(categorias);
//        listarCategorias(categorias);
//        fc.atualizarCategoria(categorias);
//    }
}*/
