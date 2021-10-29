/*
 */
package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de un diccionario por delegación en
 * una lista.
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DiccionarioListaImpl<C,E> implements Diccionario<C,E> {
	

	protected ListaEncadenada<ClaveValor<C,E>> diccionario;
	
	
	// Clases internas para implementació
	
	protected static class RecorridoConAnterior<E> implements Recorrido<E> {

		// Atributos
		
		Recorrido<E> recorridoReal;
		Posicion<E> act;
		Posicion<E> ant;
		
		
		// Constructor
		
		public RecorridoConAnterior(Recorrido<E> r) {
			recorridoReal=r;
			ant=null;
			act=null;
		}
		
		
		// Implementación de métodos de recorrido
		
		public Posicion<E> siguiente() {
			ant=act;
			act=recorridoReal.haySiguiente() ? recorridoReal.siguiente() : null;
			return act; 
		}

		public boolean haySiguiente() {
			return recorridoReal.haySiguiente();
		}
		
		
		// Metodo adicional que retorna el anterior
		
		public Posicion<E> anterior() { return ant; }
		public Posicion<E> actual() { return act; }

	}
	
	
	// Constructores
	
	public DiccionarioListaImpl() {
		diccionario=new ListaEncadenada<ClaveValor<C,E>>();
	}
	
	
	// Metodos de implementación
	
	protected RecorridoConAnterior<ClaveValor<C,E>> buscaPosicion(C clave) {
		RecorridoConAnterior<ClaveValor<C,E>> r=new RecorridoConAnterior<ClaveValor<C,E>>(diccionario.posiciones());
		boolean encontrado=false;
		while (!encontrado && r.haySiguiente()) {
			ClaveValor<C,E> parejaClauValor=r.siguiente().getElem();
			encontrado=clave.equals(parejaClauValor.getClave());
		}
		if (!encontrado)
			r.siguiente();
		return r;
	}
	
	
	// Implementación de los métodos de Diccionario
	
	public void insertar(C clave,E obj) {
		if (!esta(clave))
			diccionario.insertarAlPrincipio(new ClaveValor<C,E>(clave,obj));
	}


	public boolean esta(C clave) {
		RecorridoConAnterior<ClaveValor<C,E>> r=buscaPosicion(clave);
		return r.actual()!=null && clave.equals(r.actual().getElem().getClave());
	}

	
	public E consultar(C clave) {
		RecorridoConAnterior<ClaveValor<C,E>> r=buscaPosicion(clave);
		Posicion<ClaveValor<C,E>> actual=r.actual();
		return actual!=null ? actual.getElem().getValor() : null;
	}


	public Iterador<C> claves() {
		return new IteradorRecorridoClavesImpl<C,E>(diccionario.posiciones());
	}


	public E borrar(C clave) {
		E elementEsborrat=null;
		RecorridoConAnterior<ClaveValor<C,E>> r=buscaPosicion(clave);
		Posicion<ClaveValor<C,E>> actual=r.actual();
		if (actual!=null && clave.equals(actual.getElem().getClave())) {
			elementEsborrat=actual.getElem().getValor();
			if (r.anterior()==null)
				diccionario.borrarPrimero();
			else
				diccionario.borrarSiguiente(r.anterior());
		}
		return elementEsborrat;
	}


	public boolean estaVacio() {
		return diccionario.estaVacio();
	}


	public int numElems() {
		return diccionario.numElems();
	}


	public Iterador<E> elementos() {
		return new IteradorRecorridoValoresImpl<C,E>(diccionario.posiciones());
	}
	
	
	public String toString() {
		return Utilidades.toStringContenedorDelegacion("DiccionarioListaImpl",diccionario);
	}

}
