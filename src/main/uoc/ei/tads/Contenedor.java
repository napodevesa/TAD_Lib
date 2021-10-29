package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que define las operaciones comunes de todos los TAD que se
 * caracterice por contener un conjunto de elementos. No se contemplan los
 * m�todos crea() ni destruye(): las clases que la implementen pueden
 * definirlos o dejar que el compilador use el constructor por
 * defecto, y el "garbage collector" para los objetos no referenciados.
 *
 * Tambi�n define, a efectos pr�cticos, el m�todo elementos() que permite
 * recorrer todos los elementos del contenedor mediante un iterador.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Contenedor<E> extends Serializable
{
   /**
    * Comprueba si el contenedor est� vac�o.
    * @return  cierto si el contenedor est� vac�o
    */
   public boolean estaVacio();

   /**
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que hay en el contenedor
    */
   public int numElems();

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeraci�n. Se puede obtener un recorrido con un par de
    * l�neas de c�digo: <PRE>
    * for ( Iterador it = tad.elementos(); it.haySiguiente(); )
    *    System.out.println(it.siguiente()); </PRE>
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe.
    * @return  enumeraci�n de los elementos del contenedor.
    */
   public Iterador<E> elementos();
}
