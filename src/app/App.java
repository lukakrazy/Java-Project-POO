package app;

import console.controllers.Comandos;
import console.controllers.Database;
import console.controllers.Menu;
import console.UI;
import data.Loja;

public class App {

    public static boolean running = false;
    Database db = new Database();
    UI ui = new UI();
    Menu menu = new Menu();

    public void start() throws Exception {
        if (running) {
            return;
        }

        System.out.println(Comandos.setCorAmarelo() + "[Carregar database] Por favor aguarde..." + Comandos.setTextoReset());
        if (Database.getPath().toFile().exists()) {
            try {
                System.out.println(Comandos.setCorAmarelo() + "[Carregar database] Ficheiro carregado..." + Comandos.setTextoReset());
                Database.loja = db.loadDatabase();
            } catch (Exception e) {
                throw new Exception(Comandos.setCorAmarelo() + "[Carregar database]" + Comandos.setCorVermelho() + " Erro ao carregar a database: " + e.getMessage() + Comandos.setTextoReset());
            }
        } else {
            System.out.println(Comandos.setCorAmarelo() + "[Carregar database] Database nao encontrada...");
            System.out.println("[Carregar database]" + Comandos.setCorVerde() + " Pronto para iniciar" + Comandos.setTextoReset());
            Database.createData();
        }

        running = true;
    }

    public void stop() {
        if (!running) {
            return;
        }
        Comandos.linhasVazias(1);
        System.out.println(Comandos.setCorAmarelo() + "[Guardar database] Por favor aguarde...");
        try {
            db.storeDatabase();
            System.out.println(Comandos.setCorAmarelo() + "[Guardar database]" + Comandos.setCorVerde() + " Concluido" + Comandos.setTextoReset());
        } catch (Exception e) {
            System.out.println(Comandos.setCorAmarelo() + "[Guardar database]" + Comandos.setCorVermelho() + "Erro ao guardar a database:  " + e.getMessage() + Comandos.setTextoReset());
            return;
        }

        running = false;
    }
    public static void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    public void runMainMenu() {
        if (!running) {
            return;
        }

        ui.displaySplashScreen();
        clear();
        menu.processMainMenu(Database.loja);
    }
    public static void main(String[] args) {
        App app = new App();


        try {
            app.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return;
        }
        do {
            app.runMainMenu();
            app.stop();
        } while (running);


    }

}
