package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de un diccionario.
 *
 * Los diccionarios son estructuras que almacenan elementos con una clave
 * asociada. La clave debe disponer de una operaci�n de igualdad. En el caso
 * de los diccionarios ordenados debe permitir la ordenaci�n total entre claves.
 * Algunas implementaciones permiten claves repetidas y otros no. El elemento
 * asociado a la clave puede ser cualquier objeto.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Diccionario<C,E>  extends Contenedor<E>
{
   /**
    * A�ade un elemento con una clave asociada, si se puede.
    * @param clave  clave asociada al elemento que se quiere a�adir
    * @param elem  elemento que se quiere a�adir al diccionario
    */
   public void insertar(C clave, E elem);

   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param clave  clave asociada a un elemento
    * @return  cierto o falso, seg�n si encuentra o no encuentra la clave
    */
   public boolean esta(C clave);

   /**
    * Accesor de lectura del elemento asociado con una clave.
    * @param clave  clave de referencia
    * @return  elemento asociado con la clave
    */
   public E consultar(C clave);

   /**
    * Borra la primera clave coincidente y el elemento asociado, si se puede.
    * @param clave  clave de referencia
    * @return  elemento asociado con la clave que se ha borrado
    */
   public E borrar(C clave);

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeraci�n. 
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe
    * @return  enumeraci�n de las claves del contenedor
    */
   public Iterador<C> claves();
}
