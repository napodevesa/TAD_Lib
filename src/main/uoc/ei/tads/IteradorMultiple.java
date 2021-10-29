
package uoc.ei.tads;

/** Implementación de iterador que permite iterar usando como base
 * los elementos de cada iterador de una secuencia de iteradores.
 * @author Jordi Alvarez
 * @author Esteve Marine
 */
public class IteradorMultiple<E> implements Iterador<E> {
	
	ListaEncadenada<Iterador<E>> iteradores;
	Iterador<Iterador<E>> iteradorDeIteradores;
	Iterador<E> iteradorActual;
	
	
	public IteradorMultiple() {
		iteradores=new ListaEncadenada<Iterador<E>>();
		iteradorDeIteradores=null;
	}
	
	
	public void insertarIterador(Iterador<E> iterador) {
		iteradores.insertarAlFinal(iterador);
	}
	
	
	protected void inicializar() {
		iteradorDeIteradores=iteradores.elementos();
		siguienteIterador();
	}
	

	protected void siguienteIterador() {
		boolean encontrado=false;
		while (!encontrado && iteradorDeIteradores.haySiguiente()) {
			iteradorActual=iteradorDeIteradores.siguiente();
			encontrado=iteradorActual.haySiguiente();
		}
		if (!encontrado)
			iteradorActual=null;
	}

	
	public boolean haySiguiente() {
		if (iteradorDeIteradores==null)
			inicializar();
		return iteradorActual!=null;
	}


	public E siguiente() throws ExcepcionPosicionInvalida {
		if (iteradorDeIteradores==null)
			inicializar();
		E element=iteradorActual.siguiente();
		if (!iteradorActual.haySiguiente())
			siguienteIterador();
		return element;
	}

}
