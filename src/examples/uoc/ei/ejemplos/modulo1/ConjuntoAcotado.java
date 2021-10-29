
package uoc.ei.ejemplos.modulo1;

/** Añade a la interfaz Conjunto métodos para
 * poder saber si el conjunto está lleno.
 * @author Jordi Alvarez
 */
public interface ConjuntoAcotado extends Conjunto {
	
	/** @return true si el conjunto está lleno, y false en caso
	 * contrario. */
	boolean estaLleno();

}
