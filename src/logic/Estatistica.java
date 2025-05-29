package logic;
import console.controllers.Comandos;
import console.controllers.MenuControls;
import data.Funcionario;
import data.Loja;
import data.Venda;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Estatistica {

    // Métodos de estatísticas gerais
    public static int contarClientes(Loja loja) {
        return loja.getClientes().size();
    }

    public static int contarFuncionarios(Loja loja) {
        return loja.getFuncionarios().size();
    }

    public static int contarProdutos(Loja loja) {
        return loja.getProdutos().size();
    }

    public static int contarVendas(Loja loja) {
        return loja.getVendas().size();
    }
    public static void linhaSeparar(int maxLen){
        String line;
        line = "╠";
        while (line.length() < maxLen-1) {
            line += "═";
        }
        line += "╣";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
    }

    public static void loja(Loja loja){
        int maxLen = Comandos.menuWidth;
        String line;

        MenuControls.displayMenuOptionDetail("Loja");
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Nome: " +  loja.getNome()+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Morada: " +  loja.getMorada()+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Data de fundação: " + loja.getDataFundacao()+Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        linhaSeparar(maxLen);

        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Contagem Total" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        String[] headers = {"Clientes", "Funcionarios", "Produtos", "Vendas                                 "};
        linhaSeparar(maxLen);
        MenuControls.displayListHeader(headers,false);

        MenuControls.displayListContentRow(headers, new Object[]{
                contarClientes(loja),
                contarFuncionarios(loja),
                contarProdutos(loja),
                contarVendas(loja)
        });
        linhaSeparar(maxLen);

        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Venda Com Maior Valor" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        linhaSeparar(maxLen);

        headers = new String[]{"Código", "Funcionario              ", "Cliente                     ", "Total   "};

        Venda maiorValor = vendaComMaiorValor(loja);
        if (maiorValor != null) {
            MenuControls.displayListHeader(headers,false);
            MenuControls.displayListContentRow(headers, new Object[]{
                    maiorValor.getId() != 0 ? maiorValor.getId() : "N/A",
                    maiorValor.getFuncionario() != null ? maiorValor.getFuncionario().getNome() : "N/A",
                    maiorValor.getCliente() != null ? maiorValor.getCliente().getNome() : "N/A",
                    String.format("%.2f", maiorValor.getValorTotal())
            });
        }

        MenuControls.displayListFooter(headers);



    }
    public static Venda vendaComMaiorValor(Loja loja) {
        ArrayList<Venda> vendas = loja.getVendas();
        Venda maiorVenda = null;
        double maxValor = 0.0;
        for (Venda venda : vendas) {
            if (venda.getValorTotal() > maxValor) {
                maxValor = venda.getValorTotal();
                maiorVenda = venda;
            }
        }
        return maiorVenda;
    }
    // ---------------------------------------------

    // Métodos de estatísticas para clientes
    public static void cliente(Loja loja){
        int maxLen = Comandos.menuWidth;
        String line;
        MenuControls.displayMenuOptionDetail("Clientes");
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Clientes totais: " +  contarClientes(loja) + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        linhaSeparar(maxLen);
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Cliente Com Mais Compras" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        clienteMaisCompras(loja);
    }
    public static void clienteMaisCompras(Loja loja) {
        String[] headers = {"Nome    ", "NIF       ", "Nº de Compras"};

        MenuControls.displayListHeader(headers,false);

        Collectors Collectors = null;
        int maxCompras = loja.getVendas().stream()
                .collect(Collectors.groupingBy(v -> v.getCliente(), Collectors.counting()))
                .values().stream().max(Long::compare).orElse(0L).intValue();

        loja.getClientes().stream()
                .filter(c -> loja.getVendas().stream()
                        .filter(v -> v.getCliente().equals(c))
                        .count() == maxCompras)
                .forEach(c -> {
                    int numVendas = (int) loja.getVendas().stream()
                            .filter(v -> v.getCliente().equals(c))
                            .count();
                    MenuControls.displayListContentRow(headers, new Object[]{
                            c.getNome(),
                            c.getNif(),
                            numVendas
                    });
                });
        MenuControls.displayListFooter(headers);
    }
    // ---------------------------------------------

    // Métodos de estatísticas para funcionários
    public static void funcionario(Loja loja){
        int maxLen = Comandos.menuWidth;
        String line;
        MenuControls.displayMenuOptionDetail("Funcionários");
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Funcionários totais: " +  contarFuncionarios(loja) + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Média Total De Vendas De Funcionários" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        funcionarioMediaVendas(loja,maxLen);
        linhaSeparar(maxLen);
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Funcionário Com Mais Vendas" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        funcionarioMaisVendas(loja,maxLen);
        linhaSeparar(maxLen);
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Funcionário Mais Lucrativo" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        funcionarioMaisLucrativo(loja,maxLen);
    }
    public static void funcionarioMediaVendas(Loja loja,int maxLen) {

        if (loja.getVendas().isEmpty()) {
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"0" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        } else {
            double media = loja.getVendas().size() / (double) loja.getFuncionarios().size();
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+media + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        }
    }

    public static void funcionarioMaisVendas(Loja loja,int maxLen) {
     ;
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Nome" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        if (loja.getVendas().isEmpty()) {
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Nome: Nenhum funcionário" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        } else {
            Funcionario funcionario = loja.getVendas().stream()
                    .collect(Collectors.groupingBy(v -> v.getFuncionario(), Collectors.counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+funcionario.getNome()+ Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());


        }
    }

    public static void funcionarioMaisLucrativo(Loja loja,int maxLen) {
        String[] headers = {"Nome"};
        String nome;
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Nome" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);

        Funcionario funcionario = loja.getVendas().stream()
                .collect(Collectors.groupingBy(v -> v.getFuncionario(), Collectors.summingDouble(Venda::getValorTotal)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if(funcionario == null) {
            nome = "Nenhum funcionário";
        }else{
            nome = funcionario.getNome();
        }

        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+nome+ Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        MenuControls.displayListFooter(headers);
    }
    // ---------------------------------------------

    // Métodos de estatísticas para produtos/venda

    public static void produto(Loja loja){
        int maxLen = Comandos.menuWidth;
        String line;
        MenuControls.displayMenuOptionDetail("Funcionários");
        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Produtos totais: " +  contarProdutos(loja) + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Produtos Mais Vendidos" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        produtoMaisVendido(loja,maxLen);
        linhaSeparar(maxLen);
        produtosVendidos(loja,maxLen);
    }

    public static void produtoMaisVendido(Loja loja,int maxLen) {
            String[] headers = {"Produto    "};

        Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"Produto"+ Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);
        if (loja.getVendas().isEmpty()) {
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset() + "Nenhum produto" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

        } else {
            loja.getVendas().stream()
                .flatMap(v -> v.getProdutos().stream())
                .collect(Collectors.groupingBy(p -> p.getProduto(), Collectors.summingInt(p -> p.getQuantidade())))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> {
                    Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+entry.getKey().getNome()+Comandos.setTextoReset()+"("+entry.getValue()+")"+ Comandos.setCorLaranja(), maxLen +21) + "║", Comandos.setCorLaranja());

                });
        }
    }

    public static void produtosVendidos(Loja loja,int maxLen) {
        String[] headers = {"Quantidade total de produtos vendidos"};
        Comandos.setCorMenssagem("║ " + MenuControls.centerText(Comandos.setTextoReset()+"Quantidade Total De Produtos Vendidos" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());
        linhaSeparar(maxLen);

        if (loja.getVendas().isEmpty()) {
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+"0" + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

            MenuControls.displayListFooter(headers);
        } else {
            int total = loja.getVendas().stream()
                    .flatMap(v -> v.getProdutos().stream())
                    .mapToInt(p -> p.getQuantidade())
                    .sum();
            Comandos.setCorMenssagem("║ " + MenuControls.leftAlignText(Comandos.setTextoReset()+total + Comandos.setCorLaranja(), maxLen +17) + "║", Comandos.setCorLaranja());

            MenuControls.displayListFooter(headers);
        }
    }


    // ---------------------------------------------

}
