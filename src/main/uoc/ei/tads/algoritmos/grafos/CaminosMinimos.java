package uoc.ei.tads.algoritmos.grafos;

import uoc.ei.tads.*;
import uoc.ei.tads.grafos.*;

/** Proporciona una implementaciçon para el algoritmo de caminos
 * mínimos.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class CaminosMinimos<TElem,TEtiqueta extends Number> {
	
	
	protected static class VCM<E> {
		private Vertice<E> vertice;
		private double distancia;
		private boolean yaTenemosDistanciaMinima;
		
		public VCM(Vertice<E> vertice,double distancia) {
			this.vertice=vertice;
			this.distancia=distancia;
			this.yaTenemosDistanciaMinima=false;
		}
		
		public void yaTenemosDistanciaDefinitiva() { yaTenemosDistanciaMinima=true; } 
		public boolean tenemosYaDistanciaDefinitiva() { return yaTenemosDistanciaMinima; }
		public double getDistancia() { return distancia; }
		public Vertice<E> getVertice() { return vertice; }
		public void setDistancia(double distancia) { this.distancia=distancia; }
		
		public String toString() { return "["+vertice+","+distancia+","+yaTenemosDistanciaMinima+"]"; }
		
	}
	

	public ClaveValor<Vertice<TElem>,Number>[] calcular(GrafoDirigido<TElem,TEtiqueta> grafo, Vertice<TElem> vertice) {
		VCM<TElem>[] d=(VCM<TElem>[])new VCM[grafo.numVertices()];
		int verticesConDistanciaDefinitiva=0;
		Iterador<Vertice<TElem>> vertices=grafo.vertices();
		// calculamos distancias iniciales
		for(int i=0;vertices.haySiguiente();i++) {
			Vertice<TElem> v=vertices.siguiente();
			d[i]=new VCM<TElem>(v,distanciaDirecta(grafo,vertice,v));
			if (d[i].getDistancia()==0) {
				d[i].yaTenemosDistanciaDefinitiva();
				verticesConDistanciaDefinitiva++;
			}
		}
		while (verticesConDistanciaDefinitiva<grafo.numVertices()-1) {
			int posicionW=escogerVerticeMasProximo(grafo,d);
			d[posicionW].yaTenemosDistanciaDefinitiva();
			verticesConDistanciaDefinitiva++;
			// actualizamos las distancias comprobando si mediante
			// w llegamos antes
			double distanciaAW=d[posicionW].getDistancia();
			for(int i=0;i<d.length;i++) {
				if (!d[i].tenemosYaDistanciaDefinitiva()) {
					double distanciaDesdeW=distanciaDirecta(grafo,d[posicionW].getVertice(),d[i].getVertice());
					if (distanciaAW+distanciaDesdeW<d[i].getDistancia())
						d[i].setDistancia(distanciaAW+distanciaDesdeW);
				}
			}
		}
		ClaveValor<Vertice<TElem>,Number>[] resultado=(ClaveValor<Vertice<TElem>,Number>[])new ClaveValor[d.length];
		for(int i=0;i<d.length;i++)
			resultado[i]=new ClaveValor<Vertice<TElem>,Number>(d[i].getVertice(),d[i].getDistancia());
		return resultado;
	}
	
	
	private int escogerVerticeMasProximo(GrafoDirigido<TElem,TEtiqueta> grafo,VCM<TElem>[] distanciasActuals) {
		double minim=Double.POSITIVE_INFINITY;
		int posicionW=-1;
		// escogemos vertice con menor distancia directa que no está en s
		for(int i=0;i<distanciasActuals.length;i++)
			if (!distanciasActuals[i].tenemosYaDistanciaDefinitiva())
				if (distanciasActuals[i].getDistancia()<minim) {
					minim=distanciasActuals[i].getDistancia();
					posicionW=i;
				}
		return posicionW;
	}
	
	
	private double distanciaDirecta(GrafoDirigido<TElem,TEtiqueta> grafo,Vertice<TElem> origen,Vertice<TElem> destino) {
		double valor;
		if (origen==destino)
			valor=0;
		else {
			Arista<TEtiqueta,TElem> aresta=grafo.obtenerArista(origen,destino);
			if (aresta!=null)
				valor=aresta.getEtiqueta().doubleValue();
			else
				valor=Double.POSITIVE_INFINITY;
		}
		return valor;
	}

}
