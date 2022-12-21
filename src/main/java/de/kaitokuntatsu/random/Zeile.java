package de.kaitokuntatsu.random;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 10.01.2022
 * @author Axel Dumschat
 */

public class Zeile {
  
  // Anfang Attribute
  private String inhalt;
  private int zeilenNummer;
  // Ende Attribute
  
  public Zeile(String pInhalt, int pZeilenNummer) {
    inhalt = pInhalt;
    zeilenNummer = pZeilenNummer;
  }

  // Anfang Methoden
  public String getInhalt() {
    return inhalt;
  }

  public void setInhalt(String inhaltNeu) {
    inhalt = inhaltNeu;
  }

  public void setZeilenNummer(int zeilenNummerNeu) {
    zeilenNummer = zeilenNummerNeu;
  }

  public int getZeilenNummer() {
    return zeilenNummer;
  }
  
  public boolean isEqual(Zeile pZeile){
  if  (this.getZeilenNummer()==pZeile.getZeilenNummer()){
  return true;
  }
  else {
  return false;  
  } // end of if-else

  }
  // Ende Methoden
} // end of Zeile
