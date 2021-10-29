package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el �ltimo
 * elemento insertado: last-in-first-out (LIFO).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */

public interface Pila<E>  extends Contenedor<E>
{
   /**
    * A�ade un elemento a la pila, si cabe.
    * @param elem  elemento que se quiere a�adir en la pila
    */
   public void apilar(E elem);

   /**
    * Borra el elemento de la cima de la pila, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la pila est� vac�a
    * @return  elemento que hab�a en la cima de la pila
    */
   public E desapilar();

   /**
    * Accesor de lectura del �ltimo elemento a�adido en la pila, si hay.
    * @exception ExcepcionContenedorVacio  si la pila est� vac�a
    * @return  elemento de la cima de la pila
    */
   public E cima();
}
