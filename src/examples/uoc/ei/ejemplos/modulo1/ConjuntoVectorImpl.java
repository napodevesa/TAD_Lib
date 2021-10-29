
package uoc.ei.ejemplos.modulo1;

/** Implementación del TAD Conjunto que utiliza una tabla como representación para
 * guardar los elementos. Esta implementación tiene la restricción de que hay que
 * especificar en el momento de la creación del conjunto el número de elementos máximo que
 * se guardarán en el conjunto.
 */
public class ConjuntoVectorImpl implements ConjuntoAcotado {
	
	/** Tabla que guarda los elementos del conjunto. */
	private Object[] elementos;
	
	
	/** Número de elementos que el conjunto contiene actualmente. */ 
	private int numElementosActual;
	
	
	/** Crea un conjunto con un número máximo de elementos.
	 * @pre n>=0
	 * @post elementos.length==n && numElementosActual==0
	 * @param n Número máximo de elementos del conjunto.
	 */
	public ConjuntoVectorImpl(int n) {
		elementos=new Object[n];
		numElementosActual=0;
	}

	
	/**
	 * @pre elem!=null && (!estaPle() || hiEs(elem))
	 * @post this.hiEs(elem) &&
	 * 		($old(this).hiEs(elem) && $old(numElementosActual)==numElementosActual ||
	 * 		 !$old(this).hiEs(elem) && $old(numElementosActual)+1==numElementosActual) &&
     * 		$all(y:elementos,y>=$old(numElementosActual) || $old(elementos[y])==elementos[y])
	 */
	public void insertar(Object elem) {
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
	public boolean esta(Object elem) {
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

	public Object borrar(Object elem) {
		Object elementoBorrado=null;
		int posicion=buscarPosicionElemento(elem);
		if (posicion!=-1) {
			elementoBorrado=elementos[posicion];
			numElementosActual--;
			if (posicion<numElementosActual)
				elementos[posicion]=elementos[numElementosActual];
			// importante para el garbage collection
			elementos[numElementosActual]=null;
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
	 * 		 ($return == -1 && !hiEs(elem) ||
	 * 		  elementos[$return].equals(elem))
	 * @param elem
	 * @return
	 */
	protected int buscarPosicionElemento(Object elem) {
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
