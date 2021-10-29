package uoc.ei.ejemplos.modulo1;

public class NatbImpl implements Nat {

	/** Esta clase representa el natural mediante
	 * una tabla de booleanos. La representación de un natural
	 * N se corresponde con una tabla donde las N primeras
	 * posiciones de la tabla tienen el valor true y la posición
	 * N+1 tiene el valor false.
	 */
	private boolean[] nat;

	/** Crea una instancia de natural con valor 0.
	 * @post nat!=null && nat[0]==false
	 */
	public NatbImpl() {
		nat=new boolean[10];
	}

	public void succ() {
		int i=buscarUltimaPosicion();
		marcarPosicion(i+1,true);
	}

	public void pred() {
		int i=buscarUltimaPosicion();
		marcarPosicion(i,false);
	}

	public void sumarCantidad(Nat y) {
		int i=buscarUltimaPosicion();
		int j=y.consultar();
		while (j-->0)
			marcarPosicion(++i,true);
	}
	
	/** 
	 * @pre true
	 * @post nat == inicial(nat) &&
	 * 		$all(nat,0,$return-1,index,nat[index] == true) &&
	 * 		(nat.length<$return || nat[$return]==false)
	 */
	public int consultar() {
		return buscarUltimaPosicion()+1;
	}
	
	/** Retorna una representación en formato texto del
	 * natural.
	 * @pre true
	 * @post nat==inicial(nat)
	 */
	public String toString() {
		return Integer.toString(consultar());
	}
	
	
	/** 
	 * @pre true
	 * @post nat == inicial(nat) &&
	 * 		$all(nat,0,$return,i,nat[i]) &&
	 * 		(nat.length<$return+1 || nat[$return+1]==false)
	 */
	private int buscarUltimaPosicion() {
		int i=0;
		while (i<nat.length && nat[i])
			i++;
		return i-1;
	}

	/** 
	 * @pre true
	 * @post $all(nat,j,j==i || nat[j] == $inicial(nat[j])) && nat[i]==valor
	 */
	private void marcarPosicion(int i,boolean valor) {
		if (i>=nat.length)
			duplicarVector();
		nat[i]=valor;
	}
	
	/** 
	 * @pre true
	 * @post $all(nat,i,
	 * 					i<$inicial(nat.length) && nat[i]==$inicial(nat[i]) ||
	 * 					i>=$inicial(nat.length) && !nat[i])
	 */
	private void duplicarVector() {
		boolean[] nuevo=new boolean[nat.length*2];
		for(int i=0;i<nat.length;i++)
			nuevo[i]=nat[i];
		nat=nuevo;
	}

}
