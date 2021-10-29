package uoc.ei.tads.grafos;

/** 
 * V�rtice (nodo) de un grafo.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Vertice<TElem> extends java.io.Serializable {
   	
  /**
   * Obtiene el objeto con informaci�n asociada al Vertice.
   * @pre true
   * @post true
   */
  public TElem getValor();
    
}
