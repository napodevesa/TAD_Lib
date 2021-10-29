package uoc.ei.tads;
/**
 * Clase que implementa las operaciones básicas de un conjunto por
 * delegación en una tabla de dispersión.
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
public class ConjuntoTablaImpl<E>  extends ConjuntoAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();


   /** Tabla de dispersión que implementa las operaciones por delegación. */
   protected TablaDispersion<E,E> td;

   
   /** Constructor sin parámetros (medida de la tabla por defecto). */
   public ConjuntoTablaImpl()
   {
   	td = new TablaDispersion<E,E>();
   }

   
   /** Constructor con un parámetro (tabla del tamaño dado).
    * @param tamano  tamaño de la tabla de dispersión 
    * @exception ExcepcionParametroIncorrecto  si el tamaño es negativo
    * @pre tamano>=0, new ExcepcionParametroIncorrecto("tamaño negativo")
    */
   public ConjuntoTablaImpl(int tamano)
   {
      td = new TablaDispersion<E,E>(tamano);
   }

   
   /**
    * Accesor de lectura del número de elementos que hay al contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  td.numElems(); }


   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  td.estaVacio(); }

   
   /**
    * Añade un elemento, si se puede. Utiliza el mismo elemento como clave
    * y como valor.
    * @param elem  elemento que se quiere añadir al conjunto
    */
   public void insertar(E elem)
   {
   	td.insertar(elem, elem);
   }


   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param elem  elemento
    * @exception ExcepcionContenedorVacio  si la tabla está vacía
    * @exception ExcepcioElementsNoComparables  si el elemento es no
    *            comparable
    * @return  cierto o falso, según si encuentra o no encuentra la clave
    */
   public boolean esta(E elem)
   {
      return  td.consultar(elem) != null;
   }


   /**
    * Borra la clave y el elemento asociado, si se puede.
    * @see  ClaveValor
    * @param elem elemento a borrar
    * @exception ExcepcionContenedorVacio  si la tabla está vacía
    * @exception ExcepcioElementsNoComparables  si el elemento es no
    *            comparable
    * @return  elemento borrado asociado con la clave;
    *          o null, si no estaba
    */
   public E borrar(E elem)
   {
   	return  td.borrar(elem);
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
    * @return  enumeración de los elementos asociados con las claves
    */
   public Iterador<E> elementos() { return  td.elementos(); }


   /**
    * Método que sobrescribe Object.toString().
    * Separa un par del siguiente con el salto de línea de la
    * plataforma.
    */
   public String toString()
   {
	  return Utilidades.toStringContenedorDelegacion("ConjuntoTablaImpl",td);
   }
}
