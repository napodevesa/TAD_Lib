package uoc.ei.tads.grafos;

import java.io.Serializable;

/** 
 * Arista de un grafo (dirigido o no dirigido).
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Arista<TEtiqueta,TElem> extends Serializable {

  /**
   * Obtiene el valor asociado a la arista.
   * @return null si el grafo es no valorado.
   */
   public TEtiqueta getEtiqueta();
   
   /**
   * Establece el valor asociado a la arista.
   * @pre true
   * @post obtenerValor()@pre == v
   */
  public void setEtiqueta(TEtiqueta v);
  
  /**
   * Indica si la arista incide en el vértice v.
   * @pre v!=null
   * @post true
   */
  public boolean incideEnVertice(Vertice<TElem> v);
  
}
