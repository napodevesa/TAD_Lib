package uoc.ei.tads;


public abstract class ArbolAbstracto<E> implements Arbol<E> {
	
	
	/**
	 * Comprueba si el arbol o sub�rbol tiene alg�n hijo.
	 * @param nodo  posici�n de referencia
	 * @return cierto si la posici�n tiene alg�n hijo y falso de lo contrario
	 */
	public boolean esHoja(Posicion<E> nodo) {
		return !hijos(nodo).haySiguiente();
	}


	public boolean estaVacio() {
		return raiz()==null;
	}
	
	
	/**
	 * M�todo que retorna el n�mero de hijos de un nodo.
	 * @param nodo  posici�n de referencia
	 * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
	 * v�lida
	 * @return  cantidad de hijos, o cero si es una hoja
	 */
	public int numHijos(Posicion<E> nodo) {
		int n=0;
		Recorrido<E> hijos=hijos(nodo);
		while (hijos.haySiguiente()) {
			n++;
			hijos.siguiente();
		}
      return  n;
	}


	/**
	* Accesor de lectura de los elementos que hay al contenedor.
	* Retorna una enumeraci�n. Se puede obtener un recorrido con un par de
	* l�neas de c�digo: <PRE>
	* for ( Iterador it = tad.elementos(); it.haySiguiente(); )
	*    System.out.println(it.siguiente()); </PRE>
	* Enumerar es simplemente enunciar la una detr�s la otra (las cosas
	* de una serie, las partes de un todo). Pero si el contenedor tiene definido
	* alg�n tipo de ordenaci�n o de recorrido, la enumeraci�n debe ser
	* consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
	* etc.), sin alterar el estado actual del contenedor.
	* @see  Iterador#haySiguiente()
	* @see  Iterador#siguiente()
	* @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
	*            elemento de la enumeraci�n y este no existe
	* @return  enumeraci�n de los elementos del contenedor preorden
	*/
	public Iterador<E> elementos() {
	      return  new IteradorRecorridoImpl<E,E>(recorridoPreorden());
	   }

	/**
	* M�todo que soporta m�ltiples recorridos, de las posiciones del
	* contenedor, simult�neos e independientes entre ellos.
	* @return  enumeraci�n de las posiciones del contenedor por nivel
	*/
	public Recorrido<E> posiciones() { return  new RecorridoPorNiveles<E>(this); }


	public Recorrido<E> recorridoPreorden() {
		return new RecorridoPreorden<E>(this);
	}

	
	public Recorrido<E> recorridoPostorden() {
		return new RecorridoPostorden<E>(this);
	}

	
	public Recorrido<E> recorridoPorNiveles() {
		return new RecorridoPorNiveles<E>(this);
	}
	
	
	public int numElems(Posicion<E> posicion) {
		int n=1;
		Recorrido<E> r=hijos(posicion);
		while (r.haySiguiente())
			n+=numElems(r.siguiente());
		return n;
	}

	
	public String toString() {
		StringBuffer sb=new StringBuffer("{");
		if (raiz()!=null)
			toString(sb,raiz());
		sb.append("}");
		return sb.toString();
	}
	
	
	protected void toString(StringBuffer sb,Posicion<E> posicion) {
		sb.append(posicion.toString()+" ");
		if (!esHoja(posicion)) {
			sb.append("{");
			Recorrido<E> r=hijos(posicion);
			while (r.haySiguiente()) {
				toString(sb,r.siguiente());
				if (r.haySiguiente())
					sb.append(",");
			}
			sb.append("}");
		}
	}


	/**
	 * Clase que proporciona el comportamiento b�sico para los tres recorridos
	 * preorden, inordre y posorden. En esta clase se define el
	 * comportamiento com�n a los tres recorridos, de manera que posteriormente
	 * �nicamente habr� que definir, para cada recorrido concreto, el m�todo
	 * siguiente.
	 * @see  Recorrido#haySiguiente()
	 * @see  Recorrido#siguiente()
	 */
	protected static abstract class RecorridoOrdenBasico<E> implements Recorrido<E> {
		/** El arbol que se est� recorriendo. */
		protected Arbol<E> arbol;
		   	
		   	
		/** Pila auxiliar. Esta pila guarda los elementos que hemos tratado pero que
		 * a�n no hemos recorrido. La cima de la pila siempre debe tener el siguiente elemento
		 * a recorrer. Los tres recorridos pre/in/post se pueden ver de la siguiente
		 * manera: dado un nodo, este tiene un conjunto de descendientes que aparecer�n antes de que
		 * �l en el recorrido (son m�s prioritarios) y un conjunto de descendientes que aparecer�n
		 * despu�s de �l en el recorrido (son menos prioritarios). */
		protected Pila<Posicion<E>> pila;
		
