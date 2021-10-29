package uoc.ei.tads;
/**
 * Secuencia posicional que se caracteriza por disponer de operaciones
 * basadas en la posici�n que ocupa un elemento dentro del contenedor.
 *
 * @see  Posicion
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ListaEncadenada<E>  implements Lista<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** N�mero de elementos que hay actualmente en el contenedor. */
   protected int n;

   /**
    * Posici�n del �ltimo elemento de la lista. En esta representaci�n
    * circular, la posici�n siguiente es la primera.
    */
   protected NodoEncadenado<E> ultimo;
   
   
   /** Construye una lista vac�a.
    */
   public ListaEncadenada() {
	   ultimo=null;
	   n=0;
   }


   /**
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  n; }

   
   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   
   /**
    * A�ade un elemento al principio de la lista.
    * @param elem  elemento que se quiere a�adir a la lista
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarAlPrincipio(E elem) {
      return  nuevaPosicion(ultimo, elem);
   }

   
   /**
    * A�ade un elemento al final de la lista.
    * @param elem  elemento que se quiere a�adir a la lista
    * @return  nueva posici�n que contiene el elemento
    */
   public Posicion<E> insertarAlFinal(E elem) {
      ultimo = nuevaPosicion(ultimo, elem);
	  return ultimo;
   }

   
   /**
    * A�ade un elemento antes de la posici�n recibida.
    * @param elem  elemento que se quiere a�adir a la lista
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  nueva posici�n que contiene el elemento
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
    * A�ade un elemento despu�s de la posici�n recibida.
    * @param elem  elemento que se quiere a�adir a la lista
    * @param nodo  posici�n de referencia
    * @pre nodo!=null, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  nueva posici�n que contiene el elemento
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
    * Borra la primera posici�n de la lista.
    * @pre estaVacio(), ExcepcionContenedorVacio
    * @exception ExcepcionContenedorVacio  si la lista est� vac�a
    * @return  elemento que hab�a en la posici�n
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
    * Borra la posici�n recibida.
    * @param nodo  posici�n que se quiere eliminar
    * @pre nodo!=null, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a en la posici�n
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
    * Borra la posici�n siguiente.
    * @param nodo  posici�n anterior a la que se quiere eliminar; si es null
    *              se elimina la primera posici�n
    * @pre !estaVacio() && nodo!=ultimo, ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  si la posici�n es no v�lida
    * @return  elemento que hab�a en la posici�n siguiente
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
    * Reemplaza el elemento contenido en la posici�n recibida.
    * @param elem  nuevo elemento
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  elemento que hab�a en la posici�n
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
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es null o no
    *            v�lida
    * @pre !estaVacio() && nodo1!=null && nodo2!=null, ExcepcionPosicionInvalida
    */
   public void intercambiar(Posicion<E> nodo1, Posicion<E> nodo2) {
      E tmp = nodo1.getElem();
      ((NodoEncadenado<E>)nodo1).setElem(nodo2.getElem());
      ((NodoEncadenado<E>)nodo2).setElem(tmp);
   }

   
   /**
    * Retorna el nodo anterior al recibido como par�metro. En esta lista
    * simplemente encadenada la operaci�n tiene un coste temporal lineal: O(n).
    * @param nodo  posici�n de referencia, no null
    * @return  nodo anterior al recibido como par�metro
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
    * Crea un nodo encadenado, le sit�a a continuaci�n del recibido como
    * par�metro e incrementa el n�mero de elementos. Se puede sobrescribir
    * por las subclases que necesiten trabajar con descendientes de
    * NodoEncadenado.
    * @param nodo  posici�n anterior a la del nuevo nodo; puede ser null
    * @param elem  elemento que se quiere a�adir a la lista
    * @return  nodo nuevo conteniendo el elemento recibido como par�metro; situado
    *          entre el nodo recibido como par�metro y su siguiente
    */
   protected NodoEncadenado<E> nuevaPosicion(NodoEncadenado<E> nodo, E elem) {
      NodoEncadenado<E> nuevoNodo = null;
      if (nodo == null) {                // ser� el �nico nodo
         nuevoNodo = new NodoEncadenado<E>(elem, null);
         nuevoNodo.setSeguiente(nuevoNodo);
         ultimo=nuevoNodo;
      }
      else {
         nuevoNodo = new NodoEncadenado<E>(elem, nodo.getSiguiente());
         nodo.setSeguiente(nuevoNodo);
      }
      n++;                              // incrementa el n�mero de elementos
      return  nuevoNodo;
   }

   
   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeraci�n. 
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * Si durante el recorrido se altera la estructura de posiciones del
    * contenedor, los resultados son imprevisibles.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe.
    * @return  enumeraci�n de los elementos del contenedor
    */
   public Iterador<E> elementos() { return  new IteradorRecorridoImpl<E,E>(posiciones()); }

   
   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @return  enumeraci�n de las posiciones del contenedor
    */
   public Recorrido<E> posiciones() { return  new RecorridoUnidireccionalLista<E>(this); }

   
   /**
    * Clase que implementa un nodo con un encadenamiento a nodo, que une un
    * nodo con el siguiente en una estructura de datos con encadenamiento simple.
    * Solo facilita las operaciones b�sicas: constructores y accesores de
    * lectura y de escritura. Puede almacenar cualquier elemento (Object).
    *
    * @author  Jordi �lvarez Canal
    * @author  Esteve Marin� Gallis�.
    *          Estructura de la Informaci�n,
    *          Universitat Oberta de Catalunya (UOC)
    * @version 2.0.0
    */
   protected static class NodoEncadenado<E>  implements Posicion<E> {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
   	
   	
      /** Elemento contenido al nodo. */
      private E elemento;

      /** Encadenamiento al nodo siguiente. */
      protected NodoEncadenado<E> sig;

      /** Constructor sin par�metros. Da valor nulo a los atributos. */
      public NodoEncadenado() {
      	  elemento = null;
      	  sig = null;
      }

      /**
       * Constructor con un par�metro.
       * @param elem  valor del elemento contenido en el nodo
       */
      public NodoEncadenado(E elem) {
         elemento = elem;
         sig = null;
      }

      /**
       * Constructor con dos par�metros.
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
       * M�todo que define la conversi�n del objeto a String para
       * facilitar la depuraci�n del c�digo. Delega en el m�todo toString() de
       * el elemento almacenado a la posici�n.
       * @return  cadena de caracteres representativa del elemento
       */
      public String toString() {
         return  "[NodoEncadenado: "+((elemento == null) ? "null" : elemento.toString())+"]";
      }
   }

   
   /**
    * Clase que proporciona un recorrido de las posiciones. Basada en el
    * pattern Iterator, soporta m�ltiples recorridos simult�neos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones y se actualiza de acuerdo con los cambios.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoUnidireccionalLista<E>  implements Recorrido<E> {

    private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
 	  /** Nodo actual. */
    protected NodoEncadenado<E> nodoActual;

	  /** �ltimo nodo de la lista. */
    protected NodoEncadenado<E> ultimo;

    /** puede haber siguiente (control del encadenamiento circular). */
      protected boolean tieneSiguiente;
      
      
      public RecorridoUnidireccionalLista(ListaEncadenada<E> llista) {
      	nodoActual=llista.ultimo;
      	ultimo=llista.ultimo;
      	tieneSiguiente=(nodoActual!=null);
      }

      /**
       * Comprueba si hay una primera o siguiente posici�n. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si est� vac�o o ya se ha visitado la �ltima posici�n.
       * @return  cierto o falso, seg�n si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  tieneSiguiente; }

      /**
       * Primero avanza, si se puede, y despu�s retorna la posici�n.
       * @exception ExcepcionPosicionInvalida  si la siguiente posici�n no
       *            existe
       * @return  siguiente posici�n
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
    * M�todo que sobrescribe Object.toString(). los elementos aparecen separados
    * por el salto de l�nea de la plataforma.
    */
   public String toString() {
	  return Utilidades.toStringContenedor("ListaEncadenada",new RecorridoUnidireccionalLista<E>(this));
   }

}
