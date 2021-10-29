package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de cualquiera arbol (tree).
 *
 * Los árboles son estructuras que relacionan sus elementos, llamados
 * nodos, formando jerarquías: todo nodo (excepto la raíz que es la cabeza de
 * la jerarquía) es descendiente de un nodo único, y puede ser ascendente
 * de otros nodos (cuando no tiene descendientes se llama hoja). Cuando un nodo
 * puede tener un número indeterminado de hijos hablamos de árboles generales
 * (general tree) y, si tiene un número fijo N, de árboles de orden N (n-ary
 * tree); en estos últimos destaca el caso de N = 2, los llamados árboles
 * binarios (binary tree).
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Arbol<E>  extends Contenedor<E>
{
   /**
    * Accesor de lectura de la raíz del arbol, si hay.
    * @return  raíz del arbol
    */
   public Posicion<E> raiz();

   /**
    * Método que soporta múltiples recorridos, de las posiciones hijas
    * de la posición de referencia, simultáneos e independientes entre ellos.
    * @param padre  posición de referencia
    * @return  enumeración de las posiciones hijas
    */   public Recorrido<E> hijos(Posicion<E> padre);

    /**
     * Comprueba si el arbol o subárbol tiene algún hijo.
     * @param pos  posición de referencia
     * @return  falso o cierto, según si tiene algún hijo o no tiene ningún
     */
   public boolean esHoja(Posicion<E> pos);

   /**
    * Añade un elemento como nuevo hijo de la posición recibida, si se puede.
    * Si el padre es null, se añade en la raíz; si es una hoja, se añade
    * como primero hijo (hijo más a la izquierda).
    * @param padre  posición de referencia
    * @param elem  elemento que se quiere añadir al arbol
    * @return  nuevo hijo; o la raíz, si el padre era null
    */
   public Posicion<E> insertar(Posicion<E> padre,E elem);

   /**
    * Reemplaza el elemento contenido en la posición recibida.
    * @param elem  nuevo elemento
    * @param pos  posición de referencia
    * @return  elemento que había a la posición
    */
   public E reemplazar(Posicion<E> pos, E elem);

   /**
    * Intercambia en el arbol los elementos contenidos a las
    * posiciones recibidas. El arbol resultante contendrá los mismos
    * elementos que tenía, excepto por los dos elementos recibos como
    * parámetro, que aparecerán intercambiados.<p>
    * El hecho de si los objetos posición se intercambian, o bien el que
    * se intercambia son los elementos contenidos en las posiciones, dependerá
    * de cada implementación concreta de arbol.
    * @param pos1  primera de las dos posiciones de referencia
    * @param pos2  segunda de las dos posiciones de referencia
    */
   public void intercambiar(Posicion<E> pos1, Posicion<E> pos2);

   /**
    * Borra el subárbol representado por la posición hijo, si se puede.
    * Si la posición del padre es null, borra el arbol entero.
    * @param padre  posición del padre; puede ser null
    * @param hijo  posición del hijo
    */
   public void borrar(Posicion<E> padre, Posicion<E> hijo);

   /** Recorrido de las posiciones del arbol.
    * @return  enumeración de las posiciones del contenedor por nivel
    */
   public Recorrido<E> posiciones();

   /** Recorrido en preorden de las posiciones del arbol.
    * @return  enumeración de las posiciones del contenedor preorden
    */
   public Recorrido<E> recorridoPreorden();

   /** Recorrido en posorden de las posiciones del arbol.
    * @return  enumeración de las posiciones del contenedor posorden
    */
   public Recorrido<E> recorridoPostorden();


   /** Recorrido por niveles de las posiciones del arbol.
    * @return  enumeración de las posiciones del contenedor por niveles
    */
   public Recorrido<E> recorridoPorNiveles();
}
