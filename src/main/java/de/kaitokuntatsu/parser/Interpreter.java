package de.kaitokuntatsu.parser;

import javax.swing.*;
import java.awt.*;
import de.kaitokuntatsu.datastructures.List;

public class Interpreter
{
    Stift stift;
    List<Befehl> befehle;

    public Interpreter()
    {
        befehle = new List<Befehl>();
        // Testaufrufe
        for (int i = 0; i < 52; i++)
        {
            befehle.append(new Befehl("VW", 3+i));
            befehle.append(new Befehl("RE", 20));
        }
    }

    public void bearbeiteAlleBefehle()
    {
        befehle.toFirst();
        while (befehle.hasAccess())
        {
            switch (befehle.getContent().getTyp())
            {
                case "VW":
                    stift.zeichneVorwaerts(befehle.getContent().getWert());
                    break;
                case "RE":
                    stift.dreheRechts(befehle.getContent().getWert());
                    break;
                case "LI":
                    stift.dreheRechts(befehle.getContent().getWert());
                    break;
                default:
                    break;
            }
            befehle.next();
        }
    }








    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Interpreter().erstelleGUI();
            }
        });
    }

    private void erstelleGUI()
    {
        JFrame f = new JFrame("GEO Interpreter");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GeoPanel geo = new GeoPanel();
        f.add(geo);
        f.pack();
        f.setVisible(true);

    }


    // Innere Klasse zum Erstellen der Grafik
    class GeoPanel extends JPanel
    {
        public GeoPanel()
        {
            setBorder(BorderFactory.createLineBorder(Color.black));
        }

        public Dimension getPreferredSize()
        {
            return new Dimension(600,400);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            stift = new Stift(g, 300, 200);
            bearbeiteAlleBefehle();
        }
    }
}