package uoc.ei.tads;

/**
 * Clase que implementa las operaciones de un diccionario mediante una
 * tabla de dispersión indirecta, conocida con el número de tabla encadenada
 * abierta (separate chaining). Seguramente es la manera más sencilla de
 * resolver las colisiones. Por la función de dispersión se accede a
 * el índice del vector y los sinónimos se encadenan a partir de esta
 * posición.
 *
 * Los diccionarios son estructuras que almacenan elementos con una clave
 * asociada. La clave debe disponer de una operación de igualdad y el elemento
 * asociado a la clave puede ser cualquier objeto.
 *
 * En esta implementación se utilizan elementos de la clase ClaveValor
 * que sobrescribe, por delegación en la clave, la función hashCode().
 *
 * @see  ClaveValor
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class TablaDispersion<C,E>  implements Diccionario<C,E>
{
    /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
     * serialitzables de la misma clase. El identificador se calcula
     * mediante un método de la clase Utilidades. */
    private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
    /** Medida por defecto de la tabla de dispersión. */
   public static final int TAMANO_TABLA_POR_DEFECTO = 257;


   /** Número de elementos que hay actualmente al contenedor. */
   protected int n = 0;

   
   /** Vector de nodos encadenados. */
   protected ListaEncadenada<ClaveValor<C,E>>[] tabla;

   
   /** Constructor sin parámetros (tamaño de la tabla por defecto). */
   public TablaDispersion()
   {
   	  this(TAMANO_TABLA_POR_DEFECTO);
   }

   
   /**
    * Constructor con un parámetro. Crea un vector con la tamaño dada.
    * @exception ExcepcionParametroIncorrecto  si la tamaño es negativa
    * @param tamano  tamaño de la tabla de dispersión
    * @pre tamano>=0, new ExcepcionParametroIncorrecto("la tamaño no puede ser negativa")
    */
   public TablaDispersion(int tamano)  throws  ExcepcionParametroIncorrecto
   {
      tabla = new ListaEncadenada[tamano];
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
   public boolean estaVacio() { return  (n == 0); }

   
   /**
    * Añade un elemento con una clave asociada, si se puede. Si encuentra un
    * elemento con la misma clave lo sobrescribe.
    * @see  ClaveValor
    * @param clave  clave asociada al elemento que se quiere añadir
    * @param elem  elemento que se quiere añadir al diccionario
    * @exception ExcepcionTADs  si la clave es null
    */
   public void insertar(C clave, E elem) {
      ClaveValor<C,E> kv = new ClaveValor<C,E>(clave, elem);
      int indice = calcularIndiceTabla(clave);
      ListaEncadenada<ClaveValor<C,E>> sinonimos=tabla[indice];
      Posicion<ClaveValor<C,E>> posicion=null;
      if (sinonimos==null)
      	sinonimos=creaListaDeSinonimos(indice,kv);
      else
      	posicion=buscarClaveEnSinonimos(sinonimos,clave);
      if (posicion!=null)
    	  sinonimos.reemplazar(posicion,kv);
      else {
    	  sinonimos.insertarAlPrincipio(kv);
    	  n++;
      }
   }


   /**
    * Comprueba si hay un elemento con una determinada clave.
    * @param clave  clave asociada a un elemento
    * @return  cierto o falso, según si encuentra o no encuentra la clave
    */
   public boolean esta(C clave) {
      return  consultar(clave) != null;
   }

   
   /**
    * Accesor de lectura del elemento asociado con una clave.
    * @see  ClaveValor
    * @param clave  clave de referencia
    * @exception ExcepcionElementosNoComparables  si el elemento es no
    *            comparable
    * @return  elemento ClaveValor asociado con la clave;
    *          o null, si no era
    */
   public E consultar(C clave) {
   		E objetoBuscado=null;
		int indice = calcularIndiceTabla(clave);
		ListaEncadenada<ClaveValor<C,E>> sinonimos=tabla[indice];
		Posicion<ClaveValor<C,E>> posicionElemento=buscarClaveEnSinonimos(sinonimos,clave);
		if (posicionElemento!=null) {
			ClaveValor<C,E> kv=posicionElemento.getElem();
			objetoBuscado=kv.getValor();
		}
		return objetoBuscado;
   }


   /**
    * Borra la primera clave coincidente y el elemento asociado, si se puede.
    * @see  ClaveValor
    * @param clave  clave de referencia
    * @exception ExcepcionContenedorVacio  si la tabla está vacía
    * @exception ExcepcionElementosNoComparables  si el elemento es no
    *            comparable
    * @return  elemento borrado asociado con la clave;
    *          o null, si no era
    */
   public E borrar(C clave)
   {
      E valorAnterior = null;
      int indice = calcularIndiceTabla(clave);
      ListaEncadenada<ClaveValor<C,E>> sinonimos=tabla[indice];
      if (sinonimos!=null) {
	 	Recorrido<ClaveValor<C,E>> recorridoSinonimos=sinonimos.posiciones();
	  	Posicion<ClaveValor<C,E>> posicion=null,posicionAnterior=null;
	  	boolean encontrado=false;
	  	while (recorridoSinonimos.haySiguiente() && !encontrado) {
	  		posicionAnterior=posicion;
	  		posicion=recorridoSinonimos.siguiente();
	  		ClaveValor<C,E> kvPosicion=posicion.getElem();
	  		encontrado=clave.equals(kvPosicion.getClave());
	  	}
	  	if (encontrado) {
	  		valorAnterior=posicion.getElem().getValor();
	  		if (sinonimos.numElems()==1)
	  			borrarListaDeSinonimos(indice);
	  		else
			  	sinonimos.borrarSiguiente(posicionAnterior);
		  	n--;
	  	}
      }
	 return valorAnterior;
   }

   
   protected int calcularIndiceTabla(C clave) {
	   int hash = clave.hashCode();                  // código hash de la clave
	   // recorta el bit de signo y ajusta el índice al intervalo
	   return (hash & 0x7FFFFFFF) % tabla.length;
   }


   protected ListaEncadenada<ClaveValor<C,E>> creaListaDeSinonimos(int indice,ClaveValor<C,E> kv) {
		tabla[indice] = new ListaEncadenada<ClaveValor<C,E>>();
		return tabla[indice];
   }


   protected void borrarListaDeSinonimos(int indice) {
		tabla[indice] = null;
   }


   protected Posicion<ClaveValor<C,E>> buscarClaveEnSinonimos(ListaEncadenada<ClaveValor<C,E>> sinonimos,C clave) {
	  	Posicion<ClaveValor<C,E>> posicion=null;
	  	boolean encontrado=false;
		if (sinonimos!=null) {
		 	Recorrido<ClaveValor<C,E>> recorridoSinonimos=sinonimos.posiciones();
		  	while (recorridoSinonimos.haySiguiente() && !encontrado) {
		  		posicion=recorridoSinonimos.siguiente();
		  		ClaveValor<C,E> kvPosicion=posicion.getElem();
		  		encontrado=clave.equals(kvPosicion.getClave());
		  	}
		}
	  	return encontrado ? posicion : null;
	}

	
   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeración.
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @return  enumeración de las claves del contenedor sin ordenar
    */
   public Iterador<C> claves() {
      return  new IteradorRecorridoClavesImpl<C,E>(new RecorridoNodos<C,E>(this));
   }

   /**
    * Accesor de lectura de los elementos que hay en el contenedor.
    * Retorna una enumeración.
    * Enumerar es simplemente enunciar la una detrás la otra (las cosas
    * de una serie, las partes de un todo). Pero si el contenedor tiene definido
    * algún tipo de ordenación o de recorrido, la enumeración debe ser
    * consecuente y ofrecer los elementos por orden (FIFO, LIFO, inordre,
    * etc.), sin alterar el estado actual del contenedor.
    * @see  Iterador#haySiguiente()
    * @see  Iterador#siguiente()
    * @return  enumeración de los elementos asociados con las claves
    */
   public Iterador<E> elementos() {
      return  new IteradorRecorridoValoresImpl<C,E>(new RecorridoNodos<C,E>(this));
   }

   /**
    * Clase que proporciona un recorrido de las posiciones. Basada en el
    * pattern Iterator, soporta múltiples recorridos simultáneos y
    * independientes del contenedor. Es sensible a eventuales alteraciones de
    * la estructura de posiciones.
    * @see  Recorrido#haySiguiente()
    * @see  Recorrido#siguiente()
    */
   protected static class RecorridoNodos<C,E>  implements Recorrido<ClaveValor<C,E>> {

    private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
    /** Tabla de dispersión que se está recorriendo. */
    protected TablaDispersion<C,E> tablaDispersion;

    /** Recorrido auxiliar. */
    protected Recorrido<ClaveValor<C,E>> recorridoSinonimos = null;

    /** Índice de la tabla. */
    protected int i = 0;

    public RecorridoNodos(TablaDispersion<C,E> td) {
    	tablaDispersion=td;
    	recorridoSinonimos = null;
    	i = 0;
		buscarSiguiente();
    }
    
	
    /**
     * Comprueba si hay una primera o siguiente posición. Es sensible a
     * eventuales alteraciones de la estructura de posiciones. Retorna falso
     * si el contenedor está vacío o ya se ha visitado la última posición.
     * @return  cierto o falso, según si se puede avanzar o no se puede
     */
    public boolean haySiguiente() { return  recorridoSinonimos!=null && recorridoSinonimos.haySiguiente(); }

	
    /**
     * Primero avanza, si se puede, y después retorna la posición.
     * Si no hay siguiente posición lanza una excepción.
     * @pre haySiguiente()
     * @return  siguiente posición
     */
    public Posicion<ClaveValor<C,E>> siguiente() {
    	if (!haySiguiente())
    		throw new ExcepcionPosicionInvalida();
		Posicion<ClaveValor<C,E>> posicion=recorridoSinonimos.siguiente();
		buscarSiguiente();
		return posicion;
    }

	
    private void buscarSiguiente() {
		while (i<tablaDispersion.tabla.length && (recorridoSinonimos==null || !recorridoSinonimos.haySiguiente())) {
			if (tablaDispersion.tabla[i]!=null)
				recorridoSinonimos=tablaDispersion.tabla[i].posiciones();
			else
				recorridoSinonimos=null;
			i++;
		}
    }
    
   }

   
   /**
    * Método que sobrescribe Object.toString(). Muestra pares de elementos.
    * Separa una par de la siguiente con el salto de línea de la
    * plataforma.
    */
   public String toString()
   {
   	  return Utilidades.toStringContenedor("TablaDispersion",new RecorridoNodos<C,E>(this));
   }
   
}
