
package uoc.ei.tads.grafos;

import uoc.ei.tads.*;

/** Clase que implementa un vértice.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
abstract class VerticeImpl<TElem,TEtiqueta> implements Vertice<TElem>
{
	
	/** Valor del nodo. */
   private TElem elemento;
   

   /** Construye un vertice a partir de su valor.
    * @param elemento
    */
   public VerticeImpl(TElem element) {
      this.elemento = element;
   }

   
   /** Retorna el valor del nodo.
    */
   public TElem getValor() { return  elemento; }

   
   /** Retorna el conjunto de aristas del grafo en las que el nodo participa.
    * @return Conjunto de aristas en las que participa el nodo.
    */ 
   public abstract Iterador<Arista<TEtiqueta,TElem>> aristas();
   
   
   /** Elimina la referencia a una arista.
    * @param arista Arista de la cual queremos eliminar la referencia.
    */
   public abstract void eliminarArista(AristaImpl<TEtiqueta,TElem> arista);
   
   
   /** Añade una referencia a la arista como arista que conecta el vértice
    * con otro vértice.
    * @param arista La arista añadida.
    * @return
    */
   public abstract void insertarArista(Arista<TEtiqueta,TElem> arista);
   
   
   /** Implementación del método heredado de Object.
    */
   public int hashCode() { return  elemento.hashCode(); }

   
   /** Implementación del método heredado de Object.
    */
   public boolean equals(Object vertice) {
   	  if (getClass()!=vertice.getClass()) return false;
      return elemento.equals( ((VerticeImpl<TElem,TEtiqueta>)vertice).getValor() );
   }

   
   /** Implementación del método heredado de Object.
    */
   public String toString() { return "(Vertice:"+elemento+")"; }

}
