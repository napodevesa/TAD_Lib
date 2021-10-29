package uoc.ei.tads.grafos;

import uoc.ei.tads.Iterador;

/** 
 * Grafo (dirigido o no).
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface Grafo<TElem,TEtiqueta> {
 
  /**
   * Obtiene un iterador a los v�rtices del grafo.
   * @pre true
   * @post @return.size()>=1 (un grafo ha de tener al menos un v�rtice, seg�n la definici�n matem�tica).
   */
  public Iterador<Vertice<TElem>> vertices();
  
  
  /** 
   * @return El n�mero de v�rtices del grafo.
   */
  public int numVertices();
  
   /**
   * Obtiene un iterador a las aristas del grafo.
   * @pre true
   * @post true
   */
  public Iterador<Arista<TEtiqueta,TElem>> aristas();
  
   /**
   * Crea un nuevo v�rtice con un valor asociado, y lo incluye en el grafo.
   * @pre valor != null
   * @post vertices().size()=@old.vertices().size()+1
   * @post @return.obtenerValor().equals(valor);
   */
  public Vertice<TElem> crearVertice(TElem valor);
  
  /**
   * Elimina el v�rtice del grafo, incluyendo todas las aristas en las que participa.
   * @pre n!=null
   * @post @old.vertices().contains(n)=>vertices().size()=@old.vertices().size()-1
   */
  public void eliminarVertice(Vertice<TElem> vertice);
  
  
  /** Retorna el vertice correspondiente a un elemento determinado.
   * 
   * @param elem
   * @return El vertice
   */
  public Vertice<TElem> consultarVertice(TElem elem);

  
  /**
   * Elimina la arista del grafo (si estaba en �l)
   * @pre a !=null
   * @post @old.aristas().contains(a)=>aristas().size()=@old.aristas().size()-1
   */
  public void eliminarArista(Arista<TEtiqueta,TElem> arista);
  
  
  /**
   * Obtiene un iterador a los v�rtices adyacentes al v�rtice v.
   * @pre v!=null
   * @post true
   */
  public Iterador<Vertice<TElem>> adyacentes(Vertice<TElem> vertice);
  
}
