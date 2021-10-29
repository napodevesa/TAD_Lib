package uoc.ei.tads.grafos;

/** 
 * Artista de un grafo dirigido.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface AristaDirigida<TEtiqueta,TElem> extends Arista<TEtiqueta,TElem> {
 
  /**
   * @pre true
   * @post @return!=null
   */
  public Vertice<TElem> getVerticeOrigen();
  
  /**
   * @pre true
   * @post  @return!=null
   */
  public Vertice<TElem> getVerticeDestino();

}
