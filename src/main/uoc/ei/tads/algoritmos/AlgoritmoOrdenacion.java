
package uoc.ei.tads.algoritmos;

/** Proporciona una interfaz estándar para todos los algoritmos
 * de ordenación. Se ofrece un único método ordenar, que ordena los
 * elementos de un vector.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface AlgoritmoOrdenacion<E> {
	
	void ordenar(E[] vector,int n);

}
