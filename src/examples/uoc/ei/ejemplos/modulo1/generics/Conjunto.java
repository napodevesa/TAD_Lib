package uoc.ei.ejemplos.modulo1.generics;

/**
 * Interfaz que define las operaciones de un conjunto.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operación de igualdad.
 *
 */
public interface Conjunto<E> {

	/**
     * Añade un elemento al conjunto. Si este ya es, no hace nada.
     * @param elem El elemento que se quiere añadir al conjunto
     */
   public void insertar(E elem);

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, según si el conjunto contiene o no el elemento.
    */
   public boolean esta(E elem);

   /**
    * Borra un elemento del conjunto. Si el elemento no está, no hace nada.
    * @param elem  elemento a borrar.
    * @return El elemento borrado; o null, si no estaba
    */
   public E borrar(E elem);
   
   /** 
    * @return true si el conjunto está vacío y false en caso contrario.
    */
   public boolean estaVacio();

}
