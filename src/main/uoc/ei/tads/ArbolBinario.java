package uoc.ei.tads;
/**
 * Clase abstracta que define las operaciones de cualquiera arbol binario,
 * el cual se caracteriza para organizar sus elementos (nodos)
 * formando una jerarqu�a: todo nodo (excepto la ra�z) es descendiente de 
 * un nodo �nico, y puede ser ascendente de un m�ximo
 * de dos nodos (cuando no tiene descendientes se llama hoja).
 *
 * Implementa los recorridos habituales y otras operaciones auxiliares que
 * se derivan de las operaciones b�sicas ya definidas en este nivel de
 * la jerarqu�a.
 *
 * Implementa tambi�n la interfaz Serializable para poder convertir a
 * cadenas o flujos de bytes (streams) los objetos del contenedor y
 * grabarlos o transmitirlos.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public abstract class ArbolBinario<E>  extends ArbolAbstracto<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * A�ade un elemento como hijo izquierdo de la posici�n escogida, si se
    * puede.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @return  nuevo hijo izquierdo
    */
   public abstract Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem);

   /**
    * A�ade un elemento como hijo derecho de la posici�n escogida, si se puede.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @return  nuevo hijo derecho
    */
   public abstract Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem);

   /**
    * Accesor de lectura del hijo izquierdo de una posici�n del arbol.
    * @param pos  posici�n de referencia
    * @return  hijo izquierdo de la posici�n escogida; o null si no tiene
    */
   public abstract Posicion<E> hijoIzquierdo(Posicion<E> pos);

   /**
    * Accesor de lectura del hijo derecho de una posici�n del arbol.
    * @param pos  posici�n de referencia
    * @return  hijo derecho de la posici�n escogida; o null si no tiene
    */
   public abstract Posicion<E> hijoDerecho(Posicion<E> pos);
   

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones hijas
    * de la posici�n de referencia, simult�neos e independientes entre ellos.
    * @param padre  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  enumeraci�n de las posiciones hijas
    */
   public Recorrido<E> hijos(Posicion<E> padre) {
      return  new RecorridoHijos<E>(this,padre);
   }

   /**
    * Comprueba si el arbol o sub�rbol tiene alg�n hijo.
    * @param nodo  posici�n de referencia
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  falso o cierto, seg�n si tiene alg�n hijo o no tiene ning�n
    */
   public boolean esHoja(Posicion<E> nodo) {
      return  (hijoIzquierdo(nodo) == null) && (hijoDerecho(nodo) == null);
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Enumerar es simplemente enunciar la una detr�s la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe
    * @return  enumeraci�n de los elementos del contenedor inordre
    */
   public Iterador<E> elementos() {
      return  new IteradorRecorridoImpl<E,E>(recorridoInorden());
   }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @see  RecorridoPorNiveles
    * @return  enumeraci�n de las posiciones del contenedor por nivel
    */
   public Recorrido<E> posiciones() { return  new RecorridoPorNiveles<E>(this); }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @see  RecorridoPreorden
    * @return  enumeraci�n de las posiciones del contenedor preorden
    */
   public Recorrido<E> recorridoPreorden() {
      return  new RecorridoPreorden<E>(this);
   }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @see  RecorridoInorden
    * @return  enumeraci�n de las posiciones del contenedor inorden
    */
   public Recorrido<E> recorridoInorden() {
      return  new RecorridoInorden<E>(this);
   }

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones del
    * contenedor, simult�neos e independientes entre ellos.
    * @see  RecorridoPostorden
    * @return  enumeraci�n de las posiciones del contenedor posorden
    */
   public Recorrido<E> recorridoPostorden() {
      return  new RecorridoPostorden<E>(this);
   }

   /**
    * Clase que proporciona un recorrido de las posiciones hijas. Basada
    * en el pattern Iterator, soporta m�ltiples recorridos simult�neos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoHijos<E>  implements Recorrido<E> {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un m�todo de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
      
      /** Arbol que se est� recorriendo. */
      ArbolBinario<E> arbol;
 	

      /** Cola para almacenar los hijos. */
      private Cola<Posicion<E>> hijos;

      /**
       * Constructor con un par�metro.
       * @param padre  posici�n no null de referencia
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
       * Comprueba si hay una primera o siguiente posici�n. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si es hoja o ya se ha visitado la �ltima posici�n.
       * @return  cierto o falso, seg�n si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  ! hijos.estaVacio(); }

      /**
       * Primero avanza, si se puede, y despu�s retorna la posici�n.
       * Si no hay siguiente posici�n lanza una excepci�n.
       * @return  siguiente posici�n
       * @pre haySiguiente(), ExcepcionPosicionInvalida
       */
      public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida
      {
         return  hijos.desencolar();
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
   protected static class RecorridoPorNiveles<E> implements Recorrido<E>
   {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un m�todo de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Arbol que se est� recorriendo. */
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
       * Comprueba si hay una primera o siguiente posici�n. Es sensible a
       * eventuales alteraciones de la estructura de posiciones. Retorna falso
       * si el contenedor est� vac�o o ya se ha visitado la �ltima posici�n.
       * @return  cierto o falso, seg�n si se puede avanzar o no se puede
       */
      public boolean haySiguiente() { return  ! cola.estaVacio(); }

      /**
       * Primero avanza, si se puede, y despu�s retorna la posici�n.
       * Si no hay siguiente posici�n lanza una excepci�n.
       * @return  siguiente posici�n
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
    * Clase que proporciona el comportamiento b�sico para los tres recorridos
    * preorden, inordre y postorden. En esta clase se define el
    * comportamiento com�n a los tres recorridos, de manera que posteriormente
    * �nicamente habr� que definir, para cada recorrido concreto, el m�todo
    * siguiente.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static abstract class RecorridoOrdenBasico<E> implements Recorrido<E>
   {
   	/** El arbol que se est� recorriendo. */
   	protected ArbolBinario<E> arbol;
   	
   	
    /** Pila auxiliar. Esta pila guarda los elementos que hemos tratado pero que
     * a�n no hemos recorrido. La cima de la pila siempre debe tener el siguiente elemento
     * a recorrer. Todos los tres recorridos pre/in/post se pueden ver de la siguiente
     * manera: dado un nodo, este tiene un conjunto de descendientes que aparecer�n antes de que
     * �l en el recorrido (son m�s prioritarios) y un conjunto de descendientes que aparecer�n
     * despu�s de �l en el recorrido (son menos prioritarios). */
    protected Pila<Posicion<E>> pila;

    /** Este m�todo apila los descendientes de un nodo que han de aparecer
     * antes de que �l en el recorrido del arbol. Esta caracter�stica
     * diferencia los tres recorridos preorden, inordre y postorden.
     * Por lo tanto, el m�todo esta definido en esta clase como abstracto
     * y debe ser definido en las clases derivadas de la manera pertinente.
     * El resto de m�todos que implementan el recorrido se pueden definir en
     * esta misma clase en base a este m�todo abstracto.
     */ 
    protected abstract void apilaDescendientesConMasPrioridad(Posicion<E> padre);

    
    /** Este m�todo apila los descendientes de un nodo que han de aparecer
     * despu�s de que �l en el recorrido del arbol. Esta caracter�stica
     * es el otro caracter�stica que diferencia los tres recorridos preorden,
     * inordre y postorden.
     * Por lo tanto, el m�todo est� definido en esta clase como abstracto
     * y debe ser definido en las clases derivadas de la manera pertinente.
     * El resto de m�todos que implementan el recorrido se pueden definir en
     * esta misma clase en base a este m�todo abstracto.
     */ 
    protected abstract void apilaDescendientesConMenosPrioridad(Posicion<E> padre);


    /** Constructor.
     * @param arbol El �rbol a recorrer.
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
     * Comprueba si hay una primera o siguiente posici�n. Es sensible a
     * eventuales alteraciones de la estructura de posiciones. Retorna falso
     * si el contenedor est� vac�o o ya se ha visitado la �ltima posici�n.
     * @return  cierto o falso, seg�n si se puede avanzar o no se puede
     */
    public boolean haySiguiente() { return  ! pila.estaVacio(); }
    

    /**
     * Primero avanza, si se puede, y despu�s retorna la posici�n.
     * Si no hay siguiente posici�n lanza una excepci�n.
     * @return  siguiente posici�n
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
    * pattern Iterator, soporta m�ltiples recorridos simult�neos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoPreorden<E> extends RecorridoOrdenBasico<E>
   {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un m�todo de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Constructor.
       * @param arbol El arbol a recorrer.
       */
      public RecorridoPreorden(ArbolBinario<E> arbol)
      {
      	super(arbol);
      }

      	
      /** En el caso del recorrido preorden, dado un nodo, este siempre
	    * aparecer� antes de que sus descendientes en el recorrido,
	    * por lo tanto, un nodo no tiene descendientes con m�s prioridad que �l.
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
    * pattern Iterator, soporta m�ltiples recorridos simult�neos y
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

      	
      /** Los descendientes con m�s prioridad para el caso del recorrido
       * inordre son el hijo izquierdo y sus descendientes. Por lo tanto,
       * apilamos el hijo izquierdo. Posteriormente, si el hijo izquierdo
       * tiene alg�n descendiente con m�s prioridad, lo deberemos apilar tambi�n.
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
       * del hijo derecho que tienen m�s prioridad que este.
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
    * pattern Iterator, soporta m�ltiples recorridos simult�neos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoPostorden<E> extends RecorridoInorden<E>
   {

      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
      
      
      /** �ltimo nodo del cual se han apilado en la pila usada internamente
       * por el recorrido tanto los hijos con m�s prioridad como los hijos con menos
       * prioridad */
      private Pila<Posicion<E>> nodosTotalmenteDesplegados; 
 	
 	
      /** Constructor.
       * @param arbol El arbol a recorrer.
       */
      public RecorridoPostorden(ArbolBinario<E> arbol)
      {
       	super(arbol);
      }

      	
      /** Redefinici�n de este m�todo para tener en cuenta los descendientes
       * con menos prioridad del �ltimo nodo de la rama izquierda.
       */ 
      protected void apilaDescendientesConMasPrioridad(Posicion<E> padre)
      {
    	  super.apilaDescendientesConMasPrioridad(padre);
    	  apilaDescendientesConMenosPrioridad(pila.cima());
      }


      /** Los descendientes con menos prioridad para el caso del recorrido
       * inorden son el hijo derecho y sus descendientes. Por lo tanto,
       * apilamos el hijo derecho. Acto seguido pasamos a apilar los descendientes
       * del hijo derecho que tienen m�s prioridad que este.
       */ 
      protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre)
      {
    	  if (nodosTotalmenteDesplegados==null)
    	       	nodosTotalmenteDesplegados=new PilaVectorImpl<Posicion<E>>(arbol.numElems());
    	  nodosTotalmenteDesplegados.apilar(padre);
    	  super.apilaDescendientesConMenosPrioridad(padre);
      }


      /** Esta clase reaprovecha las prioridades establecidas por el recorrido
       * inorden y modifica el m�todo siguiente. La modificaci�n consiste en 
       * apilar los elementos con menos prioridad del nodo que queda en la cima
       * de la pila despu�s de desamontonar el actual.
       * Es decir, lo que estamos haciendo es cambiar el significado de los m�todos
       * apilaDescendientesConMas/MenosPrioridad para reaprovechar el m�ximo
       * c�digo posible. En esta clase todos los descendientes de un nodo
       * tienen m�s prioridad que este. Entonces dividimos sus descendientes
       * en dos grupos: los que tienen m�s prioridad (hijo izquierdo y descendientes)
       * y los que tienen menos (hijo derecho y descendientes).
       * Si quisi�ramos respetar la estructura definida en la clase base acabar�amos
       * teniendo una pila que inicialmente tendr�a todos los nodos del arbol.
       * @return  siguiente posici�n
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
