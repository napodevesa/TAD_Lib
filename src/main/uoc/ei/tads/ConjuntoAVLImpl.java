package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de un conjunto por delegaci�n en un
 * arbol binario AVL.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operaci�n de igualdad.
 *
 * En esta implementaci�n se espera que la clase de los elementos
 * implemente la interfaz java.lang.Comparable o bien que se facilite un
 * java.util.Comparator como par�metro del constructor.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ConjuntoAVLImpl<E> extends ConjuntoAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario AVL que implementa las operaciones por delegaci�n. */
   protected ArbolAVL<E> avl;

   
   /**
    * Constructor sin par�metros. Se espera que las clases de los elementos
    * implementen la interfaz java.lang.Comparable.
    */
   public ConjuntoAVLImpl() { avl = new ArbolAVL<E>(); }

   
   /**
    * Constructor con un par�metro y elementos de una clase comparable con
    * el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ConjuntoAVLImpl(java.util.Comparator<E> comparador)
   {
      avl = new ArbolAVL<E>(comparador);
   }

   /**
    * Accesor de lectura del n�mero de elementos que hay al contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  avl.numElems(); }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  avl.estaVacio(); }

   /**
    * A�ade un elemento, si se puede.
    * @param elem  elemento que se quiere a�adir al conjunto
    */
   public void insertar(E elem)
   {
   	avl.insertar(elem);
   }

   /**
    * Comprueba si hay un elemento.
    * @param elem  elemento de referencia
    * @return  cierto o falso, seg�n si encuentra o no encuentra el elemento
    */
   public boolean esta(E elem)
   {
      return  avl.consultar(elem) != null;
   }

   /**
    * Borra un elemento, si se puede.
    * @param elem  elemento de referencia
    * @return  elemento borrado; o null, si no exist�a
    */
   public E borrar(E elem)
   {
   		return avl.borrar(elem);
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeraci�n.
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y no existe tal elemento.
    * @return  enumeraci�n de los elementos asociados con las claves.
    */
   public Iterador<E> elementos() { return  avl.elementos(); }

   /**
    * M�todo que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de l�nea de la plataforma.
    */
   public String toString()
   {
 	  return Utilidades.toStringContenedorDelegacion("ConjuntoAVLImpl",avl);
   }
}
