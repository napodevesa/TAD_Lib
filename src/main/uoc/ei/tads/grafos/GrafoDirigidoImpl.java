
package uoc.ei.tads.grafos;

import uoc.ei.tads.*;

/** Implementación de un grafo dirigido mediante listas de adyacencia.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class GrafoDirigidoImpl<TElem,TEtiqueta> extends GrafoAbstracto<TElem,TEtiqueta>
											implements GrafoDirigido<TElem,TEtiqueta> {

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
	public GrafoDirigidoImpl() {
		super();
	}


	protected VerticeImpl<TElem, TEtiqueta> nuevoVertice(TElem valor) {
		return new VerticeDImpl<TElem,TEtiqueta>(valor);
	}


	public AristaDirigida<TEtiqueta, TElem> crearArista(Vertice<TElem> origen,
			Vertice<TElem> desti) {
		VerticeDImpl<TElem,TEtiqueta> o=(VerticeDImpl<TElem,TEtiqueta>)origen;
		VerticeDImpl<TElem,TEtiqueta> d=(VerticeDImpl<TElem,TEtiqueta>)desti;
		AristaDImpl<TEtiqueta,TElem> aresta=new AristaDImpl<TEtiqueta,TElem>(o,d);
		aresta.insertarEnElGrafo(this);
		return aresta;
	}


	public AristaDirigida<TEtiqueta, TElem> obtenerArista(Vertice<TElem> origen,
			Vertice<TElem> desti) {
		AristaDirigida<TEtiqueta, TElem> resultat=null;
		Iterador<Arista<TEtiqueta, TElem>> arestes=aristasConOrigenEn(origen);
		while (arestes.haySiguiente() && resultat==null) {
			AristaDirigida<TEtiqueta, TElem> aresta=(AristaDirigida<TEtiqueta, TElem>)arestes.siguiente();
			if (aresta.getVerticeDestino()==desti)
				resultat=aresta;
		}
		return resultat;
	}


	public Iterador<Arista<TEtiqueta, TElem>> aristasConOrigenEn(Vertice<TElem> vertex) {
		VerticeDImpl<TElem,TEtiqueta> v=(VerticeDImpl<TElem,TEtiqueta>)vertex;
		return v.aristasConOrigenEnElVertice.elementos();
	}


	public Iterador<Arista<TEtiqueta, TElem>> aristasConDestinoEn(Vertice<TElem> vertex) {
		VerticeDImpl<TElem,TEtiqueta> v=(VerticeDImpl<TElem,TEtiqueta>)vertex;
		return (Iterador<Arista<TEtiqueta, TElem>>)v.aristasConDestinoEnElVertice.elementos();
	}

}
