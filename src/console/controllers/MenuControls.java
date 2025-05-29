package console.controllers;

import java.util.HashMap;

public class MenuControls {
    public static void displayMenuOptionDetail(String title) {
        int maxLen = Comandos.menuWidth;

        String line = "╭";
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╮";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        // Título do menu
        line = "║"+Comandos.setTextoReset() + centerText(title, maxLen - 2)+Comandos.setCorLaranja() + "║";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        // Linha separadora do título
        line = "╠";
        while (line.length() < maxLen - 5) {
            line += "═";
        }
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╣";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
    }
    public static void displayTopList(String title) {
        int maxLen = Comandos.menuWidth;

        String line = "╭";
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╮";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        // Título do menu
        line = "║"+Comandos.setTextoReset() + centerText(title, maxLen - 2)+Comandos.setCorLaranja() + "║";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        // Linha separadora do título
        line = "╰";
        while (line.length() < maxLen - 5) {
            line += "═";
        }
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╯";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
    }
    public static void displayMenu(String title, String[] options) {
        int maxLen = Comandos.menuWidth;

        // Linha superior do menu

        String line ;
        displayMenuOptionDetail(title);

        int optionId = 1;
        for (String option : options) {
            if (option.equals(options[options.length - 1])) {
                optionId = 0;
            }

            line = "║  " + Comandos.setTextoReset() + optionId + Comandos.setCorLaranja() + "  │ " + Comandos.setTextoReset() + leftAlignText(option, maxLen - 9) + Comandos.setCorLaranja() + "║";
            if (option.isEmpty()) {
                line = "║   " + Comandos.setTextoReset() +  leftAlignText(option, maxLen - 9) + Comandos.setCorLaranja() + "    ║";
            }
            optionId++;
            Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
        }


        line = "╰";
        while (line.length() < maxLen - 5) {
            line += "═";
        }
        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╯";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
    }
    public static void displayQuestion(String question) {
        int maxLen = Comandos.menuWidth;

        String line1 = "+---";
        while (line1.length() < maxLen) {
            line1 += "-";
        }
        line1 += "+";
        Comandos.setCorMenssagem(line1, Comandos.setCorVerde() + Comandos.setTextoBold());

        line1 = "| " + question;
        while (line1.length() < maxLen) {
            line1 += " ";
        }
        line1 += "|";
        Comandos.setCorMenssagem(line1, Comandos.setCorVerde() + Comandos.setTextoBold());

        line1 = "+---";
        while (line1.length() < maxLen) {
            line1 += "-";
        }
        line1 += "+";
        Comandos.setCorMenssagem(line1, Comandos.setCorVerde() + Comandos.setTextoBold());
    }
    public static void displayListHeader(String headers[]) {
         displayListHeader(headers, true);
    }
    public static void displayListHeader(String headers[], boolean withTop) {
        int maxLen = Comandos.menuWidth;
        String line = "";
        if (withTop) {
            line = "╭";
            for (int i = 0; i < headers.length; i++) {
                line += "═" + headers[i].replaceAll(".", "═") + "═";
                if (i < headers.length - 1) {
                    line += "╤";
                }
            }
            while (line.length() < maxLen-1) {
                line += "═";
            }
            line += "╮";
            Comandos.setCorMenssagem(line, Comandos.setCorLaranja());

        }



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
    }
    public static void displayListContentRow(String headers[], Object[] columns) {
        int maxLen = Comandos.menuWidth;

        HashMap<String, Integer> sizes = new HashMap();
        for (String header : headers) {
            sizes.put(header, header.length());
        }

        String line1 = "";
        for (int columnIdx = 0; columnIdx < columns.length; columnIdx++) {
            String header = headers[columnIdx];
            String sColumn = columns[columnIdx].toString();

            while (sColumn.length() < sizes.get(header)) {
                sColumn += " ";
            }
            if (columnIdx == 0) {
                line1 += Comandos.setCorLaranja() + "║ " + Comandos.setTextoReset() + sColumn + " ";
            }else{
                line1 += Comandos.setCorLaranja() + "│ " + Comandos.setTextoReset() + sColumn + " ";
            }

        }

        while (line1.length() < maxLen+59) {
            line1 += " ";
        }
        line1 += Comandos.setCorLaranja() +"║";
        Comandos.setCorMenssagem(line1,Comandos.setCorLaranja());
    }

    public static void displayListFooter(String headers[]) {
        int maxLen = Comandos.menuWidth;;

        String line = "╰";
        for (int i = 0; i < headers.length; i++) {
            line += "═" + headers[i].replaceAll(".", "═") + "═";
            if (i < headers.length - 1) {
                line += "╧";
            }
        }

        while (line.length() < maxLen - 1) {
            line += "═";
        }
        line += "╯";
        Comandos.setCorMenssagem(line, Comandos.setCorLaranja());
    }

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(text);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

    public static String leftAlignText(String text, int width) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

}
