package uoc.ei.ejemplos.modulo9.dns;


import uoc.ei.tads.*;

/**
 * Implementación de la interfaz DNS usando un contenedor de TLDs (ContTLDs)
 * y un contenedor para los más visitados (TopTen).
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DNSimpl implements DNS {
	
  private DiccionarioTLDs tlds;
  private TopTen topTen;

  /**
   * Método que inicializa un nuevo objeto DNSimpl.
   */
  public DNSimpl() {
    tlds=new DiccionarioTLDs(2001);
    topTen=new TopTen();
  }

  /**
   * Método que da de alta un dominio relacionándolo con una ip.
   * 
   * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
   * @param ip  cadena que contiene la dirección ip del dominio. ej: "129.34.2.3".
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

  
  /** Método que da de baja un dominio.
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

  
  /** Método que consulta la dirección ip de un dominio.
   * @param dominio objeto Dominio que contiene el dominio. ej: "www.uoc.edu".
   * @return ip  returna una cadena que contiene la dirección ip del dominio. ej: "129.34.2.3".
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

	
	/** Método que consulta uno de los dominios más visitados.
	  * @param núm posición dentro de del ranking (0..9). 
	  * @return dominio retorna un objeto Dominio que contiene el número del dominio.
	  */
  public Dominio masVisitados(int n) {
	  Dominio resultado=null;
	  InformacionHost info=topTen.consulta(n);
	  if (info!=null)
		  resultado=info.getDominio();
	  return resultado;
  }


  /** Método que inicializa un tld para hacer un recorrido.
   * @param tld cuyo tld queremos hacer un recorrido.
   */
  public Iterador<Entidad> entidades(TLD t) {
	  Iterador<Entidad> res=null;
	  if (tlds.esta(t))
		  res=tlds.consultar(t).claves();
	  return res;
  }
  
}
 