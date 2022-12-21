package de.kaitokuntatsu.random;

import de.joshua.DataStructures.List;

import java.util.Random;
import java.util.Scanner;

/**
 * Aufgabe [LD - 4.08] - Lineare Liste (List) - Vokabeltrainer<br>
 * Ein zweisprachiger Vokabeltrainer übers Terminal
 *
 * @author Joshua Hartjes
 * @version 1.0 | 19.01.2022
 * */
public class Vokabeltrainer {

    Scanner reader;
    Random rn;
    // Vokabellisten: noch völlig unbekannte Vokabeln, etwas bekanntere und bekannte Vokabeln
    List<Vokabel> list_unknown, list_known, list_perfect;

    /**
     * Aufgabe [LD - 4.08] - Lineare Liste (List) - Vokabeltrainer<br>
     * Ein zweisprachiger Vokabeltrainer übers Terminal
     *
     * @author Joshua Hartjes
     * */
    public Vokabeltrainer()
    {
        rn = new Random();
        list_known = new List<>();
        list_unknown = new List<>();
        list_perfect = new List<>();
        reader = new Scanner(System.in);
        System.out.println("Vocab Trainer by Joshua Hartjes\nTry /help to see all commands");
    }


    /**
     * Fügt ein Wort der Liste unbekannter Wörter hinzu
     * @param word - Vokabel, welche hinzugefügt werden soll
     * @param translation - Übersetzung der Vokabel
     * */
    private void add_word(String word, String translation)
    {
        list_unknown.append(new Vokabel(word, translation));
    }

    /**
     * Entfernt ein Wort aus dem Vokabeltrainer
     * @param word - Vokabel, welche gelöscht werden soll (nimmt die erste Übereinstimmung)
     * */
    private void remove_word(String word)
    {
        list_unknown.toFirst();
        while (list_unknown.hasAccess())
        {
            if (list_unknown.getContent().getContent().equals(word))
            {
                list_unknown.remove();
                return;
            }
            list_unknown.next();
        }
        list_known.toFirst();
        while (list_known.hasAccess())
        {
            if (list_known.getContent().getContent().equals(word))
            {
                list_known.remove();
                return;
            }
            list_known.next();
        }
        list_perfect.toFirst();
        while (list_perfect.hasAccess())
        {
            if (list_perfect.getContent().getContent().equals(word))
            {
                list_perfect.remove();
                return;
            }
            list_perfect.next();
        }
    }

    /**
     * Gibt die übergebene Liste im Terminal wieder
     * @param list - Die auszugebene Liste
     * */
    private void print_list(List<Vokabel> list)
    {
        list.toFirst();
        while (list.hasAccess())
        {
            System.out.println(list.getContent().getContent() + " : " + list.getContent().getTranslation());
            list.next();
        }
    }

    /**
     *  Gibt alle Listen aus
     * */
    private void print_all()
    {
        print_list(list_unknown);
        print_list(list_known);
        print_list(list_perfect);
    }

    /**
     *  Wählt eine Liste aus, aus der Vokabeln abgefragt werden
     * @return Eine der drei Vokabellisten abhängig von einem Zufallswert und absteigender Wahrscheinlichkeit je bannter die Vokabeln sind
     * */
    private List<Vokabel> choose_list_to_check()
    {
        int chance = rn.nextInt(11);
        if (chance > 8 && !list_perfect.isEmpty()) return list_perfect;
        else if (chance > 5 && !list_known.isEmpty()) return list_known;
        else if (!list_unknown.isEmpty()) return list_unknown;
        else if (!list_known.isEmpty()) return list_known;
        else if (!list_perfect.isEmpty()) return list_perfect;
        return null;
    }

