
package uoc.ei.tads.grafos;

import java.io.Serializable;

import uoc.ei.tads.*;

/**
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
abstract class GrafoAbstracto<TElem,TEtiqueta> implements Grafo<TElem,TEtiqueta>, Serializable {
	
	
	//-----------------------------------------------------------------------
	// atributos 
	//-----------------------------------------------------------------------
	
	ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>> listaAristas;

	
	Diccionario<TElem,Vertice<TElem>> diccionarioVertices;
	
	
	//-----------------------------------------------------------------------
	// metodos 
	//-----------------------------------------------------------------------
	
	public GrafoAbstracto() {
		diccionarioVertices=crearDiccionarioVertices();
		listaAristas=new ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>>();
	}
	
	
	protected Diccionario<TElem,Vertice<TElem>> crearDiccionarioVertices() {
		return new DiccionarioListaImpl<TElem,Vertice<TElem>>();
	}
	

	public Iterador<Vertice<TElem>> vertices() {
		return diccionarioVertices.elementos();
	}
	
	
	public int numVertices() {
		return diccionarioVertices.numElems();
	}


	public Iterador<Arista<TEtiqueta, TElem>> aristas() {
		return listaAristas.elementos();
	}


	public Vertice<TElem> crearVertice(TElem valor) {
		Vertice<TElem> vertex=nuevoVertice(valor);
		diccionarioVertices.insertar(valor,vertex);
		return vertex;
	}
	

	public void eliminarVertice(Vertice<TElem> vertex) {
		VerticeImpl<TElem,TEtiqueta> v=(VerticeImpl<TElem,TEtiqueta>)vertex;
		Iterador<Arista<TEtiqueta,TElem>> arestes=v.aristas();
		Lista<Arista<TEtiqueta,TElem>> arestesAEsborrar=new ListaEncadenada<Arista<TEtiqueta,TElem>>();
		while (arestes.haySiguiente()) {
			Arista<TEtiqueta,TElem> aresta=arestes.siguiente();
			arestesAEsborrar.insertarAlFinal(aresta);
		}
		while (!arestesAEsborrar.estaVacio()) {
			Arista<TEtiqueta,TElem> aresta=arestesAEsborrar.borrarPrimero();
			eliminarArista(aresta);
		}
		diccionarioVertices.borrar(vertex.getValor());
	}

	
	public void eliminarArista(Arista<TEtiqueta, TElem> aresta) {
		((AristaImpl<TEtiqueta,TElem>)aresta).eliminarDelGrafo(this);
	}


	public Iterador<Vertice<TElem>> adyacentes(Vertice<TElem> vertex) {
		return new IteradorAdyacentes<TElem,TEtiqueta>((VerticeImpl<TElem,TEtiqueta>)vertex);
	}
	
	
	public Vertice<TElem> consultarVertice(TElem elem) {
		return diccionarioVertices.consultar(elem);
	}
	
	
	protected abstract VerticeImpl<TElem,TEtiqueta> nuevoVertice(TElem valor);
	
	
	public String toString() {
		return "[Grafo\n  "+listaAristas+"\n"+diccionarioVertices+"]";
	}

	
	//-----------------------------------------------------------------------
	// clases internas: iteradores i recorridoss
	//-----------------------------------------------------------------------
	
	/** Classe que proporciona un iterador sobre els vertices adyacentes a un vertice dado.
	 * @author Jordi Alvarez
	 * @author Esteve Mariné
	 *          Estructura de la Información,
	 *          Universitat Oberta de Catalunya (UOC)
	 * @version 2.0.0
	 * @param <TElem>
	 * @param <TEtiqueta>
	 */
	
	static class IteradorAdyacentes<TElem,TEtiqueta> implements Iterador<Vertice<TElem>> {
		
		Iterador<Arista<TEtiqueta,TElem>> aristas;
		Vertice<TElem> vertice;
		
		public IteradorAdyacentes(VerticeImpl<TElem,TEtiqueta> vertice) {
			this.vertice=vertice;
			aristas=vertice.aristas();
		}
		
		
		public Vertice<TElem> siguiente() {
			AristaImpl<TEtiqueta,TElem> arista=(AristaImpl<TEtiqueta,TElem>)aristas.siguiente();
			return arista.extremoAlternativo(vertice);
		}
		
		
		public boolean haySiguiente() {
			return aristas.haySiguiente();
		}
		
		
	}
	
	
}
