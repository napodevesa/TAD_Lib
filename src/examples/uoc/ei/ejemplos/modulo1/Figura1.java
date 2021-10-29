package uoc.ei.ejemplos.modulo1;

public class Figura1 {

	/** Crea una instancia de alguna implementación de Nat.
	 * En el momento en que aparece la figura 1 en el texto
	 * aún no se ha visto ninguna implementación de Nat. Aquí
	 * se utiliza NatiImpl.
	 * @return Una instancia de Nat
	 */ 
	private static Nat crearNat() {
		return new NatiImpl();
	}

	/** Crea una instancia de Nat, llama alguno de sus
	 * métodos y finalmente lo imprime por la salida
	 * estàndar.
	 */
	public static void main(String[] args) {
		Nat n=crearNat();
		n.succ();
		n.pred();
		System.out.println(n);
	}

}
