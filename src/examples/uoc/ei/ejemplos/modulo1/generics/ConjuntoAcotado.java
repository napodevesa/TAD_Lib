
package uoc.ei.ejemplos.modulo1.generics;

/** Añade a la interfaz Conjunto métodos para
 * poder saber si el conjunto es lleno.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 * 			Universitat Oberta de Catalunya (UOC)
 */
public interface ConjuntoAcotado<E> extends Conjunto<E> {
	
	/** @return true si el conjunto está lleno, y false en caso
	 * contrario. */
	boolean estaLleno();

}
