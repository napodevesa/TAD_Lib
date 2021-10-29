package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el primero
 * elemento insertado: first-in-first-out (FIFO).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Cola<E>  extends Contenedor<E>
{
   /**
    * A�ade un elemento a la cola, si ning�n.
    * @param elem  elemento que se quiere a�adir a la cola
    */
   public void encolar(E elem);

   /**
    * Borra el primer elemento de la cola, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la cola est� vac�a
    * @return  primer elemento insertado en la cola
    */
   public E desencolar();

   /**
    * Accesor de lectura del primer elemento a�adido a la cola, si hay.
    * @exception ExcepcionContenedorVacio  si la cola est� vac�a
    * @return  primer elemento de la cola
    */
   public E primero();
}
