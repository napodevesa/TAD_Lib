package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de cualquier arbol binario, el cual
 * se caracteriza por organizar sus elementos (nodos) formando una
 * jerarqu�a: todo nodo (excepto la ra�z) es
 * descendiente de un nodo �nico, y puede ser ascendente de un m�ximo de dos nodos
 * (cuando no tiene descendientes se nombra a hoja).
 *
 * En la representaci�n secuencial del arbol se organizan los nodos dentro de
 * de un vector de manera que los hijos y el padre son accesibles aplicando
 * solo una formula. La primera posici�n del vector contendr� siempre
 * la ra�z del arbol; si un nodo est� en la posici�n [k], su hijo
 * izquierdo estar� en la [2*k] y su hijo derecho en la [2*k+1].
 *
 * Esta representaci�n tiene dos operaciones modificadoras (a�adir al final
 * y borrar la �ltima posici�n) que permiten mantener una estructura
 * de arbol quasicompleto; es decir, no habr� ning�na posici�n libre en un
 * recorrido por niveles del arbol. Dado que la primera posici�n [0] del
 * vector no se utiliza para facilitar los c�lculos mencionados, si el n�mero
 * de casillas del vector es potencia de dos, el arbol podr� llegar a ser
 * completo.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolBinarioVectorImpl<E>  extends ArbolBinario<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad m�xima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /** N�mero de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Tabla de elementos del contenedor. Las posiciones empiezan por el cero.*/
   protected Rango<E>[] elems;

   /** Constructor sin par�metros (capacidad m�xima, por defecto). */
   public ArbolBinarioVectorImpl() {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un par�metro. Crea un vector con una capacidad de
    * max.
    * @exception ExcepcionParametroIncorrecto  si la capacidad m�xima del
    *                                         nuevo arbol es negativa
    * @pre max>=0 , new ExcepcionParametroIncorrecto("el n�mero de elementos m�ximo no puede ser negativo")
    * @param max  n�mero m�ximo de elementos del arbol 
    */
   public ArbolBinarioVectorImpl(int max) {
      elems = new Rango[max];
      n = 0;
   }

   /**
    * Accesor de lectura del n�mero de elementos que hay al contenedor.
    * @return  n�mero de elementos que contiene actualmente
    */
   public int numElems() { return  n; }

   /**
    * M�todo para comprobar si el contenedor est� vac�o.
    * @return  cierto o falso, seg�n si est� vac�o o no lo est�
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * M�todo para comprobar si el contenedor est� lleno.
    * @return  cierto o falso, seg�n si est� lleno o no lo est�
    */
   public boolean estaLleno() { return  ( n == elems.length ); }

   /**
    * Accesor de lectura de la ra�z del arbol, si hay.
    * @pre !estaVacio(), new ExcepcionContenedorVacio("No se puede acceder a la ra�z de un arbol vac�o")
    * @exception ExcepcionContenedorVacio  si el arbol est� vac�o
    * @return  ra�z del arbol
    */
   public Posicion<E> raiz() { return  elems[0]; }
   
   
   /**
    * A�ade un elemento como nuevo hijo de la posici�n escogida.
    * Si el padre es null, se a�ade en la ra�z; si es una hoja, se a�ade
    * como hijo izquierdo; y si el padre tiene �nicamente un hijo, se a�ade como
    * el otro hijo.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @pre posicionValida(padre) &&
    * 	    (indiceHijoIzquierdo(padre)<maximo && !tieneHijoIzquierdo(padre)) ||
    * 	    (indiceHijoDerecho(padre)<maximo && !tieneHijoDerecho(padre)), ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  Si la posici�n de padre es inv�lida para poder
    * a�adir un hijo. Eso incluye tambi�n los casos en que el padre ya tenga dos hijos y
    * el caso en que los hijos caigan fuera del rango m�ximo permitido al crear el arbol.
    * @return La posici�n del nodo a�adido.
    */
   public Posicion<E> insertar(Posicion<E> padre, E elem)
   {
   	  Posicion<E> posicionNuevoHijo;
   	  if (hijoIzquierdo(padre)!=null)
   	  	 posicionNuevoHijo=insertarHijoDerecho(padre,elem);
   	  else
   	  	 posicionNuevoHijo=insertarHijoIzquierdo(padre,elem);
   	  return posicionNuevoHijo;
   }
   

   /**
    * A�ade un elemento como hijo izquierdo de la posici�n escogida.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @pre posicioValida(padre) &&
    * 	    indiceHijoIzquierdo(padre)<maxim && !tieneHijoIzquierdo(padre)
    * @exception ExcepcionPosicionInvalida  Si la posici�n de padre es inv�lida para poder
    * a�adir un hijo. Eso incluye tambi�n los casos en que el padre ya tenga hijo izquierdo
    * y el caso en que este caiga fuera del rango m�ximo permitido al crear el arbol.
    * @return La posici�n del nodo a�adido.
    */
   public Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem)
   {
   	  int indice = getIndiceHijoIzquierdo(padre);
   	  n++;
   	  elems[indice]=new Rango<E>(indice,elem);
   	  return elems[indice];
   }
   

   /**
    * A�ade un elemento como hijo derecho de la posici�n escogida, si se puede.
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @pre posicionValida(padre) &&
    * 	    indiceHijoDerecho(padre)<maximo && !tieneHijoDerecho(padre)
   * @exception ExcepcionPosicionInvalida  Si la posici�n de padre es inv�lida para poder
    * a�adir un hijo. Eso incluye tambi�n los casos en que el padre ya tenga hijo derecho
    * y el caso en que este caiga fuera del rango m�ximo permitido al crear el arbol.
    * @return La posici�n del nodo a�adido.
    */
   public Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem)
   {
 	  int indice = getIndiceHijoDerecho(padre);
   	  n++;
   	  elems[indice]=new Rango<E>(indice,elem);
   	  return elems[indice];
   }

   
   /**
    * Accesor de lectura del padre de una posici�n del arbol, si hay.
    * @param pos  posici�n de referencia
    * @return  padre de la posici�n recibida; o null si era la ra�z.
    */
   public Posicion<E> padre(Posicion<E> pos)
   {
   	  if (getIndice(pos)==0) return null;
   	  return elems[getIndicePadre(pos)];
   }

   /**
    * Accesor de lectura del hijo izquierdo de una posici�n del arbol.
    * @param pos  posici�n de referencia
    * @pre posicionValida(pos)
    * @exception ExcepcionPosicionInvalida  si la posici�n no es v�lida.
    * @return  hijo izquierdo de la posici�n escogida; o null si no tiene.
    */
   public Posicion<E> hijoIzquierdo(Posicion<E> pos)
   {
 	  int indice=getIndiceHijoIzquierdo((Rango<E>)pos);
   	  if (indice>=elems.length || elems[indice]==null)
   	  	 return null;
   	  return elems[indice];
   }

   /**
    * Accesor de lectura del hijo derecho de una posici�n del arbol.
    * @param pos  posici�n de referencia
    * @pre posicionValida(pos)
    * @exception ExcepcionPosicionInvalida  si la posici�n es null o no
    *            v�lida
    * @return  hijo derecho de la posici�n escogida; o null si no tiene.
    */
   public Posicion<E> hijoDerecho(Posicion<E> pos)
   {
	  int indice=getIndiceHijoDerecho((Rango<E>)pos);
   	  if (indice>=elems.length || elems[indice]==null)
   	  	 return null;
   	  return elems[indice];
   }

   /**
    * Borra el sub�rbol representado por la posici�n hijo, si se puede.
    * Si la posici�n del padre es null, borra el arbol entero.
    * @param padre  posici�n del padre; puede ser null
    * @param hijo  posici�n del hijo
    * @pre esPadreDe(padre,hijo)
    * @exception ExcepcionPosicionInvalida  si alguna posici�n es no v�lida o
    * bien padre no es el padre de hijo.
    */
   public void borrar(Posicion<E> padre, Posicion<E> hijo)
   {
 	  Posicion<E> nietoIzquierdo = hijoIzquierdo(hijo);
   	  Posicion<E> nietoDerecho = hijoDerecho(hijo);
   	  if (nietoIzquierdo!=null)
   	  	  borrar(hijo,nietoIzquierdo);
   	  if (nietoDerecho!=null)
   	  	  borrar(hijo,nietoDerecho);
   	  int indiceHijo=getIndice(hijo);
   	  n--;
   	  elems[indiceHijo]=null;
   }


   /**
    * Reemplaza el elemento contenido en la posici�n escogida.
    * @param elem  nuevo elemento
    * @param pos  posici�n de referencia
    * @pre posicioValida(pos)
    * @return  elemento que hab�a a la posici�n antes de reemplazarlo.
    */
   public E reemplazar(Posicion<E> pos, E elem)
   {
   	  Rango<E> rango=(Rango<E>)pos;
   	  E old=rango.getElem();
   	  rango.setElem(elem);
   	  return old;
   }

   /**
    * Intercambia dos posiciones dentro de de l'arbol. Los objetos
    * posici�n se intercambian de posici�n, con lo cual su
    * contenido se mantiene constante.
    * @param pos1  primera de las dos posiciones de referencia.
    * @param pos2  segunda de las dos posiciones de referencia.
    * @pre posicionValida(pos1) && posicionValida(pos2)
    */
   public void intercambiar(Posicion pos1, Posicion pos2)
   {
 	  Rango<E> r1=(Rango<E>)pos1;
   	  Rango<E> r2=(Rango<E>)pos2;
   	  int indice1=r1.getIndice();
   	  int indice2=r2.getIndice();
   	  r1.setIndice(indice2);
   	  r2.setIndice(indice1);
   	  elems[indice1]=r2;
   	  elems[indice2]=r1;
   }


   private int getIndice(Posicion<E> pos) {
   	  if (pos==null) return -1;
	  return ((Rango)pos).getIndice();
   }


   private int getIndicePadre(Posicion<E> pos) {
   	  int indice=((Rango)pos).getIndice();
   	  if (indice==0) return -1;
	  return (indice-1)/2;
   }


   private int getIndiceHijoIzquierdo(Posicion<E> pos) {
 	  if (pos==null) return 0;
	  return ((Rango)pos).getIndice()*2+1;
   }


   private int getIndiceHijoDerecho(Posicion<E> pos) {
 	  if (pos==null) return 0;
 	  return ((Rango)pos).getIndice()*2+2;
 }
 

}
