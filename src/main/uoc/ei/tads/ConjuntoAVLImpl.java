package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de un conjunto por delegación en un
 * arbol binario AVL.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operación de igualdad.
 *
 * En esta implementación se espera que la clase de los elementos
 * implemente la interfaz java.lang.Comparable o bien que se facilite un
 * java.util.Comparator como parámetro del constructor.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ConjuntoAVLImpl<E> extends ConjuntoAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario AVL que implementa las operaciones por delegación. */
   protected ArbolAVL<E> avl;

   
   /**
    * Constructor sin parámetros. Se espera que las clases de los elementos
    * implementen la interfaz java.lang.Comparable.
    */
   public ConjuntoAVLImpl() { avl = new ArbolAVL<E>(); }

   
   /**
    * Constructor con un parámetro y elementos de una clase comparable con
    * el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ConjuntoAVLImpl(java.util.Comparator<E> comparador)
   {
      avl = new ArbolAVL<E>(comparador);
   }

   /**
    * Accesor de lectura del número de elementos que hay al contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  avl.numElems(); }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  avl.estaVacio(); }

   /**
    * Añade un elemento, si se puede.
    * @param elem  elemento que se quiere añadir al conjunto
    */
   public void insertar(E elem)
   {
   	avl.insertar(elem);
   }

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, según si encuentra o no encuentra el elemento
    */
   public boolean esta(E elem)
   {
      return  avl.consultar(elem) != null;
   }

   /**
    * Borra un elemento, si se puede.
    * @param elem  elemento de referencia
    * @return  elemento borrado; o null, si no existía
    */
   public E borrar(E elem)
   {
   		return avl.borrar(elem);
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeración.
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y no existe tal elemento.
    * @return  enumeración de los elementos asociados con las claves.
    */
   public Iterador<E> elementos() { return  avl.elementos(); }

   /**
    * Método que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de línea de la plataforma.
    */
   public String toString()
   {
 	  return Utilidades.toStringContenedorDelegacion("ConjuntoAVLImpl",avl);
   }
}
