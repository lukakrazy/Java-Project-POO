package console.controllers;

import app.App;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static app.App.clear;

public class Comandos {

    public static int menuWidth = 80;
    public static String defaultWaitForUserMessage = "Pressiona [ENTER] para continuar";

    /*------------------------- Comandos Texto -------------------------*/
    public static String setCorBranca() {
        return "\u001B[37m";
    }

    public static String setCorAmarelo() {
        return "\u001B[38;2;255;255;0m";
    }

    public static String setCorVerde() {
        return "\u001B[32m";
    }
    public static String setCorLaranja() {
        return "\u001B[38;2;255;69;0m";
    }

    public static String setCorVermelho() {
        return "\u001b[31m";
    }

    public static String setCorAzul() {
        return "\u001B[38;2;95;158;160m";
    }

    public static String setTextoReset() {
        return "\u001B[0m";
    }

    public static String setTextoBold() {
        return "\u001b[1m";
    }
/*------------------------- Comandos Inputs -------------------------*/
    public static String readEmail(String texto, String errorMessage, boolean permitirVazio) {
        while (true) {
            if (!texto.isEmpty() )
                System.out.print(setTextoBold() + texto + ": " + setTextoBold());
            try {
                String tmp = Ler.umaString().trim();

                if (!permitirVazio && tmp.isEmpty()) {
                    mensagemErro("[Não introduziu nenhum valor. Por favor, tente novamente.]");

                    continue;
                }
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(tmp);
                if(!matcher.matches()){
                    mensagemErro("[Não introduziu um email válido. Por favor, tente novamente.]");

                    continue;
                }

                return tmp;
            }catch(Exception e){
                mensagemErro(errorMessage);
            }
        }

    }
    static public String readString(String texto, String errorMessage, boolean permitirVazio){
        return readString( texto, errorMessage, permitirVazio, null);
    }
    static public String readString(String texto, String errorMessage, boolean permitirVazio, String[] escolhas) {
        while (true) {
            if (!texto.isEmpty() )
                System.out.print(setTextoBold() + texto + ": " + setTextoBold());
            try {
                String tmp = Ler.umaString().trim();

                if (escolhas != null) {
                    boolean valid = false;
                    for (String escolha : escolhas) {
                        if (escolha.equalsIgnoreCase(tmp)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        mensagemErro(errorMessage);
                        continue;
                    }
                }

                if (!permitirVazio && tmp.isEmpty()) {
                    mensagemErro("[Não introduziu nenhum valor. Por favor, tente novamente.]");

                    continue;
                }

                return tmp;
            }catch(Exception e){
                mensagemErro(errorMessage);
            }
        }
    }
    static public LocalDate converterData(String texto){
        try {
            texto = texto.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(texto, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido! Use o formato dd-MM-yyyy.");
        }
    }
    static public LocalDate readDateNascimento(String texto, String errorMessage) {
        return readDate( texto, errorMessage, false,true);
    }
    static public LocalDate readDateValidade(String texto, String errorMessage) {
        return readDate( texto, errorMessage, true,false);
    }
    static public LocalDate readDate(String texto, String errorMessage) {
        return readDate( texto, errorMessage, false,false);
    }
    static public LocalDate readDate(String texto, String errorMessage, boolean dataValidade,boolean nascimento) {
        while (true) {
            if (!texto.isEmpty())
                System.out.print(setTextoBold() + texto + ": " + setTextoReset());

            try {
                String input = Ler.umaString().trim();
                LocalDate date = converterData(input);

                if (dataValidade) {
                    if (date.isBefore(LocalDate.now())) {
                        mensagemErro("Produto fora de prazo!");
                        continue;
                    }
                }
                if(nascimento){
                    Period idade = Period.between(date, LocalDate.now());
                    if(idade.getYears() < 16){
                        mensagemErro("Tem que ter mais do que 16 anos!");
                        continue;
                    }
                }
                return date;
            } catch (Exception e) {
                mensagemErro(errorMessage);
            }
        }
    }

    static public int readInt(String texto, String errorMessage, boolean permitirVazio) {
        return readInt(texto,  errorMessage,  Integer.MIN_VALUE,  Integer.MAX_VALUE,permitirVazio);
    }
    static public int readInt(String texto, String errorMessage, int lenght, boolean permitirVazio) {
        return readInt(texto,  errorMessage,  lenght,  lenght,permitirVazio);
    }
    static public int readInt(String texto, String errorMessage, int minLenght, int maxLenght, boolean permitirVazio) {
        while (true) {
            if (!texto.isEmpty())
                System.out.print(setTextoBold() + texto + ": " + setTextoReset());
            try {
                String input = Ler.umaString().trim();

                if (!permitirVazio && texto.isEmpty()) {
                    mensagemErro(errorMessage);

                    continue;
                }
                int value = Integer.parseInt(input);

                if(input.length() < minLenght || input.length() > maxLenght) {
                    if (!errorMessage.isEmpty()) {
                        mensagemErro(errorMessage);
                    }
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                if (!errorMessage.isEmpty()) {
                    mensagemErro(errorMessage);
                }
            }
        }
    }

    static public double readDouble(String texto, String errorMessage, boolean permitirVazio) {
        return readDouble(texto, errorMessage, permitirVazio, Double.MIN_VALUE, Double.MAX_VALUE);
    }
    static public double readDouble(String texto, String errorMessage, double min, double max) {
        return readDouble(texto, errorMessage, false, min, max);
    }
    static public double readDouble(String texto, String errorMessage, boolean permitirVazio, double min, double max) {
        while (true) {
            if (!texto.isEmpty())
                System.out.print(setTextoBold() + texto + ": " + setTextoReset());
            try {
                String input = Ler.umaString().trim();
                if (!permitirVazio && texto.isEmpty()) {
                    mensagemErro(errorMessage);

                    continue;
                }
                double value = Double.parseDouble(input);

                if (value < min || value > max) {
                    if (!errorMessage.isEmpty()) {
                        mensagemErro(errorMessage);
                    }
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                if (!errorMessage.isEmpty()) {
                    mensagemErro(errorMessage);
                }
            }
        }
    }

    static public boolean readBoolean(String texto, String errorMessage) {
        while (true) {
            if (!texto.isEmpty())
                System.out.print(setTextoBold() + texto + ": " + setTextoReset());
            try {
                String input = Ler.umaString().trim().toLowerCase();

                if (input.equals("sim") || input.equals("s")) {
                    return true;
                } else if (input.equals("não") || input.equals("n")) {
                    return false;
                } else {
                    mensagemErro(errorMessage);
                }
            } catch (Exception e) {
                mensagemErro(errorMessage);
            }
        }
    }


    public static void waitForUserInput() {

        while (true) {
            String input = Ler.umaString();

            if (input.isEmpty()) {
                App.clear();
                return;
            }

            System.out.println(setCorAmarelo() + "[AVISO] " + defaultWaitForUserMessage + setTextoReset());
        }
    }

    public static void linhasVazias(int numero) {
        for (int i = numero; i > 0; i--) {
            System.out.println();
        }
    }
    /*------------------------- Comandos Erros -------------------------*/
    public static void mensagemErro(String text) {
        setCorMenssagem("[ERRO] " + text, setCorVermelho());
    }

    public static void menssagemAviso(String text) {
        setCorMenssagem("[AVISO] " + text, setCorAmarelo());
    }

    public static void menssagemSucesso(String text) {
        setCorMenssagem("[SUCESSO] " + text, setCorVerde());
    }

    public static void setCorMenssagem(String message, String ansiColor) {
        System.out.println(ansiColor + message + setTextoReset());
    }
}
