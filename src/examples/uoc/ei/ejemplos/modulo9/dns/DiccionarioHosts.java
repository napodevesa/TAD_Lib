package uoc.ei.ejemplos.modulo9.dns;

import uoc.ei.tads.*;

/**
 * Clase que encapsula la representaci�n escogida para guardar los hosts de cada una de las entidades 
 * de TLDs.<br>
 * En esta versi�n se ha usado la implementaci�n de los diccionarios mediante listas encadenadas redefiniendo el
 * m�todo consultar para poder reorganizar los hosts para optimizar las consultas de los m�s recientemente 
 * consultados.
 * @author David F�guls
 * @author Jordi Alvarez (adaptaci�n para la versi�n 2.0.0 de la librer�a)
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DiccionarioHosts extends DiccionarioListaImpl<Host,InformacionHost> {

	/**
	 * M�todo que consulta los datos de un host a partir del suyo dominio. Est� definiendo el m�todo 
	 * original de la DiccionarioListaImpl para permitir reordenar los hosts para optimizar
	 * las consultas de los m�s recientemente consultados. <br>
	 * Para optimizar las consultas simplemente situamos al principio el host consultado.
	 * 
	 * @param clave el Dominio del host que queremos consultar.
	 * @return InformacionHost informaci�n del host.
	 */
	public InformacionHost consultar(Host clave) {
		InformacionHost element = null;
		// consultamos el elemento (clonamos implementaci�n de clase padre)
		RecorridoConAnterior<ClaveValor<Host,InformacionHost>> r=buscaPosicion(clave);
		Posicion<ClaveValor<Host,InformacionHost>> actual=r.actual();
		if (actual!=null) {
			element = actual.getElem().getValor();
			// borramos el elemento usando la posici�n anterior.
			// lo hacemos siempre y cuando no se trate del primer elemento!!!
			Posicion<ClaveValor<Host,InformacionHost>> anterior=r.anterior();
			if (anterior!=null) {
				diccionario.borrar(anterior);
				// lo volvemos a a�adir al principio de la lista
				diccionario.insertarAlPrincipio(actual.getElem());
			}
		}
		return element;
	}

} 