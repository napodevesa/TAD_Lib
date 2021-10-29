package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el primer
 * elemento insertado: first-in-first-out (FIFO).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaVectorImpl<E>  implements Cola<E>, ContenedorAcotado<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad m�xima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /** Tabla de elementos del contenedor. */
   protected E[] elementos;

   /** N�mero de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Primer elemento de la cola. */
   private int  primero;

   /**
    * Constructor sin par�metros (capacidad m�xima, por defecto).
    * @post n == 0 && this.max == MAXIMO_ELEMENTOS_POR_DEFECTO
    */
   public ColaVectorImpl()
   {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un par�metro.
    * @exception ExcepcionParametroIncorrecto  si la capacidad m�xima de la
    *                                         nueva cola es negativa
    * @param max  n�mero m�ximo de elementos de la cola 
    * @pre max>=0
    * @post n == 0 && this.max == max
    */
   public ColaVectorImpl(int max)
   {
      elementos = (E[])new Object[max];
      n = 0;
      primero = 0;
   }
   
   
   private int posicion(int posicio) { return posicio % elementos.length; }
   private int siguiente(int posicio) { return (posicio+1)==elementos.length ? 0 : posicio+1; }

   
   /**
    * Accesor de lectura del n�mero de elementos que hay al contenedor.
    * @return  n�mero de elementos que contiene actualmente
    * @post $return == n && this == $old(this)
    */
   public int numElems() { return  n; }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    * @post $return == (n == 0) && this == $old(this)
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * M�todo para comprobar si el contenedor est� lleno.
    * @return  cierto o falso, seg�n si est� lleno o no lo est�
    * @post $return == (n == max) && this == $old(this)
    */
   public boolean estaLleno() { return  ( n == elementos.length ); }

   /**
    * A�ade un elemento a la cola, si cabe.
    * @exception ExcepcionContenedorLleno  si la cola est� llena
    * @param elem  elemento que se quiere a�adir a la cola
    * @pre !estaPle(), ExcepcionContenedorLleno
    * @post n == $old(n)+1 
    *  && primero == $old(primero)
    *  && ultimo == ($old(�ltimo) == max-1 ? 0 : $old(ultimo)+1)
    *  && vector[ultimo] == elem
    *  && $all(y:vector,y == ultimo || $old(vector[y]) == vector(y))
    */
   public void encolar(E elem)
   {
	  if (estaLleno()) throw new ExcepcionContenedorLleno();
   	  int ultimo = posicion(primero + n);
      elementos[ultimo] = elem;
   	  n++;
   }

   /**
    * Borra el primer elemento de la cola, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la cola est� vac�a
    * @return  primer elemento insertado a la cola
    * @pre !estaVacio(), ExcepcionContenedorVacio
    * @post n == $old(n)-1
    *  && ultimo == $old(ultimo)
    *  && primero == ($old(primero) == max-1 ? 0 : $old(primero)+1)
    *  && $all(y:vector,y == $old(primero) || $old(vector[y]) == vector(y))
    */
   public E desencolar()
   {
	  if (estaVacio()) throw new ExcepcionContenedorVacio();
      E elem = elementos[primero];
      elementos[primero]=null;					 // necesario para el Garbage Collector
      primero = siguiente(primero);
      n--;
      return  elem;
   }

   /**
    * Accesor de lectura del primer elemento a�adido a la cola, si hay.
    * @exception ExcepcionContenedorVacio  si la cola est� vac�a
    * @return  primer elemento de la cola
    * @pre !estaVacio()
    * @post $return == vector[primero] && this == $old(this)
    */
   public E primero()
   {
      return  elementos[primero];                    // retorna primero elemento
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
    *            elemento de la enumeraci�n y no hay tal elemento.
    * @return  enumeraci�n de los elementos del contenedor
    */
   public Iterador<E> elementos() {
		return new IteradorVectorImpl<E>(elementos,numElems(),primero);
}

   /**
    * M�todo que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de l�nea de la plataforma.
    */
   public String toString()
   {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{COLA:");
    for (Iterador<E> it = elementos(); it.haySiguiente(); ) {
       buffer.append(it.siguiente());
       if (it.haySiguiente()) buffer.append(',');
    }
    buffer.append("}");
    return  buffer.toString();
   }
}
