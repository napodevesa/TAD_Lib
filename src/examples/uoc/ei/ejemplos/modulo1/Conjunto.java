package uoc.ei.ejemplos.modulo1;

/**
 * Interfaz que define las operaciones de un conjunto.
 *
 * Los conjuntos son estructures que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operaci�n de igualdad.
 *
 */
public interface Conjunto {

	/**
	 * A�ade un elemento al conjunto. Si este ya est�, no hace nada.
	 * @param elem el elemento que es quiere a�adir al conjunto
	 */
   public void insertar(Object elem);

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, seg�n si el conjunto tiene o no el elemento.
    */
   public boolean esta(Object elem);

   /**
    * Borra un elemento del conjunto. Si el elemento no esta, no hace nada.
    * @param elem  elemento a borrar.
    * @return el elemento borrado; o null, si no estaba
    */
   public Object borrar(Object elem);


   /** 
    * @return true si el conjunto est� vac�o y false de lo contrario.
    */
   public boolean estaVacio();

}
