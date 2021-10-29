package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que representa una posición de un contenedor. Una posición es
 * el lugar que ocupa un elemento del contenedor y se define relativamente,
 * por ejemplo, en relación con sus vecinos.
 * 
 * Las clases que lo implementen deben concretar la mencionada posición
 * asociándole un nodo encadenado, el índice de un vector, etc.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Posicion<E> extends Serializable
{
   /**
    * Accessor de lectura del elemento almacenado a la posición.
    * @return  elemento almacenado a la posición
    */
   public E getElem();

}
