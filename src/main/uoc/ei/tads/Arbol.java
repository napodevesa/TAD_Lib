package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de cualquiera arbol (tree).
 *
 * Los �rboles son estructuras que relacionan sus elementos, llamados
 * nodos, formando jerarqu�as: todo nodo (excepto la ra�z que es la cabeza de
 * la jerarqu�a) es descendiente de un nodo �nico, y puede ser ascendente
 * de otros nodos (cuando no tiene descendientes se llama hoja). Cuando un nodo
 * puede tener un n�mero indeterminado de hijos hablamos de �rboles generales
 * (general tree) y, si tiene un n�mero fijo N, de �rboles de orden N (n-ary
 * tree); en estos �ltimos destaca el caso de N = 2, los llamados �rboles
 * binarios (binary tree).
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Arbol<E>  extends Contenedor<E>
{
   /**
    * Accesor de lectura de la ra�z del arbol, si hay.
    * @return  ra�z del arbol
    */
   public Posicion<E> raiz();

   /**
    * M�todo que soporta m�ltiples recorridos, de las posiciones hijas
    * de la posici�n de referencia, simult�neos e independientes entre ellos.
    * @param padre  posici�n de referencia
    * @return  enumeraci�n de las posiciones hijas
    */   public Recorrido<E> hijos(Posicion<E> padre);

    /**
     * Comprueba si el arbol o sub�rbol tiene alg�n hijo.
     * @param pos  posici�n de referencia
     * @return  falso o cierto, seg�n si tiene alg�n hijo o no tiene ning�n
     */
   public boolean esHoja(Posicion<E> pos);

   /**
    * A�ade un elemento como nuevo hijo de la posici�n recibida, si se puede.
    * Si el padre es null, se a�ade en la ra�z; si es una hoja, se a�ade
    * como primero hijo (hijo m�s a la izquierda).
    * @param padre  posici�n de referencia
    * @param elem  elemento que se quiere a�adir al arbol
    * @return  nuevo hijo; o la ra�z, si el padre era null
    */
   public Posicion<E> insertar(Posicion<E> padre,E elem);

   /**
    * Reemplaza el elemento contenido en la posici�n recibida.
    * @param elem  nuevo elemento
    * @param pos  posici�n de referencia
    * @return  elemento que hab�a a la posici�n
    */
   public E reemplazar(Posicion<E> pos, E elem);

   /**
    * Intercambia en el arbol los elementos contenidos a las
    * posiciones recibidas. El arbol resultante contendr� los mismos
    * elementos que ten�a, excepto por los dos elementos recibos como
    * par�metro, que aparecer�n intercambiados.<p>
    * El hecho de si los objetos posici�n se intercambian, o bien el que
    * se intercambia son los elementos contenidos en las posiciones, depender�
    * de cada implementaci�n concreta de arbol.
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    */
   public void intercambiar(Posicion<E> pos1, Posicion<E> pos2);

   /**
    * Borra el sub�rbol representado por la posici�n hijo, si se puede.
    * Si la posici�n del padre es null, borra el arbol entero.
    * @param padre  posici�n del padre; puede ser null
    * @param hijo  posici�n del hijo
    */
   public void borrar(Posicion<E> padre, Posicion<E> hijo);

   /** Recorrido de las posiciones del arbol.
    * @return  enumeraci�n de las posiciones del contenedor por nivel
    */
   public Recorrido<E> posiciones();

   /** Recorrido en preorden de las posiciones del arbol.
    * @return  enumeraci�n de las posiciones del contenedor preorden
    */
   public Recorrido<E> recorridoPreorden();

   /** Recorrido en posorden de las posiciones del arbol.
    * @return  enumeraci�n de las posiciones del contenedor posorden
    */
   public Recorrido<E> recorridoPostorden();


   /** Recorrido por niveles de las posiciones del arbol.
    * @return  enumeraci�n de las posiciones del contenedor por niveles
    */
   public Recorrido<E> recorridoPorNiveles();
}
