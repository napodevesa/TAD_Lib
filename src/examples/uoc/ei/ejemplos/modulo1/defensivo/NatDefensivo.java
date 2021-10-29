package uoc.ei.ejemplos.modulo1.defensivo;

public interface NatDefensivo {
	
	/** Este m�todo incrementa el valor del natural.
	 * @pre true
	 * @post consultar() == $inicial(consultar())+1
	 */
	void succ();

	/** Este m�todo decrementa el valor del natural.
	 * @pre consultar()>0
	 * @post consultar() == $inicial(consultar())-1
	 */
	void pred() throws ExcepcionNat;
	
	/** Este m�todo suma un natural al objeto.
	 * @pre true
	 * @post consultar() == $inicial(consultar())+y.consultar()
	 * @param y El natural a sumar.
	 */
	void sumarCantidad(NatDefensivo y);
	
	/** M�todo consultor que retorna el valor del 
	 * natural como un entero.
	 * @return el valor del objeto.
	 */
	int consultar();
}
