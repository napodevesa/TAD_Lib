package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de las colas prioritarias (priority
 * queue) y se distingue porque los elementos no se insertan por el final,
 * sino que se ordenan según una prioridad que tienen asociada.
 * Las operaciones de supresión y consulta afectan siempre al elemento que tiene
 * una prioridad más pequeña. En consecuencia, los elementos deberán
 * permitir una operación de comparación de donde se pueda deducir la prioridad.
 *
 * En la representación secuencial del arbol mediante un montículo
 * se organizan los nodos del arbol dentro de de un vector de manera que los
 * hijos y el padre son accesibles aplicando solo una formula. La primera
 * posición del vector contendrá siempre la raíz del arbol que es,
 * precisamente, el elemento más pequeño; si un nodo está en la posición [k], su
 * izquierdo estará a [2*k] y su hijo derecho a [2*k+1].
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaConPrioridad<E>  implements Cola<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
   
	
	/** Clase que extiende el comportamiento de un arbol binario con dos métodos que nos
	 * proporcionan la funcionalidad de un arbol cuasicompleto.
	 * @param <E>
	 */
	
	protected static class ArbolBinarioQuasicompletoVectorImpl<E> extends ArbolBinarioVectorImpl<E> {

		   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
			
			
		   public ArbolBinarioQuasicompletoVectorImpl() { super(); }
		   public ArbolBinarioQuasicompletoVectorImpl(int max) { super(max); }
		   

		   /** Retorna el último elemento en un recorrido por niveles del arbol cuasicompleto.
		    * @pre !estaVacio(), ExcepcionContenedorVacio
		    * @return El último elemento en un recorrido por niveles.
		    */
		   public Posicion<E> ultimo() {
		   	  return elems[n-1]; 
		   }
		
		   /** Añade un elemento a continuación de la última posición del arbol en
		    * un recorrido por niveles. Mantiene su condición de arbol cuasicompleto.
		    * @param elem  elemento que se quiere añadir al arbol
		    * @pre !estaLleno(), ExcepcionContenedorLleno
		    * @return  nueva última posición
		    */
		   public Posicion<E> insertarUltimo(E elem) {
		   	  n++;
		      elems[n-1] = new Rango<E>(n-1, elem);
		      return ultimo();
		   }

		   /**
		    * Borra la última posición ocupada del arbol en un recorrido por
		    * niveles. Mantiene su condición de arbol cuasicompleto.
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
    * Constructor sin parámetros (capacidad máxima, por defecto) y
    * elementos de una clase que implementa java.lang.Comparable.
    */
   public ColaConPrioridad() {
   	  heap = new ArbolBinarioQuasicompletoVectorImpl<E>();
   }

   /**
    * Constructor con un parámetro (capacidad dada) y elementos de una
    * clase que implementa java.lang.Comparable.
    * @param max  número máximo de elementos que puede contener la cola
    */
   public ColaConPrioridad(int max) {
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>(max);
   }

   /**
    * Constructor con un parámetro (capacidad máxima, por defecto) y
    * elementos de una clase comparable con el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ColaConPrioridad(java.util.Comparator<E> comparador) {
      this.comparador = comparador;
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>();
   }

   /**
    * Constructor con dos parámetros (capacidad dada) y elementos de una
    * clase comparable con el comparador dado.
    * @param max  número máximo de elementos que puede contener la cola
    * @param comparador  comparador que permite deducir la prioridad
    */
   public ColaConPrioridad(int max, java.util.Comparator<E> comparador) {
      this.comparador = comparador;
      heap = new ArbolBinarioQuasicompletoVectorImpl<E>(max);
   }

   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  heap.numElems(); }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  heap.estaVacio(); }

   /**
    * Método para comprobar si el contenedor está lleno.
    * @return  cierto o falso, según si está lleno o no lo está
    */
   public boolean estaLleno() { return  heap.estaLleno(); }

   /**
    * Añade un elemento a la posición que le corresponde, si cabe.
    * @pre !heap.estaLleno(), ExcepcionContenedorLleno
    * @param elem elemento comparable que se quiere añadir a la cola
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
    * @exception ExcepcionContenedorVacio  si la cola está vacía
    * @return  primer elemento, que es el de menor prioridad
    */
   public E primero() {
	   if (estaVacio())
		   throw new ExcepcionContenedorVacio();
	   return  heap.raiz().getElem();
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
    * @return  enumeración de los elementos del contenedor inordre
    */
   public Iterador<E> elementos() { return  heap.elementos(); }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @return  enumeración de las posiciones del contenedor por nivel
    */
   public Recorrido posiciones() { return  heap.posiciones(); }

   /**
    * Método que sobrescribe Object.toString(). Treo los elementos separados
    * por el salto de línea de la plataforma.
    * @return  recorrido de los elementos en un recorrido por niveles
    */
   public String toString() { return  heap.toString(); }


   /**
    * Método protegido que compara los elementos contenidos en las posiciones
    * recibidas. Si el constructor no ha definido un comparador, se utiliza
    * java.lang.Comparable para deducir la prioridad entre ambos elementos.
    * @exception ExcepcionPosicionInvalida  si alguna posición es null o no
    *            válida
    * @exception ExcepcioElementsNoComparables  si alguno de los elementos
    *            contenidos en las posiciones es no comparable
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    * @pre posicioValida(pos1) && posicioValida(pos2)
    * @return  un entero negativo, cero o positivo, según si el elemento de la
    *          primera posición tiene menos, igual o más prioridad que
    *          el elemento de la segunda posición, de acuerdo con la
    *          implementación del comparador
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

   
   /** Método auxiliar que dada una posición del arbol que no está ordenada dentro del arbol y
    * que queremos ordenar, determina por cuál de sus dos hijos debería ser intercambiada
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
