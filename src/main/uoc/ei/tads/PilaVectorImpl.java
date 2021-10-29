package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el �ltimo
 * elemento insertado: last-in-first-out (LIFO).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class PilaVectorImpl<E>  implements Pila<E>, ContenedorAcotado<E>
{
	
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad m�xima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /**
    * N�mero de elementos que hay actualmente al contenedor. Tambi�n
    * representa la posici�n donde se debe empilar un nuevo elemento.
    */
   protected int n;

   /** Tabla de elementos del contenedor. Las posiciones empiezan por el cero.*/
   protected E[] elementos;

   /**
    * Constructor sin par�metros (capacidad m�xima, por defecto).
    * @post n == 0 && this.max == MAXIMO_ELEMENTOS_POR_DEFECTO && elementos.length==max
    */
   public PilaVectorImpl() {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un par�metro.
    * @exception ExcepcionParametroIncorrecto  si la capacidad m�xima de la
    *                                         nueva pila es negativa
    * @param max  n�mero m�ximo de elementos que puede contener 
    * @pre max>0
    * @post n == 0 && this.max == max && elementos.length==max
    */
   public PilaVectorImpl(int max) {
      elementos = (E[])new Object[max];
      n = 0;
   }

   /**
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que contiene actualmente
    * @post $return==n && this == $old(this)
    */
   public int numElems() { return  n; }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    * @post $return==(n==0) && this == $old(this)
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * M�todo para comprobar si el contenedor est� lleno.
    * @return  cierto o falso, seg�n si est� lleno o no lo est�
    * @post $return==(n==max) && this == $old(this)
    */
   public boolean estaLleno() { return  ( n == elementos.length ); }

   /**
    * A�ade un elemento a la pila, si ning�n.
    * @exception ExcepcionContenedorLleno  si la pila est� llena
    * @param elem  elemento que se quiere a�adir en la pila
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
    * @exception ExcepcionContenedorVacio  si la pila est� vac�a
    * @return  elemento que hab�a en la cima de la pila
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
    * Accesor de lectura del �ltimo elemento a�adido en la pila, si hay.
    * @exception ExcepcionContenedorVacio  si la pila est� vac�a
    * @return  elemento de la cima de la pila
    * @pre !estaVacio()
    * @post $return == vector[n-1] && this==$old(this)
    */
   public E cima() {
      return  elementos[n-1];
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
    * @return  enumeraci�n de los elementos del contenedor
    */
   public Iterador<E> elementos() {
   		return new IteradorVectorImpl<E>(elementos,numElems(),0);
   }

   /**
    * M�todo que sobrescribe Object.toString(). Saca los elementos separados
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
