package uoc.ei.tads;
/**
 * Clase abstracta que define las operaciones de cualquiera arbol binario,
 * el cual se caracteriza para organizar sus elementos (nodos)
 * formando una jerarquía: todo nodo (excepto la raíz) es descendiente de 
 * un nodo único, y puede ser ascendente de un máximo
 * de dos nodos (cuando no tiene descendientes se llama hoja).
 *
 * Implementa los recorridos habituales y otras operaciones auxiliares que
 * se derivan de las operaciones básicas ya definidas en este nivel de
 * la jerarquía.
 *
 * Implementa también la interfaz Serializable para poder convertir a
 * cadenas o flujos de bytes (streams) los objetos del contenedor y
 * grabarlos o transmitirlos.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public abstract class ArbolBinario<E>  extends ArbolAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Añade un elemento como hijo izquierdo de la posición escogida, si se
    * puede.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @return  nuevo hijo izquierdo
    */
   public abstract Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem);

   /**
    * Añade un elemento como hijo derecho de la posición escogida, si se puede.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @return  nuevo hijo derecho
    */
   public abstract Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem);

   /**
    * Accesor de lectura del hijo izquierdo de una posición del arbol.
    * @param pos  posición de referencia
    * @return  hijo izquierdo de la posición escogida; o null si no tiene
    */
   public abstract Posicion<E> hijoIzquierdo(Posicion<E> pos);

   /**
    * Accesor de lectura del hijo derecho de una posición del arbol.
    * @param pos  posición de referencia
    * @return  hijo derecho de la posición escogida; o null si no tiene
    */
   public abstract Posicion<E> hijoDerecho(Posicion<E> pos);
   

   /**
    * Método que soporta múltiples recorridos, de las posiciones hijas
    * de la posición de referencia, simultáneos e independientes entre ellos.
    * @param padre  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  enumeración de las posiciones hijas
    */
   public Recorrido<E> hijos(Posicion<E> padre) {
      return  new RecorridoHijos<E>(this,padre);
   }

   /**
    * Comprueba si el arbol o subárbol tiene algún hijo.
    * @param nodo  posición de referencia
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  falso o cierto, según si tiene algún hijo o no tiene ningún
    */
   public boolean esHoja(Posicion<E> nodo) {
      return  (hijoIzquierdo(nodo) == null) && (hijoDerecho(nodo) == null);
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y éste no existe
    * @return  enumeración de los elementos del contenedor inordre
    */
   public Iterador<E> elementos() {
      return  new IteradorRecorridoImpl<E,E>(recorridoInorden());
   }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @see  RecorridoPorNiveles
    * @return  enumeración de las posiciones del contenedor por nivel
    */
   public Recorrido<E> posiciones() { return  new RecorridoPorNiveles<E>(this); }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @see  RecorridoPreorden
    * @return  enumeración de las posiciones del contenedor preorden
    */
   public Recorrido<E> recorridoPreorden() {
      return  new RecorridoPreorden<E>(this);
   }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @see  RecorridoInorden
    * @return  enumeración de las posiciones del contenedor inorden
    */
   public Recorrido<E> recorridoInorden() {
      return  new RecorridoInorden<E>(this);
   }

   /**
    * Método que soporta múltiples recorridos, de las posiciones del
    * contenedor, simultáneos e independientes entre ellos.
    * @see  RecorridoPostorden
    * @return  enumeración de las posiciones del contenedor posorden
    */
   public Recorrido<E> recorridoPostorden() {
      return  new RecorridoPostorden<E>(this);
   }

   /**
    * Clase que proporciona un recorrido de las posiciones hijas. Basada
    * en el pattern Iterator, soporta múltiples recorridos simultáneos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoHijos<E>  implements Recorrido<E> {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un método de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
      
      /** Arbol que se está recorriendo. */
      ArbolBinario<E> arbol;
 	

      /** Cola para almacenar los hijos. */
      private Cola<Posicion<E>> hijos;

      /**
       * Constructor con un parámetro.
       * @param padre  posición no null de referencia
       */
      public RecorridoHijos(ArbolBinario<E> arbol,Posicion<E> padre) {
      	 this.arbol=arbol;
         hijos = new ColaVectorImpl<Posicion<E>>(2);
         if (arbol.hijoIzquierdo(padre) != null)
             hijos.encolar(arbol.hijoIzquierdo(padre));
         if (arbol.hijoDerecho(padre) != null)
             hijos.encolar(arbol.hijoDerecho(padre));
      }

      /**
       * Comprueba si hay una primera o siguiente posición. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si es hoja o ya se ha visitado la última posición.
       * @return  cierto o falso, según si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  ! hijos.estaVacio(); }

      /**
       * Primero avanza, si se puede, y después retorna la posición.
       * Si no hay siguiente posición lanza una excepción.
       * @return  siguiente posición
       * @pre haySiguiente(), ExcepcionPosicionInvalida
       */
      public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida
      {
         return  hijos.desencolar();
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
   protected static class RecorridoPorNiveles<E> implements Recorrido<E>
   {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un método de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Arbol que se está recorriendo. */
      ArbolBinario<E> arbol;
 	

      /** Cola auxiliar. */
      protected Cola<Posicion<E>> cola;

      /** Constructor. */
      public RecorridoPorNiveles(ArbolBinario<E> arbol)
      {
      	 this.arbol=arbol;
         cola = new ColaVectorImpl<Posicion<E>>(arbol.numElems());
         if (! arbol.estaVacio())  cola.encolar(arbol.raiz());
      }

      /**
       * Comprueba si hay una primera o siguiente posición. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si el contenedor está vacío o ya se ha visitado la última posición.
       * @return  cierto o falso, según si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  ! cola.estaVacio(); }

      /**
       * Primero avanza, si se puede, y después retorna la posición.
       * Si no hay siguiente posición lanza una excepción.
       * @return  siguiente posición
       * @pre haySiguiente(), ExcepcionPosicionInvalida
       */
      public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida
      {
         Posicion<E> padre = (Posicion<E>)cola.desencolar();
         if (arbol.hijoIzquierdo(padre) != null)
            cola.encolar(arbol.hijoIzquierdo(padre));
         if (arbol.hijoDerecho(padre) != null)
            cola.encolar(arbol.hijoDerecho(padre));
         return padre;
      }
   }

   
   /**
    * Clase que proporciona el comportamiento básico para los tres recorridos
    * preorden, inordre y postorden. En esta clase se define el
    * comportamiento común a los tres recorridos, de manera que posteriormente
    * únicamente habrá que definir, para cada recorrido concreto, el método
    * siguiente.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static abstract class RecorridoOrdenBasico<E> implements Recorrido<E>
   {
   	/** El arbol que se está recorriendo. */
   	protected ArbolBinario<E> arbol;
   	
   	
    /** Pila auxiliar. Esta pila guarda los elementos que hemos tratado pero que
     * aún no hemos recorrido. La cima de la pila siempre debe tener el siguiente elemento
     * a recorrer. Todos los tres recorridos pre/in/post se pueden ver de la siguiente
     * manera: dado un nodo, este tiene un conjunto de descendientes que aparecerán antes de que
     * él en el recorrido (son más prioritarios) y un conjunto de descendientes que aparecerán
     * después de él en el recorrido (son menos prioritarios). */
    protected Pila<Posicion<E>> pila;

    /** Este método apila los descendientes de un nodo que han de aparecer
     * antes de que él en el recorrido del arbol. Esta característica
     * diferencia los tres recorridos preorden, inordre y postorden.
     * Por lo tanto, el método esta definido en esta clase como abstracto
     * y debe ser definido en las clases derivadas de la manera pertinente.
     * El resto de métodos que implementan el recorrido se pueden definir en
     * esta misma clase en base a este método abstracto.
     */ 
    protected abstract void apilaDescendientesConMasPrioridad(Posicion<E> padre);

    
    /** Este método apila los descendientes de un nodo que han de aparecer
     * después de que él en el recorrido del arbol. Esta característica
     * es el otro característica que diferencia los tres recorridos preorden,
     * inordre y postorden.
     * Por lo tanto, el método está definido en esta clase como abstracto
     * y debe ser definido en las clases derivadas de la manera pertinente.
     * El resto de métodos que implementan el recorrido se pueden definir en
     * esta misma clase en base a este método abstracto.
     */ 
    protected abstract void apilaDescendientesConMenosPrioridad(Posicion<E> padre);


    /** Constructor.
     * @param arbol El árbol a recorrer.
     */
    public RecorridoOrdenBasico(ArbolBinario<E> arbol)
    {
		this.arbol=arbol;
		pila = new PilaVectorImpl<Posicion<E>>(arbol.numElems());
		if (! arbol.estaVacio()) {
	       	pila.apilar(arbol.raiz());
	       	apilaDescendientesConMasPrioridad(arbol.raiz());
       }
    }


    /**
     * Comprueba si hay una primera o siguiente posición. Es sensible a
     * eventuales alteraciones de la estructura de posiciones. Retorna falso
     * si el contenedor está vacío o ya se ha visitado la última posición.
     * @return  cierto o falso, según si se puede avanzar o no se puede
     */
    public boolean haySiguiente() { return  ! pila.estaVacio(); }
    

    /**
     * Primero avanza, si se puede, y después retorna la posición.
     * Si no hay siguiente posición lanza una excepción.
     * @return  siguiente posición
     * @pre haySiguiente(), ExcepcionPosicionInvalida
     */
    public Posicion<E> siguiente()
    {
       if (pila.estaVacio())
    	   throw new ExcepcionPosicionInvalida();
       Posicion<E> nodo=(Posicion<E>)pila.desapilar();
       apilaDescendientesConMenosPrioridad(nodo);
       return nodo;
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
   protected static class RecorridoPreorden<E> extends RecorridoOrdenBasico<E>
   {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un método de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Constructor.
       * @param arbol El arbol a recorrer.
       */
      public RecorridoPreorden(ArbolBinario<E> arbol)
      {
      	super(arbol);
      }

      	
      /** En el caso del recorrido preorden, dado un nodo, este siempre
	    * aparecerá antes de que sus descendientes en el recorrido,
	    * por lo tanto, un nodo no tiene descendientes con más prioridad que él.
	    */ 
	  protected void apilaDescendientesConMasPrioridad(Posicion<E> padre) {}
	

	   /** En el caso del recorrido preorden, los descendientes con menos
	    * prioridad que un nodo son sus dos hijos (y a la vez los
	    * descendientes de estos). tenemos bastante con apilar los dos hijos,
	    * pues posteriormente cuando llegamos en el recorrido a estos hijos
	    * ya los trataremos y ya trataremos sus hijos adecuadamente.
	    */ 
	  protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre)
	   {
	   	Posicion<E> hijo=arbol.hijoDerecho(padre);
	   	if (hijo!=null) pila.apilar(hijo);
	   	hijo=arbol.hijoIzquierdo(padre);
	   	if (hijo!=null) pila.apilar(hijo);
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
   protected static class RecorridoInorden<E> extends RecorridoOrdenBasico<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Constructor.
       * @param arbol El arbol a recorrer.
       */
      public RecorridoInorden(ArbolBinario<E> arbol)
      {
      	super(arbol);
      }

      	
      /** Los descendientes con más prioridad para el caso del recorrido
       * inordre son el hijo izquierdo y sus descendientes. Por lo tanto,
       * apilamos el hijo izquierdo. Posteriormente, si el hijo izquierdo
       * tiene algún descendiente con más prioridad, lo deberemos apilar también.
       * Eso resulta en, en tal caso, apilar los descendientes izquierdo;
       * es decir, el hijo izquierdo, el hijo izquierdo del hijo izquierdo, etc.
       */ 
      protected void apilaDescendientesConMasPrioridad(Posicion<E> padre)
      {
      	Posicion<E> hijo=arbol.hijoIzquierdo(padre);
  		while (hijo!=null) {
  			pila.apilar(hijo);
  			padre=hijo;
  			hijo=arbol.hijoIzquierdo(padre);
  		}
  	}

      /** Los descendientes con menos prioridad para el caso del recorrido
       * inordre son el hijo derecho y sus descendientes. Por lo tanto,
       * empilamos el hijo derecho. Acto seguido pasamos a empilar los descendientes
       * del hijo derecho que tienen más prioridad que este.
       */ 
      protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre)
      {
      	Posicion<E> hijo=arbol.hijoDerecho(padre);
      	if (hijo!=null) {
      		pila.apilar(hijo);
      		apilaDescendientesConMasPrioridad(hijo);
      	}
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
   protected static class RecorridoPostorden<E> extends RecorridoInorden<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
      
      
      /** Último nodo del cual se han apilado en la pila usada internamente
       * por el recorrido tanto los hijos con más prioridad como los hijos con menos
       * prioridad */
      private Pila<Posicion<E>> nodosTotalmenteDesplegados; 
 	
 	
      /** Constructor.
       * @param arbol El arbol a recorrer.
       */
      public RecorridoPostorden(ArbolBinario<E> arbol)
      {
       	super(arbol);
      }

      	
      /** Redefinición de este método para tener en cuenta los descendientes
       * con menos prioridad del último nodo de la rama izquierda.
       */ 
      protected void apilaDescendientesConMasPrioridad(Posicion<E> padre)
      {
    	  super.apilaDescendientesConMasPrioridad(padre);
    	  apilaDescendientesConMenosPrioridad(pila.cima());
      }


      /** Los descendientes con menos prioridad para el caso del recorrido
       * inorden son el hijo derecho y sus descendientes. Por lo tanto,
       * apilamos el hijo derecho. Acto seguido pasamos a apilar los descendientes
       * del hijo derecho que tienen más prioridad que este.
       */ 
      protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre)
      {
    	  if (nodosTotalmenteDesplegados==null)
    	       	nodosTotalmenteDesplegados=new PilaVectorImpl<Posicion<E>>(arbol.numElems());
    	  nodosTotalmenteDesplegados.apilar(padre);
    	  super.apilaDescendientesConMenosPrioridad(padre);
      }


      /** Esta clase reaprovecha las prioridades establecidas por el recorrido
       * inorden y modifica el método siguiente. La modificación consiste en 
       * apilar los elementos con menos prioridad del nodo que queda en la cima
       * de la pila después de desamontonar el actual.
       * Es decir, lo que estamos haciendo es cambiar el significado de los métodos
       * apilaDescendientesConMas/MenosPrioridad para reaprovechar el máximo
       * código posible. En esta clase todos los descendientes de un nodo
       * tienen más prioridad que este. Entonces dividimos sus descendientes
       * en dos grupos: los que tienen más prioridad (hijo izquierdo y descendientes)
       * y los que tienen menos (hijo derecho y descendientes).
       * Si quisiéramos respetar la estructura definida en la clase base acabaríamos
       * teniendo una pila que inicialmente tendría todos los nodos del arbol.
       * @return  siguiente posición
       * @pre haySiguiente(), ExcepcionPosicionInvalida
       */
      public Posicion<E> siguiente()
      {
         Posicion<E> nodo=pila.desapilar();
       	 nodosTotalmenteDesplegados.desapilar();
         if (!pila.estaVacio() && !yaDesplegadoTotalmente(pila.cima())) {
        	 apilaDescendientesConMenosPrioridad(pila.cima());
         }
         return nodo;
      }
      
      
      protected boolean yaDesplegadoTotalmente(Posicion<E> nodo) {
    	  return !nodosTotalmenteDesplegados.estaVacio() && nodosTotalmenteDesplegados.cima()==nodo;
      }

   
   }

   
	protected void toString(StringBuffer sb,Posicion<E> posicion) {
		if (posicion!=null) {
			sb.append(posicion.toString()+" ");
			sb.append("{");
			toString(sb,hijoIzquierdo(posicion));
			sb.append(",");
			toString(sb,hijoDerecho(posicion));
			sb.append("}");
		}
	}

}
