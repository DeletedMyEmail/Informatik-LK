package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.Stack;

public class Palindrome {

    Stack<Character> stack = new Stack<>();

    public boolean palindromPruefen(String pEingabe)
    {
        char[] eingabeChar = pEingabe.toCharArray();

        for(char x : eingabeChar) { stack.push(x); }

        for (int i = 0; i < eingabeChar.length/2; i++)
        {
            if (eingabeChar[i] != stack.top()) return false;
            stack.pop();
        }
        return true;
    }
}
