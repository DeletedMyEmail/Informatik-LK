package de.kaitokuntatsu.random;
import de.joshua.DataStructures.BinaryTree;

public class InformatikerVerwaltung
{
    private BinaryTree<Informatiker> informatikerBaum;


    public InformatikerVerwaltung()
    {
        informatikerBaum = new BinaryTree<Informatiker>();
    }



    public void neuenInformatikerAnlegen(String pName, String pDatum)
    {
        Informatiker informatiker = new Informatiker(pName, pDatum);
        fuegeEin(informatikerBaum, informatiker);
    }

    public void fuegeEin(Informatiker... pInformatiker)
    {
        for (Informatiker info : pInformatiker) { fuegeEin(informatikerBaum, info ); }
    }

    
    private void fuegeEin(BinaryTree<Informatiker> b, Informatiker pInformatiker)
    {
        if (b.isEmpty() || b.getContent().isEqual(pInformatiker))
        {
            b.setContent(pInformatiker);
        }

        else if (pInformatiker.isLess(b.getContent()))
        {
            fuegeEin(b.getLeftTree(), pInformatiker);
        }
        else
        {
            fuegeEin(b.getRightTree(), pInformatiker);
        }
    }

    private boolean isLeaf(BinaryTree<Informatiker> tree)
    {
        return tree.getRightTree().isEmpty() && tree.getLeftTree().isEmpty();
    }

    private Informatiker findeKleistesElementImRechtenTeilbaum(BinaryTree<Informatiker> tree)
    {
        if (!tree.getLeftTree().isEmpty())
        {
            if (isLeaf(tree.getLeftTree()))
            {
                Informatiker ret = tree.getLeftTree().getContent();
                tree.setLeftTree(new BinaryTree<Informatiker>());
                return ret;
            }
            return findeKleistesElementImRechtenTeilbaum(tree.getLeftTree());
        }

        if (!tree.getRightTree().isEmpty())
        {
            if (isLeaf(tree.getRightTree()))
            {
                Informatiker ret = tree.getRightTree().getContent();
                tree.setRightTree(new BinaryTree<Informatiker>());
                return ret;
            }
            return findeKleistesElementImRechtenTeilbaum(tree.getRightTree());
        }
        if (!tree.isEmpty())
        {
            return tree.getContent();
        }
        else return null;
    }

    public void loesche(String pName) {
        BinaryTree<Informatiker> current = informatikerBaum;
        BinaryTree<Informatiker> parent = informatikerBaum;

        while (!current.isEmpty() && current.getContent().gibName().equals(pName)) {
            parent = current;
            if (current.getContent().gibName().compareTo(pName) > 0) {
                current = current.getLeftTree();
            } else {
                current = current.getRightTree();
            }
        }

        if (current.isEmpty()) {
            return;
        }

        if (isLeaf(current)) {
            if (parent.getLeftTree().equals(current)) {
                parent.setLeftTree(new BinaryTree<Informatiker>());
            } else {
                parent.setRightTree(new BinaryTree<Informatiker>());
            }
        }
        else if (!current.getRightTree().isEmpty())
        {
            if (!isLeaf(current.getRightTree()))
            {
                Informatiker right_subtree = findeKleistesElementImRechtenTeilbaum(current.getRightTree());
                current.setContent(right_subtree);
            }
            else
            {
                current.setContent(current.getRightTree().getContent());
                current.setRightTree(new BinaryTree<Informatiker>());
            }
        }
        else
        {
            if (parent.getLeftTree().equals(current))
            {
                parent.setLeftTree(current.getLeftTree());
            }
            else
            {
                parent.setRightTree(current.getLeftTree());
            }
        }
    }


    public Informatiker search(String pName) { return search(informatikerBaum, pName);}

    private Informatiker search(BinaryTree<Informatiker> tree, String pName)
    {
        if (tree.isEmpty()) {
            return null;
        }
        else
        {
            if (pName.compareTo(tree.getContent().gibName()) < 0)
            {
                return search(tree.getLeftTree(), pName);
            }
            else if (pName.compareTo(tree.getContent().gibName()) > 0)
            {
                return search(tree.getRightTree(), pName);
            }
            else if (pName.equals(tree.getContent().gibName()))
            {
                return tree.getContent();
            }
            else
            {
                return null;
            }
        }
    }

    public BinaryTree<Informatiker> getInformatikerBaum() {
        return informatikerBaum;
    }

    public static void main(String[] args) {

        InformatikerVerwaltung verwaltung = new InformatikerVerwaltung();

        Informatiker info = new Informatiker("3", "");

        verwaltung.fuegeEin(new Informatiker("5", ""), info, new Informatiker("4",""));

        verwaltung.loesche("3");

        BaumZeichner<Informatiker> zeichner = new BaumZeichner<Informatiker>(verwaltung.getInformatikerBaum());

    }
}
