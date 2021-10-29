package uoc.ei.tads;
/**
 * Les arboles son estructuras que relacionan sus elementos, llamados
 * nodos, formando jerarqu�as: todo nodo (excepto la ra�z) es descendiente
 * de un nodo �nico, y puede ser ascendente
 * de otros nodos (cuando no tiene descendientes se nombra a hoja). Cuando un nodo
 * puede tener un n�mero indeterminado de hijos hablamos de �rboles generales
 * (general tree) y, si tiene un n�mero fijo N, de �rboles de orden N (n-ary
 * tree); en estos �ltimos destaca el caso de N = 2, les llamados �rboles
 * binarios (binary tree).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolGeneralDelegImpl<E>  extends ArbolAbstracto<E> {
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Arbol binario que implementa las operaciones por delegaci�n. */
   protected ArbolBinarioEncadenadoImpl<E> ab;
   
   
   public ArbolGeneralDelegImpl() {
	   ab = crearArbolRepresentacion();
   }
   
   
   protected ArbolBinarioEncadenadoImpl<E> crearArbolRepresentacion() {
	   return new ArbolBinarioEncadenadoImpl<E>();
   }


   /**
    * Accesor de lectura del n�mero de elementos que hay en el contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  ab.numElems(); }


   /**
    * Accesor de lectura de la ra�z del arbol, si hay.
    * @return  ra�z del arbol
    */
   public Posicion<E> raiz() { return  ab.raiz(); }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones hijas
    * de la posici�n de referencia, simult�neos e independientes entre ellos.
    * @param padre  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  enumeraci�n de las posiciones hijas
    */
   public Recorrido<E> hijos(Posicion<E> padre)
   {
      if (estaVacio() || (padre == null))
         throw  new ExcepcionPosicionInvalida();

      return new RecorridoHijos<E>(this,padre);
   }

   /**
    * A�ade un elemento como nuevo hijo de la posici� recibida, si se puede.
    * Si el padre es null, se a�ade en la ra�z; si es una hoja, se a�ade
    * como primer hijo (hijo m�s a la izquierda);  sino, se a�ade a
    * la primera posici�n libre (de izquierda a derecha).
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @return  nuevo hijo; o la ra�z, si el padre era null
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
    * Reemplaza el elemento contenido en la posici�n rebuda.
    * @param elem  nuevo elemento
    * @param pos  posici�n de referencia
    * @return  elemento que hab�a en la posici�n
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
    * Borra el sub�rbol representado por la posici�n hijo, si se puede.
    * Si la posici�n del padre es null, borra el arbol entero.
    * @param padre  posici�n del padre; puede ser null
    * @param hijo  posici�n del hijo
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es no v�lida
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
    * pattern Iterator, soporta m�ltiples recorridos simult�neos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoHijos<E> implements Recorrido<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();

      
      /** Arbol que se est� recorriendo. */
      protected ArbolGeneralDelegImpl<E> arbol;

      
      /** Posici�n auxiliar. */
      protected Posicion<E> hijo;


      /**
       * Constructor.
       * @param padre  posicio de referencia; se espera que sea una
       *              posici�n v�lida
       */
      public RecorridoHijos(ArbolGeneralDelegImpl<E> arbol,Posicion<E> padre) {
      	 this.arbol=arbol;
         hijo = arbol.ab.hijoIzquierdo(padre);
      }

      
      /**
       * Comprueba si hay una primera o siguiente posici�n. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si est� vac�o o ya se ha visitado la �ltima posici�n.
       * @return  cierto o falso, seg�n si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  hijo != null; }

      
      /**
       * Primero avanza, si se puede, y despu�s retorna la posici�n.
       * Si no hay siguiente posici�n lanza una excepci�n.
       * @return  siguiente posici�n
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
