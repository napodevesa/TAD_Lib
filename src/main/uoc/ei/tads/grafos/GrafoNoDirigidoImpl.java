
package uoc.ei.tads.grafos;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.Utilidades;

/** Implementación de un grafo no dirigido mediante listas de adyacencia.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class GrafoNoDirigidoImpl<TElem,TEtiqueta> extends GrafoAbstracto<TElem,TEtiqueta>
												implements GrafoNoDirigido<TElem,TEtiqueta> {

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
		
	public GrafoNoDirigidoImpl() {
		super();
	}


	protected VerticeImpl<TElem, TEtiqueta> nuevoVertice(TElem valor) {
		return new VerticeNDImpl<TElem,TEtiqueta>(valor);
	}


	public AristaNoDirigida<TEtiqueta, TElem> crearArista(Vertice<TElem> vertex1,
			Vertice<TElem> vertex2) {
		VerticeNDImpl<TElem,TEtiqueta> v1=(VerticeNDImpl<TElem,TEtiqueta>)vertex1;
		VerticeNDImpl<TElem,TEtiqueta> v2=(VerticeNDImpl<TElem,TEtiqueta>)vertex2;
		AristaNDImpl<TEtiqueta,TElem> aresta=new AristaNDImpl<TEtiqueta,TElem>(v1,v2);
		aresta.insertarEnElGrafo(this);
		return aresta;
	}


	public AristaNoDirigida<TEtiqueta, TElem> obtenerArista(Vertice<TElem> vertex1,
			Vertice<TElem> vertex2) {
		AristaNoDirigida<TEtiqueta, TElem> resultat=null;
		VerticeNDImpl<TElem,TEtiqueta> v1=(VerticeNDImpl<TElem,TEtiqueta>)vertex1;
		Iterador<Arista<TEtiqueta, TElem>> arestes=v1.aristas();
		while (arestes.haySiguiente() && resultat==null) {
			AristaImpl<TEtiqueta, TElem> aresta=(AristaImpl<TEtiqueta, TElem>)arestes.siguiente();
			if (aresta.incideEnVertices(vertex1,vertex2))
				resultat=(AristaNoDirigida<TEtiqueta,TElem>)aresta;
		}
		return resultat;
	}

}
