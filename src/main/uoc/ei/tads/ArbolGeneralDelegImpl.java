package uoc.ei.tads;
/**
 * Les arboles son estructuras que relacionan sus elementos, llamados
 * nodos, formando jerarquías: todo nodo (excepto la raíz) es descendiente
 * de un nodo único, y puede ser ascendente
 * de otros nodos (cuando no tiene descendientes se nombra a hoja). Cuando un nodo
 * puede tener un número indeterminado de hijos hablamos de árboles generales
 * (general tree) y, si tiene un número fijo N, de árboles de orden N (n-ary
 * tree); en estos últimos destaca el caso de N = 2, les llamados árboles
 * binarios (binary tree).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolGeneralDelegImpl<E>  extends ArbolAbstracto<E> {
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario que implementa las operaciones por delegación. */
   protected ArbolBinarioEncadenadoImpl<E> ab;
   
   
   public ArbolGeneralDelegImpl() {
	   ab = crearArbolRepresentacion();
   }
   
   
   protected ArbolBinarioEncadenadoImpl<E> crearArbolRepresentacion() {
	   return new ArbolBinarioEncadenadoImpl<E>();
   }


   /**
    * Accesor de lectura del número de elementos que hay en el contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  ab.numElems(); }


   /**
    * Accesor de lectura de la raíz del arbol, si hay.
    * @return  raíz del arbol
    */
   public Posicion<E> raiz() { return  ab.raiz(); }

   /**
    * Método que soporta múltiples recorridos, de las posiciones hijas
    * de la posición de referencia, simultáneos e independientes entre ellos.
    * @param padre  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  enumeración de las posiciones hijas
    */
   public Recorrido<E> hijos(Posicion<E> padre)
   {
      if (estaVacio() || (padre == null))
         throw  new ExcepcionPosicionInvalida();

      return new RecorridoHijos<E>(this,padre);
   }

   /**
    * Añade un elemento como nuevo hijo de la posició recibida, si se puede.
    * Si el padre es null, se añade en la raíz; si es una hoja, se añade
    * como primer hijo (hijo más a la izquierda);  sino, se añade a
    * la primera posición libre (de izquierda a derecha).
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @return  nuevo hijo; o la raíz, si el padre era null
    */
   public Posicion<E> insertar(Posicion<E> padre, E elem)
   {
      if ( (padre == null) || esHoja(padre) )
         return  ab.insertar(padre, elem);

      padre = ab.hijoIzquierdo(padre);
      while (ab.hijoDerecho(padre) != null)  padre = ab.hijoDerecho(padre);

      return  ab.insertarHijoDerecho(padre, elem);
   }

   /**
    * Reemplaza el elemento contenido en la posición rebuda.
    * @param elem  nuevo elemento
    * @param pos  posición de referencia
    * @return  elemento que había en la posición
    */
   public E reemplazar(Posicion<E> pos, E elem)
   {
      return  ab.reemplazar(pos, elem);
   }

   /**
    * Intercambia los elementos contenidos en las las posiciones recibidas.
    * Las posiciones no son intercambiadas.
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    */
   public void intercambiar(Posicion<E> pos1, Posicion<E> pos2)
   {
      ab.intercambiar(pos1, pos2);
   }

   /**
    * Borra el subárbol representado por la posición hijo, si se puede.
    * Si la posición del padre es null, borra el arbol entero.
    * @param padre  posición del padre; puede ser null
    * @param hijo  posición del hijo
    * @exception ExcepcionPosicionInvalida  si alguna posición es no válida
    */
   public void borrar(Posicion<E> padre, Posicion<E> hijo)
               throws  ExcepcionPosicionInvalida
   {
      if (estaVacio() || (hijo == null))
         throw  new ExcepcionPosicionInvalida();

      if (padre == null)  ab.borrar(padre, hijo);
      else
      {
         if (hijo != ab.hijoIzquierdo(padre))
         {
            for ( padre = ab.hijoIzquierdo(padre);
                  ab.hijoDerecho(padre) != hijo;
                  padre = ab.hijoDerecho(padre) );
         }
         ab.reemplazarSubarbol(padre, hijo, ab.hijoDerecho(hijo));
      }
   }

   /**
    * Clase que proporciona un recorrido de las posiciones. Basada en el
    * pattern Iterator, soporta múltiples recorridos simultáneos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoHijos<E> implements Recorrido<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();

      
      /** Arbol que se está recorriendo. */
      protected ArbolGeneralDelegImpl<E> arbol;

      
      /** Posición auxiliar. */
      protected Posicion<E> hijo;


      /**
       * Constructor.
       * @param padre  posicio de referencia; se espera que sea una
       *              posición válida
       */
      public RecorridoHijos(ArbolGeneralDelegImpl<E> arbol,Posicion<E> padre) {
      	 this.arbol=arbol;
         hijo = arbol.ab.hijoIzquierdo(padre);
      }

      
      /**
       * Comprueba si hay una primera o siguiente posición. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si está vacío o ya se ha visitado la última posición.
       * @return  cierto o falso, según si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  hijo != null; }

      
      /**
       * Primero avanza, si se puede, y después retorna la posición.
       * Si no hay siguiente posición lanza una excepción.
       * @return  siguiente posición
       */
      public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida {
          if ( ! haySiguiente() )  throw new ExcepcionPosicionInvalida(
                                           "No hay siguiente");

         Posicion<E> temp = hijo;
         hijo = arbol.ab.hijoDerecho(hijo);
         return  temp;
      }
   }
}
