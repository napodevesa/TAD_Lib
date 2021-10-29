package uoc.ei.ejemplos.modulo9.dns;


import uoc.ei.tads.*;

/**
 * Implementaci�n de la interfaz DNS usando un contenedor de TLDs (ContTLDs)
 * y un contenedor para los m�s visitados (TopTen).
 * @author David F�guls
 * @author Jordi Alvarez (adaptaci�n para la versi�n 2.0.0 de la librer�a)
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DNSimpl implements DNS {
	
  private DiccionarioTLDs tlds;
  private TopTen topTen;

  /**
   * M�todo que inicializa un nuevo objeto DNSimpl.
   */
  public DNSimpl() {
    tlds=new DiccionarioTLDs(2001);
    topTen=new TopTen();
  }

  /**
   * M�todo que da de alta un dominio relacion�ndolo con una ip.
   * 
   * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
   * @param ip  cadena que contiene la direcci�n ip del dominio. ej: "129.34.2.3".
   */
  public void altaDominio(Dominio d, IP ip) {
    TLD tld=d.getTLD();
    Entidad entidad=d.getEntidad();
    Host host=d.getHost();
    if (!tlds.esta(tld))
		tlds.insertar(tld,new DiccionarioEntidades());
	DiccionarioEntidades ets=tlds.consultar(tld);
    if (!ets.esta(entidad))
		ets.insertar(entidad,new DiccionarioHosts());
    DiccionarioHosts hosts=(DiccionarioHosts)ets.consultar(entidad);
    if (hosts.esta(host)) {
    	InformacionHost h=hosts.consultar(host);
    	h.setIP(ip);
    } else {
        hosts.insertar(host,new InformacionHost(d,ip));
    }
  }

  
  /** M�todo que da de baja un dominio.
   * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
   */
  public void bajaDomini(Dominio d) {
    TLD tld=d.getTLD();
    Entidad entidad=d.getEntidad();
    Host host=d.getHost();
    if (tlds.esta(tld)) {
		DiccionarioEntidades ets=tlds.consultar(tld);
		if (ets.esta(entidad)) {
			DiccionarioHosts hosts=ets.consultar(entidad);
			if (hosts.esta(host))
				hosts.borrar(host);
		}
    }
  }

  
  /** M�todo que consulta la direcci�n ip de un dominio.
   * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
   * @return ip  returna una cadena que contiene la direcci�n ip del dominio. ej: "129.34.2.3".
   */
  	public IP consultar(Dominio d) {
		IP ip=null;
		TLD tld=d.getTLD();
		Entidad entidad=d.getEntidad();
		Host host=d.getHost();
		if (tlds.esta(tld)) {
			DiccionarioEntidades ets=tlds.consultar(tld);
			if (ets.esta(entidad)) {
				DiccionarioHosts hosts=ets.consultar(entidad);
				if (hosts.esta(host)) {
					InformacionHost infoHost=hosts.consultar(host);
					infoHost.visita();
					topTen.actualiza(infoHost);
					ip=infoHost.getIP();
				}
			}
		}
    return ip;
  }

	
	/** M�todo que consulta uno de los dominios m�s visitados.
	  * @param n�m posici�n dentro de del ranking (0..9). 
	  * @return dominio retorna un objeto Dominio que contiene el n�mero del dominio.
	  */
  public Dominio masVisitados(int n) {
	  Dominio resultado=null;
	  InformacionHost info=topTen.consulta(n);
	  if (info!=null)
		  resultado=info.getDominio();
	  return resultado;
  }


  /** M�todo que inicializa un tld para hacer un recorrido.
   * @param tld cuyo tld queremos hacer un recorrido.
   */
  public Iterador<Entidad> entidades(TLD t) {
	  Iterador<Entidad> res=null;
	  if (tlds.esta(t))
		  res=tlds.consultar(t).claves();
	  return res;
  }
  
}
 