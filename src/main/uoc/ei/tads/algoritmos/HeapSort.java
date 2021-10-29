package uoc.ei.tads.algoritmos;

import uoc.ei.tads.ColaConPrioridad;


/** Algoritmo de HeapSort. Usa una cola con prioridad para
 * ordenar una secuencia de elementos.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class HeapSort<E> implements AlgoritmoOrdenacion<E> {

	public void ordenar(E[] vector,int n) {
		ColaConPrioridad<E> c=new ColaConPrioridad<E>(vector.length);
		for(int i=0;i<n;i++)
			c.encolar(vector[i]);
		for(int i=0;i<n;i++)
			vector[i]=c.desencolar();
	}

}
