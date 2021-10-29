package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el primero
 * elemento insertado: first-in-first-out (FIFO).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Cola<E>  extends Contenedor<E>
{
   /**
    * Añade un elemento a la cola, si ningún.
    * @param elem  elemento que se quiere añadir a la cola
    */
   public void encolar(E elem);

   /**
    * Borra el primer elemento de la cola, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la cola está vacía
    * @return  primer elemento insertado en la cola
    */
   public E desencolar();

   /**
    * Accesor de lectura del primer elemento añadido a la cola, si hay.
    * @exception ExcepcionContenedorVacio  si la cola está vacía
    * @return  primer elemento de la cola
    */
   public E primero();
}
