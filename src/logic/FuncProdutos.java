package logic;
import console.controllers.Comandos;
import console.controllers.Ler;
import console.controllers.MenuControls;
import data.Categoria;
import data.Cliente;
import data.Loja;
import data.Produto;
import java.time.LocalDate;
import java.util.ArrayList;

public class FuncProdutos {

    protected static Produto buscarPorId(Loja loja, int id) {
        return loja.getProdutoById(id);
    }

    public static void criarProduto(Loja loja) {
        ArrayList<Categoria> categorias = loja.getCategorias();
        if (categorias.isEmpty()) {
            System.out.println("Não há categorias disponíveis. Por favor, adicione uma categoria antes de criar um produto.");
            return;
        }

        System.out.println("Escolha uma categoria: ");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println(i + 1 + " - " + categorias.get(i).getNome());
        }

        int escolha;
        do {
            System.out.print("Escolha: ");
            escolha = Ler.umInt();
            if (escolha < 1 || escolha > categorias.size()) {
                System.out.println("Opção inválida. Escolha novamente.");
            }
        } while (escolha < 1 || escolha > categorias.size());

        Categoria categoria = categorias.get(escolha - 1);
        String nome = Comandos.readString("Nome                               ", "Opção inválida", false);
        double preco = Comandos.readDouble("Preço                              ", "Opção inválida", false);
        LocalDate validade = Comandos.readDateValidade("Data de validade (DD-MM-YYYY)      ", "Opção inválida");
        boolean vendidoPorPeso = Comandos.readBoolean("O produto é vendido por peso? (s/n)", "Opção inválida");
        int iva = Comandos.readInt("IVA                                ","Opção inválida",false);
        String desc = Comandos.readString("Descrição? (s/n)                    ", "Opção inválida", true);
        if(desc.equalsIgnoreCase("s")){
            System.out.print("Descrição: ");
            String descricao = Ler.umaString();
            Produto p = new Produto(categoria, nome, descricao, preco, validade, vendidoPorPeso,iva);
            loja.adicionarProduto(p);
            Comandos.setCorMenssagem("Produto criado!",Comandos.setCorVerde());
        } else {
            Produto p = new Produto(categoria, nome, preco, validade, vendidoPorPeso,iva);
            loja.adicionarProduto(p);
            Comandos.setCorMenssagem("Produto criado!",Comandos.setCorVerde());
        }
    }

    public void verificarValidade(ArrayList<Produto> produtos) {
        LocalDate hoje = LocalDate.now();
        for (Produto produto : produtos) {
            if (produto.getValidade().isBefore(hoje)) {
                produto.setDisponivel(false);
            }
        }
    }

    public static void adicionarStock(Loja loja) {
        Produto produtos = null;

        do {
            int id = Comandos.readInt("Introduz o código do produto que pretende adicionar stock ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            produtos = loja.getProdutoById(id);
            if (produtos != null) {
                break;
            }

            Comandos.setCorMenssagem("Código do produto não existe!",Comandos.setCorVermelho());
        } while (true);
        double quantidade = Comandos.readDouble("Quantidade", "Opção inválida",false);
        double somaQuantidade = quantidade + produtos.getStock();
        produtos.setStock(somaQuantidade);
        Comandos.setCorMenssagem("Stock atualizado do produto " + produtos.getNome() + ": " + produtos.getStock() + " " + (produtos.isVendidoPorPeso() ? "kg" : "unidades"),Comandos.setCorVerde());
    }

    public static void reduzirStock(Loja loja) {
        Produto produtos = null;

        do {
            int id = Comandos.readInt("Introduz o código do produto que pretende reduzir stock ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            produtos = loja.getProdutoById(id);
            if (produtos != null) {
                break;
            }

            Comandos.setCorMenssagem("Código do produto não existe!",Comandos.setCorVermelho());
        } while (true);
        double quantidade = Comandos.readDouble("Quantidade", "Opção inválida",false);
        if (quantidade > produtos.getStock()) {
            Comandos.setCorMenssagem("Quantidade inválida! Não é possível reduzir o stock para um valor negativo.",Comandos.setCorVermelho());
            return;
        }
        double somaQuantidade = produtos.getStock() - quantidade;
        produtos.setStock(somaQuantidade);
        Comandos.setCorMenssagem("Stock atualizado do produto " + produtos.getNome() + ": " + produtos.getStock() + " " + (produtos.isVendidoPorPeso() ? "kg" : "unidades"),Comandos.setCorVerde());
    }

    public static void atualizarProduto(Loja loja) {
        Produto produtos = null;

        do {
            int id = Comandos.readInt("Introduz o código do produto que pretende atualizar ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            produtos = loja.getProdutoById(id);
            if (produtos != null) {
                break;
            }

            Comandos.setCorMenssagem("Código do produto não existe!",Comandos.setCorVermelho());
        } while (true);
        String options[] = new String[]{"Nome", "Descrição", "Preço", "Stock", "Validade", "", "Sair"};
        MenuControls.displayMenu("ATUALIZAR PRODUTO", options);

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
                    produtos.setNome(nome);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());
                    break;
                case 2:
                    String descricao = Comandos.readString("Descrição", "Opção inválida", false);
                    produtos.setDescricao(descricao);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());

                    break;
                case 3:
                    double preco = Comandos.readDouble("Preço", "Opção inválida",false);
                    produtos.setPreco(preco);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());

                    break;
                case 4:
                    double stock = Comandos.readDouble("Stock", "Opção inválida",false);
                    produtos.setStock(stock);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());

                    break;
                case 5:
                    LocalDate validade = Comandos.readDateValidade("Data de validade (DD-MM-YYYY)", "Opção inválida");
                    produtos.setValidade(validade);

                    Comandos.setCorMenssagem("Alterações efetuadas",Comandos.setCorVerde());

                    break;
            }
        }
    }

    public static void consultarProd(Loja loja) {
        String option = Comandos.readString("Pretende listar todos os produtos (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) {
            return;
        }

        if (option.equalsIgnoreCase("S")) {
            listarTodosProdutos(loja,false);
        }

        Produto produto = null;

        do {
            int id = Comandos.readInt("Introduz o código do produto pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            produto = buscarPorId(loja, id);
            if (produto != null) break;

            Comandos.mensagemErro("Código do produto não encontrado!");
        } while (true);

        listarProduto(produto);
    }

    public static void listarTodosProdutos(Loja loja, boolean completo) {
        String[] headers = completo ?
                new String[]{"Código", "Categoria              ", "Nome                            ", "Descrição                                                           " ,"Preço", "Stock", "Validade       ", "Disponível"} :
                new String[]{"Código", "Nome                            ", "Preço", "Stock", "Disponível                         "};
        MenuControls.displayListHeader(headers);

        for(Produto produto : loja.getProdutos()) {
            if (completo) {
                MenuControls.displayListContentRow(headers, new Object[]{
                    produto.getCod(),
                    produto.getCategoria().getNome(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getStock(),
                    produto.getValidade(),
                    produto.isDisponivel() ? "Sim" : "Não"
                });
            } else {
                MenuControls.displayListContentRow(headers, new Object[]{
                    produto.getCod(),
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getStock(),
                    produto.isDisponivel() ? "Sim" : "Não"
                });
            }
        }
        MenuControls.displayListFooter(headers);
    }

    public static void listarProduto(Produto produto) {
        String[] headers = new String[]{"Código", "Categoria              ", "Nome                            ", "Descrição                                                           " ,"Preço", "Stock", "Validade       ", "Disponível"};
        MenuControls.displayListHeader(headers);
        MenuControls.displayListContentRow(headers, new Object[]{
            produto.getCod(),
            produto.getCategoria().getNome(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getPreco(),
            produto.getStock(),
            produto.getValidade(),
            produto.isDisponivel() ? "Sim" : "Não"
        });
        MenuControls.displayListFooter(headers);
    }

    public static void removerProduto(Loja loja) {
        Produto produtos = null;

        do {
            int id = Comandos.readInt("Introduz o código do produto que pretende remover ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            produtos = loja.getProdutoById(id);
            if (produtos != null) {
                break;
            }

            Comandos.setCorMenssagem("Código do produto não existe!",Comandos.setCorVermelho());
        } while (true);
        loja.removerProduto(produtos);
        Comandos.setCorMenssagem("Produto removido com sucesso!",Comandos.setCorVerde());
    }
}
