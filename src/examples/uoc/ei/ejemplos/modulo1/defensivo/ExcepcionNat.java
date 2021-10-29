package uoc.ei.ejemplos.modulo1.defensivo;

/** Excepcio que será lanzada cuando alguna de las operaciones
 * del TAD Nat no cumplen las condiciones para ejecutarse.
 */
public class ExcepcionNat extends Exception {
	
	public ExcepcionNat(String mensaje) {
		super(mensaje);
	}

}
