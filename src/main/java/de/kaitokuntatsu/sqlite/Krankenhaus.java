package de.kaitokuntatsu.sqlite;

import de.joshua.DataStructures.DBMS.*;

// Aufgabe [WDH.DB.04] - Das de.joshua.sqlite.Krankenhaus
public class Krankenhaus {

    private final DatabaseConnector connector;

    public Krankenhaus() {
        connector =new DatabaseConnector("",1,"src/de/joshua/sqlite/[WDH.DB.04].db", "", "");
    }

    public boolean vorhanden(String pVorname, String pNachname) {
        connector.executeStatement("SELECT ArztNr FROM Arzt WHERE Vorname='"+pVorname+"' AND Nachname='"+pNachname+"'");
        System.out.println("Error: "+connector.getErrorMessage());
        return connector.getCurrentQueryResult().getColumnCount() > 0;
    }

    public void arztHinzufuegen(String pVorname, String pNachname, int pEinstellungsjahr, int pStammabteilung) {
        connector.executeStatement(
                "INSERT INTO Arzt " +
                "(Vorname,Nachname,Stammabteilung,Einstellungsjahr) " +
                "VALUES('"+pVorname+"','"+pNachname+"',"+pStammabteilung+","+pEinstellungsjahr+")");
        System.out.println("Error: "+connector.getErrorMessage());
    }

    public void patientHinzufuegen(String pVorname, String pNachname, String pGeburtsdatum) {
        connector.executeStatement(
                "INSERT INTO Patient " +
                "(Vorname,Nachname,Geburtsdatum) " +
                "VALUES('"+pVorname+"','"+pNachname+"','"+pGeburtsdatum+"')");
        System.out.println("Error: "+connector.getErrorMessage());
    }

    // Aufgabe abgeändert, weil Anomalien
    public void behandelt(int pPatientenID, int pArztID, String pBehandlungstext)
    {
        connector.executeStatement(
                "INSERT INTO " +
                "Behandlung (ArztNr,PatientenNr,Behandlungstext) " +
                "VALUES("+pArztID+","+pPatientenID+",'"+pBehandlungstext+"')");
        System.out.println("Error: "+connector.getErrorMessage());
    }

    public int[] gibArztIDs(String pVorname, String pNachname) {
        connector.executeStatement("SELECT ArztNr FROM Arzt WHERE Vorname='"+pVorname+"' AND Nachname='"+pNachname+"'");
        System.out.println("Error: "+connector.getErrorMessage());

        String[][] lData = connector.getCurrentQueryResult().getData();
        int[] lIDs = new int[lData.length];

        for (int i = 0; i < lData.length; i++)
        {
            lIDs[i] = Integer.parseInt(lData[i][0]);
        }
        return lIDs;
    }

    public int[] gibPatientenIDs(String pVorname, String pNachname) {
        connector.executeStatement("SELECT PatientenNr FROM Patient WHERE Vorname='"+pVorname+"' AND Nachname='"+pNachname+"'");
        System.out.println("Error: "+connector.getErrorMessage());

        String[][] lData = connector.getCurrentQueryResult().getData();
        int[] lIDs = new int[lData.length];

        for (int i = 0; i < lData.length; i++)
        {
            lIDs[i] = Integer.parseInt(lData[i][0]);
        }
        return lIDs;
    }
}
