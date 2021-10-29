package uoc.ei.ejemplos.modulo1;

/**
 * Interfaz que define las operaciones de un conjunto.
 *
 * Los conjuntos son estructures que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operación de igualdad.
 *
 */
public interface Conjunto {

	/**
	 * Añade un elemento al conjunto. Si este ya está, no hace nada.
	 * @param elem el elemento que es quiere añadir al conjunto
	 */
   public void insertar(Object elem);

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, según si el conjunto tiene o no el elemento.
    */
   public boolean esta(Object elem);

   /**
    * Borra un elemento del conjunto. Si el elemento no esta, no hace nada.
    * @param elem  elemento a borrar.
    * @return el elemento borrado; o null, si no estaba
    */
   public Object borrar(Object elem);


   /** 
    * @return true si el conjunto está vacío y false de lo contrario.
    */
   public boolean estaVacio();

}
