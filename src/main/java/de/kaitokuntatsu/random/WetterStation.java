package de.kaitokuntatsu.random;
/**
 *
 * Die Klasse WetterStation verwaltet die Wetterdaten einer Woche (hier Temperaturen zur vollen Stunde) in zwei unterschiedliche, zweidimensionalen Arrays
 * "wetterDaten1" enthält die Daten im typischen Format {Zeitstempel(JahrMonatTagUhrzeit), Temperatur-Wert (in zehntel°C)}
 * "wetterDaten2" enthält die stündlichen Temperaturwerte in einem zweidimensionalen Array[7][24]  
 * @version 1.1 vom 22.11.2021
 * @author Axel Dumschat
 * @author Edited by Joshua
 */

public class WetterStation {
  
  private int [][] wetterDaten1 = {{2021110100,105},{2021110101,10},{2021110102,89},{2021110103,91},{2021110104,93},{2021110105,98},{2021110106,89},{2021110107,88},{2021110108,102},{2021110109,108},{2021110110,113},{2021110111,123},{2021110112,13},{2021110113,131},{2021110114,133},{2021110115,116},{2021110116,121},{2021110117,10},{2021110118,94},{2021110119,94},{2021110120,94},{2021110121,87},{2021110122,83},{2021110123,73},{2021110200,64},{2021110201,63},{2021110202,59},{2021110203,57},{2021110204,66},{2021110205,67},{2021110206,69},{2021110207,69},{2021110208,76},{2021110209,84},{2021110210,84},{2021110211,9},{2021110212,88},{2021110213,94},{2021110214,98},{2021110215,98},{2021110216,85},{2021110217,82},{2021110218,63},{2021110219,79},{2021110220,71},{2021110221,6},{2021110222,48},{2021110223,57},{2021110300,49},{2021110301,54},{2021110302,54},{2021110303,43},{2021110304,41},{2021110305,55},{2021110306,51},{2021110307,51},{2021110308,52},{2021110309,62},{2021110310,67},{2021110311,72},{2021110312,75},{2021110313,73},{2021110314,73},{2021110315,76},{2021110316,73},{2021110317,71},{2021110318,7},{2021110319,71},{2021110320,72},{2021110321,72},{2021110322,72},{2021110323,71},{2021110400,69},{2021110401,65},{2021110402,64},{2021110403,62},{2021110404,61},{2021110405,59},{2021110406,56},{2021110407,55},{2021110408,6},{2021110409,6},{2021110410,62},{2021110411,71},{2021110412,78},{2021110413,84},{2021110414,87},{2021110415,83},{2021110416,82},{2021110417,8},{2021110418,78},{2021110419,62},{2021110420,54},{2021110421,39},{2021110422,35},{2021110423,47},{2021110500,64},{2021110501,69},{2021110502,68},{2021110503,67},{2021110504,65},{2021110505,62},{2021110506,63},{2021110507,63},{2021110508,67},{2021110509,73},{2021110510,83},{2021110511,97},{2021110512,105},{2021110513,114},{2021110514,114},{2021110515,111},{2021110516,107},{2021110517,104},{2021110518,93},{2021110519,64},{2021110520,57},{2021110521,67},{2021110522,7},{2021110523,67},{2021110600,37},{2021110601,43},{2021110602,36},{2021110603,3},{2021110604,45},{2021110605,44},{2021110606,52},{2021110607,57},{2021110608,68},{2021110609,74},{2021110610,82},{2021110611,99},{2021110612,108},{2021110613,112},{2021110614,112},{2021110615,102},{2021110616,10},{2021110617,99},{2021110618,101},{2021110619,102},{2021110620,101},{2021110621,99},{2021110622,10},{2021110623,101},{2021110700,102},{2021110701,95},{2021110702,9},{2021110703,89},{2021110704,92},{2021110705,96},{2021110706,10},{2021110707,10},{2021110708,103},{2021110709,109},{2021110710,112},{2021110711,118},{2021110712,116},{2021110713,119},{2021110714,121},{2021110715,116},{2021110716,97},{2021110717,9},{2021110718,87},{2021110719,78},{2021110720,82},{2021110721,74},{2021110722,67},{2021110723,64}};;
  private int [][] wetterDaten2 = {{105,10,89,91,93,98,89,88,102,108,113,123,13,131,133,116,121,10,94,94,94,87,83,73},{64,63,59,57,66,67,69,69,76,84,84,9,88,94,98,98,85,82,63,79,71,6,48,57},{49,54,54,43,41,55,51,51,52,62,67,72,75,73,73,76,73,71,7,71,72,72,72,71},{69,65,64,62,61,59,56,55,6,6,62,71,78,84,87,83,82,8,78,62,54,39,35,47},{64,69,68,67,65,62,63,63,67,73,83,97,105,114,114,111,107,104,93,64,57,67,7,67},{37,43,36,3,45,44,52,57,68,74,82,99,108,112,112,102,10,99,101,102,101,99,10,101},{102,95,9,89,92,96,10,10,103,109,112,118,116,119,121,116,97,9,87,78,82,74,67,64}};
  
