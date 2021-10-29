
package uoc.ei.tads;

/** Implementación de iterador para recorrer los elementos de una
 * colección que usa como representación un vector.
 */
public class IteradorVectorImpl<E> implements Iterador<E> {
	
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
	private E[] elementos;
	private int numElementos;
	private int indice;
	
	public IteradorVectorImpl(E[] elementos,int numElementos,int primero) {
		this.elementos=elementos;
		this.numElementos=numElementos;
		indice=primero;
	}

	public boolean haySiguiente() {
		return numElementos>0;
	}

	public E siguiente() {
		E elemento=elementos[indice];
		indice++;
		if (indice==elementos.length) indice=0;		// sirve para representaciones circulares
		numElementos--;
		return elemento;
	}

}
