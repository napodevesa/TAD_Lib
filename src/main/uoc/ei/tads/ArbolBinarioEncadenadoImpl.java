
package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de cualquiera arbol binario, el cual
 * se caracteriza para organizar sus elementos (nodos) formando una
 * jerarquía: todo nodo (excepto la raíz que se el jefe de la jerarquía) es
 * descendiente de un nodo único, y puede ser ascendente de un máximo de dos nodos
 * (cuando no tiene descendientes se nombra a hoja).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolBinarioEncadenadoImpl<E>  extends ArbolBinario<E> {
	   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
	    * serializables de la misma clase. El identificador se calcula
	    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Puntero a la raiz del arbol. */
   protected NodoArbol<E> root;


   /**
    * Accesor de lectura del número de elementos que hay al contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() {
      return  (root == null) ? 0 : root.numNodos();
   }

   
   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  ( root == null ); }


   /**
    * Accesor de lectura de la raíz del arbol, si hay.
    * @return  raíz del arbol, null en caso de que el arbol
    * esté vacío.
    */
   public Posicion<E> raiz()  throws  ExcepcionContenedorVacio {
      return  root;
   }


   /**
    * Añade un elemento como nuevo hijo de la posición recibida, si se puede.
    * Si el padre es null, se añade en la raíz; si es una hoja, se añade
    * como hijo izquierdo; de otro modo, se añade a la posición libre.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @exception ExcepcionPosicionInvalida  si no cabe ningún hijo más
    * @return  nuevo hijo; o la raíz, si el padre era null
    * @pre padre==null || nombreFills(padre)<2, ExcepcionPosicionInvalida
    */
   public Posicion<E> insertar(Posicion<E> padre, E elem) {
      NodoArbol<E> nuevo = crearNodo(padre,elem);
      if (padre == null)
		  root = nuevo;
      else {
         if (hijoIzquierdo(padre) == null)
         	setHijoIzquierdo(padre,nuevo);
         else
         	setHijoDerecho(padre,nuevo);
      }
      return  nuevo;
   }

   
   /**
    * Añade un elemento como hijo izquierdo de la posición recibida, si se
    * puede.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @exception ExcepcionPosicionInvalida  si alguna posición es null o no
    *            válida
    * @return  nuevo hijo izquierdo
    * @pre padre!=null && ((NodoArbol)padre).getHijoIzquierdo() == null, ExcepcionPosicionInvalida
    */
   public Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem) {
      NodoArbol<E> nuevo = crearNodo(padre,elem);
      setHijoIzquierdo(padre,nuevo);
      return  nuevo;
   }

   
   /**
    * Añade un elemento como hijo derecho de la posición recibida, si se puede.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @exception ExcepcionPosicionInvalida  si alguna posición es null o no
    *            válida
    * @return  nuevo hijo derecho
    * @pre padre!=null && ((NodoArbol)padre).getHijoDerecho() == null, ExcepcionPosicionInvalida
    */
   public Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem) {
      NodoArbol<E> nuevo = crearNodo(padre,elem);
      setHijoDerecho(padre,nuevo);
      return  nuevo;
   }

   
   /**
    * Accesor de lectura del hijo izquierdo de una posición del arbol.
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  hijo izquierdo de la posición recibida; o null si no tiene
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public Posicion<E> hijoIzquierdo(Posicion<E> nodo) {
      return  ((NodoArbol<E>)nodo).getHijoIzquierdo();
   }

   
   /**
    * Accesor de lectura del hijo derecho de una posición del arbol.
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  hijo derecho de la posición recibida; o null si no tiene
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public Posicion<E> hijoDerecho(Posicion<E> nodo) {
      return  ((NodoArbol<E>)nodo).getHijoDerecho();
   }

   
   /**
    * Borra el subárbol representado por la posición hijo, si se puede.
    * Si la posición del padre es null, borra el arbol entero.
    * @param padre  posición del padre; puede ser null
    * @param hijo  posición del hijo
    * @exception ExcepcionPosicionInvalida  si alguna posición es no válida
    * @pre hijo!=null, ExcepcionPosicionInvalida
    * @pre padre==null || hijoIzquierdo(padre)==hijo || hijoDerecho(padre)==hijo, ExcepcionPosicionInvalida
    */
   public void borrar(Posicion<E> padre, Posicion<E> hijo) {
      if (padre == null)
      	root = null;
      else {
         if (hijoIzquierdo(padre) == hijo)
         	setHijoIzquierdo(padre,null);
         else
         	setHijoDerecho(padre,null);
      }
   }

   
   /**
    * Reemplaza el elemento contenido a la posición recibida.
    * @param elem  nuevo elemento
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había a la posición
    * @pre nodo!=null, ExcepcionPosicionInvalida
    */
   public E reemplazar(Posicion<E> nodo, E elem) {
      E old = nodo.getElem();
      ((NodoArbol<E>)nodo).setElem(elem);
      return  old;
   }

   
   /**
    * Intercambia los elementos contenidos a las posiciones recibidas.
    * Las posiciones no se intercambian, sino que se modifica el
    * suyo contenido.
    * @param nodo1  primera de las dos posiciones de referencia
    * @param nodo2  segunda de las dos posiciones de referencia
    * @exception ExcepcionPosicionInvalida  si alguna posición es null o no
    *            válida
    * @pre nodo1!=null && nodo2!=null, ExcepcionPosicionInvalida
    */
   public void intercambiar(Posicion<E> nodo1, Posicion<E> nodo2) {
      E tmp = nodo1.getElem();
      ((NodoArbol<E>)nodo1).setElem(nodo2.getElem());
      ((NodoArbol<E>)nodo2).setElem(tmp);
   }

   
   /**
    * Reemplaza el subárbol representado por la posición hijo, si se puede.
    * Si la posición del padre es null, sustituye al arbol entero. Este
    * método auxiliar es utilizado
    * para borrar un nodo con menos de dos hijos.
    * @param padre  posición del padre; puede ser null
    * @param hijo  posición del hijo
    * @param nuevo  posición con el nuevo arbol o subárbol; puede ser null
    * @exception ExcepcionPosicionInvalida  si alguna posición es no válida
    * @pre hijo!=null, ExcepcionPosicionInvalida
    * @pre padre==null && hijo==root, ExcepcionPosicionInvalida
    * @pre padre!=null && (hijo==hijoIzquierdo(padre) || hijo==hijoDerecho(padre)), ExcepcionPosicionInvalida
    */
   protected void reemplazarSubarbol(Posicion<E> padre, Posicion<E> hijo, Posicion<E> nuevo) {
      if (padre == null)  root = (NodoArbol<E>)nuevo;
      else {
      	if (hijoIzquierdo(padre)==hijo)
      		setHijoIzquierdo(padre,(NodoArbol<E>)nuevo);
      	else
      		setHijoDerecho(padre,(NodoArbol<E>)nuevo);
      }
   }


   /**
    * Crea un nuevo nodo, con dos encadenamientos a nodo, que almacena un
    * elemento. El método debe ser sobrescrito por las subclases que necesiten
    * otro tipo de nodo.
    * @param elem  elemento que se quiere guardar al nodo
    * @return  nodo arbol binario nuevo que almacena el elemento
    */
   protected NodoArbol<E> crearNodo(Posicion<E> padre, E elem) {
      return  new NodoArbol<E>(elem);
   }

   
   private void setHijoIzquierdo(Posicion<E> nodo,NodoArbol<E> hijo) {
      ((NodoArbol<E>)nodo).setHijoIzquierdo(hijo);
   }

   
   private void setHijoDerecho(Posicion<E> nodo,NodoArbol<E> hijo) {
      ((NodoArbol<E>)nodo).setHijoDerecho(hijo);
   }

   
   /**
    * Clase que implementa un nodo con dos encadenamientos a nodo.
    * Solo facilita las operaciones básicas: constructores y accesores de
    * lectura y de escritura. Puede almacenar cualquiera elemento (Object).
    */
   protected static class NodoArbol<EN>  implements Posicion<EN> {
	      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
		   * serializables de la misma clase. El identificador se calcula
		   * mediante un método de la clase Utilidades. */
	  private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	 	
      /** Elemento contenido en el nodo. */
      protected EN elemento;

      /** Encadenamiento al hijo izquierdo. */
      protected NodoArbol<EN> hijoIzquierdo;

      /** Encadenamiento al hijo derecho. */
      protected NodoArbol<EN> hijoDerecho;

      /** Constructor sin parámetros. Da valor nulo a los atributos. */
      public NodoArbol() { super(); }

      /**
       * Constructor con un parámetro. Asigna el valor recibido al elemento
       * del nodo padre y da valor null a las posiciones hijas.
       * @param elem  valor del elemento que debe ir al nodo padre
       */
      public NodoArbol(EN elem) { elemento = elem; }

      /**
       * Constructor con tres parámetros.
       * @param hi  hijo izquierdo
       * @param elem  valor del elemento contenido en el nodo padre
       * @param hd  hijo derecho
       */
      public NodoArbol(NodoArbol<EN> hi, EN elem, NodoArbol<EN> hd) {
         setElem(elem);  hijoIzquierdo = hi;  hijoDerecho = hd;
      }

      /**
       * Cuenta recursivamente los nodos que contiene un arbol o subárbol.
       * @return  cero si está vacío; sino retorna el número de nodos
       * del subárbol izquierdo más el del subárbol derecho más uno (raíz)
       */
      public int numNodos() {
      	int nnodos=1;
      	NodoArbol<EN> hi=getHijoIzquierdo();
      	NodoArbol<EN> hd=getHijoDerecho();
      	if (hi!=null) nnodos+=hi.numNodos();
      	if (hd!=null) nnodos+=hd.numNodos();
      	return nnodos;
      }

      /**
       * Accesor de escritura del valor contenido al nodo.
       * @param elem  nuevo valor del elemento contenido al nodo
       */
      public void setElem(EN elem) { elemento = elem; }

      /**
       * Accesor de lectura del elemento contenido al nodo.
       * @return  elemento contenido al nodo
       */
      public EN getElem() { return  elemento; }

      /**
       * Accessor d'escriptura del hijo esquerre.
       * @param hi  valor per al hijo esquerre
       */
      public void setHijoIzquierdo(NodoArbol<EN> hi) { hijoIzquierdo = hi; }

      /**
       * Accesor de lectura del hijo izquierdo.
       * @return  hijo izquierdo
       */
      public NodoArbol<EN> getHijoIzquierdo() { return  hijoIzquierdo; }

      /**
       * Accesor de escritura del hijo derecho.
       * @param hd  valor para el hijo derecho
       */
      public void setHijoDerecho(NodoArbol<EN> hd) { hijoDerecho = hd; }

      /**
       * Accesor de lectura del hijo derecho.
       * @return  hijo derecho
       */
      public NodoArbol<EN> getHijoDerecho() { return  hijoDerecho; }

      /**
       * Método que define la conversión del objeto a String por
       * facilitar la depuración del código. Delega en el método toString()
       * del elemento almacenado en la posición.
       * @return  cadena de caracteres representativa del elemento.
       */
      public String toString() {
         return  (elemento == null) ? "null" : elemento.toString();
      }
   }

}
