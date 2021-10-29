package uoc.ei.ejemplos.modulo9.dns;

import uoc.ei.tads.*;

/**
 * Clase que encapsula la representación escogida para guardar los hosts de cada una de las entidades 
 * de TLDs.<br>
 * En esta versión se ha usado la implementación de los diccionarios mediante listas encadenadas redefiniendo el
 * método consultar para poder reorganizar los hosts para optimizar las consultas de los más recientemente 
 * consultados.
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DiccionarioHosts extends DiccionarioListaImpl<Host,InformacionHost> {

	/**
	 * Método que consulta los datos de un host a partir del suyo dominio. Está definiendo el método 
	 * original de la DiccionarioListaImpl para permitir reordenar los hosts para optimizar
	 * las consultas de los más recientemente consultados. <br>
	 * Para optimizar las consultas simplemente situamos al principio el host consultado.
	 * 
	 * @param clave el Dominio del host que queremos consultar.
	 * @return InformacionHost información del host.
	 */
	public InformacionHost consultar(Host clave) {
		InformacionHost element = null;
		// consultamos el elemento (clonamos implementación de clase padre)
		RecorridoConAnterior<ClaveValor<Host,InformacionHost>> r=buscaPosicion(clave);
		Posicion<ClaveValor<Host,InformacionHost>> actual=r.actual();
		if (actual!=null) {
			element = actual.getElem().getValor();
			// borramos el elemento usando la posición anterior.
			// lo hacemos siempre y cuando no se trate del primer elemento!!!
			Posicion<ClaveValor<Host,InformacionHost>> anterior=r.anterior();
			if (anterior!=null) {
				diccionario.borrar(anterior);
				// lo volvemos a añadir al principio de la lista
				diccionario.insertarAlPrincipio(actual.getElem());
			}
		}
		return element;
	}

} 