package uoc.ei.ejemplos.modulo1.defensivo;

/** Versión defensiva de NatiImpl.
 */

public class NatiDefensivoImpl implements NatDefensivo {
	
	private int nat;

	public NatiDefensivoImpl() { nat=0; }

	public void pred() throws ExcepcionNat {
		if (nat==0)
                throw new ExcepcionNat("No existe el predecesor de 0");
            nat--;
	}

	public void succ() { nat++;	}
	public void sumarCantidad(NatDefensivo y) { nat+=y.consultar(); }
	
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
