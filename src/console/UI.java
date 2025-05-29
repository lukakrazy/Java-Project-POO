package console;

import console.controllers.Comandos;

public class UI {
    public void displaySplashScreen() {
        System.out.println(Comandos.setTextoReset() + Comandos.setTextoBold());
        System.out.println(" $$$$$$\\  $$\\ $$\\                                    $$\\                               $$\\    $$\\ $$\\   $$\\               $$\\           ");
        System.out.println("$$  __$$\\ $$ |\\__|                                   $$ |                              $$ |   $$ |\\__|  $$ |              \\__|          ");
        System.out.println("$$ /  $$ |$$ |$$\\ $$$$$$\\$$$$\\   $$$$$$\\  $$$$$$$\\ $$$$$$\\    $$$$$$\\   $$$$$$$\\       $$ |   $$ |$$\\ $$$$$$\\    $$$$$$\\  $$\\  $$$$$$$\\ ");
        System.out.println("$$$$$$$$ |$$ |$$ |$$  _$$  _$$\\ $$  __$$\\ $$  __$$\\\\_$$  _|  $$  __$$\\ $$  _____|      \\$$\\  $$  |$$ |\\_$$  _|   \\____$$\\ $$ |$$  _____|");
        System.out.println("$$  __$$ |$$ |$$ |$$ / $$ / $$ |$$$$$$$$ |$$ |  $$ | $$ |    $$ /  $$ |\\$$$$$$\\         \\$$\\$$  / $$ |  $$ |     $$$$$$$ |$$ |\\$$$$$$\\  ");
        System.out.println("$$ |  $$ |$$ |$$ |$$ | $$ | $$ |$$   ____|$$ |  $$ | $$ |$$\\ $$ |  $$ | \\____$$\\         \\$$$  /  $$ |  $$ |$$\\ $$  __$$ |$$ | \\____$$\\ ");
        System.out.println("$$ |  $$ |$$ |$$ |$$ | $$ | $$ |\\$$$$$$$\\ $$ |  $$ | \\$$$$  |\\$$$$$$  |$$$$$$$  |         \\$  /   $$ |  \\$$$$  |\\$$$$$$$ |$$ |$$$$$$$  |");
        System.out.println("\\__|  \\__|\\__|\\__|\\__| \\__| \\__| \\_______|\\__|  \\__|  \\____/  \\______/ \\_______/           \\_/    \\__|   \\____/  \\_______|\\__|\\_______/ ");
        Comandos.linhasVazias(1);
        System.out.println("                                        "+Comandos.setCorLaranja()+"        ╭═══════════════════════════════════════════════════╮");
        System.out.println("                                                |   "+Comandos.setTextoReset()+"Bem vindo! [Pressiona"+Comandos.setCorVerde()+" [ENTER]"+Comandos.setTextoReset()+" para continuar] "+Comandos.setCorLaranja()+"  |");
        System.out.println("                                                ╰═══════════════════════════════════════════════════╯");

        Comandos.waitForUserInput();
        Comandos.linhasVazias(4);
    }
}
