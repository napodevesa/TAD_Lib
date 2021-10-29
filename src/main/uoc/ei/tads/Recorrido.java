package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que define las operaciones de recorrido de las posiciones
 * de un contenedor.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Recorrido<E> extends Serializable
{
	   /**
	    * Comprueba si hay un primero o siguiente elemento.
	    */
	   public boolean haySiguiente();

	   /**
	    * Accesor de lectura del primero o siguiente elemento de la enumeraci�n.
	    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
	    *            elemento de la enumeraci�n y no existe tal elemento.
	    * @return  primero o siguiente elemento al actual.
	    */
	   public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida;

}
