
package uoc.ei.tads.grafos;

import uoc.ei.tads.Posicion;

/**
 * Clase que implementa una arista.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @param <TEtiqueta>
 * @param <TElem>
 */

abstract class AristaImpl<TEtiqueta,TElem> implements Arista<TEtiqueta,TElem> {

	protected VerticeImpl<TElem,TEtiqueta> verticeA,verticeB;
	
	
	/** Etiqueta de la arista. */
	protected TEtiqueta etiqueta;

	
	/** Posicion de la arista en la lista de aristas de GrafoAbstracto.
	 * Implementa el patr�n Locator, y de esta manera logramos
	 * poder borrar la arista de la lista en un tiempo constante
	 * (ya que no es preciso buscarla).
	 */
	protected Posicion<Arista<TEtiqueta,TElem>> posicionListaAristasGrafo;
	
	
	protected Posicion<Arista<TEtiqueta,TElem>> posicionListaDeVerticeA,posicionListaDeVerticeB;


	public AristaImpl(VerticeImpl<TElem,TEtiqueta> vertice1, VerticeImpl<TElem,TEtiqueta> vertice2) {
		this.verticeA = vertice1;
		this.verticeB = vertice2;
	}

	
	/** Retorna la etiqueta asociada a la arista. */
	public TEtiqueta getEtiqueta() { return etiqueta; }
	
	
	/** Modifica el valor de la etiqueta asociada al nodo. */
	public void setEtiqueta(TEtiqueta etiqueta) { this.etiqueta = etiqueta; }
	
	
	public void setPosicionListaGrafo(Posicion<Arista<TEtiqueta,TElem>> posicio) { posicionListaAristasGrafo=posicio; }
	public void setPosicionListaVerticeA(Posicion<Arista<TEtiqueta,TElem>> posicio) { posicionListaDeVerticeA=posicio; }
	public void setPosicionListaVerticeB(Posicion<Arista<TEtiqueta,TElem>> posicio) { posicionListaDeVerticeB=posicio; }
	
	
	/** Consulta si la arista incide en un v�rtice.
	 */
	public boolean incideEnVertice(Vertice<TElem> vertice) {
		return ( this.verticeA.equals(vertice) || this.verticeB.equals(vertice) );
	}
   

	/** Consulta si la arista incide en ambos v�rtices a la vez, sin importar la orden.
	 * @param vertice1
	 * @param vertice2
	 * @return
	 */
   public boolean incideEnVertices(Vertice<TElem> vertice1,Vertice<TElem> vertice2) {
      return incideEnVertice(vertice1) && incideEnVertice(vertice2);
   }
   

   /** Elimina la arista del grafo.
    * @param grafo Grafo al que pertenece la arista.
    */
   public void eliminarDelGrafo(GrafoAbstracto<TElem,TEtiqueta> grafo) {
   	  grafo.listaAristas.borrar(posicionListaAristasGrafo);
   	  verticeA.eliminarArista(this);
   	  verticeB.eliminarArista(this);
   }
   
   
   /** A�ade la arista al grafo. Los v�rtices asociados a la arista deben pertenecer al grafo.
    * @param grafo Grafo al que se a�ade la arista.
    */
   public void insertarEnElGrafo(GrafoAbstracto<TElem,TEtiqueta> grafo) {
   		posicionListaAristasGrafo=grafo.listaAristas.insertarAlFinal(this);
   		verticeA.insertarArista(this);
   		verticeB.insertarArista(this);
   }

   
   /** dado un v�rtice perteneciente a la arista, este m�todo retorna el otro v�rtice
    * perteneciente a la arista.
    * @param verticeA
    * @return
    */
   public Vertice<TElem> extremoAlternativo(Vertice<TElem> vertice) {
   	  return (vertice==this.verticeA) ? verticeB : verticeA;
   }
   

   /** Redefinici�n de este m�todo de object.
    */ 
   public boolean equals(Object object) {
   	  if (getClass()!=object.getClass())
   	  	return false;
   	  AristaImpl<TEtiqueta,TElem> arista=(AristaImpl<TEtiqueta,TElem>)object;
   	  return verticeA.equals(arista.verticeA) &&
   	  		 verticeB.equals(arista.verticeB);
   }


   /** Redefinici�n de este m�todo de object.
    */ 
   public String toString() {
   	  return "("+verticeA+":"+etiqueta+":"+verticeB+")";
   }

}
