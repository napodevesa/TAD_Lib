package uoc.ei.tads;
/**
 * Clase que representa una posici�n en un contenedor implementado
 * mediante una representaci�n con vector. Tiene la utilidad de:
 * (a) A partir de un objeto de tipo Rango, la implementaci�n del
 * contenedor puede acceder en tiempo constante a la posici�n del vector
 * que lo almacena; y
 * (b)poder cambiar elementos de ubicaci�n dentro del contenedor
 * sin invalidar referencias externas a elementos (de tipo Posicion/Rango)
 * que se hayan creado con anterioridad.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Rango<E>  implements Posicion<E> {

   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
	/** Elemento contenido en el nodo. */
   private E elemento;

   /** �ndice del elemento dentro del contenedor. */
   private int indice;

   /**
    * Constructor con dos par�metros.
    * @param idx  �ndice de la posici�n dentro de del contenedor.
    * @param elem  valor del elemento contenido a la posici�n.
    */
   public Rango(int idx, E elem) {
      indice = idx;
      elemento = elem;
   }

   /**
    * Accesor de escritura del valor contenido en la posici�n.
    * @param elem  nuevo valor del elemento contenido al nodo
    */
   public void setElem(E elem) { elemento = elem; }

   
   /**
    * Accesor de lectura del elemento contenido al nodo.
    * @return  elemento contenido al nodo
    */
   public E getElem() { return  elemento; }

   
   /**
    * Accesor de escritura del �ndice de la posici�n.
    * @param idx �ndice de la posici�n dentro de del contenedor.
    */
   public void setIndice(int idx) { indice = idx; }

   
   /**
    * Accesor de lectura de l'�ndex de la posici�.
    * @return  enter no negatiu
    */
   public int getIndice() { return  indice; }

   
   /**
    * M�todo que define la conversi�n del objeto a String para
    * facilitar la depuraci�n del c�digo. Delega en el m�todo toString() de
    * el elemento almacenado en la posici�n.
    * @return  cadena de caracteres representativa del elemento
    */
   public String toString()
   {
      return  (elemento == null) ? ("[" + indice + ",null]") :
              ("[" + indice + ","+ elemento.toString()+"]");
   }
}
