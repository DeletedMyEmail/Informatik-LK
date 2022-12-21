package de.kaitokuntatsu.Verkehrskontrolle;

/***/
public class Fahrzeug {

	private String modell, kennzeichen, farbe;
	private boolean verkehrstauglichkeit;
	
	public Fahrzeug(String pModell, String pKennzeichen, String pFarbe, boolean pTauglichkeit) {
		modell = pModell;
		kennzeichen = pKennzeichen;
		farbe = pFarbe;
		verkehrstauglichkeit = pTauglichkeit;
	}
	
	public boolean getTauglichkeit() 
	{
		return this.verkehrstauglichkeit;
	}
	
	public String[] getAll() 
	{
		String[] ret = { modell, kennzeichen, farbe };
		return ret;
	}
}
