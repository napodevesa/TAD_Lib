package uoc.ei.tads;

/**
 * Secuencia posicional que se caracteriza por disponer de operaciones
 * basadas en la posición que ocupa un elemento dentro del contenedor.
 *
 * @see  Posicion
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Lista<E>  extends Contenedor<E>
{
   /**
    * Añade un elemento al principio de la lista.
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAlPrincipio(E elem);

   /**
    * Añade un elemento al final de la lista.
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAlFinal(E elem);

   /**
    * Añade un elemento antes de la posición recibida.
    * @param elem  elemento que se quiere añadir a la lista
    * @param pos  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAntesDe(Posicion<E> pos, E elem);

   /**
    * Añade un elemento después de la posición recibida.
    * @param elem  elemento que se quiere añadir a la lista
    * @param pos  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarDespuesDe(Posicion<E> pos, E elem);

   /**
    * Borra la primera posición de la lista.
    * @exception ExcepcionContenedorVacio  si la lista está vacía
    * @return  elemento que había en la posición
    */
   public E borrarPrimero();

   /**
    * Borra la posición recibida.
    * @param pos  posición que se quiere eliminar
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había en la posición
    */
   public E borrar(Posicion<E> pos);

   /**
    * Borra la posición siguiente.
    * @param pos  posición anterior a la que se quiere eliminar
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había en la posición siguiente
    */
   public E borrarSiguiente(Posicion<E> pos);

   /**
    * Reemplaza el elemento contenido a la posición recibida.
    * @param elem  nuevo elemento
    * @param pos  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había a la posición
    */
   public E reemplazar(Posicion<E> pos, E elem);

   /**
    * Intercambia los elementos contenidos a las posiciones recibidas.
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    */
   public void intercambiar(Posicion<E> pos1, Posicion<E> pos2);
   

   /**
    * @return Un recorrido sobre las posiciones de la lista.
    */
   public Recorrido<E> posiciones();

}