    /**
     * Verschiebt eine Vokabel zwischen den Listen
     * @param voc - Vokabel, welche verschoben werden soll
     * @param list - Liste, in der sich die Vokabel befindet
     * @param up - True: Verschiebung in eine Liste bekannterer Woerter; <br>False: Verschiebung in eine Liste unbekannterer Wörter
     * */
    private void vocab_up_or_down(Vokabel voc, List<Vokabel> list, boolean up)
    {
        if (list == list_perfect && !up)
        {
            list.remove();
            list_known.append(voc);
        }
        else if (list == list_known && !up)
        {
            list.remove();
            list_unknown.append(voc);
        }
        else if (list == list_known)
        {
            list.remove();
            list_perfect.append(voc);
        }
        else if (list == list_unknown && up)
        {
            list.remove();
            list_known.append(voc);
        }
    }

    private Vokabel find_vocab(List<Vokabel> list)
    {
        int len = 0;
        list.toFirst();
        while (list.hasAccess())
        {
            len++;
            list.next();
        }
        list.toFirst();
        for (int i = 0; i < rn.nextInt(len);i++)
        {
            list.next();
        }
        return list.getContent();
    }

    /**
     *  Vokabelabfrage
     * @return True: Abfrage konnte gemacht werden; False: Es sind keine Vokabeln vorhanden, die Abgefragt werden könnten
     * */
    private boolean check_vocab()
    {
        List<Vokabel> list = choose_list_to_check();
        if (list == null) { System.out.println("Vocab list is empty"); return false;}
        Vokabel voc = find_vocab(list);
        if (voc == null) {return false;}
        String[] answers;
        if (rn.nextInt(2) % 2==0)
        {
            System.out.println("Translate the following: "+voc.getContent());
            answers = voc.getTranslation().replace(" ","").split(",");
        }
        else
        {
            System.out.println("Translate the following: "+voc.getTranslation());
            answers = voc.getContent().replace(" ","").split(",");
        }
        String input = reader.nextLine();
        for (String answer : answers)
        {
            if (answer.equals(input))
            {
                System.out.println("Correct!");
                vocab_up_or_down(voc, list, true);
                return true;
            }
        }
        System.out.println("Incorrect!");
        vocab_up_or_down(list.getContent(), list, false);
        return true;
    }

    private void syn_error() {System.out.println("Syntax Error. Try /help");}

    /**
     * Wählt aus, wie mit einem Userinput verfahren wird
     * @param in - Input, auf den reagiert werden soll
     * */
    private void react_on_command(String in)
    {
        String[] command;
        if (in.startsWith("/"))
        {
            command = in.replace("/","").split(" ",3);
        }
        else {
            syn_error();
            return;
        }

        switch (command[0])
        {
            case "print":
                if (command.length == 2) {
                    switch (command[1]) {
                        case "known" -> print_list(list_known);
                        case "unknown" -> print_list(list_unknown);
                        case "perfect" -> print_list(list_perfect);
                        case "all" -> print_all();
                        default -> {
                            syn_error();
                            return;
                        }
                    }
                    System.out.println("Printed");
                }
                else {syn_error();}
                break;
            case "add":
                if (command.length == 3)
                {
                    add_word(command[1], command[2]);
                    System.out.println("Added "+command[1]+" to list");
                }
                else {syn_error();}
                break;
            case "remove":
                if (command.length == 2)
                {
                    remove_word(command[1]);
                }
                else { syn_error();}
                break;
            case "help":
                System.out.println("""

                        Commands:
                        \t/add [word] [translations divided by ,]
                        \t/remove [word]
                        \t/check [number of vocabs you want to be asked for]
                        \t/close or /exit
                        \t/print [unknown, known, perfect or all depending on which words you want to see]""");
                break;
            case "check":
                try {
                    for (int j = 0; j < Integer.parseInt(command[1]); j++) {
                        if (!check_vocab()) break;
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException oob) {syn_error();}
                break;
            case "close":
            case "exit":
                System.exit(0);
                break;
            default:
                syn_error();
                break;
        }
    }

    /**
     * Schleife mit der Abfrage von Inputs
     * */
    public void run()
    {
        while (true)
        {
            react_on_command(reader.nextLine());
        }
    }

    public static void main(String[] args) {
        Vokabeltrainer vok = new Vokabeltrainer();
        vok.run();
    }
}