		/** Constructor.
		 * @param arbol El arbol a recorrer.
		 */
		public RecorridoOrdenBasico(Arbol<E> arbol) {
			this.arbol=arbol;
			pila = new PilaVectorImpl<Posicion<E>>(arbol.numElems());
			if (! arbol.estaVacio()) {
				pila.apilar(arbol.raiz());
				apilaDescendientesConMasPrioridad(arbol.raiz());
		    }		
		}	
	
		/** Este m�todo apila los descendientes de un nodo que han de aparecer
		 * antes que �l en el recorrido del arbol. Esta caracter�stica
		 * diferencia los tres recorridos preorden, inordre y
		 * postorden.
		 * Por lo tanto, el m�todo es definido en esta clase como abstracta
		 * y debe ser definido en las clases derivadas de la manera pertinente.
		 * El resto de m�todos que implementan el recorrido se pueden definir en
		 * esta misma clase en base a este m�todo abstracto.
		 */ 
		protected abstract void apilaDescendientesConMasPrioridad(Posicion<E> padre);
	
		    
		/** Este m�todo apila los descendientes de un nodo que han de aparecer
		 * despu�s que �l en el recorrido del arbol. Esta caracter�stica
		 * es la otra caracter�stica que diferencia los tres recorridos preorden,
		 * inordre y posorden.
		 * Por lo tanto, el m�todo est� definido en esta clase como abstracta
		 * y debe ser definido en las clases derivadas de la manera pertinente.
		 * El resto de m�todos que implementan el recorrido se pueden definir en
		 * esta misma clase en base a este m�todo abstracto.
		 */ 
		protected abstract void apilaDescendientesConMenosPrioridad(Posicion<E> padre);
	
		
		/** Este m�todo apila los hijos de un nodo en el orden en el que est�n definidos.
		 * @param padre
		 */
		protected void apilaHijos(Posicion<E> padre) {
			Recorrido<E> hijos=arbol.hijos(padre);
			// primero creamos una pila con los hijos (para poderlos recorrer en orden inverso)
			// la pila debe ser encadenada, pues no tenemos hito. Como no tenemos pila encadenada
			// usamos directamente una lista encadenada
			ListaEncadenada<Posicion<E>> pilaHijos=new ListaEncadenada<Posicion<E>>();
			while (hijos.haySiguiente()) {
				Posicion<E> hijo=hijos.siguiente();
				pilaHijos.insertarAlPrincipio(hijo);
			}
			// una vez tenemos los hijos en orden inverso, los insertamos en la pila del recorrido
			Iterador<Posicion<E>> hijos2=pilaHijos.elementos();
			while (hijos2.haySiguiente()) {
				Posicion<E> hijo=hijos2.siguiente();
				pila.apilar(hijo);
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
		public Posicion<E> siguiente() {
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
	protected static class RecorridoPreorden<E> extends RecorridoOrdenBasico<E> {
		/** Atributo que en Java 1.5 determina la compatibilidad entre objetos
		 * serializables de la misma clase. El identificador se calcula
		 * mediante un m�todo de la clase Utilidades. */
		private static final long serialVersionUID = Utilidades.getSerialVersionUID();
		 	
		 	
		/** Constructor.
		 * @param arbol El arbol a recorrer.
		 */
		public RecorridoPreorden(Arbol<E> arbol) {
			super(arbol);
		}
	
		      	
		/** En el caso del recorrido preorden, dado un nodo, este siempre
		 * aparecer� antes de que sus descendientes en el recorrido,
		 * por lo tanto, un nodo no tiene descendientes con m�s prioridad que �l.
		 */ 
		protected void apilaDescendientesConMasPrioridad(Posicion<E> padre) {}
		
		
		/** En el caso del recorrido preorden, los descendientes con menos
		 * prioridad que un nodo son sus dos hijos (y a la vez los
		 * descendientes de estos). tenemos bastante con empilar los dos hijos,
		 * pues posteriormente cuando llegamos en el recorrido a estos hijos
		 * ya los trataremos y ya trataremos sus hijos adecuadamente.
		 */ 
		protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre) {
			apilaHijos(padre);
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
	protected static class RecorridoPostorden<E> extends RecorridoOrdenBasico<E> {
		/** Atributo que en Java 1.5 determina la compatibilidad entre objetos
		 * serialitzables de la misma clase. El identificador se calcula
		 * mediante un m�todo de la clase Utilidades. */
		private static final long serialVersionUID = Utilidades.getSerialVersionUID();
		
		
	      /** �ltimo nodo del cual se han apilado en la pila hecha servir internamiento
	       * por el recorrido tanto los hijos con m�s prioridad como los hijos con menos
	       * prioridad */
	      private Pila<Posicion<E>> nodosTotalmenteDesplegados; 
	 	
	 	
		/** Constructor.
		 * @param arbol El arbol a recorrer.
		 */
		public RecorridoPostorden(Arbol<E> arbol) {
			super(arbol);
		}
		
		      	
		/** En el caso del recorrido postorden, dado un nodo, este siempre
		 * aparecer� despu�s de que los sus descendientes en el recorrido,
		 * por lo tanto, todos los hijos de un nodo tienen m�s prioridad que �l.
		 */ 
		protected void apilaDescendientesConMasPrioridad(Posicion<E> padre) {
			apilaHijos(padre);
			while (!yaDesplegadosTotalmente(pila.cima()))
				apilaHijos(pila.cima());
		}
			
	
		/** En el caso del recorrido postorden un nodo no tiene descendientes con
		 * menos prioridad.
		 */ 
		protected void apilaDescendientesConMenosPrioridad(Posicion<E> padre) {}
		
		
		protected void apilaHijos(Posicion<E> padre) {
			if (nodosTotalmenteDesplegados==null)
				nodosTotalmenteDesplegados=new PilaVectorImpl<Posicion<E>>(arbol.numElems());
			super.apilaHijos(padre);
			nodosTotalmenteDesplegados.apilar(padre);
		}
	
			  
		public Posicion<E> siguiente() {
	         Posicion<E> nodo=pila.desapilar();
	       	 nodosTotalmenteDesplegados.desapilar();
	         if (!pila.estaVacio() && !yaDesplegadosTotalmente(pila.cima())) {
	        	 apilaDescendientesConMasPrioridad(pila.cima());
	         }
	         return nodo;
		}

	
		protected boolean yaDesplegadosTotalmente(Posicion<E> nodo) {
			return !nodosTotalmenteDesplegados.estaVacio() && nodosTotalmenteDesplegados.cima()==nodo;
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
	   protected static class RecorridoPorNiveles<E>  implements Recorrido<E>
	   {
	      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
	       * serialitzables de la misma clase. El identificador se calcula
	       * mediante un m�todo de la clase Utilidades. */
	      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	      
	      
	      /** Arbol que estamos recorriendo. */
	      Arbol<E> arbol;
	 	
	 	
	      /** Cola auxiliar. */
	      protected Cola<Posicion<E>> cola;
	
	      
	      /** Constructor. */
	      public RecorridoPorNiveles(Arbol<E> arbol)
	      {
	         this.arbol=arbol;
	         cola = new ColaVectorImpl<Posicion<E>>(arbol.numElems());
	         if (! arbol.estaVacio())  cola.encolar(arbol.raiz());
	      }
	
	      /**
	       * Comprueba si hay una primera o siguiente posici�n. Es sensible a
	       * eventuales alteraciones de la estructura de posiciones. Retorna falso
	       * si est� vac�o o ya se ha visitado la �ltima posici�n.
	       * @return  cierto o falso, seg�n si se puede avanzar o no se puede
	       */
	      public boolean haySiguiente() { return  ! cola.estaVacio(); }
	
	      /**
	       * Primero avanza, si se puede, y despu�s retorna la posici�n.
	       * Si no hay siguiente posici�n lanza una excepci�n.
	       * @return  siguiente posici�n
	       */
	      public Posicion<E> siguiente()  throws  ExcepcionPosicionInvalida
	      {
	          if ( ! haySiguiente() )  throw new ExcepcionPosicionInvalida(
	                                           "No hay siguiente");
	
	         Posicion<E> padre = cola.desencolar();
	         for (Recorrido<E> r = arbol.hijos(padre); r.haySiguiente(); )
	         {
	            cola.encolar(r.siguiente());
	         }
	         return  padre;
	      }
	   }

}
