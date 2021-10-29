
package uoc.ei.ejemplos.modulo1.generics;

/** A�ade a la interfaz Conjunto m�todos para
 * poder saber si el conjunto es lleno.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 * 			Universitat Oberta de Catalunya (UOC)
 */
public interface ConjuntoAcotado<E> extends Conjunto<E> {
	
	/** @return true si el conjunto est� lleno, y false en caso
	 * contrario. */
	boolean estaLleno();

}
