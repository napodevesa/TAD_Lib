package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que contiene la información que define el nombre de un host (ej: "www"). 
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Host implements Comparable{
  private String host;
  
  /**
   * Constructor de un host a partir de una cadena (ej "www").
   * @param s cadena que contiene el nombre del hueste (ej "ww").
   */
  public Host(String s) {
    host=s;
  }

  /**
   * Método que permite comparar dos hosts. Necesario para poder añadir hosts al contenedor de hosts.
   * @param o otro host.
   */
  public int compareTo(Object o) {
    Host h=(Host)o;
    return host.compareTo(h.host);
  }
  
  /**
   * Método que retorna una cadena con el nombre del host.
   * @return String cadena con el nombre del host (ej "www").
   */
  public String toString() {
    return host;
  }
}