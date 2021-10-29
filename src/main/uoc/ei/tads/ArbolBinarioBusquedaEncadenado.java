package uoc.ei.tads;
/**
 * Clase que implementa un arbol binario de busqueda, el cual se caracteriza
 * por tener la ra�z m�s grande que todos los elementos de su sub�rbol
 * izquierdo (si tiene) y m�s peque�a que todos los elementos de su sub�rbol
 * derecho (si tiene); y, adem�s, sus sub�rboles izquierdo y derecho (si
 * tiene) son tambi�n �rboles de busqueda.
 *
 * La clase implementa las operaciones no posicionales de a�adir, borrar y
 * consultar un elemento comparable, y solo tiene accesibilidad dentro de del
 * package porque hereda unas operaciones posicionales que podr�an alterar
 * la consistencia del arbol de busqueda. 
 *
 * Se espera que la clase de los elementos implementen la interfaz
 * java.lang.Comparable o bien que se facilite un java.util.Comparator como
 * par�metro del constructor.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class ArbolBinarioBusquedaEncadenado<E>  extends ArbolBinarioEncadenadoImpl<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
	private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Comparador concreto que permite deducir la prioridad entre los
    * elementos. Puede tener valor null y entonces se utiliza la interfaz
    * java.lang.Comparable
    */
   protected java.util.Comparator<E> comparador;

   /**
    * Constructor sin par�metros. Se espera que la clase de los elementos
    * implemente la interfaz java.lang.Comparable.
    */
   public ArbolBinarioBusquedaEncadenado() { super(); }

   /**
    * Constructor con un par�metro y elementos de una clase comparable con
    * el comparador dado.
    * @exception ExcepcionParametroIncorrecto  si el comparador es null
    * @param comparador  comparador que permite deducir la prioridad
    * @pre comparador!=null, ExcepcionParametroIncorrecto("comparador null")
    */
   public ArbolBinarioBusquedaEncadenado(java.util.Comparator<E> comparador) {
      this.comparador = comparador;
   }

   /**
    * A�ade un elemento a la posici�n que le corresponde, si se puede. Si
    * el elemento ya exist�a, de acuerdo con el comparador, lo sobrescribe.
    * @exception ExcepcionElementosNoComparables  si el elemento es no
    *            comparable
    * @param elemComp  elemento comparable que se quiere a�adir al arbol
    */
   public void insertar(E elemComp) {
      insertarEnSubarbol(estaVacio() ? null : raiz(), elemComp);
   }

   /**
    * Borra el elemento, si lo encuentra de acuerdo con el comparador.
    * @exception ExcepcionContenedorVacio  si el arbol est� vac�o
    * @exception ExcepcionElementosNoComparables  si el elemento es no
    *            comparable
    * @param elemComp  elemento comparable que se quiere suprimir del arbol
    * @return  el elemento borrado; o null, si no existia
    */
   public E borrar(E elemComp) {
      return  borrarEnSubarbol(null, raiz(), elemComp);
   }

   /**
    * Accesor de lectura de un elemento del arbol. Si no lo encuentra, de acuerdo
    * con el comparador, retorna null.
    * @exception ExcepcionContenedorVacio  si el arbol est� vac�o
    * @exception ExcepcionElementosNoComparables  si el elemento es no
    *            comparable
    * @param elemComp  elemento comparable que se quiere consultar
    * @return  el elemento interesado; o null, si no exist�a
    */   public E consultar(E elemComp) {
      return  consultarEnSubarbol(raiz(), elemComp);
   }

    /**
     * M�todo recursivo que garantiza la inserci�n ordenada de los elementos.
     * No permite elementos equivalentes: si el elemento exist�a, de acuerdo con
     * la operaci�n de comparaci�n, lo sobrescribe; sino crea un nuevo nodo
     * para almacenar el elemento y le encadena en el lugar del arbol que le
     * corresponde.
     * @param padre  posici�n de referencia (inicialmente la ra�z)
     * @param elem  elemento comparable que se quiere a�adir al arbol
     * @return  nuevo hijo; o la ra�z, si el padre era null
     */
    private Posicion<E> insertarEnSubarbol(Posicion<E> padre,E elem) {
      if (padre == null)
         return  super.insertar(padre, elem);
      Posicion<E> node = null;
      int comp = comparar(elem, padre.getElem());
      // dependiendo del resultado de comparar actuamos. Hay tres casos diferentes.
      // comp==0 (el elemento es igual al padre), sobrescribe el padre
      if (comp == 0) {                                                  
         super.reemplazar(padre, elem);
         node=padre;
      }
      // comp<0: descarta el arbol derecho y contin�a el a�adir por la izquierda
      else if (comp < 0) {
         if (hijoIzquierdo(padre) == null)
            node = insertarHijoIzquierdo(padre, elem);
         else
         	node=insertarEnSubarbol(hijoIzquierdo(padre), elem);
      }
      // comp>0: descarta izquierda y contin�a por derecho
      else {
         if (hijoDerecho(padre) == null)
            node = insertarHijoDerecho(padre, elem);
         else
         	node=insertarEnSubarbol(hijoDerecho(padre), elem);
      }
      equilibrar(padre);
      return node;
   }

   
    /**
     * M�todo recursivo que borra el elemento, si le encuentra de acuerdo con el
     * comparador.
     * @param abuelo  posici�n de referencia (inicialmente null)
     * @param padre  posici�n de referencia (inicialmente la ra�z)
     * @param elem  elemento comparable que se quiere suprimir del arbol
     * @return  elemento borrado; o null, si no era
     */
   private E borrarEnSubarbol(Posicion<E> abuelo, Posicion<E> padre, E elem) {
   	  E elemEsborrat=null;
      if (padre == null)
		  return  null;
      int comp = comparar(elem, padre.getElem());
      if (comp < 0)										// hacia la izquierda
         elemEsborrat=borrarEnSubarbol(padre, hijoIzquierdo(padre), elem);
      else if (comp > 0)                                 // hacia la derecha
         elemEsborrat=borrarEnSubarbol(padre, hijoDerecho(padre), elem);
      else {                                                  // 0: encontrado
      	 elemEsborrat=padre.getElem();
         if (esHoja(padre))                              // si no tiene hijos
            borrar(abuelo, padre);                   // super.esborrar
         else if ( (hijoIzquierdo(padre) != null) &&
                   (hijoDerecho(padre) == null) )        // solo hijo izq.
            reemplazarSubarbol(abuelo, padre, hijoIzquierdo(padre)); // sobreescr.
         else if ( (hijoIzquierdo(padre) == null) &&
                   (hijoDerecho(padre) != null) )        // solo hijo derecho
            reemplazarSubarbol(abuelo, padre, hijoDerecho(padre));    // sobreescribe
         else {                                            // tiene dos hijos
            Posicion<E> min = hijoDerecho(padre);      // minimo derecha
            while (hijoIzquierdo(min)!=null)
            	min=hijoIzquierdo(min);
            intercambiar(padre, min);
            //  y borra el que conten�a el m�nimo del sub�rbol derecho
            borrarEnSubarbol(padre, hijoDerecho(padre), min.getElem());
         }
      }
      equilibrar(abuelo);    // si es preciso, equilibra el arbol o sub�rbol de nodos
      return  elemEsborrat;
   }

   
   /**
    * Accesor de lectura de un elemento del arbol. Si no lo encuentra,
    * de acuerdo con el comparador, retorna null.
    * @param padre  posici�n de referencia (inicialmente la ra�z)
    * @param elemComp  elemento comparable que se quiere consultar
    * @exception ExcepcionElementosNoComparables  si alg�n elemento es no
    *            comparable
    * @return  el elemento interesado, o bien null si no existe
    */
   private E consultarEnSubarbol(Posicion<E> padre, E elemComp) throws ExcepcionElementosNoComparables {
   	  boolean trobat=false;
      int comp;
      while (padre!=null && !trobat) {
         comp = comparar(elemComp, padre.getElem());
         trobat = comp==0;
         if      (comp < 0)  padre = hijoIzquierdo(padre);
         else if (comp > 0)  padre = hijoDerecho(padre);
      }
      return trobat ? padre.getElem() : null;
   }

   
   /**
    * M�todo para saber si hay un comparador espec�fico. Si no
    * se utiliza la operaci�n de comparaci�n de java.lang.Comparable.
    * @return  cierto o falso, seg�n si hay o no hay un comparador
    *          espec�fico asignado
    */
   public boolean hayComparador() { return  (comparador != null); }

   
   /**
    * M�todo protegido que compara los elementos recibos. Si en la clase
    * principal hay definido un comparador, el m�todo delega en el
    * m�todo java.�til.Comparator.compadre(o1, o2); sino delega en
    * el m�todo java.lang.Comparable.compareTo(o) implementado por la
    * clase del elemento.
    * @param elem1  primer objeto de la misma clase
    * @param elem2  segundo objeto de la misma clase
    * @exception ExcepcionElementosNoComparables  si alg�n elemento es no
    *            comparable
    * @return  un entero negativo, cero o positivo, seg�n si el primer
    *          elemento tiene menos, igual o m�s prioridad que el segundo
    *          elemento, de acuerdo con la implementaci�n del comparador
    * @pre elem1!=null && elem2!=null, ExcepcionElementosNoComparables
    */
   protected int comparar(E elem1,E elem2) throws  ExcepcionElementosNoComparables {
    int comp = 0;
    if (comparador == null)
       comp = ((Comparable<E>)elem1).compareTo(elem2);
    else
    	comp = comparador.compare(elem1, elem2);
    return  comp;
   }

   /**
    * M�todo que deben implementar las subclases que necesiten trabajar
    * con �rboles equilibrados. La eficiencia temporal de las operaciones
    * de acceso individual a los elementos de un arbol de busca depende
    * exclusivamente de su altura y, en el peor de los casos, puede
    * pasar a ser lineal. Hay variantes de �rboles que garantizan un coste
    * logar�tmico en base a reducir el n�mero de niveles. Para equilibrar
    * el arbol deben hacer un tratamiento de los nodos desde el lugar de inserci�n
    * o de supresi�n hasta la ra�z del arbol. Este m�todo es llamado a
    * cada paso para insertar() y para borrar(); si no est� definido no hace
    * nada. Pero da la oportunidad a las clases descendientes de implementar
    * un tratamiento que garantice el equilibrio aprovechando el mismo
    * c�digo de a�adir y de borrar que han heredado.
    * @param abin  arbol o sub�rbol binario que se debe equilibrar
    */
   protected void equilibrar(Posicion<E> abin) {}

}
