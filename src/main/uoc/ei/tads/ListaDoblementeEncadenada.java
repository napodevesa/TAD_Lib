package uoc.ei.tads;

import uoc.ei.tads.RecorridoBidireccional.InicioRecorrido;

/**
 * Secuencia posicional que se caracteriza por disponer de operaciones
 * basadas en la posición que ocupa un elemento dentro del contenedor.
 *
 * Hereda de lista encadenada la estructura con un solo encadenamiento (nodo
 * siguiente) y añade un segundo para optimizar las operaciones que
 * necesitan conocer al nodo anterior dentro de la secuencia.
 *
 * @see  Posicion
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ListaDoblementeEncadenada<E>  extends ListaEncadenada<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Borra la primera posición de la lista.
    * @exception ExcepcionContenedorVacio  si la lista está vacía
    * @return  elemento que había en la posición
    * @pre !estaVacio(), ExcepcionContenedorVacio
    */
   public E borrarPrimero()  throws  ExcepcionContenedorVacio
   {
	  if (estaVacio()) throw new ExcepcionContenedorVacio();
   	  NodoDoblementeEncadenado<E> primero =(NodoDoblementeEncadenado<E>)ultimo.getSiguiente();
   	  if (n==1)
   	  	ultimo=null;
   	  else {
        NodoDoblementeEncadenado<E> segundo =(NodoDoblementeEncadenado<E>)primero.getSiguiente();
        eliminarElDelMedio((NodoDoblementeEncadenado<E>)ultimo,primero,segundo);
      }
   	  n--;
   	  return primero.getElem();
   }

   
   /**
    * Borra la posición recibida.
    * @param nodo  posición que se quiere eliminar
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  elemento que había a la posición
    * @pre !estaVacio() && nodo!=null, ExcepcionPosicionInvalida
    */
   public E borrar(Posicion<E> nodo)
   {
      NodoDoblementeEncadenado<E> actual = (NodoDoblementeEncadenado<E>)nodo;
   	  if (n==1)
   	  	ultimo=null;
   	  else {
         NodoDoblementeEncadenado<E> siguiente=(NodoDoblementeEncadenado<E>)actual.getSiguiente();
         NodoDoblementeEncadenado<E> anterior = actual.getAnterior();
         eliminarElDelMedio(anterior,actual,siguiente);
      }
   	  n--;
      return actual.getElem();
   }
   

   /** Borra la posición siguiente.
    * @param nodo posición anterior a la que se quiere eliminar; si es null
    * se elimina la primera posición.
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
         NodoDoblementeEncadenado<E> act = (NodoDoblementeEncadenado<E>)nodo;
         NodoDoblementeEncadenado<E> sig = (NodoDoblementeEncadenado<E>)act.getSiguiente();
         NodoDoblementeEncadenado<E> sigSeg = (NodoDoblementeEncadenado<E>)sig.getSiguiente();
         eliminarElDelMedio(act,sig,sigSeg);
         if (sig == ultimo)
         	ultimo = act;
         elementoBorrado=sig.getElem();
      }
      n--;
      return elementoBorrado;
   }

   
   /**
    * Retorna el nodo anterior al recibido como parámetro. En esta lista
    * doblemente encadenada la operación tiene un coste temporal u: O(1).
    * @param nodo  posición de referencia, no null
    * @return  nodo anterior al recibido como parámetro
    */
   protected NodoEncadenado<E> anterior(NodoEncadenado<E> nodo)
   {
      return  ((NodoDoblementeEncadenado<E>)nodo).getAnterior();
   }


   /** Método privado de ayuda que dados tres nodos situados de manera consecutiva a la lista,
    * elimina el del medio. Este método se utiliza por los métodos de borrado de nodos.
    * El método únicamente saca un elemento de la lista pero no actualiza el número de elementos
    * de esta.
    * @param anterior Elemento anterior.
    * @param central Elemento central, es el elemento a eliminar de la lista.
    * @param posterior Elemento posterior.
    */ 
   private void eliminarElDelMedio(NodoDoblementeEncadenado<E> anterior,NodoDoblementeEncadenado<E> central,NodoDoblementeEncadenado<E> posterior) {
  		// elimina el elemento central de la lista
	    anterior.setSeguiente(posterior);
	    posterior.setAnterior(anterior);
	    // elimina cualquier referencia del elemento central a un elemento de la lista
	    central.setAnterior(null);
	    central.setSeguiente(null);
	    if (central==ultimo)
	    	ultimo=anterior;
   }
   

   /**
    * Crea un nodo encadenado. Se puede sobrescribir por las subclases que
    * necesiten trabajar con descendientes de NodoEncadenado.
    * @param nodo  posición anterior a la del nuevo nodo; puede ser nulo
    * @param elem  elemento que se quiere añadir a la lista
    * @return  nodo nuevo conteniendo el elemento recibo como parámetro; situado
    *          entre el nodo recibo como parámetro y su siguiente
    */
   protected NodoEncadenado<E> nuevaPosicion(NodoEncadenado<E> nodo, E elem)
   {
      NodoDoblementeEncadenado<E> nuevoNodo = null;

      if (nodo == null)                 // será primero, último y único nodo
      {
         nuevoNodo = new NodoDoblementeEncadenado<E>(elem);
         nuevoNodo.setSeguiente(nuevoNodo);
         nuevoNodo.setAnterior(nuevoNodo);
         ultimo = nuevoNodo;
      }
      else
      {
         NodoDoblementeEncadenado<E> actual = (NodoDoblementeEncadenado<E>)nodo;
         NodoDoblementeEncadenado<E> siguiente = (NodoDoblementeEncadenado<E>)actual.getSiguiente();
         nuevoNodo = new NodoDoblementeEncadenado<E>(siguiente,elem,(NodoDoblementeEncadenado<E>)nodo);
         nodo.setSeguiente(nuevoNodo);
         siguiente.setAnterior(nuevoNodo);
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
    * @see  IteradorBidireccional#hayAnterior()
    * @see  IteradorBidireccional#anterior()
    * @param inicio  donde se inicia el recorrido; valores válidos son:
    *                - InicioRecorrido.PRINCIPIO y
    *                - InicioRecorrido.FINAL
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y no hay ningún o no hay ningún
    *            más
    * @return  enumeración de los elementos del contenedor en un sentido
    */
   public IteradorBidireccional<E> elementos(InicioRecorrido inicio)
   {
      return  new IteradorRecorridoBidireccionalImpl<E,E>(posiciones(inicio));
   }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @see  RecorridoBidireccional
    * @return  enumeración de las posiciones del contenedor
    */
   public RecorridoBidireccional<E> posiciones(InicioRecorrido inicio)
   {
      return  new RecorridoBidireccionalLista<E>(this,inicio);
   }


   /**
    * Clase que implementa un nodo con dos encadenamientos a nodo, al siguiente y al
    * anterior en una lista doblemente encadenada.. Solo facilita las operaciones
    * básicas: constructores y accessors de lectura y de escritura.
    *
    * @author  Jordi Àlvarez Canal
    * @author  Esteve Mariné Gallisà.
    *          Estructura de la Información,
    *          Universitat Oberta de Catalunya (UOC)
    * @version 2.0.0
    */
   protected static class NodoDoblementeEncadenado<E>  extends NodoEncadenado<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
   	
   	
      /** Encadenamiento al nodo anterior. */
      protected NodoDoblementeEncadenado<E> ant;


      /** Constructor sin parámetros. Da valor nulo a los atributos. */
      public NodoDoblementeEncadenado()
      {
      	  super();
      	  setAnterior(null);
      }

      
      /**
       * Constructor con un parámetro.
       * @param elem  valor del elemento contenido en el nodo
       */
      public NodoDoblementeEncadenado(E elem)
      {
      	  super(elem);
      	  setAnterior(null);
      }

      
      /**
       * Constructor con tres parámetros.
       * @param sig  encadenamiento al siguiente nodo
       * @param elem  valor del elemento contenido en el nodo
       * @param ant encadenamiento al nodo anterior
       */
      public NodoDoblementeEncadenado(NodoDoblementeEncadenado<E> sig, E elem,
                                    NodoDoblementeEncadenado<E> ant)
      {
      	  super(elem,sig);
      	  setAnterior(ant);
      }

      
      /**
       * Accesor de escritura del segundo encadenamiento (nodo anterior en una
       * secuencia, hijo derecho en un arbol binario, etc.)
       * @param nodo  valor para el nodo 2
       */
      public void setAnterior(NodoDoblementeEncadenado<E> nodo) { ant = nodo; }

      /**
       * Accesor de lectura del segundo encadenamiento (nodo anterior en una
       * secuencia, etc.)
       * @return  segundo encadenamiento a nodo
       */
      public NodoDoblementeEncadenado<E> getAnterior() { return  ant; }
   }

   
   /**
    * Clase que proporciona un recorrido de las posiciones. Basada en el
    * pattern Iterator, soporta múltiples recorridos simultáneos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones y se actualiza de acuerdo con los cambios.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    * @see  RecorridoBidireccional#hayAnterior()
    * @see  RecorridoBidireccional#anterior()
    */
   protected static class RecorridoBidireccionalLista<E> extends RecorridoUnidireccionalLista<E>
                                    implements RecorridoBidireccional<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** puede haber anterior (control del encadenamiento circular). */
      protected boolean tieneAnterior;

      /**
       * Constructor con un parámetro.
       * @param sentido  sentido del recorrido; valores válidos son:
       *                -IteradorBidireccional.NORMAL y
       *                -IteradorBidireccional.INVERSO
       */
      public RecorridoBidireccionalLista(ListaDoblementeEncadenada<E> lista,InicioRecorrido sentido) {
         super(lista);
         if (sentido == InicioRecorrido.PRINCIPIO)
          	tieneAnterior = false;
         else {
             tieneSiguiente = false;
             if (ultimo!=null)
             	nodoActual = ultimo.getSiguiente();
           	tieneAnterior=(nodoActual!=null);
          }
      }


      /**
       * Comprueba si hay una última o anterior posición. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si está vacío o ya se ha visitado la última posición. 
       * @return  cierto o falso, según si se puede ir hacia atràs o no se puede
       */
      public boolean hayAnterior()
      {
      	return  (ultimo != null) && tieneAnterior;
      }

      /**
       * Primero avanza, si se puede, y después retorna la posición.
       * @exception ExcepcionPosicionInvalida  si la siguiente posición no
       *            existe
       * @return  siguiente posición
       * @pre haySiguiente(), ExcepcionPosicionInvalida
       */
      public Posicion<E> siguiente() {
         tieneAnterior = true;
         return  super.siguiente();
      }


      /**
       * Primero retrocede, si se puede, y después retorna la posición.
       * @exception ExcepcionPosicionInvalida  si el anterior posición no
       *            existe
       * @return  anterior posición
       * @pre hayAnterior(), ExcepcionPosicionInvalida
       */
      public Posicion<E> anterior() {
    	  if (!hayAnterior())
    		  throw new ExcepcionPosicionInvalida();
    	  nodoActual = ((NodoDoblementeEncadenado<E>)nodoActual).getAnterior();
    	  // miramos si la posición anterior es la primera o no
    	  // con el objetivo de "marcar" si el nuevo nodo tiene anterior o no.
    	  tieneAnterior = (nodoActual != ultimo.getSiguiente());
    	  tieneSiguiente = true;
    	  return  nodoActual;
      }
   }
}
