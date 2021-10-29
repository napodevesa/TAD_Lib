package uoc.ei.tads;
/**
 * Interfaz que define las operaciones básicas de un conjunto.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operación de igualdad.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Conjunto<E>  extends Contenedor<E>
{
   /**
    * Añade un elemento, si se puede.
    * @param elem  elemento que se quiere añadir al conjunto
    */
   public void insertar(E elem);

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, según si encuentra o no encuentra el elemento
    */
   public boolean esta(E elem);

   /**
    * Borra un elemento, si se puede.
    * @param elem  elemento de referencia
    * @return  elemento borrado; o null, si no era
    */
   public E borrar(E elem);
   
   /**
    * Añade los elementos de un segundo conjunto que no existen en el
    * conjunto actual, si se puede. Si encuentra un elemento equivalente, según la
    * función de comparación, lo sobrescribe.
    * @param conj  conjunto que se quiere unir al actual;
    *              puede ser vacío, pero no null
    * @exception ExcepcionParametroIncorrecto  si el conjunto es null
    */
   public void union(Conjunto<E> conj);

   /**
    * Borra del conjunto actual los elementos que no existen en un
    * segundo conjunto, si se puede.
    * @exception ExcepcionParametroIncorrecto  si el conjunto es null
    * @param conj  conjunto que se quiere interseccionar con el actual;
    *              puede ser vacío, pero no null
    */
   public void interseccion(Conjunto<E> conj);

   /**
    * Borra del conjunto actual los elementos que existen en un segundo
    * conjunto, si se puede.
    * @exception ExcepcionParametroIncorrecto  si el conjunto es null
    * @param conj  conjunto que se quiere sustraer del actual;
    *              puede ser vacío, pero no null
    */
   public void diferencia(Conjunto<E> conj);
}
