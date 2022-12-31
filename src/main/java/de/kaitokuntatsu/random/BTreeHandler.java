package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.BinaryTree;
import jdk.jfr.ContentType;

public class BTreeHandler {

    public void printTree(BinaryTree<ContentType> tree)
    {

    }

    public BinaryTree<String> create_termbaum(String term)
    {
        return null;
    }

    public void preorder_traversierung(BinaryTree<Character> tree)
    {
        if (tree.isEmpty()) { return; }
        System.out.println(tree.getContent());

        if (!tree.getLeftTree().isEmpty())
        {
            preorder_traversierung(tree.getLeftTree());
        }
        if (!tree.getRightTree().isEmpty())
        {
            preorder_traversierung(tree.getRightTree());
        }
    }
    public void inorder_traversierung(BinaryTree<Character> tree)
    {
        if (tree.isEmpty()) { return; }

        if (!tree.getLeftTree().isEmpty())
        {
            inorder_traversierung(tree.getLeftTree());
        }
        System.out.println(tree.getContent());
        if (!tree.getRightTree().isEmpty())
        {
            inorder_traversierung(tree.getRightTree());
        }
    }

    public static void main(String[] args) {
        BTreeHandler handler = new BTreeHandler();
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
        handler.inorder_traversierung(tree);

    }
}
