package uoc.ei.ejemplos.modulo4.recorridos;

import uoc.ei.tads.*;


/** Esta clase extiende la clase RecorridosArbol con los recorridos básicos
 * sobre árboles disponibles únicamente para arboles binarios.
 *  @author Jordi Alvarez
 *  @author Esteve Marine
 * 			Estructura de la Información
 * 			Universitat Oberta de Catalunya (UOC)
 * *
 * @param <E>
 */

public abstract class RecorridosArbolBinario<E> extends RecorridosArbol<E> {

	
	public void preorden(ArbolBinario<E> arbol) {
		if (!arbol.estaVacio())
			preorden(arbol,arbol.raiz());
	}
	

	public void inorden(ArbolBinario<E> arbol) {
		if (!arbol.estaVacio())
			inorden(arbol,arbol.raiz());
	}
	

	public void postorden(ArbolBinario<E> arbol) {
		if (!arbol.estaVacio())
			postorden(arbol,arbol.raiz());
	}
	

	public void preorden(ArbolBinario<E> arbol,Posicion<E> p) {
		Posicion<E> hijoDerecho=arbol.hijoDerecho(p);
		Posicion<E> hijoIzquierdo=arbol.hijoIzquierdo(p);
		tratarPosicion(p);
		if (hijoIzquierdo!=null) preorden(arbol,hijoIzquierdo);
		if (hijoDerecho!=null) preorden(arbol,hijoDerecho);
	}


	public void inorden(ArbolBinario<E> arbol,Posicion<E> p) {
		Posicion<E> hijoDerecho=arbol.hijoDerecho(p);
		Posicion<E> hijoIzquierdo=arbol.hijoIzquierdo(p);
		if (hijoIzquierdo!=null) inorden(arbol,hijoIzquierdo);
		tratarPosicion(p);
		if (hijoDerecho!=null) inorden(arbol,hijoDerecho);
	}


	public void postorden(ArbolBinario<E> arbol,Posicion<E> p) {
		Posicion<E> hijoDerecho=arbol.hijoDerecho(p);
		Posicion<E> hijoIzquierdo=arbol.hijoIzquierdo(p);
		if (hijoIzquierdo!=null) postorden(arbol,hijoIzquierdo);
		if (hijoDerecho!=null) postorden(arbol,hijoDerecho);
		tratarPosicion(p);
	}

}
