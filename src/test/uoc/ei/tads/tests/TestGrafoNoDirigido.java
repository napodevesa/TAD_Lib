package uoc.ei.tads.tests;

import uoc.ei.tads.grafos.Arista;
import uoc.ei.tads.grafos.Grafo;
import uoc.ei.tads.grafos.GrafoNoDirigido;
import uoc.ei.tads.grafos.GrafoNoDirigidoImpl;
import uoc.ei.tads.grafos.Vertice;

public class TestGrafoNoDirigido extends TestGrafo {
	
	protected Grafo<Character,Integer> crearGrafo() {
		return new GrafoNoDirigidoImpl<Character,Integer>();
	}

	
	@Override
	protected Arista<Integer, Character> crearArista(
			Grafo<Character, Integer> grafo, Vertice<Character> v1,
			Vertice<Character> v2) {
		return ((GrafoNoDirigido<Character,Integer>)grafo).crearArista(v1, v2);
	}


	@Override
	protected Arista<Integer, Character> obtenerArista(
			Grafo<Character, Integer> grafo, Vertice<Character> v1,
			Vertice<Character> v2) {
		return ((GrafoNoDirigido<Character,Integer>)grafo).obtenerArista(v1, v2);
	}


	public void testCrearGrafoABC() { super.testCrearGrafoABC(); }
	public void testAdyacentes() { super.testAdyacentes(); }
	public void testVertices() { super.testVertices(); }
	public void testAristas() { super.testAristas(); }
	public void testObtenerArista() { super.testObtenerArista(); }
	public void testEliminarVertice() { super.testEliminarVertice(); }
	public void testEliminarArista() { super.testEliminarArista(); }

	
}
