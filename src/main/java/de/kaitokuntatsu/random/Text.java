package de.kaitokuntatsu.random;
import de.joshua.DataStructures.*;

import java.io.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 10.01.2022
 * @author Axel Dumschat
 */

public class Text {
  
  // Anfang Attribute
  File in_file, out_file;
  BufferedReader reader;
  PrintWriter writer;
  private String textName = null;
  private List<Zeile> zeilen = new List<Zeile>();
  int anzahlZeichen =0;
  private int zeilenLaenge = 15;
  private int anzahlZeilen = 0;
  private int aktuelleZeile=0;
  String myString;
  //private Zeile gesuchteZeile;
  // Ende Attribute
  
  // Anfang Methoden
  
  public Text(String pTextName){
  textName=pTextName;
    }

  public Zeile getZeile(int pZeilenNr) {
    goToZeile(pZeilenNr);
    return zeilen.getContent();
  }

  public void setZeile(Zeile pZeile,int pZeilenNr) {
    goToZeile(pZeilenNr);
    zeilen.setContent(pZeile);
  }

  public void goToZeile(int pZeilenNr) {
    zeilen.toFirst();
    while (zeilen.hasAccess()&& pZeilenNr!= zeilen.getContent().getZeilenNummer()) {
      zeilen.next();  ;
    } // end of while
    aktuelleZeile = zeilen.getContent().getZeilenNummer();
  }

  public void uebernehmen(){
    goToZeile(1);
    String newSubString;
    myString="";
    int i =1;
    while (zeilen.hasAccess()){
      newSubString =zeilen.getContent().getInhalt();
      myString = myString.concat(newSubString);
      zeilen.next();
      i++;
    } // end of while
    anzahlZeilen=i;
    anzahlZeichen = myString.length();
  }

  public void zeilenTauschen(int pZeilenNr1, int pZeilenNr2) {
    String z1 = getZeile(pZeilenNr1).getInhalt();
    String z2 = getZeile(pZeilenNr2).getInhalt();
    goToZeile(pZeilenNr1);
    zeilen.getContent().setInhalt(z2);
    goToZeile(pZeilenNr2);
    zeilen.getContent().setInhalt(z1);
    uebernehmen();
    textAusgeben();
  }

  public void textImportieren(String pText) {
    anzahlZeichen = pText.length();
    if (anzahlZeichen%zeilenLaenge == 0){
      anzahlZeilen = anzahlZeichen/zeilenLaenge;
    }
    else {
      anzahlZeilen = anzahlZeichen/zeilenLaenge +1;
    } // end of if-else
    System.out.println(anzahlZeilen);
    String newSubstring;
    Zeile neueZeile;
    for (int i = 0; i < anzahlZeilen; i++) {
      System.out.println("Zeile: " + i);
      if((i+1)*zeilenLaenge<=anzahlZeichen){
        newSubstring= pText.substring(i*zeilenLaenge,(i+1)*zeilenLaenge);
        }
      else {
        newSubstring= pText.substring(i*zeilenLaenge);  
        } // end of if-else
      neueZeile= new Zeile(newSubstring,i);
      zeilen.append(neueZeile);
    }
  }

  public void textAusgeben() {
    zeilen.toFirst();
    while (zeilen.hasAccess()) { 
      System.out.println(zeilen.getContent().getInhalt());
      zeilen.next();
    } // end of while
    }

  public void dateiEinlesen(String pDateiName) throws IOException {
    try {
      in_file = new File(pDateiName);
      reader = new BufferedReader(new FileReader(in_file));
    } catch (FileNotFoundException e) {System.out.println("File not found");return;}
    String file_input = "";
    String line;
    while((line=reader.readLine()) != null)
    {
      file_input = file_input.concat(line);
    }
    textImportieren(file_input);
  }

  public void dateiSpeichern(String pDateiName) {
    try{
      out_file = new File(pDateiName);
      out_file.createNewFile();
      writer = new PrintWriter(in_file);
    } catch (IOException ioe) { ioe.printStackTrace();}

    zeilen.toFirst();
    while(zeilen.hasAccess())
    {
      writer.println(zeilen.getContent().getInhalt());
      zeilen.next();
    }
    writer.flush();
  }

  // Ende Methoden
} // end of Text
