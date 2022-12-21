package de.kaitokuntatsu.parser;

import de.joshua.DataStructures.Stack;

public class KlammerschachtelungsParser {

    private static int zustand;
    private static Stack<Character> keller = new Stack<>();

    public static boolean parse(String pWord) {
        return parse(pWord.toCharArray());
    }

    public static boolean parse(char[] pWord) {

        for (char symbol : pWord) {
            if (symbol == '.')
                break;
            if (symbol == '}') {
                if (keller.isEmpty())
                    return false;
                else
                    keller.pop();
            }
            else if (symbol == '{')
                keller.push('K');
        }

        return keller.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(parse("{{}}}"));
    }
}
