package uoc.ei.tads;
/**
 * Clase que implementa las operaciones b�sicas de un conjunto por
 * delegaci�n en una tabla de dispersi�n.
 *
 * Los conjuntos son estructuras que almacenan elementos no repetidos. La
 * clase de los objetos debe disponer de una operaci�n de igualdad.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ConjuntoTablaImpl<E>  extends ConjuntoAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();


   /** Tabla de dispersi�n que implementa las operaciones por delegaci�n. */
   protected TablaDispersion<E,E> td;

   
   /** Constructor sin par�metros (medida de la tabla por defecto). */
   public ConjuntoTablaImpl()
   {
   	td = new TablaDispersion<E,E>();
   }

   
   /** Constructor con un par�metro (tabla del tama�o dado).
    * @param tamano  tama�o de la tabla de dispersi�n 
    * @exception ExcepcionParametroIncorrecto  si el tama�o es negativo
    * @pre tamano>=0, new ExcepcionParametroIncorrecto("tama�o negativo")
    */
   public ConjuntoTablaImpl(int tamano)
   {
      td = new TablaDispersion<E,E>(tamano);
   }

   
   /**
    * Accesor de lectura del n�mero de elementos que hay al contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  td.numElems(); }


   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  td.estaVacio(); }

   
   /**
    * A�ade un elemento, si se puede. Utiliza el mismo elemento como clave
    * y como valor.
    * @param elem  elemento que se quiere a�adir al conjunto
    */
   public void insertar(E elem)
   {
   	td.insertar(elem, elem);
   }


   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param elem  elemento
    * @exception ExcepcionContenedorVacio  si la tabla est� vac�a
    * @exception ExcepcioElementsNoComparables  si el elemento es no
    *            comparable
    * @return  cierto o falso, seg�n si encuentra o no encuentra la clave
    */
   public boolean esta(E elem)
   {
      return  td.consultar(elem) != null;
   }


   /**
    * Borra la clave y el elemento asociado, si se puede.
    * @see  ClaveValor
    * @param elem elemento a borrar
    * @exception ExcepcionContenedorVacio  si la tabla est� vac�a
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
    * @return  enumeraci�n de los elementos asociados con las claves
    */
   public Iterador<E> elementos() { return  td.elementos(); }


   /**
    * M�todo que sobrescribe Object.toString().
    * Separa un par del siguiente con el salto de l�nea de la
    * plataforma.
    */
   public String toString()
   {
	  return Utilidades.toStringContenedorDelegacion("ConjuntoTablaImpl",td);
   }
}
