package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de cualquier arbol binario, el cual
 * se caracteriza por organizar sus elementos (nodos) formando una
 * jerarquía: todo nodo (excepto la raíz) es
 * descendiente de un nodo único, y puede ser ascendente de un máximo de dos nodos
 * (cuando no tiene descendientes se nombra a hoja).
 *
 * En la representación secuencial del arbol se organizan los nodos dentro de
 * de un vector de manera que los hijos y el padre son accesibles aplicando
 * solo una formula. La primera posición del vector contendrá siempre
 * la raíz del arbol; si un nodo está en la posición [k], su hijo
 * izquierdo estará en la [2*k] y su hijo derecho en la [2*k+1].
 *
 * Esta representación tiene dos operaciones modificadoras (añadir al final
 * y borrar la última posición) que permiten mantener una estructura
 * de arbol quasicompleto; es decir, no habrá ningúna posición libre en un
 * recorrido por niveles del arbol. Dado que la primera posición [0] del
 * vector no se utiliza para facilitar los cálculos mencionados, si el número
 * de casillas del vector es potencia de dos, el arbol podrá llegar a ser
 * completo.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolBinarioVectorImpl<E>  extends ArbolBinario<E>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Capacidad máxima, por defecto, del contenedor. */
   public static final int MAXIMO_ELEMENTOS_POR_DEFECTO = 256;

   /** Número de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Tabla de elementos del contenedor. Las posiciones empiezan por el cero.*/
   protected Rango<E>[] elems;

   /** Constructor sin parámetros (capacidad máxima, por defecto). */
   public ArbolBinarioVectorImpl() {
   	  this(MAXIMO_ELEMENTOS_POR_DEFECTO);
   }

   /**
    * Constructor con un parámetro. Crea un vector con una capacidad de
    * max.
    * @exception ExcepcionParametroIncorrecto  si la capacidad máxima del
    *                                         nuevo arbol es negativa
    * @pre max>=0 , new ExcepcionParametroIncorrecto("el número de elementos máximo no puede ser negativo")
    * @param max  número máximo de elementos del arbol 
    */
   public ArbolBinarioVectorImpl(int max) {
      elems = new Rango[max];
      n = 0;
   }

   /**
    * Accesor de lectura del número de elementos que hay al contenedor.
    * @return  número de elementos que contiene actualmente
    */
   public int numElems() { return  n; }

   /**
    * Método para comprobar si el contenedor está vacío.
    * @return  cierto o falso, según si está vacío o no lo está
    */
   public boolean estaVacio() { return  ( n == 0 ); }

   /**
    * Método para comprobar si el contenedor está lleno.
    * @return  cierto o falso, según si está lleno o no lo está
    */
   public boolean estaLleno() { return  ( n == elems.length ); }

   /**
    * Accesor de lectura de la raíz del arbol, si hay.
    * @pre !estaVacio(), new ExcepcionContenedorVacio("No se puede acceder a la raíz de un arbol vacío")
    * @exception ExcepcionContenedorVacio  si el arbol está vacío
    * @return  raíz del arbol
    */
   public Posicion<E> raiz() { return  elems[0]; }
   
   
   /**
    * Añade un elemento como nuevo hijo de la posición escogida.
    * Si el padre es null, se añade en la raíz; si es una hoja, se añade
    * como hijo izquierdo; y si el padre tiene únicamente un hijo, se añade como
    * el otro hijo.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @pre posicionValida(padre) &&
    * 	    (indiceHijoIzquierdo(padre)<maximo && !tieneHijoIzquierdo(padre)) ||
    * 	    (indiceHijoDerecho(padre)<maximo && !tieneHijoDerecho(padre)), ExcepcionPosicionInvalida
    * @exception ExcepcionPosicionInvalida  Si la posición de padre es inválida para poder
    * añadir un hijo. Eso incluye también los casos en que el padre ya tenga dos hijos y
    * el caso en que los hijos caigan fuera del rango máximo permitido al crear el arbol.
    * @return La posición del nodo añadido.
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
    * Añade un elemento como hijo izquierdo de la posición escogida.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @pre posicioValida(padre) &&
    * 	    indiceHijoIzquierdo(padre)<maxim && !tieneHijoIzquierdo(padre)
    * @exception ExcepcionPosicionInvalida  Si la posición de padre es inválida para poder
    * añadir un hijo. Eso incluye también los casos en que el padre ya tenga hijo izquierdo
    * y el caso en que este caiga fuera del rango máximo permitido al crear el arbol.
    * @return La posición del nodo añadido.
    */
   public Posicion<E> insertarHijoIzquierdo(Posicion<E> padre, E elem)
   {
   	  int indice = getIndiceHijoIzquierdo(padre);
   	  n++;
   	  elems[indice]=new Rango<E>(indice,elem);
   	  return elems[indice];
   }
   

   /**
    * Añade un elemento como hijo derecho de la posición escogida, si se puede.
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @pre posicionValida(padre) &&
    * 	    indiceHijoDerecho(padre)<maximo && !tieneHijoDerecho(padre)
   * @exception ExcepcionPosicionInvalida  Si la posición de padre es inválida para poder
    * añadir un hijo. Eso incluye también los casos en que el padre ya tenga hijo derecho
    * y el caso en que este caiga fuera del rango máximo permitido al crear el arbol.
    * @return La posición del nodo añadido.
    */
   public Posicion<E> insertarHijoDerecho(Posicion<E> padre, E elem)
   {
 	  int indice = getIndiceHijoDerecho(padre);
   	  n++;
   	  elems[indice]=new Rango<E>(indice,elem);
   	  return elems[indice];
   }

   
   /**
    * Accesor de lectura del padre de una posición del arbol, si hay.
    * @param pos  posición de referencia
    * @return  padre de la posición recibida; o null si era la raíz.
    */
   public Posicion<E> padre(Posicion<E> pos)
   {
   	  if (getIndice(pos)==0) return null;
   	  return elems[getIndicePadre(pos)];
   }

   /**
    * Accesor de lectura del hijo izquierdo de una posición del arbol.
    * @param pos  posición de referencia
    * @pre posicionValida(pos)
    * @exception ExcepcionPosicionInvalida  si la posición no es válida.
    * @return  hijo izquierdo de la posición escogida; o null si no tiene.
    */
   public Posicion<E> hijoIzquierdo(Posicion<E> pos)
   {
 	  int indice=getIndiceHijoIzquierdo((Rango<E>)pos);
   	  if (indice>=elems.length || elems[indice]==null)
   	  	 return null;
   	  return elems[indice];
   }

   /**
    * Accesor de lectura del hijo derecho de una posición del arbol.
    * @param pos  posición de referencia
    * @pre posicionValida(pos)
    * @exception ExcepcionPosicionInvalida  si la posición es null o no
    *            válida
    * @return  hijo derecho de la posición escogida; o null si no tiene.
    */
   public Posicion<E> hijoDerecho(Posicion<E> pos)
   {
	  int indice=getIndiceHijoDerecho((Rango<E>)pos);
   	  if (indice>=elems.length || elems[indice]==null)
   	  	 return null;
   	  return elems[indice];
   }

   /**
    * Borra el subárbol representado por la posición hijo, si se puede.
    * Si la posición del padre es null, borra el arbol entero.
    * @param padre  posición del padre; puede ser null
    * @param hijo  posición del hijo
    * @pre esPadreDe(padre,hijo)
    * @exception ExcepcionPosicionInvalida  si alguna posición es no válida o
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
    * Reemplaza el elemento contenido en la posición escogida.
    * @param elem  nuevo elemento
    * @param pos  posición de referencia
    * @pre posicioValida(pos)
    * @return  elemento que había a la posición antes de reemplazarlo.
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
    * posición se intercambian de posición, con lo cual su
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
