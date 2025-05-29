package logic;

import console.controllers.Comandos;
import console.controllers.Database;
import data.Funcionario;
import data.Loja;
public class FuncAdmin {
    //_______________________ METODOS ADMIN _______________________
    public static void reiniciarDatabase() {
        Funcionario funcionario = FuncVenda.verificarFuncionario();

        if(!funcionario.getCargo().equals("Administrador")){
            return;
        }
        String option = Comandos.readString("Tem a certeza (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) {
            return;
        }

        if (option.equalsIgnoreCase("S")) {

            if (Database.getPath().toFile().exists()) {
                if (Database.getPath().toFile().delete()) {
                    Comandos.menssagemSucesso("Ficheiro apagado com sucesso!");
                    Database.createData();
                    Comandos.menssagemSucesso("Dados reiniciados!");
                } else {
                    Comandos.mensagemErro("Não foi possível apagar o ficheiro.");
                }
            } else {
                Comandos.mensagemErro("O ficheiro não existe.");
            }

        }
    }

    public static void guardarDatabase() {
        String option = Comandos.readString("Tem a certeza (S/N) ['0' para cancelar]?", "Opção inválida", false, new String[]{"S", "N"});
        if (option.equals("0")) {
            return;
        }
        if (option.equalsIgnoreCase("S")) {
            Comandos.linhasVazias(1);
            System.out.println(Comandos.setCorAmarelo() + "[Guardar database] Por favor aguarde...");
            try {
                Database.storeDatabase();
                System.out.println(Comandos.setCorAmarelo() + "[Guardar database]" + Comandos.setCorVerde() + " Concluido" + Comandos.setTextoReset());
            } catch (Exception e) {
                System.out.println(Comandos.setCorAmarelo() + "[Guardar database]" + Comandos.setCorVermelho() + "Erro ao guardar a database:  " + e.getMessage() + Comandos.setTextoReset());

            }
        }
    }

    public static void mudarCodigoSeguranca(Loja loja) {
        Funcionario admin = FuncVenda.verificarFuncionario();

        if(!admin.getCargo().equals("Administrador")){
            return;
        }
        Funcionario funcionario;
        do {
            int id = Comandos.readInt("Introduz o id do funcionario pretendido ['0' para cancelar]", "Valor errado",false);
            if (id == 0) {
                return;
            }

            funcionario = loja.getFuncionariosById( id);
            if (funcionario != null) break;

            Comandos.mensagemErro("Id do funcionario não encontrado!");
        } while (true);
        int cod = Comandos.readInt("Introduz o novo código de segurança ['0' para cancelar]", "Valor errado",false);
        try{
            funcionario.setCodigoDeSeguranca(cod);
        }catch (Exception e){
            Comandos.mensagemErro("Problema ao trocar o código de segurança");
        }

    }
}
