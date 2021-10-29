package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que define las operaciones comunes de todos los TAD que se
 * caracterice por contener un conjunto de elementos. No se contemplan los
 * métodos crea() ni destruye(): las clases que la implementen pueden
 * definirlos o dejar que el compilador use el constructor por
 * defecto, y el "garbage collector" para los objetos no referenciados.
 *
 * También define, a efectos prácticos, el método elementos() que permite
 * recorrer todos los elementos del contenedor mediante un iterador.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Contenedor<E> extends Serializable
{
   /**
    * Comprueba si el contenedor está vacío.
    * @return  cierto si el contenedor está vacío
    */
   public boolean estaVacio();

   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que hay en el contenedor
    */
   public int numElems();

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeración. Se puede obtener un recorrido con un par de
    * líneas de código: <PRE>
    * for ( Iterador it = tad.elementos(); it.haySiguiente(); )
    *    System.out.println(it.siguiente()); </PRE>
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y éste no existe.
    * @return  enumeración de los elementos del contenedor.
    */
   public Iterador<E> elementos();
}
