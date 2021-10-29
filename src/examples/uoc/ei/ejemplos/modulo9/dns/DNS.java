package uoc.ei.ejemplos.modulo9.dns;


import uoc.ei.tads.Iterador;


/**
 * Interfaz DNS definida al enunciado de la pr�ctica.
 * @author David F�guls
 * @author Jordi Alvarez (adaptaci�n para la versi�n 2.0.0 de la librer�a)
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface DNS {
	
    /**
     * M�todo que da de alta un dominio relacion�ndolo con una ip.
     * 
     * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
     * @param ip  cadena que contiene la direcci�n ip del dominio. ej: "129.34.2.3".
     */
     public void altaDominio(Dominio d, IP ip);

     /**
      * M�todo que da de baja un dominio.
      * 
      * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
      */
     public void bajaDomini(Dominio d);

     /**
      * M�todo que consulta la direcci�n ip de un dominio.
      * 
      * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
      * @return ip  retorna una cadena que contiene la direcci�n ip del dominio. ej: "129.34.2.3".
      */
     public IP consultar(Dominio d);

     /**
      * M�todo que consulta uno de los dominios m�s visitados.
      * 
      * @param n�m posici�n dentro de del ranking (0..9). 
      * @return dominio retorna un objeto Dominio que contiene el n�mero del dominio.
      */
     public Dominio masVisitados(int n);

     /**
      * M�todo que inicializa un tld para hacer un recorrido.
      * 
      * @param t tld del cual queremos hacer un recorrido.
      */
     public Iterador<Entidad> entidades(TLD t);

}
 