
package uoc.ei.ejemplos.modulo1.generics;

/** Implementación del TAD Conjunto que utiliza una tabla como representación para
 * guardar los elementos. Esta implementación tiene la restricción de que hay que
 * especificar en el momento de la creación del conjunto el número de elementos máximo que
 * se guardarán en el conjunto.
 */
public class ConjuntoVectorImpl<E> implements ConjuntoAcotado<E> {
	
	/** Tabla que guarda los elementos del conjunto. */
	private E[] elementos;
	
	
	/** Número de elementos que el conjunto contiene actualmente. */ 
	private int numElementosActual;
	
	
	/** Crea un conjunto con un número máximo de elementos.
	 * @pre n>=0
	 * @post elementos.length==n && numElementosActual==0
	 * @param n Número máximo de elementos del conjunto.
	 */
	public ConjuntoVectorImpl(int n) {
		elementos=(E[])new Object[n];
		numElementosActual=0;
	}

	
	/**
	 * @pre elem!=null && (numElementosActual<elementos.length || esta(elem))
	 * @post this.esta(elem) &&
	 * 		($old(this).esta(elem) && $old(numElementosActual)==numElementosActual ||
	 * 		 !$old(this).esta(elem) && $old(numElementosActual)+1==numElementosActual) &&
     * 		$all(i:elementos,i>=$old(numElementosActual) || $old(elementos[i])==elementos[i])
	 */
	public void insertar(E elem) {
		if (!esta(elem)) {
			elementos[numElementosActual]=elem;
			numElementosActual++;
		}
	}
	
	
	/**
	 * @pre elem!=null
	 * @post $old(this)==this &&
	 * 		 $return == $exists(i:elementos,i<numElementosActual && elementos[i].equals(elem))
	 */
	public boolean esta(E elem) {
		return buscarPosicionElemento(elem)!=-1;
	}
	

	/**
	 * @pre elem!=null
	 * @post !esta(elem) && 
	 * 		($old(this).esta(elem) && $old(numElementosActual)==numElementosActual+1 ||
	 * 		 !$old(this).esta(elem) && $old(numElementosActual)==numElementosActual) &&
     * 		$all(i:elementos,i>=$old(numElementosActual) ||
     * 						$old(elementos[i])==elem ||
     * 						this.esta($old(elementos[i])))
	 */
	public E borrar(E elem) {
		E elementoBorrado=null;
		int posicion=buscarPosicionElemento(elem);
		if (posicion!=-1) {
			elementoBorrado=elementos[posicion];
			numElementosActual--;
			if (posicion<numElementosActual)
				elementos[posicion]=elementos[numElementosActual];
		}
		return elementoBorrado;
	}

	
	/**
	 * @post $return == (numElementosActual==0)
	 */
	
	public boolean estaVacio() {
		return numElementosActual==0;
	}
	
	
	/**
	 * @post $return == (numElementosActual==elementos.length)
	 */
	
	public boolean estaLleno() {
		return numElementosActual==elementos.length;
	}

	
	/** Método privado que busca la posición de un elemento
	 * dentro de de la tabla donde se guardan todos los elementos
	 * que se han añadido al conjunto.
	 * @pre elem!=null
	 * @post $old(this)==this &&
	 * 		 ($return == -1 && !esta(elem) ||
	 * 		  elementos[$return].equals(elem))
	 * @param elem
	 * @return
	 */
	protected int buscarPosicionElemento(E elem) {
		boolean encontrado=false;
		int i=0;
		while (i<numElementosActual && !encontrado) {
			encontrado=elementos[i].equals(elem);
			if (!encontrado) i++;
		}
		return encontrado ? i : -1;
	}
	
	
	/** Definición de este método para poder obtener una representación
	 * String del conjunto donde se vean todos los elementos.
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append('{');
		int i=0;
		while (i<numElementosActual) {
			sb.append(elementos[i]);
			i++;
			if (i<numElementosActual) sb.append(',');
		}
		sb.append('}');
		return sb.toString();
	}

}
