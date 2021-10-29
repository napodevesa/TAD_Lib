package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que agrupa la información necesaria que debemos guardar de cada host.<br>
 * Incluye el dominio, la dirección ip y el número de visitas.
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class InformacionHost {
  Dominio dominio;
  IP ip;
  int numVisitas;

  /**
   * Constructor a partir de un dominio y una ip. El contador de visitas se pone a 0.
   * @param d Dominio.
   * @param e IP.
   */
  public InformacionHost(Dominio d, IP i) {
    dominio=d;
    ip=i;
    numVisitas=0;
  }

  /**
   * Método que incrementa el contador de visitas del host.
   */
  public void visita() {numVisitas++;}

  /**
   * Método que retorna el Dominio del host.
   * @return Dominio el dominio del host.
   */
  public Dominio getDominio() {return dominio;}
  
  /**
   * Método que retorna la dirección IP del host.
   * @return IP la dirección ip del host.
   */
  public IP getIP() {return ip;}

  /**
   * Método que asigna la dirección IP del host.
   * @return IP la dirección ip del host.
   */
  public void setIP(IP i) {ip=i;}

  /**
   * Método que retorna el número de visitas del host.
   * @return int número de visitas del host.
   */
  public int getVisitas() {return numVisitas;}
  
  
  public String toString() {
	  return "[InfoHost: "+dominio+","+ip+","+numVisitas+"]";
  }
}