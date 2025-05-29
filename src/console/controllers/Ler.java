package console.controllers;

import console.controllers.Comandos;

import java.io.*;

public class Ler {
    // Metodo para ler uma String:
    public static String umaString (){
        String s = "";
        try{
            BufferedReader in = new BufferedReader ( new InputStreamReader (System.in));
            s= in.readLine();
        }
        catch (IOException e){
            Comandos.mensagemErro("[Não introduziu uma string válida. Por favor, tente novamente.]");
        }
        return s;
    }
    // Metodo para ler um int:
    public static int umInt(){
        while(true){
            try{
                return Integer.parseInt(umaString().trim());
            } catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um integer válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um byte:
    public static byte umByte(){
        while(true){
            try{
                return Byte.parseByte(umaString().trim());
            }
            catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um byte válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um short:
    public static short umShort(){
        while(true){
            try{
                return Short.parseShort(umaString().trim());
            }
            catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um short válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um long:
    public static long umLong(){
        while(true){
            try{
                return Long.parseLong(umaString().trim());
            }
            catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um long válido. Por favor, tente novamente.]");
            }
        }
    }
    //// Metodo para ler um float;
    public static float umFloat(){
        while(true){
            try{
                return Float.parseFloat(umaString().trim());
            }
            catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um float válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um double:
    public static double umDouble(){
        while(true){
            try{
                return Double.valueOf(umaString().trim());
            }
            catch(NumberFormatException e){
                Comandos.mensagemErro("[Não introduziu um double válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um char:
    public static char umChar(){
        while(true){
            try{
                return umaString().charAt(0);
            }
            catch(Exception e){
                Comandos.mensagemErro("[Não introduziu um char válido. Por favor, tente novamente.]");
            }
        }
    }
    // Metodo para ler um boolean:
    public static boolean umBoolean(){
        while(true){
            try{
                return Boolean.parseBoolean(umaString().trim());
            }
            catch(Exception e){
                Comandos.mensagemErro("[Não introduziu um boolean válido. Por favor, tente novamente.]");
            }
        }
    }
    public static boolean umVazio() {
        while(true){
            try {
                if (umaString().trim().isEmpty()) {
                    Comandos.mensagemErro("[ERRO: Não introduziu nenhum valor. Por favor, tente novamente.]");
                    return true;
                }
                return false;
            } catch (Exception e) {
                Comandos.mensagemErro("[ERRO: Ocorreu um problema. Por favor, tente novamente.]");
            }
        }
    }
}