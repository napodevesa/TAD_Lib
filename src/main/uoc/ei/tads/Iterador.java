package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que define las operaciones de recorrido de los elementos de un
 * contenedor.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Iterador<E> extends Serializable
{
   /**
    * Comprueba si hay siguiente elemento.
    */
   public boolean haySiguiente();

   /**
    * Accesor de lectura del siguiente elemento de la enumeración.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y no hay tal elemento.
    * @return  primero o siguiente elemento al actual.
    */
   public E siguiente()  throws  ExcepcionPosicionInvalida;
 }
