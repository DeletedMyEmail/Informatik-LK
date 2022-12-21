package de.kaitokuntatsu.random;
import de.joshua.DataStructures.BinaryTree;

/**
 * Lösungsansatz zur Aufgabe [NLD.BTR.08] - Morsealphabet
 *
 * @author Joshua Hartjes
 * @version 2.0 | 07.02.2022 | https://github.com/KaitoKunTatsu/Java-Q1
 * */
public class Morsealphabet {

    BinaryTree<Character> tree = new BinaryTree<>(' ',
            new BinaryTree<>('E',
                    new BinaryTree<>('I',
                            new BinaryTree<>('S',
                                    new BinaryTree<>('H'),
                                    new BinaryTree<>('V')),
                            new BinaryTree<>('U',
                                    new BinaryTree<>('F'),
                                    null)),
                    new BinaryTree<>('A',
                            new BinaryTree<>('R',
                                    new BinaryTree<>('L'),
                                    null),
                            new BinaryTree<>('W',
                                    new BinaryTree<>('P'),
                                    new BinaryTree<>('J')))),
            new BinaryTree<>('T',
                    new BinaryTree<>('N',
                            new BinaryTree<>('D',
                                    new BinaryTree<>('B'),
                                    new BinaryTree<>('X')),
                            new BinaryTree<>('K',
                                    new BinaryTree<>('C'),
                                    new BinaryTree<>('Y'))),
                    new BinaryTree<>('M',
                            new BinaryTree<>('G',
                                    new BinaryTree<>('Z'),
                                    new BinaryTree<>('Q')),
                            new BinaryTree<>('O'))));


    /**
     * Wandelt einen Input von Morsezeichen in den entsprechenden char um
     * @param zeichen - String mit Morsezeichen, welche einen Buchstaben ergeben sollen
     * */
    public char morse_char(String zeichen)
    {
        BinaryTree<Character> current = tree;
        for (char chr : zeichen.toCharArray())
        {
            if (!(chr == '.' || chr == '-'))
            {
                System.out.println("Invalid String");
                return '!';
            }

            if (chr == '.' && !current.getLeftTree().isEmpty())
            {
                current = current.getLeftTree();
            }
            else if (chr == '-' && !current.getRightTree().isEmpty())
            {
                current = current.getRightTree();
            }
            else
            {
                System.out.println("Invalid String");
                return '!';
            }
        }
        return current.getContent();
    }

    public String morse_string(String str)
    {
        String[] split = str.split(" ");
        StringBuilder output = new StringBuilder();
        for (String j : split)
        {
            StringBuilder chars = new StringBuilder();
            for (String i : j.split("/"))
            {
                chars.append(morse_char(i));
            }
            output.append(chars).append(" ");
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Morsealphabet morse = new Morsealphabet();
        System.out.println(morse.morse_string("-/./.../- -----/sfs"));
    }
}
