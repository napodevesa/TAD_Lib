package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que contiene la información que define una entidad (ej: "uoc"). 
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Entidad implements Comparable {
  private String entidad;

  /**
   * Constructor de una Entidad a partir de una cadena (ej "uoc").
   * @param s cadena que contiene el nombre del entidad (ej "uoc").
   */
  public Entidad(String s) {
    entidad=s;
  }

  /**
   * Método que permite comparar dos entidades. Necesario para poder añadir Entidades al ContEntidades (un arbol AVL).
   * @param o otra entidad.
   */
  public int compareTo(Object o) {
    Entidad e=(Entidad)o;
    return entidad.compareTo(e.entidad);
  }

  /**
   * Método que retorna una cadena con el nombre de la entidad.
   * @return String cadena con el nombre de la entidad (ej "uoc").
   */
  public String toString() {
    return entidad;
  }
}