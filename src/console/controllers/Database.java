package console.controllers;

import data.*;
import app.App;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Database {
    static Path baseDir = Paths.get(System.getProperty("user.dir"));

    // Caminho para a pasta src/database
    static Path databaseDir = baseDir.resolve("src").resolve("database");
    static Path path = Paths.get(String.valueOf(databaseDir), "app_database.dat");
    public static Loja loja ;
    public static Path getPath() {
        return path;
    }
    public Loja loadDatabase() throws Exception {
        ObjectInputStream is = null;

        try {
             is = new ObjectInputStream(new FileInputStream(path.toString()));

            Loja loja = (Loja) is.readObject();

            // Restore ID counters
            Cliente.setUltimo(loja.getClientes().size());
            Funcionario.setUltimo(loja.getFuncionarios().size());
            System.out.println(Comandos.setCorAmarelo() + "[Esperar database]" + Comandos.setCorVerde() + " Feito" + Comandos.setTextoReset());

            return loja;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(Comandos.setCorAmarelo() + "[Esperar database]" + Comandos.setCorVermelho() + "Erro inesperado por " + e.getMessage() + Comandos.setTextoReset());
            }
        }
    }
    public static void storeDatabase() throws Exception {
        if (!App.running) {
            return;
        }

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path.toString()));
        System.out.println(Comandos.setCorAmarelo() + "[Guardar database] A guardar ficheiro..." + Comandos.setTextoReset());
        os.writeObject(loja);
        os.flush();
        os.close();
    }

    public static void createData() {
        loja = new Loja("Continente", "02-03-1959",  "Rua de Santo Brás"); // Create base escola

        Cliente clienteA = new Cliente("luka", 247445347,  LocalDate.of(1999, 8, 5) , "luis_amandio@hotmail.com");
        Cliente clienteB = new Cliente("Ana", 123456789, LocalDate.of(1987, 4, 15), "ana.santos@gmail.com");
        Cliente clienteC = new Cliente("Carlos", 987654321, LocalDate.of(1992, 11, 23), "carlos.martins@hotmail.com");
        Cliente clienteD = new Cliente("Beatriz", 654321987, LocalDate.of(2000, 1, 10), "beatriz.silva@gmail.com");
        Cliente clienteE = new Cliente("João", 321987654, LocalDate.of(1995, 6, 30), "joao.rocha@yahoo.com");

        Funcionario funcionario1 = new Funcionario("Ana Silva", 123456789, LocalDate.of(1990, 5, 10), "ana.silva@email.com", "Colaborador", 2500.00);
        Funcionario funcionario2 = new Funcionario("João Pereira", 987654321, LocalDate.of(1985, 8, 22), "joao.pereira@email.com", "Colaborador", 3200.00);
        Funcionario funcionario3 = new Funcionario("Maria Oliveira", 456789123, LocalDate.of(1993, 2, 15), "maria.oliveira@email.com", "Colaborador", 2800.00);
        Funcionario funcionario4 = new Funcionario("Carlos Mendes", 654321987, LocalDate.of(1978, 12, 30), "carlos.mendes@email.com", "Administrador", 4500.00);
        Funcionario funcionario5 = new Funcionario("Sofia Costa", 321987654, LocalDate.of(1995, 7, 5), "sofia.costa@email.com", "Colaborador", 2000.00);

        Categoria categoria1 = new Categoria("Frescos", "Produtos como frutas, vegetais, carnes, peixes e laticínios frescos.");
        Categoria categoria2 = new Categoria("Congelados", "Alimentos congelados, incluindo vegetais, carnes, refeições prontas e sobremesas.");
        Categoria categoria3 = new Categoria("Enlatados e Conservas", "Produtos enlatados ou em conserva, como atum, milho, feijão, picles e molhos.");
        Categoria categoria4 = new Categoria("Padaria e Confeitaria", "Produtos como pães, bolos, biscoitos, tortas e croissants, entre outros.");
        Categoria categoria5 = new Categoria("Bebidas", "Inclui sucos, refrigerantes, águas, cafés, vinhos e outras bebidas.");

        Produto produto1 = new Produto(categoria1, "Maçã Gala", "Fruta fresca, ideal para consumo direto ou em saladas.", 1.50, LocalDate.of(2024, 1, 15), true,23 );
        Produto produto2 = new Produto(categoria1,"Pizza Congelada","Pizza de queijo e fiambre, pronta para assar.", 5.99, LocalDate.of(2024, 6, 30), false,23);
        Produto produto3 = new Produto(categoria3, "Atum em Lata", "Atum sólido em óleo vegetal, ideal para saladas e sanduíches.", 2.79, LocalDate.of(2026, 12, 1), false,23);
        Produto produto4 = new Produto(categoria4, "Pão de Centeio", "Pão artesanal de centeio, com crosta crocante.", 1.20, LocalDate.of(2024, 1, 5), false,23);
        Produto produto5 = new Produto(categoria5,"Sumo de Laranja Natural","Sumo de laranja 100% natural, sem adição de açúcar.", 2.50, LocalDate.of(2024, 3, 20), false,23);

        try {
            loja.increveCliente(clienteA);
            loja.increveCliente(clienteB);
            loja.increveCliente(clienteC);
            loja.increveCliente(clienteD);
            loja.increveCliente(clienteE);

            loja.increveFuncionario(funcionario1);
            loja.increveFuncionario(funcionario2);
            loja.increveFuncionario(funcionario3);
            loja.increveFuncionario(funcionario4);
            loja.increveFuncionario(funcionario5);

            loja.criarCat(categoria1);
            loja.criarCat(categoria2);
            loja.criarCat(categoria3);
            loja.criarCat(categoria4);
            loja.criarCat(categoria5);

            loja.adicionarProduto(produto1);
            loja.adicionarProduto(produto2);
            loja.adicionarProduto(produto3);
            loja.adicionarProduto(produto4);
            loja.adicionarProduto(produto5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
