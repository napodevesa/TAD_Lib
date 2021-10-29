package uoc.ei.ejemplos.modulo4.recorridos;

import uoc.ei.tads.*;

/** Esta clase define de manera recursiva los recorridos básicos sobre
 * los árboles generales. Proporciona un tratamiento por defecto que consiste
 * en mostrar por la salida estàndar los elementos del arbol.<p>
 * En clases derivadas se puede definir el método tratarPosicion con el objetivo
 * de definir el tratamiento aplicado al arbol.
 * @author Jordi Alvarez
 * @author Esteve Marine
 * 			Estructura de la Información
 * 			Universitat Oberta de Catalunya (UOC)
 *
 * @param <E>
 */

public abstract class RecorridosArbol<E> {

	public RecorridosArbol() {
		super();
	}
	
	
	public void preorden(Arbol<E> arbol) {
		inicializarRecorrido();
		if (!arbol.estaVacio())
			preorden(arbol,arbol.raiz());
		finalizarRecorrido();
	}
	

	public void postorden(Arbol<E> arbol) {
		inicializarRecorrido();
		if (!arbol.estaVacio())
			postorden(arbol,arbol.raiz());
		finalizarRecorrido();
	}
	
	
	public void porNiveles(Arbol<E> arbol) {
		inicializarRecorrido();
		Cola<Posicion<E>> posiciones=new ColaVectorImpl<Posicion<E>>(arbol.numElems());
		if (!arbol.estaVacio())
			posiciones.encolar(arbol.raiz());
		while (!posiciones.estaVacio()) {
			Posicion<E> p=posiciones.desencolar();
			tratarPosicion(p);
			Recorrido<E> hijos=arbol.hijos(p);
			while (hijos.haySiguiente())
				posiciones.encolar(hijos.siguiente());
		}
		finalizarRecorrido();
	}
	
	
	protected void preorden(Arbol<E> arbol,Posicion<E> p) {
		tratarPosicion(p);
		Recorrido<E> hijos=arbol.hijos(p);
		while (hijos.haySiguiente()) {
			Posicion<E> hijo=hijos.siguiente();
			preorden(arbol,hijo);
		}
	}
		

	protected void postorden(Arbol<E> arbol,Posicion<E> p) {
		Recorrido<E> hijos=arbol.hijos(p);
		while (hijos.haySiguiente()) {
			Posicion<E> hijo=hijos.siguiente();
			postorden(arbol,hijo);
		}
		tratarPosicion(p);
	}
		
		
	protected void tratarPosicion(Posicion<E> posicio) {
		System.out.print(posicio.getElem()+" ");
	}
	
	
	protected void inicializarRecorrido() {}
	
	
	protected void finalizarRecorrido() {}
	

}
