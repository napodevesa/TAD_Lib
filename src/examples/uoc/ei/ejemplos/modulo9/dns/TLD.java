package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que contiene la información que define un Top Level Domain o TLD(ej: "edu"). 
 * @author David Fíguls
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 1.0.0
 */
public class TLD implements Comparable{
  private String tld;

  /**
   * Constructor de un TLD a partir de una cadena (ej "edu").
   * @param s cadena que contiene el número del TLD (ej "edu").
   */
  public TLD(String s) {
    tld=s;
  }
  

  /**
  * Método que permite comparar dos TLDs. 
  * @param u otro TLD.
  */
  public int compareTo(Object o) {
    TLD t=(TLD)o;
    return tld.compareTo(t.tld);
  }
  
  
  /** Calcula el hashcode del TLD. Delega en el método
   * hashcode de String.
   */
  public int hashCode() {
	  return tld.hashCode();
  }
  

  /**
   * Método que retorna una cadena con el número del TLD.
   * @return String cadena con el número del tld (ej "edu").
   */
  public String toString() {
    return tld;
  }
}