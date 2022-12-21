package de.kaitokuntatsu.Einkaufsliste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 *  Shopping list GUI
 *
 * @version 1.0 | 14.12.2021
 * @author Joshua Hartjes
 * */
public class GUI {

    // Fenster
    JFrame frame = new JFrame("Shopping List By Joshua");

    // Panels
    JPanel mainPanel = new JPanel();
    JPanel listManager = new JPanel();

    // Fields
    JLabel title = new JLabel("Einkaufsliste");
    JLabel listTitle = new JLabel("Artikel:");
    JLabel bugetLabel = new JLabel("0$");

    JButton[] artikelArr = {
            new JButton("1."),
            new JButton("2."),
            new JButton("3."),
            new JButton("4."),
            new JButton("5.")
    };
    JCheckBox[] checkBoxArr = {
            new JCheckBox(),
            new JCheckBox(),
            new JCheckBox(),
            new JCheckBox(),
            new JCheckBox()
    };

    JLabel txt_addToList = new JLabel("Artikel hinzufuegen");
    JLabel txt_removeFromList = new JLabel("Artikel entfernen");
    JTextField inputName = new JTextField("Name");
    JTextField inputPreis = new JTextField("Preis");
    JTextField inputBeschreibung = new JTextField("Beschreibung");
    JTextField inputAnzahl = new JTextField("Anzahl oder Gewicht");
    JTextField inputNum = new JTextField();

    // Fonts
    Font font = new Font(Font.SANS_SERIF,Font.BOLD,25);
    Font font2 = new Font(Font.SANS_SERIF,Font.BOLD,18);
    Font font3 = new Font(Font.SANS_SERIF,Font.BOLD,14);

    Map attributes = font.getAttributes();

    // Buttons
    JButton button_add = new JButton();
    JButton remove = new JButton("Remove");
    JButton confirmInput = new JButton("Add");
    JButton left = new JButton("<");
    JButton right = new JButton(">");
    JButton openListManager = new JButton("Hinzufuegen / entfernen");


    JComboBox<Artikel.kategorien> kategorieAuswahl = new JComboBox<Artikel.kategorien>(Artikel.kategorien.values());
    JComboBox<Artikel.einheiten> einheitAuswahl = new JComboBox<Artikel.einheiten>(Artikel.einheiten.values());

    Color backColor = new Color(132, 96, 5, 255);
    int side = 0;
    boolean ausgeklappt = false;

