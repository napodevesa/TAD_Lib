package uoc.ei.tads.grafos;

/** 
 * Arista de un grafo  no dirigido.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface AristaNoDirigida<TEtiqueta,TElem> extends Arista<TEtiqueta,TElem> {
 
  public Vertice<TElem> getVertice1();
  public Vertice<TElem> getVertice2();


}
