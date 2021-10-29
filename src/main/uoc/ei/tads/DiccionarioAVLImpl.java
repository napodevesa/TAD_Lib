package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de un diccionario por delegación en
 * un arbol binario AVL.
 *
 * Los diccionarios son estructuras que almacenan elementos con una clave
 * asociada. La clave debe disponer de una operación de igualdad. En el caso
 * de los diccionarios ordenados debe permitir la ordenación total entre claves.
 * Algunas implementaciones permiten claves repetidas y otras no. El elemento
 * asociado a la clave puede ser cualquier objeto.
 *
 * En esta implementación se utilizan elementos de la clase ClaveValor,
 * que empareja la clave y el valor asociado. Si no hay comparador
 * específico se utilitza ClaveValorComparable que implementa la interfaz
 * java.lang.Comparable, por delegación en la clave.
 *
 * @see  ClaveValor
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DiccionarioAVLImpl<C,E>  implements Diccionario<C,E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario AVL que implementa las operaciones por delegación. */
   protected ArbolAVL<ClaveValor<C,E>> avl;

   /**
    * Constructor sin parámetros. Se espera que las clases de los elementos
    * implementen la interfaz java.lang.Comparable.
    */
   public DiccionarioAVLImpl() { avl = new ArbolAVL<ClaveValor<C,E>>(); }

   /**
    * Constructor con un parámetro y elementos de una clase comparable con
    * el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    * @exception ExcepcionParametroIncorrecto  si el comparador es null
    */
   public DiccionarioAVLImpl(java.util.Comparator<ClaveValor<C,E>> comparador)
          throws  ExcepcionParametroIncorrecto
   {
      avl = new ArbolAVL<ClaveValor<C,E>>(comparador);
   }

   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  avl.numElems(); }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  avl.estaVacio(); }

   /**
    * Añade un elemento con una clave asociada, si se puede. Si encuentra un
    * elemento con la misma clave lo sobrescribe.
    * @see  ClaveValor
    * @param clave  clave asociada al elemento que se quiere añadir
    * @param elem  elemento que se quiere añadir al diccionario
    */
   public void insertar(C clave,E elem)
   {
      avl.insertar( new ClaveValor<C,E>(clave, elem));
   }

   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param clave  clave asociada a un elemento
    * @return  cierto o falso, según si encuentra o no encuentra la clave
    */
   public boolean esta(C clave)
   {
      return  consultar(clave) != null;
   }

   /**
    * Accesor de lectura del elemento asociado con una clave.
    * @see  ClaveValor
    * @param clave  clave de referencia
    * @return  elemento asociado con la clave; o null, si no era
    */
   public E consultar(C clave)
   {
   	E result=null;
   	ClaveValor<C,E> aux=new ClaveValor<C,E>(clave,null);
    ClaveValor<C,E> claveValor =  avl.consultar(aux);
    if (claveValor!=null)
    	result=claveValor.getValor();
    return result;
   }

   /**
    * Borra la primera clave coincidente y el elemento asociado, si se puede.
    * @see  ClaveValor
    * @param clave  clave de referencia
    * @return  elemento borrado asociado con la clave;
    *          o null, si no era
    */
   public E borrar(C clave)
   {
   	E result=null;
   	ClaveValor<C,E> aux=new ClaveValor<C,E>(clave,null);
    ClaveValor<C,E> claveValor =  avl.borrar(aux);
    if (claveValor!=null)
    	result=claveValor.getValor();
    return result;
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
    *            elemento de la enumeración y éste no existe
    * @return  enumeración de las claves del contenedor ordenadas
    */
   public Iterador<C> claves()
   {
      return  new IteradorRecorridoClavesImpl<C,E>(avl.recorridoInorden());
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
    *            elemento de la enumeración y no hay ningún o no hay ningún
    *            más
    * @return  enumeración de los elementos asociados con las claves
    */
   public Iterador<E> elementos()
   {
      return  new IteradorRecorridoValoresImpl<C,E>(avl.recorridoInorden());
   }

   /**
    * Método que sobrescribe Object.toString().
    * Separa una par de la siguiente con el salto de línea de la
    * plataforma.
    */
   public String toString() {
	   return  Utilidades.toStringContenedorDelegacion("DiccionarioAVLImpl",avl);
   }
   
}
