package uoc.ei.tads;

import java.io.Serializable;

/**
 * Interfaz que representa una posici�n de un contenedor. Una posici�n es
 * el lugar que ocupa un elemento del contenedor y se define relativamente,
 * por ejemplo, en relaci�n con sus vecinos.
 * 
 * Las clases que lo implementen deben concretar la mencionada posici�n
 * asoci�ndole un nodo encadenado, el �ndice de un vector, etc.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Posicion<E> extends Serializable
{
   /**
    * Accessor de lectura del elemento almacenado a la posici�n.
    * @return  elemento almacenado a la posici�n
    */
   public E getElem();

}
