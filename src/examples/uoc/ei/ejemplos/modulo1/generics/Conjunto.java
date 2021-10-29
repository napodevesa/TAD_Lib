package uoc.ei.ejemplos.modulo1.generics;

/**
 * Interfaz que define las operaciones de un conjunto.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operaci�n de igualdad.
 *
 */
public interface Conjunto<E> {

	/**
     * A�ade un elemento al conjunto. Si este ya es, no hace nada.
     * @param elem El elemento que se quiere a�adir al conjunto
     */
   public void insertar(E elem);

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, seg�n si el conjunto contiene o no el elemento.
    */
   public boolean esta(E elem);

   /**
    * Borra un elemento del conjunto. Si el elemento no est�, no hace nada.
    * @param elem  elemento a borrar.
    * @return El elemento borrado; o null, si no estaba
    */
   public E borrar(E elem);
   
   /** 
    * @return true si el conjunto est� vac�o y false en caso contrario.
    */
   public boolean estaVacio();

}
