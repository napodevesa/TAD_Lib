package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el último
 * elemento insertado: last-in-first-out (LIFO).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class PilaVectorImpl<E>  implements Pila<E>, ContenedorAcotado<E>
{
	
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad máxima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /**
    * Número de elementos que hay actualmente al contenedor. También
    * representa la posición donde se debe empilar un nuevo elemento.
    */
   protected int n;

   /** Tabla de elementos del contenedor. Las posiciones empiezan por el cero.*/
   protected E[] elementos;

   /**
    * Constructor sin parámetros (capacidad máxima, por defecto).
    * @post n == 0 && this.max == MAXIMO_ELEMENTOS_POR_DEFECTO && elementos.length==max
    */
   public PilaVectorImpl() {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un parámetro.
    * @exception ExcepcionParametroIncorrecto  si la capacidad máxima de la
    *                                         nueva pila es negativa
    * @param max  número máximo de elementos que puede contener 
    * @pre max>0
    * @post n == 0 && this.max == max && elementos.length==max
    */
   public PilaVectorImpl(int max) {
      elementos = (E[])new Object[max];
      n = 0;
   }

   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que contiene actualmente
    * @post $return==n && this == $old(this)
    */
   public int numElems() { return  n; }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    * @post $return==(n==0) && this == $old(this)
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * Método para comprobar si el contenedor está lleno.
    * @return  cierto o falso, según si está lleno o no lo está
    * @post $return==(n==max) && this == $old(this)
    */
   public boolean estaLleno() { return  ( n == elementos.length ); }

   /**
    * Añade un elemento a la pila, si ningún.
    * @exception ExcepcionContenedorLleno  si la pila está llena
    * @param elem  elemento que se quiere añadir en la pila
    * @pre !estaPle(), ExcepcionContenedorLleno
    * @post n == $old(n)+1 
    * 		&& vector[n-1] == elem
    * 		&& $all(y:vector,y == n-1 || $old(vector[y]) == vector(y))
    */
   public void apilar(E elem) {
	   if (estaLleno()) throw new ExcepcionContenedorLleno();
      elementos[n] = elem;
      n++;
   }

   /**
    * Borra el elemento de la cima de la pila, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la pila está vacía
    * @return  elemento que había en la cima de la pila
    * @pre !estaBuit(), ExcepcionContenedorVacio
    * @post n == $old(n)-1
    * 		&& $all(y:vector,y >= n || $old(vector[y]) == vector(y))
    */
   public E desapilar() {
	   if (estaVacio()) throw new ExcepcionContenedorVacio();
   		E aux=elementos[n-1];
   		elementos[n-1]=null;
   		n--;
   		return aux;
   }

   /**
    * Accesor de lectura del último elemento añadido en la pila, si hay.
    * @exception ExcepcionContenedorVacio  si la pila está vacía
    * @return  elemento de la cima de la pila
    * @pre !estaVacio()
    * @post $return == vector[n-1] && this==$old(this)
    */
   public E cima() {
      return  elementos[n-1];
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
    * @return  enumeración de los elementos del contenedor
    */
   public Iterador<E> elementos() {
   		return new IteradorVectorImpl<E>(elementos,numElems(),0);
   }

   /**
    * Método que sobrescribe Object.toString(). Saca los elementos separados
    * por comas.
    */
   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("{PILA:");
      for (Iterador it = elementos(); it.haySiguiente(); ) {
         buffer.append(it.siguiente());
         if (it.haySiguiente()) buffer.append(',');
      }
      buffer.append("}");
      return  buffer.toString();
   }
}
