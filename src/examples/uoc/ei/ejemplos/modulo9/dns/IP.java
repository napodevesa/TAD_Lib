package uoc.ei.ejemplos.modulo9.dns;


/**
 * Clase que contiene la información que define una dirección IP (ej: "100.23.98.255"). 
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class IP implements Comparable{
  private String ip;
  
  /**
   * Constructor de una IP a partir de una cadena (ej "100.23.98.255").
   * @param s cadena que contiene la dirección IP(ej "100.23.98.255").
   */
  public IP(String s) {
    ip=s;
  }

  /**
   * Método que permite comparar dos IP's. 
   * @param u otra IP.
   */
  public int compareTo(Object o) {
    IP i=(IP)o;
    return ip.compareTo(i.ip);
  }

  /**
   * Método que retorna una cadena con la dirección ip.
   * @return String cadena con la dirección ip (ej "100.23.98.255").
   */
  public String toString() {
    return ip;
  }
}