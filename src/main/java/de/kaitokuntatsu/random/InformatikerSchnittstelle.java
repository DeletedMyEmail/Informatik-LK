package de.kaitokuntatsu.random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformatikerSchnittstelle
{
    InformatikerVerwaltung verwaltung = new InformatikerVerwaltung();
    InformatikerBaumZeichner zeichner;

    // GUI components

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();

    private static JLabel labelDatum, labelUsername;
    private static JTextField username, password;
    private static JButton buttonLogin, buttonCreate, buttonShow;


    public InformatikerSchnittstelle()
    {
    }

    private void zeichne()
    {
        // creating a JPanel class

        panel.setLayout(null);

        // JFrame class

        frame.setTitle("GUI");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 320));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username label constructor
        labelUsername = new JLabel("Informatiker Name");
        labelUsername.setBounds(100, 8, 170, 20);
        labelUsername.setVisible(true);
        panel.add(labelUsername);

        // Username TextField constructor
        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);

        // Password Label constructor
        labelDatum = new JLabel("Geburtsdatum");
        labelDatum.setBounds(100, 55, 170, 20);
        panel.add(labelDatum);

        // Password TextField
        password = new JTextField();
        password.setBounds(100, 75, 193, 28);
        panel.add(password);



        // CreateButton constructor
        buttonCreate = new JButton("Neuen Informatiker anlegen");
        buttonCreate.setBounds(100, 110, 193, 25);
        buttonCreate.setForeground(Color.WHITE);
        buttonCreate.setBackground(Color.BLACK);
        buttonCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                verwaltung.neuenInformatikerAnlegen(username.getText(), password.getText());
                JOptionPane.showMessageDialog(null, "Neuer Informatiker \"" + username.getText() + "\" angelegt!");
            }
        });
        panel.add(buttonCreate);



        // ShowButton constructor
        buttonShow = new JButton("Datenstruktur anzeigen");
        buttonShow.setBounds(100, 145, 193, 25);
        buttonShow.setForeground(Color.WHITE);
        buttonShow.setBackground(Color.BLACK);
        buttonShow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                zeichner = new InformatikerBaumZeichner(verwaltung.getInformatikerBaum());
            }
        });
        panel.add(buttonShow);
    }



    public static void main (String[] args)
    {

        InformatikerSchnittstelle schnittstelle = new InformatikerSchnittstelle();

        schnittstelle.zeichne();
        schnittstelle.frame.setVisible(true);
    }
}
