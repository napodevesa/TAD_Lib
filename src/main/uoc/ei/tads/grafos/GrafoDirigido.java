package uoc.ei.tads.grafos;

import uoc.ei.tads.Iterador;

/** 
 * Grafo dirigido.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface GrafoDirigido<TElem,TEtiqueta> extends Grafo<TElem,TEtiqueta> {
	
 /**
   * Crea e incluye una nueva arista en el grafo.
   * @pre n!=null && m!=null 
   * @post (@return.obtenerVertice1()==n &&  @return.obtenerVertice2()==m) ||
           (@return.obtenerVertice1()==m &&  @return.obtenerVertice2()==n)
   */
  public AristaDirigida<TEtiqueta,TElem> crearArista(Vertice<TElem> n, Vertice<TElem> m);
	
  /**
   * Obtiene la arista (si existe) entre los vÃ©rtices n y m.
   * Devuelve null si no hay una arista entre ellos.
   * @pre n !=null && m!=null
   * @post (@return.vertices().contains(m) &&  @return.vertices().contains(n))||@return==null
   */
  public AristaDirigida<TEtiqueta,TElem> obtenerArista(Vertice<TElem> n, Vertice<TElem> m);
  
	/**
   * Obtiene un iterador a las aristas con origen en v.
   * @pre n!=null && m!=null 
   * @post forall @return.value()=> obtenerVerticeOrigen()==v 
   */
   public Iterador<Arista<TEtiqueta,TElem>> aristasConOrigenEn(Vertice<TElem> v);
   
   /**
   * Obtiene un iterador a las aristas con destino en v.
   * @pre n!=null && m!=null 
   * @post forall @return.value()=> obtenerVerticeDestino()==v 
   */
   public Iterador<Arista<TEtiqueta,TElem>> aristasConDestinoEn(Vertice<TElem> v);
}
