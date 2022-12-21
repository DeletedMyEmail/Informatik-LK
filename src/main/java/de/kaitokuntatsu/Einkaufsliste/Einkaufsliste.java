package de.kaitokuntatsu.Einkaufsliste;
import de.joshua.DataStructures.List;

/**
 *  Einkaufsliste mit GUI?
 *
 * @version 1.1 | 20.12.2021
 * @author Joshua Hartjes | KaitoKunTatsu
 * */
public class Einkaufsliste {

    private static List<Artikel> list = new List<Artikel>();
    private static double buget;
    GUI gui;

    public Einkaufsliste(double pBudget)
    {
        this.buget = pBudget;
        gui = new GUI();
    }

    public static void addArtikel(Artikel artikel)
    {
        list.append(artikel);
    }

    public static Artikel getArtikel(int index)
    {
        list.toFirst();
        try
        {
            if (index <= 0) return null;
            for (int i = 0; i < index-1; i++) list.next();
            return list.getContent();
        }
        catch (Exception ex) {return null;}
    }

    public static void markArtikel(int index)
    {
        if (getArtikel(index) != null) getArtikel(index).gekauft();
    }

    public static void removeArtikel(int index)
    {
        list.toFirst();
        try
        {
            if (index <= 0) return;
            for (int i = 0; i < index-1; i++) list.next();
            if (list.hasAccess()) list.remove();

        }
        catch (Exception ex) {ex.printStackTrace();}
    }

    public static Artikel[] getFuenfArtikel(int side)
    {
        Artikel[] ret = new Artikel[5];
        list.toFirst();

        for (int j = 0; j < side*5;j++) { list.next(); }

        for(int i = 0; i < 5 ;i++)
        {
            if (list.hasAccess()) ret[i] = list.getContent();
            else { ret[i] = null; }
            list.next();
        }
        return ret;
    }

    public static double getBuget() {return buget;}

    public static void setBuget(double num)
    {
            buget = num;
    }

    public static String getBugetString()
    {
        if (buget * buget > 999*999) return "999+$";
        return String.valueOf(buget)+"$";
    }

    public static void main(String[] args)
    {
        new Einkaufsliste(1000);
    }

}
