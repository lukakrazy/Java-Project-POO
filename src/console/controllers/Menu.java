package console.controllers;
import app.App;
import data.Cliente;
import data.Funcionario;
import data.Loja;
import logic.Estatistica;
import logic.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu {
    public void processMainMenu(Loja loja) {
        int menuLevel = 0;
        while (true) {
            int escolha = displayMenuAndReadOption(menuLevel);

            escolha += menuLevel;

            Comandos.linhasVazias(1);
            App.clear();
            switch (escolha) {
                case 0:
                    return;

                case 100:
                case 200:
                case 300:
                case 400:
                case 500:
                case 600:
                case 700:
                case 800:
                    menuLevel = 0;
                    break;

                // Menu level 0-99
                case 1: // Salto para segundo level (Cliente)
                    menuLevel = 100;
                    break;

                case 2: // Salto para segundo level (Funcionário)
                    menuLevel = 200;
                    break;
                case 3: // Salto para segundo level (Categoria)
                    menuLevel = 300;
                    break;
                case 4: // Salto para segundo level (Produto)
                    menuLevel = 400;
                    break;
                case 5: // Salto para segundo level (Stock)
                    menuLevel = 500;
                    break;
                case 6: // Salto para segundo level (Venda)
                    menuLevel = 600;
                    break;
                case 7: // Salto para segundo level (Estatísticas)
                    menuLevel = 700;
                    break;
                case 8: // Salto para segundo level (Admin)
                    menuLevel = 800;
                    break;




                // Menu level 100-199

                case 101:

                    MenuControls.displayMenuOptionDetail("CLIENTES > INSCREVER CLIENTES");

                    new FuncClientes().inscrever(loja);
                    Comandos.waitForUserInput();

                    break;

                case 102:

                    MenuControls.displayTopList("MENU CLIENTES > ATUALIZAR CLIENTES");

                    new FuncClientes().atualizar(loja);
                    Comandos.waitForUserInput();
                    break;

                case 103:
                    MenuControls.displayTopList("MENU CLIENTES > CONSULTAR DETALHES CLIENTES");

                    new FuncClientes().consultar(loja);
                    Comandos.waitForUserInput();
                    break;

                case 104:
                    MenuControls.displayTopList("MENU CLIENTES > LISTA TODOS OS CLIENTES");
                    new FuncClientes().listarTodasPessoas(loja,true);
                    Comandos.waitForUserInput();
                    break;




                case 201:
                    MenuControls.displayMenuOptionDetail("FUNCIONÁRIOS > INSCREVER FUNCIONÁRIOS");
                    new FuncFuncionarios().inscrever(loja);
                    Comandos.waitForUserInput();
                    break;

                case 202:
                    MenuControls.displayTopList("MENU FUNCIONÁRIOS > ATUALIZAR FUNCIONÁRIOS");
                    new FuncFuncionarios().atualizar(loja);
                    Comandos.waitForUserInput();
                    break;

                case 203:
                    MenuControls.displayTopList("MENU FUNCIONÁRIOS > CONSULTAR DETALHES FUNCIONÁRIOS");
                    new FuncFuncionarios().consultar(loja);
                    Comandos.waitForUserInput();
                    break;

                case 204:
                    MenuControls.displayTopList("MENU FUNCIONÁRIOS > LISTA TODOS OS FUNCIONÁRIOS");
                    new FuncFuncionarios().listarTodasPessoas(loja,true);
                    Comandos.waitForUserInput();
                    break;




                case 301:
                    MenuControls.displayMenuOptionDetail("CATEGORIAS > INSCREVER CATEGORIAS");
                    FuncCategorias.criarCategoria(loja);
                    Comandos.waitForUserInput();
                    break;

                case 302:
                    MenuControls.displayTopList("MENU CATEGORIAS > ATUALIZAR CATEGORIAS");
                    FuncCategorias.atualizarCategoria(loja);
                    Comandos.waitForUserInput();
                    break;

                case 303:
                    MenuControls.displayTopList("MENU CATEGORIAS > LISTA TODOS OS CATEGORIAS");
                    FuncCategorias.listarTodasCategorias(loja);
                    Comandos.waitForUserInput();
                    break;

                case 304:
                    MenuControls.displayMenuOptionDetail("MENU CATEGORIAS > APAGAR CATEGORIAS");
                    FuncCategorias.removerCategoria(loja);
                    Comandos.waitForUserInput();
                    break;





                case 401:
                    MenuControls.displayMenuOptionDetail("PRODUTOS > CRIAR PRODUTOS");
                    FuncProdutos.criarProduto(loja);
                    Comandos.waitForUserInput();
                    break;

                case 402:
                    MenuControls.displayTopList("PRODUTOS > ATUALIZAR PRODUTOS");
                    FuncProdutos.atualizarProduto(loja);
                    Comandos.waitForUserInput();
                    break;

                case 403:
                    MenuControls.displayTopList("PRODUTOS > CONSULTAR DETALHES PRODUTOS");
                    FuncProdutos.consultarProd(loja);
                    Comandos.waitForUserInput();
                    break;

                case 404:
                    MenuControls.displayTopList("PRODUTOS > LISTA TODOS OS PRODUTOS");
                    FuncProdutos.listarTodosProdutos(loja, true);
                    Comandos.waitForUserInput();
                    break;

                case 405:
                    MenuControls.displayMenuOptionDetail("PRODUTOS > REMOVER PRODUTOS");
                    FuncProdutos.removerProduto(loja);
                    Comandos.waitForUserInput();
                    break;




                case 501:
                    MenuControls.displayMenuOptionDetail("STOCK > ADICIONAR STOCK");
                    FuncProdutos.adicionarStock(loja);
                    Comandos.waitForUserInput();
                    break;

                case 502:
                    MenuControls.displayTopList("STOCK > REMOVER STOCK");
                    FuncProdutos.reduzirStock(loja);
                    Comandos.waitForUserInput();
                    break;



                case 601:
                    MenuControls.displayMenuOptionDetail("VENDA > REALIZAR VENDA");
                    FuncVenda.registar(loja);
                    Comandos.waitForUserInput();
                    break;

                case 602:
                    MenuControls.displayTopList("VENDA > IMPRIMIR TALAO");
                    FuncVenda.listarRecibo(loja);
                    Comandos.waitForUserInput();
                    break;
                case 603:
                    MenuControls.displayMenuOptionDetail("VENDA > LISTAR VENDAS");
                    FuncVenda.listarTodasVendas(loja);
                    Comandos.waitForUserInput();
                    break;

                case 604:
                    MenuControls.displayTopList("VENDA > REMOVER VENDA");
                    FuncVenda.remover(loja);
                    Comandos.waitForUserInput();
                    break;


                case 701:
                    MenuControls.displayTopList("ESTATÍSTICAS > LOJA");
                    Estatistica.loja(loja);
                    Comandos.waitForUserInput();
                    break;
                case 702:
                    MenuControls.displayMenuOptionDetail("ESTATÍSTICAS > CLIENTES");
                    Estatistica.cliente(loja);
                    Comandos.waitForUserInput();
                    break;

                case 703:
                    MenuControls.displayTopList("ESTATÍSTICAS > FUNCIONÁRIOS");
                    Estatistica.funcionario(loja);
                    Comandos.waitForUserInput();
                    break;

                case 704:
                    MenuControls.displayTopList("ESTATÍSTICAS > PRODUTOS");
                    Estatistica.produto(loja);
                    Comandos.waitForUserInput();
                    break;






                case 801:
                    MenuControls.displayMenuOptionDetail("ADMIN > REINICIAR DATABASE");
                    FuncAdmin.reiniciarDatabase();
                    Comandos.waitForUserInput();
                    break;

                case 802:
                    MenuControls.displayTopList("ADMIN > GUARDAR DATABASE");
                    FuncAdmin.guardarDatabase();
                    Comandos.waitForUserInput();
                    break;

                case 803:

                    MenuControls.displayTopList("ADMIN > TROCAR CODIGO DE SEGURANÇA");
                    FuncAdmin.mudarCodigoSeguranca(loja);
                    Comandos.waitForUserInput();
                    break;


            }
        }
    }

    private int displayMenuAndReadOption(int menuLevel) {
        String options[] = null;

        switch (menuLevel) {
            case 0:
                options = new String[]{"Clientes", "Funcionários", "Categorias", "Produtos","Stock","Venda" ,"Estatísticas", "Admin", "", "Sair"};
                MenuControls.displayMenu("MENU PRINCIPAL", options);
                break;
            case 100:
                options = new String[]{"Inscrever", "Atualizar", "Consultar", "Listar todos", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU CLIENTES", options);
                break;
            case 200:
                options = new String[]{"Inscrever", "Atualizar", "Consultar", "Listar todos", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU FUNCIONÁRIOS", options);
                break;
            case 300:
                options = new String[]{"Criar", "Atualizar", "Listar todos","Apagar Categorias", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU CATEGORIAS", options);
                break;
            case 400:
                options = new String[]{"Criar", "Atualizar", "Consultar", "Listar todos", "Remover", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU PRODUTOS", options);
                break;
            case 500:
                options = new String[]{"Adicionar", "Remover", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU STOCK", options);
                break;
            case 600:
                options = new String[]{"Realizar venda", "Imprimir talão","Procurar venda","Apagar venda", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU VENDA", options);
                break;
            case 700:
                options = new String[]{"Loja","Clientes", "Funcionários", "Produtos",  "", "Voltar atrás"};
                MenuControls.displayMenu("MENU ESTATÍSTICAS", options);
                break;
            case 800:
                options = new String[]{"Reiniciar database", "Guardar database", "Mudar código se segurança", "", "Voltar atrás"};
                MenuControls.displayMenu("MENU ADMIN", options);
                break;

            default:
                Comandos.mensagemErro("Unknown menu level");
                break;
        }

        int optionCount = 0;
        for (String option : options) {
            if (!option.isEmpty()) {
                optionCount++;
            }
        }
        optionCount--;

        do {
            int optionSelected = Ler.umInt();
            if (optionSelected < 0 || optionSelected > optionCount) {
                Comandos.setCorMenssagem("Introduz uma opção válida!",Comandos.setCorVermelho());
            } else {
                return optionSelected;
            }
        } while (true);
    }
}
