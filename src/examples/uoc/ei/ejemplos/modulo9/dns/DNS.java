package uoc.ei.ejemplos.modulo9.dns;


import uoc.ei.tads.Iterador;


/**
 * Interfaz DNS definida al enunciado de la práctica.
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface DNS {
	
    /**
     * Método que da de alta un dominio relacionándolo con una ip.
     * 
     * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
     * @param ip  cadena que contiene la dirección ip del dominio. ej: "129.34.2.3".
     */
     public void altaDominio(Dominio d, IP ip);

     /**
      * Método que da de baja un dominio.
      * 
      * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
      */
     public void bajaDomini(Dominio d);

     /**
      * Método que consulta la dirección ip de un dominio.
      * 
      * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
      * @return ip  retorna una cadena que contiene la dirección ip del dominio. ej: "129.34.2.3".
      */
     public IP consultar(Dominio d);

     /**
      * Método que consulta uno de los dominios más visitados.
      * 
      * @param núm posición dentro de del ranking (0..9). 
      * @return dominio retorna un objeto Dominio que contiene el número del dominio.
      */
     public Dominio masVisitados(int n);

     /**
      * Método que inicializa un tld para hacer un recorrido.
      * 
      * @param t tld del cual queremos hacer un recorrido.
      */
     public Iterador<Entidad> entidades(TLD t);

}
 