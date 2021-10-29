package uoc.ei.ejemplos.modulo9.dns;


import java.util.StringTokenizer;

/**
 * Clase que contiene la cadena que define un dominio (ej: "www.uoc.edu"). 
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Dominio {
  private TLD tld;
  private Entidad entidad;
  private Host host;
  
  /**
   * Constructor de un Dominio a partir de un TLD, una Entidad y un host.
   * @param t cadena que identifica el TLD (ej "edu").
   * @param e cadena que identifica la entidad (ej "uoc").
   * @param h cadena que identifica el host (ej "www").
   */
  public Dominio(TLD t,Entidad e,Host h) {
    tld=t; entidad=e; host=h;
  }

  /**
   * Constructor de un Dominio a partir de una cadena (ej "www.uoc.edu").
   * @param d cadena que contiene el número del dominio (ej "www.uoc.edu").
   */
  public Dominio(String d) {
  	StringTokenizer s=new StringTokenizer(d,".");
  	host=new Host(s.nextToken());
  	entidad=new Entidad(s.nextToken());
  	tld=new TLD(s.nextToken());
  }
  
  /**
   * Método que retorna el tld del dominio.
   * @return TLD  el tld del dominio (ej de "www.uoc.edu" retorno "edu").
   */
  public TLD getTLD() {return tld;}

  /**
   * Método que retorna la entidad del dominio.
   * @return Entidad el entidad del dominio (ej de "www.uoc.edu" retorno "uoc").
   */
  public Entidad getEntidad() {return entidad;}

  /**
   * Método que retorna el host del dominio.
   * @return Hueste  el host del dominio (ej de "www.uoc.edu" retorno "www").
   */
  public Host getHost() {return host;}

  /**
   * Método que retorna una cadena con el nombre del dominio.
   * @return String cadena con el nombre del dominio (ej "www.uoc.edu").
   */
  public String toString() {
    return tld.toString()+"."+entidad.toString()+"."+host.toString();
  }
}