package uoc.ei.ejemplos.modulo3.referencia;

import uoc.ei.tads.*;

/** Implementación encadenada de la colección Cola
 */
public class ColaEncadenadaImpl<E>  implements Cola<E> {

	private static final long serialVersionUID = 1;
	
   /** Número de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Primer elemento de la cola. */
   private Nodo<E> primero;

   /** Último elemento de la cola. */
   private Nodo<E> ultimo;

   public ColaEncadenadaImpl() {
	   primero=null;
	   ultimo=null;
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
    * Añade un elemento a la cola, si cabe.
    * @exception ExcepcionContenedorLleno  si la cola está llena
    * @param elem  elemento que se quiere añadir a la cola
    */
   public void encolar(E elem) {
	   Nodo<E> nuevo=new Nodo<E>(elem);
	   if (primero==null)
		   primero=nuevo;
	   else
		   ultimo.setSiguiente(nuevo);
	   ultimo=nuevo;
	   n++;
   }


   /**
    * Borra el primer elemento de la cola, si hay alguno.
    * @exception ExcepcionContenedorVacio  si la cola está vacía
    * @return  primer elemento insertado a la cola
    * @pre primero!=null
    */
   public E desencolar() {
	   Nodo<E> aux=primero;
	   primero=primero.getSiguiente();
	   if (primero==null)
		   ultimo=null;
	   n--;
	   return aux.getElem();
   }

   /**
    * Accesor de lectura del primer elemento añadido a la cola, si hay.
    * @exception ExcepcionContenedorVacio  si la cola está vacía
    * @return  primer elemento de la cola
    * @pre primero!=null
    */
   public E primero() {
	   return primero.getElem();
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * @return  enumeración de los elementos del contenedor
    */
   public Iterador<E> elementos() {
	   return new IteradorCola<E>(this);
   }

   /**
    * Clase que implementa un nodo con un encadenamiento a nodo, que une un
    * nodo con el siguiente en una estructura de datos con encadenamiento simple.
    */
   protected static class Nodo<EN>  implements Posicion<EN> {

      private static final long serialVersionUID = 1;
   	
   	
      /** Elemento contenido en el nodo. */
      private EN elemento;

      /** Encadenamiento al nodo siguiente. */
      private Nodo<EN> sig;

      public Nodo(EN elem) {
         elemento = elem;
         sig = null;
      }

      public EN getElem() { return  elemento; }
      public Nodo<EN> getSiguiente() { return  sig; }
      public void setSiguiente(Nodo<EN> nodo) { sig=nodo; }

   }
   
   
   /** Clase que proporciona un recorrido de las posiciones de la cola.
    */
   protected static class IteradorCola<ER>  implements Iterador<ER> {

    private static final long serialVersionUID = 1;
 	
	/** Siguiente nodo a visitar. */
    protected Nodo<ER> nodoActual;

    public IteradorCola(ColaEncadenadaImpl<ER> cola) {
      	nodoActual=cola.primero;
      }

	/** Comprueba si quedan posiciones para visitar.
	 * @return  cierto si quedan posiciones y falso de lo contrario.
	 */
	public boolean haySiguiente() { return  nodoActual!=null; }

	/** Retorna el siguiente nodo a visitar y avanza.
	 * @exception ExcepcionPosicionInvalida  si la siguiente posición no
	 *            existe
	 * @return  siguiente posición.
	 * @pre haySiguiente(), new ExcepcionPosicionInvalida("no hay siguiente")
	 */
	public ER siguiente() {
		Nodo<ER> aux=nodoActual;
		nodoActual = nodoActual.getSiguiente();
		return  aux.getElem();
	}
   }
   
}
