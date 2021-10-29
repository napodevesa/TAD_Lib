package uoc.ei.tads;
/**
 * Secuencia posicional que se caracteriza por disponer de operaciones
 * basadas en la posición que ocupa un elemento dentro del contenedor.
 *
 * @see  Posicion
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ListaEncadenada<E>  implements Lista<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Número de elementos que hay actualmente en el contenedor. */
   protected int n;

   /**
    * Posición del último elemento de la lista. En esta representación
    * circular, la posición siguiente es la primera.
    */
   protected NodoEncadenado<E> ultimo;
   
   
   /** Construye una lista vacía.
    */
   public ListaEncadenada() {
	   ultimo=null;
	   n=0;
   }


   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  n; }

   
   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   
   /**
    * Añade un elemento al principio de la lista.
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAlPrincipio(E elem) {
      return  nuevaPosicion(ultimo, elem);
   }

   
   /**
    * Añade un elemento al final de la lista.
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAlFinal(E elem) {
      ultimo = nuevaPosicion(ultimo, elem);
	  return ultimo;
   }

   
   /**
    * Añade un elemento antes de la posición recibida.
    * @param elem  elemento que se quiere añadir a la lista
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarAntesDe(Posicion<E> nodo, E elem) {
   	  Posicion<E> nuevoNodo;
      NodoEncadenado<E> ant = anterior((NodoEncadenado<E>)nodo);
      if (ant==ultimo)
    	  nuevoNodo=insertarAlPrincipio(elem);
      else
    	  nuevoNodo=insertarDespuesDe(ant,elem);
      return nuevoNodo;
   }

   
   /**
    * Añade un elemento después de la posición recibida.
    * @param elem  elemento que se quiere añadir a la lista
    * @param nodo  posición de referencia
    * @pre nodo!=null, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  nueva posición que contiene el elemento
    */
   public Posicion<E> insertarDespuesDe(Posicion<E> nodo, E elem) {
   	  Posicion<E> nuevoNodo;
   	  if (ultimo==nodo)
   		nuevoNodo=insertarAlFinal(elem);
   	  else
   		nuevoNodo=nuevaPosicion((NodoEncadenado<E>)nodo,elem);
   	  return nuevoNodo;
   }

   
   /**
    * Borra la primera posición de la lista.
    * @pre estaVacio(), ExcepcionContenedorVacio
    * @exception ExcepcionContenedorVacio  si la lista está vacía
    * @return  elemento que había en la posición
    */
   public E borrarPrimero() {
	   if (estaVacio())
		   throw new ExcepcionContenedorVacio();
      NodoEncadenado<E> primero = ultimo.getSiguiente();
      if (n == 1)
      	ultimo = null;
      else
      	ultimo.setSeguiente( primero.getSiguiente() );
      n--;
      return  primero.getElem();
   }

   
   /**
    * Borra la posición recibida.
    * @param nodo  posición que se quiere eliminar
    * @pre nodo!=null, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había en la posición
    */
   public E borrar(Posicion<E> nodo) {
      if (n == 1)
      	ultimo = null;
      else {
         NodoEncadenado<E> act = (NodoEncadenado<E>)nodo;
         NodoEncadenado<E> ant = anterior(act);
         ant.setSeguiente(act.getSiguiente());
         if (act == ultimo)  ultimo = ant;
      }
      n--;
      return  nodo.getElem();
   }

   
   /**
    * Borra la posición siguiente.
    * @param nodo  posición anterior a la que se quiere eliminar; si es null
    *              se elimina la primera posición
    * @pre !estaVacio() && nodo!=ultimo, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posición es no válida
    * @return  elemento que había en la posición siguiente
    */
   public E borrarSiguiente(Posicion<E> nodo) {
	   if (nodo==ultimo)
		   throw new ExcepcionPosicionInvalida();
   	  E elementoBorrado;
      if (nodo == null)
    	  elementoBorrado=borrarPrimero();
      else {
         NodoEncadenado<E> act = (NodoEncadenado<E>)nodo;
         NodoEncadenado<E> sig = act.getSiguiente();
         act.setSeguiente(sig.getSiguiente());
         if (sig == ultimo)  ultimo = act;  // si el siguiente era el ultimo
         elementoBorrado=sig.getElem();
         n--;                                          // descuenta el elemento
      }
      return elementoBorrado;
   }

   
   /**
    * Reemplaza el elemento contenido en la posición recibida.
    * @param elem  nuevo elemento
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había en la posición
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public E reemplazar(Posicion<E> nodo, E elem) {
      E old = nodo.getElem();
      ((NodoEncadenado<E>)nodo).setElem(elem);
      return  old;
   }

   
   /**
    * Intercambia los elementos contenidos a las posiciones recibidas.
    * @param nodo1  primera de las dos posiciones de referencia
    * @param nodo2  segunda de las dos posiciones de referencia
    * @exception ExcepcionPosicionInvalida  si alguna posición es null o no
    *            válida
    * @pre !estaVacio() && nodo1!=null && nodo2!=null, ExcepcionPosicionInvalida
    */
   public void intercambiar(Posicion<E> nodo1, Posicion<E> nodo2) {
      E tmp = nodo1.getElem();
      ((NodoEncadenado<E>)nodo1).setElem(nodo2.getElem());
      ((NodoEncadenado<E>)nodo2).setElem(tmp);
   }

   
   /**
    * Retorna el nodo anterior al recibido como parámetro. En esta lista
    * simplemente encadenada la operación tiene un coste temporal lineal: O(n).
    * @param nodo  posición de referencia, no null
    * @return  nodo anterior al recibido como parámetro
    * @pre numElems()>1
    */
   protected NodoEncadenado<E> anterior(NodoEncadenado<E> nodo) {
      NodoEncadenado<E> ant=ultimo;
      boolean encontrado=false;
      while (!encontrado) {
      	encontrado = ant.getSiguiente()==nodo;
      	if (!encontrado)
      		ant=ant.getSiguiente();
      }
      return  ant;
   }


   /**
    * Crea un nodo encadenado, le sitúa a continuación del recibido como
    * parámetro e incrementa el número de elementos. Se puede sobrescribir
    * por las subclases que necesiten trabajar con descendientes de
    * NodoEncadenado.
    * @param nodo  posición anterior a la del nuevo nodo; puede ser null
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nodo nuevo conteniendo el elemento recibido como parámetro; situado
    *          entre el nodo recibido como parámetro y su siguiente
    */
   protected NodoEncadenado<E> nuevaPosicion(NodoEncadenado<E> nodo, E elem) {
      NodoEncadenado<E> nuevoNodo = null;
      if (nodo == null) {                // será el único nodo
         nuevoNodo = new NodoEncadenado<E>(elem, null);
         nuevoNodo.setSeguiente(nuevoNodo);
         ultimo=nuevoNodo;
      }
      else {
         nuevoNodo = new NodoEncadenado<E>(elem, nodo.getSiguiente());
         nodo.setSeguiente(nuevoNodo);
      }
      n++;                              // incrementa el número de elementos
      return  nuevoNodo;
   }

   
   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeración. 
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * Si durante el recorrido se altera la estructura de posiciones del
    * contenedor, los resultados son imprevisibles.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y éste no existe.
    * @return  enumeración de los elementos del contenedor
    */
   public Iterador<E> elementos() { return  new IteradorRecorridoImpl<E,E>(posiciones()); }

   
   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @return  enumeración de las posiciones del contenedor
    */
   public Recorrido<E> posiciones() { return  new RecorridoUnidireccionalLista<E>(this); }

   
   /**
    * Clase que implementa un nodo con un encadenamiento a nodo, que une un
    * nodo con el siguiente en una estructura de datos con encadenamiento simple.
    * Solo facilita las operaciones básicas: constructores y accesores de
    * lectura y de escritura. Puede almacenar cualquier elemento (Object).
    *
    * @author  Jordi Àlvarez Canal
    * @author  Esteve Mariné Gallisà.
    *          Estructura de la Información,
    *          Universitat Oberta de Catalunya (UOC)
    * @version 2.0.0
    */
   protected static class NodoEncadenado<E>  implements Posicion<E> {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
   	
   	
      /** Elemento contenido al nodo. */
      private E elemento;

      /** Encadenamiento al nodo siguiente. */
      protected NodoEncadenado<E> sig;

      /** Constructor sin parámetros. Da valor nulo a los atributos. */
      public NodoEncadenado() {
      	  elemento = null;
      	  sig = null;
      }

      /**
       * Constructor con un parámetro.
       * @param elem  valor del elemento contenido en el nodo
       */
      public NodoEncadenado(E elem) {
         elemento = elem;
         sig = null;
      }

      /**
       * Constructor con dos parámetros.
       * @param ne  encadenamiento a nodo
       * @param elem  valor del elemento contenido en el nodo
       */
      public NodoEncadenado(E elem, NodoEncadenado<E> ne ) {
         elemento = elem;
         sig = ne;
      }

      /**
       * Accesor de escritura del valor contenido en el nodo.
       * @param elem  nuevo valor del elemento contenido en el nodo
       */
      public void setElem(E elem) { elemento = elem; }

      /**
       * Accesor de lectura del elemento contenido en el nodo.
       * @return  elemento contenido en el nodo
       */
      public E getElem() { return  elemento; }

      /**
       * Accesor de escritura del encadenamiento.
       * @param nodo  valor para el nodo
       */
      public void setSeguiente(NodoEncadenado<E> nodo) { sig = nodo; }

      /**
       * Accesor de lectura del nodo encadenado.
       * @return  encadenamiento a nodo
       */
      public NodoEncadenado<E> getSiguiente() { return  sig; }

      /**
       * Método que define la conversión del objeto a String para
       * facilitar la depuración del código. Delega en el método toString() de
       * el elemento almacenado a la posición.
       * @return  cadena de caracteres representativa del elemento
       */
      public String toString() {
         return  "[NodoEncadenado: "+((elemento == null) ? "null" : elemento.toString())+"]";
      }
   }

   
   /**
    * Clase que proporciona un recorrido de las posiciones. Basada en el
    * pattern Iterator, soporta múltiples recorridos simultáneos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones y se actualiza de acuerdo con los cambios.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoUnidireccionalLista<E>  implements Recorrido<E> {

    private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
 	  /** Nodo actual. */
    protected NodoEncadenado<E> nodoActual;

	  /** Último nodo de la lista. */
    protected NodoEncadenado<E> ultimo;

    /** puede haber siguiente (control del encadenamiento circular). */
      protected boolean tieneSiguiente;
      
      
      public RecorridoUnidireccionalLista(ListaEncadenada<E> llista) {
      	nodoActual=llista.ultimo;
      	ultimo=llista.ultimo;
      	tieneSiguiente=(nodoActual!=null);
      }

      /**
       * Comprueba si hay una primera o siguiente posición. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si está vacío o ya se ha visitado la última posición.
       * @return  cierto o falso, según si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  tieneSiguiente; }

      /**
       * Primero avanza, si se puede, y después retorna la posición.
       * @exception ExcepcionPosicionInvalida  si la siguiente posición no
       *            existe
       * @return  siguiente posición
       * @pre haySiguiente(), new ExcepcionPosicionInvalida("no hay siguiente")
       */
      public Posicion<E> siguiente() {
    	  if (!haySiguiente())
    		  throw new ExcepcionPosicionInvalida();
         nodoActual = nodoActual.getSiguiente();
         tieneSiguiente = (nodoActual != ultimo);
         return  nodoActual;
      }
   }

   /**
    * Método que sobrescribe Object.toString(). los elementos aparecen separados
    * por el salto de línea de la plataforma.
    */
   public String toString() {
	  return Utilidades.toStringContenedor("ListaEncadenada",new RecorridoUnidireccionalLista<E>(this));
   }

}
