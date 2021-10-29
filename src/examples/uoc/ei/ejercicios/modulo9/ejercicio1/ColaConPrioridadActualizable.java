package uoc.ei.ejercicios.modulo9.ejercicio1;

import uoc.ei.tads.*;

/** Extensión de una cola con prioridad que permite modificar los valores
 * guardados a la cola y actualizar la cola de manera que vuelva a estar
 * ordenada en tiempo logarítmico.
 * @author Jordi Alvarez
 * @author Esteve Marine
 * 			Estructura de la Información
 * 			Universitat Oberta de Catalunya (UOC)
 */
public class ColaConPrioridadActualizable<E> extends ColaConPrioridad<E> {
	
	
	private ListaEncadenada<ObservadorContenedorPosicional<E>> observadores;
	

	public ColaConPrioridadActualizable() {
		super();
		observadores=new ListaEncadenada<ObservadorContenedorPosicional<E>>();
	}

	public ColaConPrioridadActualizable(int max) {
		super(max);
		observadores=new ListaEncadenada<ObservadorContenedorPosicional<E>>();
	}

	public ColaConPrioridadActualizable(java.util.Comparator<E> comparador) {
		super(comparador);
		observadores=new ListaEncadenada<ObservadorContenedorPosicional<E>>();
	}

	public ColaConPrioridadActualizable(int max, java.util.Comparator<E> comparador) {
		super(max,comparador);
		observadores=new ListaEncadenada<ObservadorContenedorPosicional<E>>();
	}
	
	
	public void actualizarPosicion(Posicion<E> posicion) {
		hundirElemento(posicion);
		subirElemento(posicion);
	}
	
	
	public void insertarObservador(ObservadorContenedorPosicional observador) {
		observadores.insertarAlFinal(observador);
	}
	
	
	public void borrarObservador(ObservadorContenedorPosicional observador) {
		Recorrido<ObservadorContenedorPosicional<E>> r=observadores.posiciones();
		boolean encontrado=false;
		Posicion<ObservadorContenedorPosicional<E>> p=null;
		while (r.haySiguiente() && !encontrado) {
			p=r.siguiente();
			encontrado=p.getElem()==observador;
		}
		if (encontrado)
			observadores.borrar(p);
	}
	
	
	protected Posicion<E> nuevaUltimaPosicion(E elem) {
		Posicion<E> posicion=super.nuevaUltimaPosicion(elem);
		Iterador<ObservadorContenedorPosicional<E>> iter=observadores.elementos();
		while (iter.haySiguiente()) {
			iter.siguiente().notificarPosicionCreada(posicion);
		}
		return posicion;
	}
	
	
	protected Posicion<E> borrarUltimaPosicion() {
		Posicion<E> posicion=super.borrarUltimaPosicion();
		Iterador<ObservadorContenedorPosicional<E>> iter=observadores.elementos();
		while (iter.haySiguiente()) {
			iter.siguiente().notificarPosicionBorrada(posicion);
		}
		return posicion;
	}
	
	
}
