package de.kaitokuntatsu.Einkaufsliste;

public class Artikel {

    public enum kategorien
    {
        LEBENSMITTEL,
        ELEKTRONIK,
        WERKZEUG,
        SONSTIGES;
    }
    public enum einheiten
    {
        KILOGRAMM,
        STUECKZAHL,
        LITER;
    }

    private String name;
    private kategorien kategorie;
    private String beschreibung;
    private einheiten einheit;
    private int anzahl, preis;
    private boolean gekauft = false;


    public Artikel(String pName, kategorien pKategorie, String pBeschreibung, einheiten pEinheit,int pAnzahl, int pPreis) {
        this.name = pName;
        this.preis =pPreis;
        this.anzahl = pAnzahl;
        this.beschreibung = pBeschreibung;
        this.kategorie = pKategorie;
        this.einheit = pEinheit;
    }


    public String getName() {return this.name;}


    public int getAnzahl() { return this.anzahl;}

    public double getPreis() { return this.preis;}


    public kategorien getKategorie() {return this.kategorie;}


    public einheiten getEinheit() {return this.einheit;}


    public String getBeschreibung() {return this.beschreibung;}


    public boolean istGekauft() { return gekauft; }


    public void gekauft() { gekauft = true; }

}

