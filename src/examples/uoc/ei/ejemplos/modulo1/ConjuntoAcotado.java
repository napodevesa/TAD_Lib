
package uoc.ei.ejemplos.modulo1;

/** A�ade a la interfaz Conjunto m�todos para
 * poder saber si el conjunto est� lleno.
 * @author Jordi Alvarez
 */
public interface ConjuntoAcotado extends Conjunto {
	
	/** @return true si el conjunto est� lleno, y false en caso
	 * contrario. */
	boolean estaLleno();

}
