package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.*;
import uoc.ei.tads.grafos.*;

class AsignaturasProfesor extends ListaEncadenada<Vertice<Asignatura>> {
	
	private double creditos;
	
	
	public AsignaturasProfesor() {
		creditos=0;
	}
	
	
	public double getCreditos() { return creditos; }
	
	
	public Vertice<Asignatura> borrarPrimero() {
		Vertice<Asignatura> asig=super.borrarPrimero();
		creditos-=asig.getValor().getCreditos();
		return asig;
	}

   
	public Vertice<Asignatura> borrar(Posicion<Vertice<Asignatura>> nodo) {
		Vertice<Asignatura> asig=super.borrar(nodo);
		creditos-=asig.getValor().getCreditos();
		return asig;
	}

	
	public Vertice<Asignatura> borrarSiguiente(Posicion<Vertice<Asignatura>> nodo) {
		Vertice<Asignatura> asig=super.borrarSiguiente(nodo);
		creditos-=asig.getValor().getCreditos();
		return asig;
	}

	
	public Vertice<Asignatura> reemplazar(Posicion<Vertice<Asignatura>> nodo, Vertice<Asignatura> elem) {
		Vertice<Asignatura> old = super.reemplazar(nodo,elem);
		creditos=creditos-old.getValor().getCreditos()+elem.getValor().getCreditos();
		return old;
	}

		   
	protected NodoEncadenado<Vertice<Asignatura>> nuevaPosicion(NodoEncadenado<Vertice<Asignatura>> nodo,Vertice<Asignatura> elem) {
		creditos+=elem.getValor().getCreditos();
		return super.nuevaPosicion(nodo,elem);
	}
		   
}