  /**
   * 	Gibt die Temperatur zu einer bestimmten Uhrzeit eines Tages an
   * 	@param pTag	Definirt den Tag, an welchem eine Temperatur abgelesen werden soll
   * 	@param pUhrZeit	Definirt die Uhrzeit (in vollen Stunden), an welcher eine Temperatur abgelesen werden soll
   * 	@return Gibt den Zahlenwert der Temperatur des Zeitpunktes zurück
   * */
  public int getTemp(int pTag, int pUhrZeit) {
	  return wetterDaten2[pTag][pUhrZeit];
  }

  /**
   *	Gibt die Durchschnittstemperatur eines Tages an
   *	@param pTag Definiert den Tag, an welchem die Durchschnittstemperatur gemessen werden soll 	
   * 	@return Gibt den Zahlenwert (double) der Durchschnittstemperatur des definierten Tages zurück
   * */
  public double getTagesMittel(int pTag) {
    double tagesMittel = 0;
    for (int temp : wetterDaten2[pTag]) {
      tagesMittel += temp;
    }
    tagesMittel /= 24;
    return tagesMittel;
    }

  /**
   *	Gibt die Durchschnittstemperatur einer Woche an
   *	@return Gibt den Zahlenwert (double) der Durchschnittstemperatur der Woche aus wetterDaten2 zurück
   * */
  public double getWochenMittel() {
    double wochenMittel =0;
    for (int[] day : wetterDaten2) 
    {
    	for (int temp : day) 
    	{
    		wochenMittel += temp;
    	}
    }
    wochenMittel = wochenMittel / (24*7);
    return wochenMittel;
  }

  /**
   *	Gibt die Durchschnittstemperatur zu einer bestimmten Uhrzeit einer Woche an
   *	@return Gibt den Zahlenwert (double) der Durchschnittstemperatur der Woche zu einer bestimmten Uhrzeit (in vollen Stunden) aus wetterDaten2 zurück
   * */
  public double getWochenMittel(int pUhrZeit) {
    double wochenMittel =0;
    for (int i = 0; i < 7; i++) 
    {
    	wochenMittel += wetterDaten2[i][pUhrZeit];
    }
    wochenMittel /= 7;
    return wochenMittel;
  }
  
  
  /**
   * 	Gibt die höchste Temperatur innerhalb der Woche aus wetterDaten2 zurück
   * 	@return Gibt den Zahlenwert der höchstebn Temperatur zurück
   * */
  public int getMaxTemp() {
    int maxTemp =0;
    for (int[] day : wetterDaten2) 
    {
    	for (int temp : day) 
    	{
    		if (maxTemp < temp) maxTemp = temp;
    	}
    }
    return maxTemp;
  }

  /**
   * 	Gibt die niedrigste Temperatur innerhalb der Woche aus wetterDaten2 zurück
   * 	@return Gibt den Zahlenwert der niedrigsten Temperatur zurück
   * */
  public int getMinTemp() {
    int minTemp =0;
    for (int[] day : wetterDaten2) 
    {
    	for (int temp : day) 
    	{
    		if (minTemp > temp) minTemp = temp;
    	}
    }
    return minTemp;
  }
  
} // end of WetterStation
