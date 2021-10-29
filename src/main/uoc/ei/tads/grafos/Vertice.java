package uoc.ei.tads.grafos;

/** 
 * Vértice (nodo) de un grafo.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Vertice<TElem> extends java.io.Serializable {
   	
  /**
   * Obtiene el objeto con información asociada al Vertice.
   * @pre true
   * @post true
   */
  public TElem getValor();
    
}
