
package uoc.ei.tads.algoritmos;

/** Proporciona una interfaz est�ndar para todos los algoritmos
 * de ordenaci�n. Se ofrece un �nico m�todo ordenar, que ordena los
 * elementos de un vector.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface AlgoritmoOrdenacion<E> {
	
	void ordenar(E[] vector,int n);

}
