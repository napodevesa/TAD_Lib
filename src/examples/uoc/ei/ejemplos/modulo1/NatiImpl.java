package uoc.ei.ejemplos.modulo1;

public class NatiImpl implements Nat {

	/** Esta clase representa el natural mediante
	 * un atributo entero.
	 */
	private int nat;

	/** Crea una instancia de Nat con valor 0.
	 * @post nat==0
	 */
	public NatiImpl() { nat=0; }

	public void succ() { nat++;	}
	public void pred() { nat--;	}
	public void sumarCantidad(Nat y) { nat+=y.consultar(); }
	
	/**
	 * @pre true
	 * @post nat==inicial(nat)
	 * @return nat
	 */
	public int consultar() { return nat; }

	/** Retorna una representación en formato texto del
	 * natural.
	 * @pre true
	 * @post nat==inicial(nat)
	 */
	public String toString() {
		return Integer.toString(consultar());
	}

}
