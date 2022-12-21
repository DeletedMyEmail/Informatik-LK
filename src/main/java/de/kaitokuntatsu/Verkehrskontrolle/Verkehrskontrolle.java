package de.kaitokuntatsu.Verkehrskontrolle;
import de.joshua.ArrayUtils;
import de.joshua.DataStructures.CustomQueue;

/**
 * 	Loesungsansatz zu "Aufgabe LD 2.3: de.joshua.Verkehrskontrolle"
 * 
 * 	@author Joshua Hartjes
 * 	@version 1.0 | 06.12.2021
 * */
public class  Verkehrskontrolle {

	ArrayUtils arrUtils = new ArrayUtils();
	
	// maxAnzahl: Maximal zu kontrollierende Fahrzeuge, minMaengel: wieviele Maengel gefunden werden sollen
	private final int maxAnzahl, minMaengel;
	
	// Aktuell gefundene Maengel und die Anzahl kontrollierter Fahrzeuge
	private int maengel, anzahl;
	
	// kolonne: erste Fahrzeugreihe, heruasgewunken: Fahrzeugreihe der heruasgewunkenen Fahrzeuge
	CustomQueue<Fahrzeug> kolonne, herausgewunken;
	
	/**
	 * 	@param maxAnzahl Maximal zu kontrollierende Fahrzeuge
	 * 	@param minMaengel Wieviele Maengel gefunden werden sollen
	 * */
	public Verkehrskontrolle(int minMaengel, int maxAnzahl) {
		this.maxAnzahl = maxAnzahl;
		this.minMaengel = minMaengel;
		kolonne = new CustomQueue<Fahrzeug>();
		herausgewunken = new CustomQueue<Fahrzeug>();
		
	}

	public void kolonneAufloesen()
	{
		while (!kolonne.isEmpty()) 
		{
			kolonne.remove();
		}
	}
	
	public void herauswinken() 
	{
		herausgewunken.enqueue(kolonne.poll());
		maengel++;
	}
	
	public void entlassenAusKolonne() 
	{
		kolonne.remove();
	}
	
	public Fahrzeug entlassenAusHerausgewunken() 
	{
		return herausgewunken.poll();
	}
	
	public void addToKolonne(Fahrzeug pFahrzeug) 
	{
		kolonne.enqueue(pFahrzeug);
	}
	
	public boolean fahrzeugUeberpruefen() 
	{
		return kolonne.peek().getTauglichkeit();
	}
	
	public int getMaengel()
	{
		return maengel;
	}
	
	public boolean reached() 
	{
		return maengel >= minMaengel || anzahl >= maxAnzahl;
	}
	
	public boolean isKolonneEmpty() 
	{
		return kolonne.isEmpty();
	}
	
	public boolean isHerausgewunkenEmpty() 
	{
		return herausgewunken.isEmpty();
	}
	
	/**
	 * 	F�llt die Queue, welche als erste Kolonne fungiert, mit Fahrzeugen
	 * 
	 * 	@param lAnzahl Anzahl der zu generierenden Fahrzeuge
	 * */
	public void erstelleKolonne(int lAnzahl) 
	{
		for (int i = 0; i < lAnzahl; i++) {
			this.addToKolonne(new Fahrzeug("idk", "E:"+i, "Gelb", i%2==0));
		}
	}
	
	/**
	 *	
	 **/
	public void abarbeiten() 
	{
		// Abarbeiten der Kolonne: weiterfahren oder herauswinken
		while (!this.reached() && !this.isKolonneEmpty()) 
		{
			anzahl++;
			if (fahrzeugUeberpruefen()) 
			{
				entlassenAusKolonne();
				System.out.println("OK!");
			}
			else 
			{
				herauswinken();
				System.out.println(maengel);
			}
		}
		
		// Herausgewunkene ausgeben und gleichzeitig aufl�sen
		while (isHerausgewunkenEmpty()) 
		{
			arrUtils.printlnArr(entlassenAusHerausgewunken().getAll());
		}
		
		kolonneAufloesen();
		if (isKolonneEmpty()) System.out.println("Finished");
	
	}
	
	public static void main(String[] args) {
		Verkehrskontrolle vkk = new Verkehrskontrolle(10, 22);
		vkk.erstelleKolonne(100);
		vkk.abarbeiten();
	}

}
