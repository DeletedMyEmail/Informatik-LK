package de.kaitokuntatsu.random;
/**
 * Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, Materialien zum schulinternen Lehrplan Informatik SII</p>
 *
 * @version 2014-03-13
 */

import de.kaitokuntatsu.datastructures.ComparableContent;
/**
 * 03.03.2011
 */
public class Informatiker implements ComparableContent<Informatiker> {
  private String name;
  private String gebDatum;

  public Informatiker(String pName, String pDatum) {
    super();
    name = pName;
    gebDatum = pDatum;
  }

  public String gibName() {
    return name;
  }

  public String gibGebDatum() {
    return gebDatum;
  }

  public boolean isLess(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())<0;
  }

  public boolean isEqual(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())==0;
  }

  public boolean isGreater(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())>0;
  }

  public String toString() {
    return name + " *" + gebDatum;
  }
}
