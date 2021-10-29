
package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de cualquiera arbol binario, el cual
 * se caracteriza para organizar sus elementos (nodos) formando una
 * jerarqu�a: todo nodo (excepto la ra�z que se el jefe de la jerarqu�a) es
 * descendiente de un nodo �nico, y puede ser ascendente de un m�ximo de dos nodos
 * (cuando no tiene descendientes se nombra a hoja).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolBinarioEncadenadoImpl<E>  extends ArbolBinario<E> {
	   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
	    * serializables de la misma clase. El identificador se calcula
	    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Puntero a la raiz del arbol. */
   protected NodoArbol<E> root;


   /**
    * Accesor de lectura del n�mero de elementos que hay al contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() {
      return  (root == null) ? 0 : root.numNodos();
   }

   
   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  ( root == null ); }


   /**
    * Accesor de lectura de la ra�z del arbol, si hay.
    * @return  ra�z del arbol, null en caso de que el arbol
    * est� vac�o.
    */
   public Posicion<E> raiz()  throws  ExcepcionContenedorVacio {
      return  root;
   }


   /**
    * A�ade un elemento como nuevo hijo de la posici�n recibida, si se puede.
    * Si el padre es null, se a�ade en la ra�z; si es una hoja, se a�ade
    * como hijo izquierdo; de otro modo, se a�ade a la posici�n libre.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @exception ExcepcionPosicionInvalida  si no cabe ning�n hijo m�s
    * @return  nuevo hijo; o la ra�z, si el padre era null
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
    * A�ade un elemento como hijo izquierdo de la posici�n recibida, si se
    * puede.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es null o no
    *            v�lida
    * @return  nuevo hijo izquierdo
    * @pre padre!=null && ((NodoArbol)padre).getHijoIzquierdo() == null, ExcepcionPosicionInvalida
    */
   public Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem) {
      NodoArbol<E> nuevo = crearNodo(padre,elem);
      setHijoIzquierdo(padre,nuevo);
      return  nuevo;
   }

   
   /**
    * A�ade un elemento como hijo derecho de la posici�n recibida, si se puede.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es null o no
    *            v�lida
    * @return  nuevo hijo derecho
    * @pre padre!=null && ((NodoArbol)padre).getHijoDerecho() == null, ExcepcionPosicionInvalida
    */
   public Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem) {
      NodoArbol<E> nuevo = crearNodo(padre,elem);
      setHijoDerecho(padre,nuevo);
      return  nuevo;
   }

   
   /**
    * Accesor de lectura del hijo izquierdo de una posici�n del arbol.
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  hijo izquierdo de la posici�n recibida; o null si no tiene
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public Posicion<E> hijoIzquierdo(Posicion<E> nodo) {
      return  ((NodoArbol<E>)nodo).getHijoIzquierdo();
   }

   
   /**
    * Accesor de lectura del hijo derecho de una posici�n del arbol.
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  hijo derecho de la posici�n recibida; o null si no tiene
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public Posicion<E> hijoDerecho(Posicion<E> nodo) {
      return  ((NodoArbol<E>)nodo).getHijoDerecho();
   }

   
   /**
    * Borra el sub�rbol representado por la posici�n hijo, si se puede.
    * Si la posici�n del padre es null, borra el arbol entero.
    * @param padre  posici�n del padre; puede ser null
    * @param hijo  posici�n del hijo
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es no v�lida
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
    * Reemplaza el elemento contenido a la posici�n recibida.
    * @param elem  nuevo elemento
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a a la posici�n
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
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es null o no
    *            v�lida
    * @pre nodo1!=null && nodo2!=null, ExcepcionPosicionInvalida
    */
   public void intercambiar(Posicion<E> nodo1, Posicion<E> nodo2) {
      E tmp = nodo1.getElem();
      ((NodoArbol<E>)nodo1).setElem(nodo2.getElem());
      ((NodoArbol<E>)nodo2).setElem(tmp);
   }

   
   /**
    * Reemplaza el sub�rbol representado por la posici�n hijo, si se puede.
    * Si la posici�n del padre es null, sustituye al arbol entero. Este
    * m�todo auxiliar es utilizado
    * para borrar un nodo con menos de dos hijos.
    * @param padre  posici�n del padre; puede ser null
    * @param hijo  posici�n del hijo
    * @param nuevo  posici�n con el nuevo arbol o sub�rbol; puede ser null
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es no v�lida
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
    * elemento. El m�todo debe ser sobrescrito por las subclases que necesiten
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
    * Solo facilita las operaciones b�sicas: constructores y accesores de
    * lectura y de escritura. Puede almacenar cualquiera elemento (Object).
    */
   protected static class NodoArbol<EN>  implements Posicion<EN> {
	      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
		   * serializables de la misma clase. El identificador se calcula
		   * mediante un m�todo de la clase Utilidades. */
	  private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	 	
      /** Elemento contenido en el nodo. */
      protected EN elemento;

      /** Encadenamiento al hijo izquierdo. */
      protected NodoArbol<EN> hijoIzquierdo;

      /** Encadenamiento al hijo derecho. */
      protected NodoArbol<EN> hijoDerecho;

      /** Constructor sin par�metros. Da valor nulo a los atributos. */
      public NodoArbol() { super(); }

      /**
       * Constructor con un par�metro. Asigna el valor recibido al elemento
       * del nodo padre y da valor null a las posiciones hijas.
       * @param elem  valor del elemento que debe ir al nodo padre
       */
      public NodoArbol(EN elem) { elemento = elem; }

      /**
       * Constructor con tres par�metros.
       * @param hi  hijo izquierdo
       * @param elem  valor del elemento contenido en el nodo padre
       * @param hd  hijo derecho
       */
      public NodoArbol(NodoArbol<EN> hi, EN elem, NodoArbol<EN> hd) {
         setElem(elem);  hijoIzquierdo = hi;  hijoDerecho = hd;
      }

      /**
       * Cuenta recursivamente los nodos que contiene un arbol o sub�rbol.
       * @return  cero si est� vac�o; sino retorna el n�mero de nodos
       * del sub�rbol izquierdo m�s el del sub�rbol derecho m�s uno (ra�z)
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
       * M�todo que define la conversi�n del objeto a String por
       * facilitar la depuraci�n del c�digo. Delega en el m�todo toString()
       * del elemento almacenado en la posici�n.
       * @return  cadena de caracteres representativa del elemento.
       */
      public String toString() {
         return  (elemento == null) ? "null" : elemento.toString();
      }
   }

}