    public GUI()
    {
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        // Einstellung des Fensters
        frame.setPreferredSize(new Dimension(400,550));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(mainPanel);
        frame.setBackground(Color.gray);
        frame.setLocation(600,200);


        listManager.setLayout(null);
        listManager.setBackground(backColor);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.pink,2));

        mainPanel.setLayout(null);
        mainPanel.setBackground(backColor);

        // Add compontents to panel
        mainPanel.add(title);
        mainPanel.add(listTitle);
        mainPanel.add(left);
        mainPanel.add(right);
        mainPanel.add(openListManager);
        mainPanel.add(bugetLabel);

        // Erstes Panel ----------------------


        // color font and location of the title
        title.setForeground(Color.WHITE);
        title.setFont(font.deriveFont(attributes));
        title.setBounds(125,10,250,50);

        bugetLabel.setBounds(320,20,80,30);
        bugetLabel.setFont(font3);
        bugetLabel.setForeground(Color.white);
        bugetLabel.setText(Einkaufsliste.getBugetString());

        listTitle.setBounds(40,80,100,50);
        listTitle.setFont(font2);
        listTitle.setForeground(Color.white);

        // sets location + font of the article list
        for (int i =0;i<artikelArr.length;i++) {
            int finalI = i;

            mainPanel.add(artikelArr[i]);
            artikelArr[i].setFont(font3);
            artikelArr[i].setBounds(10,130+i*50,150,50);
            artikelArr[i].setBackground(backColor);
            artikelArr[i].setHorizontalAlignment(SwingConstants.LEFT);
            artikelArr[i].setBorderPainted(false);
            artikelArr[i].setContentAreaFilled(false);
            artikelArr[i].setFocusPainted(false);
            artikelArr[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Artikel artikel = Einkaufsliste.getArtikel(finalI+1+side*5);
                    if (artikel != null) JOptionPane.showMessageDialog(frame,
                            "Name:\n" + artikel.getName()
                            + "\n\nKategorie:\n" + artikel.getKategorie()+ "\n\n" + artikel.getEinheit() + "\n"
                            + artikel.getAnzahl() + "\n\nPreis:\n" + artikel.getPreis()
                            + "\n\nBeschreibung:\n" + artikel.getBeschreibung(),
                            "Artikel Informationen", JOptionPane.INFORMATION_MESSAGE);
                    reloadList();
                }
            });

            mainPanel.add(checkBoxArr[i]);
            checkBoxArr[i].setBackground(backColor);
            checkBoxArr[i].setFont(font3);
            checkBoxArr[i].setBounds(160,140+i*50,20,20);
            checkBoxArr[i].setForeground(Color.white);
            checkBoxArr[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Einkaufsliste.markArtikel(finalI +1+side*5);
                    if (Einkaufsliste.getArtikel(finalI +1+side*5) != null) { Einkaufsliste.setBuget(Einkaufsliste.getBuget() - Einkaufsliste.getArtikel(finalI +1+side*5).getPreis()); }
                    checkBoxArr[finalI].setEnabled(false);
                    reloadList();
                }
            });
        }

        left.setBounds(50,375,45,35);
        left.setFont(font2);
        left.setBackground(Color.orange);

        left.addActionListener(new ActionListener() {
            // If pressed the button shows the 5 previous articles
            @Override
            public void actionPerformed(ActionEvent e) {
                if (side > 0) side--;
                reloadList();
            }
        });
        right.setBounds(110,375,45,35);
        right.setFont(font2);
        right.setBackground(Color.orange);

        right.addActionListener(new ActionListener() {
            // If pressed the button shows the 5 following articles
            @Override
            public void actionPerformed(ActionEvent e) {
                if (side < 2147483646) side++;
                reloadList();
            }
        });

        openListManager.setBounds(50,450,300,40);
        openListManager.setFont(font2);
        openListManager.setBackground(Color.orange);

        openListManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ausgeklappt)
                {
                    frame.setPreferredSize(new Dimension(700, 550));
                    frame.add(listManager);
                    frame.pack();
                    ausgeklappt = true;
                }
                else
                {
                    ausgeklappt = false;
                    frame.setPreferredSize(new Dimension(400, 550));
                    frame.remove(listManager);
                    frame.pack();
                }
            }
        });

        // Zweites Panel ----------------------

        listManager.add(inputName);
        listManager.add(inputAnzahl);
        listManager.add(inputBeschreibung);
        listManager.add(inputPreis);
        listManager.add(inputNum);
        listManager.add(confirmInput);
        listManager.add(remove);
        listManager.add(txt_removeFromList);
        listManager.add(txt_addToList);
        listManager.add(kategorieAuswahl);
        listManager.add(einheitAuswahl);
        listManager.setBorder(BorderFactory.createLineBorder(Color.pink,2));

        txt_addToList.setBounds(450, 150, 180, 40);
        txt_addToList.setFont(font2);
        txt_addToList.setForeground(Color.white);

        inputName.setBounds(450, 200, 180, 30);
        inputName.setFont(font2);
        inputName.setBackground(Color.gray);

        inputAnzahl.setBounds(450, 240, 180, 30);
        inputAnzahl.setFont(font2);
        inputAnzahl.setBackground(Color.gray);

        inputPreis.setBounds(450, 280, 180, 30);
        inputPreis.setFont(font2);
        inputPreis.setBackground(Color.gray);

        inputBeschreibung.setBounds(450, 320, 180, 30);
        inputBeschreibung.setFont(font2);
        inputBeschreibung.setBackground(Color.gray);

        kategorieAuswahl.setBounds(450, 360, 180, 30);
        kategorieAuswahl.setFont(font2);
        kategorieAuswahl.setBackground(Color.gray);

        einheitAuswahl.setBounds(450, 400, 180, 30);
        einheitAuswahl.setFont(font2);
        einheitAuswahl.setBackground(Color.gray);

        confirmInput.setBounds(450,440, 70,30);
        confirmInput.setBackground(Color.ORANGE);
        confirmInput.setFont(font2);

        confirmInput.addActionListener(new ActionListener() {
            // If pressed the button appends the content of the input field to list
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                try {
                    Einkaufsliste.addArtikel(new Artikel(inputName.getText(), (Artikel.kategorien) kategorieAuswahl.getSelectedItem(), inputBeschreibung.getText(), (Artikel.einheiten) einheitAuswahl.getSelectedItem(), Integer.parseInt(inputAnzahl.getText()), Integer.parseInt(inputPreis.getText())));
                    reloadList();
                }
                catch (NumberFormatException nfe) {}
            }
        });

        txt_removeFromList.setBounds(450, 20, 180, 40);
        txt_removeFromList.setFont(font2);
        txt_removeFromList.setForeground(Color.white);

        inputNum.setBounds(450, 70, 30, 30);
        inputNum.setFont(font2);
        inputNum.setBackground(Color.gray);

        remove.setBounds(500,70, 120,30);
        remove.setBackground(Color.ORANGE);
        remove.setFont(font2);

        remove.addActionListener(new ActionListener() {
            // If pressed the button removes the object which has the index equal to the content field to list
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Einkaufsliste.removeArtikel(Integer.parseInt(inputNum.getText()));
                } catch (NumberFormatException nfe) {}
                reloadList();
            }
        });

        frame.pack();
    }

    /**
     *
     *
     * */
    private void reloadList()
    {
        Artikel[] artikel = Einkaufsliste.getFuenfArtikel(side);

        for (int i = 0; i < 5; i++)
        {
            checkBoxArr[i].setEnabled(true);
            if (artikel[i] != null) {
                artikelArr[i].setText(i+1+side*5 + ". " + artikel[i].getName());
                checkBoxArr[i].setSelected(artikel[i].istGekauft());
                if (artikel[i].istGekauft()) checkBoxArr[i].setEnabled(false);
            }
            else
            {
                artikelArr[i].setText(i+1+side*5 + ". ");
                checkBoxArr[i].setSelected(false);
            }
            checkBoxArr[i].updateUI();
        }
        bugetLabel.setText(Einkaufsliste.getBugetString());
    }
}
