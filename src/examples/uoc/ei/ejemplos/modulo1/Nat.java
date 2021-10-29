package uoc.ei.ejemplos.modulo1;

public interface Nat {
	
	/** Este método incrementa el valor del natural.
	 * @pre true
	 * @post consultar() == $inicial(consultar())+1
	 */
	void succ();

	/** Este método decrementa el valor del natural.
	 * @pre consultar()>0
	 * @post consultar() == $inicial(consultar())-1
	 */
	void pred();
	
	/** Este método suma un natural al objeto.
	 * @pre true
	 * @post consultar() == $inicial(consultar())+y.consultar()
	 * @param y El natural a sumar.
	 */
	void sumarCantidad(Nat y);
	
	/** Método consultor que retorna el valor del 
	 * natural como un entero.
	 * @return el valor del objeto.
	 */
	int consultar();
}
