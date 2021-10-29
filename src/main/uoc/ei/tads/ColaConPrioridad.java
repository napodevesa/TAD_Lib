package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de las colas prioritarias (priority
 * queue) y se distingue porque los elementos no se insertan por el final,
 * sino que se ordenan seg�n una prioridad que tienen asociada.
 * Las operaciones de supresi�n y consulta afectan siempre al elemento que tiene
 * una prioridad m�s peque�a. En consecuencia, los elementos deber�n
 * permitir una operaci�n de comparaci�n de donde se pueda deducir la prioridad.
 *
 * En la representaci�n secuencial del arbol mediante un mont�culo
 * se organizan los nodos del arbol dentro de de un vector de manera que los
 * hijos y el padre son accesibles aplicando solo una formula. La primera
 * posici�n del vector contendr� siempre la ra�z del arbol que es,
 * precisamente, el elemento m�s peque�o; si un nodo est� en la posici�n [k], su
 * izquierdo estar� a [2*k] y su hijo derecho a [2*k+1].
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaConPrioridad<E>  implements Cola<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
   
	
	/** Clase que extiende el comportamiento de un arbol binario con dos m�todos que nos
	 * proporcionan la funcionalidad de un arbol cuasicompleto.
	 * @param <E>
	 */
	
	protected static class ArbolBinarioQuasicompletoVectorImpl<E> extends ArbolBinarioVectorImpl<E> {

		   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
			
			
		   public ArbolBinarioQuasicompletoVectorImpl() { super(); }
		   public ArbolBinarioQuasicompletoVectorImpl(int max) { super(max); }
		   

		   /** Retorna el �ltimo elemento en un recorrido por niveles del arbol cuasicompleto.
		    * @pre !estaVacio(), ExcepcionContenedorVacio
		    * @return El �ltimo elemento en un recorrido por niveles.
		    */
		   public Posicion<E> ultimo() {
		   	  return elems[n-1]; 
		   }
		
		   /** A�ade un elemento a continuaci�n de la �ltima posici�n del arbol en
		    * un recorrido por niveles. Mantiene su condici�n de arbol cuasicompleto.
		    * @param elem  elemento que se quiere a�adir al arbol
		    * @pre !estaLleno(), ExcepcionContenedorLleno
		    * @return  nueva �ltima posici�n
		    */
		   public Posicion<E> insertarUltimo(E elem) {
		   	  n++;
		      elems[n-1] = new Rango<E>(n-1, elem);
		      return ultimo();
		   }

		   /**
		    * Borra la �ltima posici�n ocupada del arbol en un recorrido por
		    * niveles. Mantiene su condici�n de arbol cuasicompleto.
		    * @pre !estaVacio(), ExcepcionContenedorVacio
		    * @return El elemento borrado
		    */
		   public Posicion<E> borrarUltimo() {
		   	  n--;
		   	  Posicion<E> aux=elems[n];
		      elems[n] = null;
		      return  aux;
		   }

	}
	
	
	
   /** Arbol binario vector que sirve de apoyo a la cola prioritaria. */
   protected ArbolBinarioQuasicompletoVectorImpl<E> heap;

   /**
    * Comparador concreto que permite deducir la prioridad entre los
    * elementos. Puede tener valor null y entonces se utiliza la interfaz
    * java.lang.Comparable
    */
   protected java.util.Comparator<E> comparador;

   /**
    * Constructor sin par�metros (capacidad m�xima, por defecto) y
    * elementos de una clase que implementa java.lang.Comparable.
    */
   public ColaConPrioridad() {
   	  heap = new ArbolBinarioQuasicompletoVectorImpl<E>();
   }

   /**
    * Constructor con un par�metro (capacidad dada) y elementos de una
    * clase que implementa java.lang.Comparable.
    * @param max  n�mero m�ximo de elementos que puede contener la cola
    */
   public ColaConPrioridad(int max) {
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>(max);
   }

   /**
    * Constructor con un par�metro (capacidad m�xima, por defecto) y
    * elementos de una clase comparable con el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ColaConPrioridad(java.util.Comparator<E> comparador) {
      this.comparador = comparador;
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>();
   }

   /**
    * Constructor con dos par�metros (capacidad dada) y elementos de una
    * clase comparable con el comparador dado.
    * @param max  n�mero m�ximo de elementos que puede contener la cola
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ColaConPrioridad(int max, java.util.Comparator<E> comparador) {
      this.comparador = comparador;
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>(max);
   }

   /**
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  heap.numElems(); }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  heap.estaVacio(); }

   /**
    * M�todo para comprobar si el contenedor est� lleno.
    * @return  cierto o falso, seg�n si est� lleno o no lo est�
    */
   public boolean estaLleno() { return  heap.estaLleno(); }

   /**
    * A�ade un elemento a la posici�n que le corresponde, si cabe.
    * @pre !heap.estaLleno(), ExcepcionContenedorLleno
    * @param elem elemento comparable que se quiere a�adir a la cola
    */
   public void encolar(E elem) {
   	  subirElemento(nuevaUltimaPosicion(elem));
   }
   
   
   protected Posicion<E> nuevaUltimaPosicion(E elem) {
	   return heap.insertarUltimo(elem);
   }
   
   
   protected void subirElemento(Posicion<E> nuevaPosicion) {
   	  boolean encontrado = false;
   	  while (!encontrado) {
   	  	 Posicion<E> padre=heap.padre(nuevaPosicion);
   	  	 encontrado= padre==null || comparar(nuevaPosicion,padre)>=0;
   	  	 if (!encontrado)
   	  	 	heap.intercambiar(nuevaPosicion,padre);
   	  }
   }
   

   /**
    * Borra el elemento de menor prioridad, si hay alguno.
    * @see  ColaConPrioridad#escogeHijoAIntercambiar(Posicion)
    * @pre !heap.estaVacio(), ExcepcionContenedorVacio
    * @return  primer elemento, que es el de menor prioridad
    */
   public E desencolar() {
	   if (estaVacio())
		   throw new ExcepcionContenedorVacio();
 	  E primer = heap.raiz().getElem();
 	  heap.intercambiar(heap.raiz(),heap.ultimo());
 	  borrarUltimaPosicion();
 	  hundirElemento(heap.raiz());
 	  return primer;
   }
   
   
   protected Posicion<E> borrarUltimaPosicion() {
	   return heap.borrarUltimo();
   }
   
   
   protected void hundirElemento(Posicion<E> posicioAOrdenar) {
 	  boolean encontrado=false;
 	  while (!encontrado) {
  	  	 Posicion<E> hijoAIntercambiar=escogeHijoAIntercambiar(posicioAOrdenar);
  	  	 encontrado = hijoAIntercambiar==null || comparar(posicioAOrdenar,hijoAIntercambiar)<=0;
  	  	 if (!encontrado) 
   	  	 	heap.intercambiar(posicioAOrdenar,hijoAIntercambiar);
 	  }
   }


   /**
    * Accesor de lectura del elemento de menor prioridad, si hay.
    * Si se quiere desencolar el objeto se debe borrar seguidamente.
    * @pre !estaVacio(), ExcepcionContenedorVacio
    * @exception ExcepcionContenedorVacio  si la cola est� vac�a
    * @return  primer elemento, que es el de menor prioridad
    */
   public E primero() {
	   if (estaVacio())
		   throw new ExcepcionContenedorVacio();
	   return  heap.raiz().getElem();
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
    * @return  enumeraci�n de los elementos del contenedor inordre
    */
   public Iterador<E> elementos() { return  heap.elementos(); }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @return  enumeraci�n de las posiciones del contenedor por nivel
    */
   public Recorrido posiciones() { return  heap.posiciones(); }

   /**
    * M�todo que sobrescribe Object.toString(). Treo los elementos separados
    * por el salto de l�nea de la plataforma.
    * @return  recorrido de los elementos en un recorrido por niveles
    */
   public String toString() { return  heap.toString(); }


   /**
    * M�todo protegido que compara los elementos contenidos en las posiciones
    * recibidas. Si el constructor no ha definido un comparador, se utiliza
    * java.lang.Comparable para deducir la prioridad entre ambos elementos.
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es null o no
    *            v�lida
    * @exception ExcepcioElementsNoComparables  si alguno de los elementos
    *            contenidos en las posiciones es no comparable
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    * @pre posicioValida(pos1) && posicioValida(pos2)
    * @return  un entero negativo, cero o positivo, seg�n si el elemento de la
    *          primera posici�n tiene menos, igual o m�s prioridad que
    *          el elemento de la segunda posici�n, de acuerdo con la
    *          implementaci�n del comparador
    */
   protected int comparar(Posicion<E> pos1, Posicion<E> pos2) {
      E elem1 = pos1.getElem();
      E elem2 = pos2.getElem();
      int comp = 0;
      if (comparador == null)
         comp = ((Comparable<E>)elem1).compareTo(elem2);
      else
      	comp = comparador.compare(elem1, elem2);
      return  comp;
   }

   
   /** M�todo auxiliar que dada una posici�n del arbol que no est� ordenada dentro del arbol y
    * que queremos ordenar, determina por cu�l de sus dos hijos deber�a ser intercambiada
    * (caso de producirse este intercambio.
    * @param pos
    * @return
    */
	private Posicion<E> escogeHijoAIntercambiar(Posicion<E> pos) {
	   Posicion<E> hijoDerecho=heap.hijoDerecho(pos);
	   Posicion<E> hijoIzquierdo=heap.hijoIzquierdo(pos);
	   if (hijoDerecho==null) return hijoIzquierdo;
	   if (hijoIzquierdo==null) return hijoDerecho;
	   return comparar(hijoIzquierdo,hijoDerecho)<0 ? hijoIzquierdo : hijoDerecho;
	}

	  	 
}
