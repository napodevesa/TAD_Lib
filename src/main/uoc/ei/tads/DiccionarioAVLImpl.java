package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de un diccionario por delegaci�n en
 * un arbol binario AVL.
 *
 * Los diccionarios son estructuras que almacenan elementos con una clave
 * asociada. La clave debe disponer de una operaci�n de igualdad. En el caso
 * de los diccionarios ordenados debe permitir la ordenaci�n total entre claves.
 * Algunas implementaciones permiten claves repetidas y otras no. El elemento
 * asociado a la clave puede ser cualquier objeto.
 *
 * En esta implementaci�n se utilizan elementos de la clase ClaveValor,
 * que empareja la clave y el valor asociado. Si no hay comparador
 * espec�fico se utilitza ClaveValorComparable que implementa la interfaz
 * java.lang.Comparable, por delegaci�n en la clave.
 *
 * @see  ClaveValor
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class DiccionarioAVLImpl<C,E>  implements Diccionario<C,E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario AVL que implementa las operaciones por delegaci�n. */
   protected ArbolAVL<ClaveValor<C,E>> avl;

   /**
    * Constructor sin par�metros. Se espera que las clases de los elementos
    * implementen la interfaz java.lang.Comparable.
    */
   public DiccionarioAVLImpl() { avl = new ArbolAVL<ClaveValor<C,E>>(); }

   /**
    * Constructor con un par�metro y elementos de una clase comparable con
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
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  avl.numElems(); }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  avl.estaVacio(); }

   /**
    * A�ade un elemento con una clave asociada, si se puede. Si encuentra un
    * elemento con la misma clave lo sobrescribe.
    * @see  ClaveValor
    * @param clave  clave asociada al elemento que se quiere a�adir
    * @param elem  elemento que se quiere a�adir al diccionario
    */
   public void insertar(C clave,E elem)
   {
      avl.insertar( new ClaveValor<C,E>(clave, elem));
   }

   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param clave  clave asociada a un elemento
    * @return  cierto o falso, seg�n si encuentra o no encuentra la clave
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
    * Retorna una enumeraci�n.
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe
    * @return  enumeraci�n de las claves del contenedor ordenadas
    */
   public Iterador<C> claves()
   {
      return  new IteradorRecorridoClavesImpl<C,E>(avl.recorridoInorden());
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
    *            elemento de la enumeraci�n y no hay ning�n o no hay ning�n
    *            m�s
    * @return  enumeraci�n de los elementos asociados con las claves
    */
   public Iterador<E> elementos()
   {
      return  new IteradorRecorridoValoresImpl<C,E>(avl.recorridoInorden());
   }

   /**
    * M�todo que sobrescribe Object.toString().
    * Separa una par de la siguiente con el salto de l�nea de la
    * plataforma.
    */
   public String toString() {
	   return  Utilidades.toStringContenedorDelegacion("DiccionarioAVLImpl",avl);
   }
   
}
