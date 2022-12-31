package de.kaitokuntatsu.random;

import de.kaitokuntatsu.datastructures.BinaryTree;

public class RekursivesEinfuegen {

    BinaryTree<Informatiker> info_tree = new BinaryTree<>();

    public void add(Informatiker pInformatiker)
    {
        BinaryTree<Informatiker> current = info_tree;
        while(true)
        {
            if (current.isEmpty() || current.getContent().isEqual(pInformatiker))
            {
                current.setContent(pInformatiker);
                return;
            }

            if (pInformatiker.isLess(current.getContent()))
            {
                current = current.getLeftTree();
            }
            else
            {
                current = current.getRightTree();
            }
        }
    }

    public static void main(String[] args) {

    }
}
