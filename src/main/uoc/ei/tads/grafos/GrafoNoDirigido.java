package uoc.ei.tads.grafos;

/** 
 * Grafo  no dirigido.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface GrafoNoDirigido<TElem,TEtiqueta> extends Grafo<TElem,TEtiqueta> {
 
   /**
   * Crea e incluye una nueva arista en el grafo.
   * @pre n!=null && m!=null && n!=m
   * @post (@return.obtenerVertice1()==n &&  @return.obtenerVertice2()==m) ||
           (@return.obtenerVertice1()==m &&  @return.obtenerVertice2()==n)
   */
  public AristaNoDirigida<TEtiqueta,TElem> crearArista(Vertice<TElem> n, Vertice<TElem> m);
    
   /**
   * Obtiene la arista que conecta dos vertices dados.
   * @pre n!=null & m!=null & n!=m
   * @post (@return.obtenerVertice1()==n &&  @return.obtenerVertice2()==m) ||
           (@return.obtenerVertice1()==m &&  @return.obtenerVertice2()==n)
   */
  public AristaNoDirigida<TEtiqueta,TElem> obtenerArista(Vertice<TElem> n, Vertice<TElem> m);
  
}
