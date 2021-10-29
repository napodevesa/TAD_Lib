package uoc.ei.tads;
/**
 * Secuencia que se caracteriza porque se consulta y se borra el primer
 * elemento insertado: first-in-first-out (FIFO).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaVectorImpl<E>  implements Cola<E>, ContenedorAcotado<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad máxima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /** Tabla de elementos del contenedor. */
   protected E[] elementos;

   /** Número de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Primer elemento de la cola. */
   private int  primero;

   /**
    * Constructor sin parámetros (capacidad máxima, por defecto).
    * @post n == 0 && this.max == MAXIMO_ELEMENTOS_POR_DEFECTO
    */
   public ColaVectorImpl()
   {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un parámetro.
    * @exception ExcepcionParametroIncorrecto  si la capacidad máxima de la
    *                                         nueva cola es negativa
    * @param max  número máximo de elementos de la cola 
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
    * Accesor de lectura del número de elementos que hay al contenedor.
    * @return  número de elementos que contiene actualmente
    * @post $return == n && this == $old(this)
    */
   public int numElems() { return  n; }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    * @post $return == (n == 0) && this == $old(this)
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * Método para comprobar si el contenedor está lleno.
    * @return  cierto o falso, según si está lleno o no lo está
    * @post $return == (n == max) && this == $old(this)
    */
   public boolean estaLleno() { return  ( n == elementos.length ); }

   /**
    * Añade un elemento a la cola, si cabe.
    * @exception ExcepcionContenedorLleno  si la cola está llena
    * @param elem  elemento que se quiere añadir a la cola
    * @pre !estaPle(), ExcepcionContenedorLleno
    * @post n == $old(n)+1 
    *  && primero == $old(primero)
    *  && ultimo == ($old(último) == max-1 ? 0 : $old(ultimo)+1)
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
    * @exception ExcepcionContenedorVacio  si la cola está vacía
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
    * Accesor de lectura del primer elemento añadido a la cola, si hay.
    * @exception ExcepcionContenedorVacio  si la cola está vacía
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
    * Retorna una enumeración.
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y no hay tal elemento.
    * @return  enumeración de los elementos del contenedor
    */
   public Iterador<E> elementos() {
		return new IteradorVectorImpl<E>(elementos,numElems(),primero);
}

   /**
    * Método que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de línea de la plataforma.
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
