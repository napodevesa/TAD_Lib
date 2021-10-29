package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el último
 * elemento insertado: last-in-first-out (LIFO).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */

public interface Pila<E>  extends Contenedor<E>
{
   /**
    * Añade un elemento a la pila, si cabe.
    * @param elem  elemento que se quiere añadir en la pila
    */
   public void apilar(E elem);

   /**
    * Borra el elemento de la cima de la pila, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la pila está vacía
    * @return  elemento que había en la cima de la pila
    */
   public E desapilar();

   /**
    * Accesor de lectura del último elemento añadido en la pila, si hay.
    * @exception ExcepcionContenedorVacio  si la pila está vacía
    * @return  elemento de la cima de la pila
    */
   public E cima();
}
