package logic;

import console.controllers.Comandos;
import console.controllers.MenuControls;
import data.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static console.controllers.Database.loja;


public class FuncVenda {

    public static void registar(Loja loja){
        Venda venda ;
        Funcionario funcionario = verificarFuncionario();

        if(funcionario == null){
            return;
        }
        ArrayList<ProdutoQuantidade> produtos = produtosComprados();

        if(produtos.isEmpty()){
            return;
        }
        String option = Comandos.readString("O cliente pretende fatura (S/N)  ['sair' para cancelar]?", "Opção inválida", false, new String[]{"S", "N","sair"});
        if (option.equals("sair")) {
            return;
        }
        if (option.compareToIgnoreCase("n")== 0) {
            venda = new Venda( funcionario, produtos);
            venda.setProdutos(produtos);
        }else{
            Cliente cliente;
            while(true) {
                int nif = Comandos.readInt("Qual o contribuinte do cliente?", "Contribuinte inválido", 9, false);

                cliente = loja.getClienteByNIF(nif);
                if(cliente == null){
                    Comandos.mensagemErro("Cliente não encontrado");
                    continue;
                }
                break;
            }
            venda = new Venda(funcionario,cliente, produtos);
            Fatura fatura = venda.criarFatura(funcionario);

            venda.setFatura(fatura);
        }
        venda.setProdutos(produtos);
        try {
            loja.registarVenda(venda);
            imprimirRecibo(venda);
        } catch (Exception e) {
            Comandos.mensagemErro("Falha ao criar venda: " + e.getMessage());
        }
    }
    public static void remover(Loja loja){
        Funcionario funcionario = verificarFuncionario();

        if(!funcionario.getCargo().equals("Administrador")){
            return;
        }
        Venda venda = null;

        do {
            int id = Comandos.readInt("Introduz o código da venda pretendida ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            venda = loja.getVendaById( id);
            if (venda != null) break;

            Comandos.mensagemErro("Código da venda não encontrada!");
        } while (true);

        loja.eliminarVenda(venda);

    }
    public static void listarRecibo(Loja loja){
        Venda venda = null;

        do {
            int id = Comandos.readInt("Introduz o código da venda pretendida ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            venda = loja.getVendaById( id);
            if (venda != null) break;

            Comandos.mensagemErro("Código do produto não encontrado!");
        } while (true);

        imprimirRecibo(venda);
    }
    public static void listarTodasVendas(Loja loja) {
        String[] headers = new String[]{"Código", "Funcionario              ", "Cliente                            ", "Total   "} ;

        List<Venda> vendasOrdenadas = new ArrayList<>(loja.getVendas());
        vendasOrdenadas.sort(Comparator.comparing(Venda::getId).reversed());

        MenuControls.displayListHeader(headers);

        for (Venda venda : vendasOrdenadas) {
            MenuControls.displayListContentRow(headers, new Object[]{
                    venda.getId(),
                    venda.getFuncionario().getNome(),
                    venda.getCliente() != null ? venda.getCliente().getNome() : "N/A",
                    venda.getValorTotal(),
            });
        }
        MenuControls.displayListFooter(headers);
    }
    public static Funcionario verificarFuncionario(){
        while (true){
            int codFuncionario = Comandos.readInt("Qual o seu número de funcionário? ['0' para cancelar]","Número inválido",false);
            if (codFuncionario == 0) {
                return null;
            }

            Funcionario funcionario = loja.getFuncionariosById(codFuncionario);
            if (funcionario == null) {
                Comandos.mensagemErro("ID do funcionário não encontrado!");
                continue;
            }

            int codigoSeguranca = Comandos.readInt("Qual o seu código de segurança?", "Código inválido", 4, false);
            if (codigoSeguranca != funcionario.getCodigoDeSeguranca()) {
                Comandos.mensagemErro("Código de segurança incorreto!");
                continue;
            }

            return funcionario;
        }
    }

    public static ArrayList<ProdutoQuantidade> produtosComprados(){
        ArrayList<ProdutoQuantidade> produtos = new ArrayList<>();

        while (true){
            String produtoStr = Comandos.readString("Selecione o produto que deseja vender ['0' para quando terminar]", "Produto inválido",false);
            if (produtoStr.equals("0") ) {
                break;
            }
            if (!Character.isDigit(produtoStr.charAt(0))) {
                listarTodosProdutos(produtoStr);
                continue;
            }
            Produto produto = loja.getProdutoById(Integer.parseInt(produtoStr));
            if(produto == null){
                Comandos.mensagemErro("Não existe esse produto " );
                continue;
            }
            int quant = Comandos.readInt("Diga a quantidade de "+ produto.getNome(),"Número inválido",false);
            if(produto.getStock() < quant ){
                Comandos.mensagemErro("Não existe stock para o produto " + produto.getNome());
                continue;
            }
            ProdutoQuantidade produtoqQt = new ProdutoQuantidade(produto, quant);
            produtos.add(produtoqQt);
        }
        return produtos;
    }
    public static void listarTodosProdutos(String texto) {
        String[] headers = new String[]{"Código", "Categoria              ", "Nome                            ", "Descrição                                                           " ,"Preço", "Stock", "Validade       ", "Disponível"};

        MenuControls.displayListHeader(headers);
        ArrayList<Produto> produtos = loja.getProdutoByCategoria(texto);
        for(Produto produto : produtos) {

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

        }
        MenuControls.displayListFooter(headers);
    }

    public static void imprimirRecibo(Venda venda) {
        int maxLen = Comandos.menuWidth;
        String[] options = new String[] { "ID da Venda  ", "Data da Venda", "Funcionário  ", "Cliente      " };
        Object[] obj = new Object[] {
                venda.getId(),
                venda.getDataVenda(),
                venda.getFuncionario().getNome(),
                venda.getCliente() != null ? venda.getCliente().getNome() : "Não informado"
        };


        MenuControls.displayMenuOptionDetail("Recibo");
        String line;
        for (int i = 0; i < options.length; i++) {
            line = "║  " + Comandos.setTextoReset() + options[i] + Comandos.setCorLaranja() + "  │ " + Comandos.setTextoReset()
                    + MenuControls.leftAlignText(obj[i].toString(), maxLen - 21) + Comandos.setCorLaranja() + "║";
            Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
        }
        line = "╠";
        while (line.length() < maxLen-1) {
            line += "═";
        }
        line += "╣";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
        String[] headers = new String[]{"Código", "Nome              ", "Quant       ", "Preço Uni    " ,"Total          "};
        line = "║";
        for (int i = 0; i < headers.length; i++) {
            if (i > 0) {
                line += Comandos.setCorLaranja() + "│";
            }

            line += " " +Comandos.setTextoReset() +headers[i] + Comandos.setCorLaranja() + " ";
            while (line.length() < maxLen - 1 && i == headers.length - 1) {
                line += " ";
            }
        }
        while (line.length() < maxLen+91) {
            line += " ";
        }
        line += "║";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        line = "╠";
        for (int i = 0; i < headers.length; i++) {
            line += "═" + headers[i].replaceAll(".", "═") + "═";
            if (i < headers.length - 1) {
                line += "╪";
            }
        }

        while (line.length() < maxLen-1) {
            line += "═";
        }
        line += "╣";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
        for(ProdutoQuantidade pq : venda.getProdutos()) {
            Produto produto = pq.getProduto();
            double totalProduto = produto.getPreco() * pq.getQuantidade();
            MenuControls.displayListContentRow(headers, new Object[]{
                    produto.getCod(),
                    produto.getNome(),
                    pq.getQuantidade(),
                    String.format("%.2f", produto.getPreco()),
                    String.format("%.2f", totalProduto)
            });

        }

        line = "╠";
        while (line.length() < maxLen-1) {
            line += "═";
        }
        line += "╣";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());


        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Valor Total (sem IVA): " + String.format("%.2f", venda.getValorTotal())+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Valor do IVA: " + String.format("%.2f", venda.getValorTotalIVA())+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Valor Total (com IVA): " + String.format("%.2f", venda.getValorTotal() + venda.getValorTotalIVA())+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        // Mensagem de agradecimento
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText("", maxLen -3) + "║", Comandos.setCorLaranja());
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"OBRIGADO"+Comandos.setCorLaranja(), maxLen + 17) + "║", Comandos.setCorLaranja());


        // Rodapé do recibo
        line = "╰";
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╯";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

    }


}
