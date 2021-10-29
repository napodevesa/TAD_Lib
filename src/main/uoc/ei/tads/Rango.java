package uoc.ei.tads;
/**
 * Clase que representa una posición en un contenedor implementado
 * mediante una representación con vector. Tiene la utilidad de:
 * (a) A partir de un objeto de tipo Rango, la implementación del
 * contenedor puede acceder en tiempo constante a la posición del vector
 * que lo almacena; y
 * (b)poder cambiar elementos de ubicación dentro del contenedor
 * sin invalidar referencias externas a elementos (de tipo Posicion/Rango)
 * que se hayan creado con anterioridad.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Rango<E>  implements Posicion<E> {

   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
	/** Elemento contenido en el nodo. */
   private E elemento;

   /** Índice del elemento dentro del contenedor. */
   private int indice;

   /**
    * Constructor con dos parámetros.
    * @param idx  índice de la posición dentro de del contenedor.
    * @param elem  valor del elemento contenido a la posición.
    */
   public Rango(int idx, E elem) {
      indice = idx;
      elemento = elem;
   }

   /**
    * Accesor de escritura del valor contenido en la posición.
    * @param elem  nuevo valor del elemento contenido al nodo
    */
   public void setElem(E elem) { elemento = elem; }

   
   /**
    * Accesor de lectura del elemento contenido al nodo.
    * @return  elemento contenido al nodo
    */
   public E getElem() { return  elemento; }

   
   /**
    * Accesor de escritura del índice de la posición.
    * @param idx índice de la posición dentro de del contenedor.
    */
   public void setIndice(int idx) { indice = idx; }

   
   /**
    * Accesor de lectura de l'índex de la posició.
    * @return  enter no negatiu
    */
   public int getIndice() { return  indice; }

   
   /**
    * Método que define la conversión del objeto a String para
    * facilitar la depuración del código. Delega en el método toString() de
    * el elemento almacenado en la posición.
    * @return  cadena de caracteres representativa del elemento
    */
   public String toString()
   {
      return  (elemento == null) ? ("[" + indice + ",null]") :
              ("[" + indice + ","+ elemento.toString()+"]");
   }
}
