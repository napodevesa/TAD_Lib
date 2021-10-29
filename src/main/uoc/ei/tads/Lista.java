package uoc.ei.tads;

/**
 * Secuencia posicional que se caracteriza por disponer de operaciones
 * basadas en la posici�n que ocupa un elemento dentro del contenedor.
 *
 * @see  Posicion
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Lista<E>  extends Contenedor<E>
{
   /**
    * A�ade un elemento al principio de la lista.
    * @param elem  elemento que se quiere a�adir a la lista
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarAlPrincipio(E elem);

   /**
    * A�ade un elemento al final de la lista.
    * @param elem  elemento que se quiere a�adir a la lista
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarAlFinal(E elem);

   /**
    * A�ade un elemento antes de la posici�n recibida.
    * @param elem  elemento que se quiere a�adir a la lista
    * @param pos  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarAntesDe(Posicion<E> pos, E elem);

   /**
    * A�ade un elemento despu�s de la posici�n recibida.
    * @param elem  elemento que se quiere a�adir a la lista
    * @param pos  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarDespuesDe(Posicion<E> pos, E elem);

   /**
    * Borra la primera posici�n de la lista.
    * @exception ExcepcionContenedorVacio  si la lista est� vac�a
    * @return  elemento que hab�a en la posici�n
    */
   public E borrarPrimero();

   /**
    * Borra la posici�n recibida.
    * @param pos  posici�n que se quiere eliminar
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a en la posici�n
    */
   public E borrar(Posicion<E> pos);

   /**
    * Borra la posici�n siguiente.
    * @param pos  posici�n anterior a la que se quiere eliminar
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a en la posici�n siguiente
    */
   public E borrarSiguiente(Posicion<E> pos);

   /**
    * Reemplaza el elemento contenido a la posici�n recibida.
    * @param elem  nuevo elemento
    * @param pos  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a a la posici�n
    */
   public E reemplazar(Posicion<E> pos, E elem);

   /**
    * Intercambia los elementos contenidos a las posiciones recibidas.
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    */
   public void intercambiar(Posicion<E> pos1, Posicion<E> pos2);
   

   /**
    * @return Un recorrido sobre las posiciones de la lista.
    */
   public Recorrido<E> posiciones();

}
