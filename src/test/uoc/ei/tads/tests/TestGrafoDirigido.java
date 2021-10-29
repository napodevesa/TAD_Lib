package uoc.ei.tads.tests;

import uoc.ei.tads.grafos.Arista;
import uoc.ei.tads.grafos.Grafo;
import uoc.ei.tads.grafos.GrafoDirigido;
import uoc.ei.tads.grafos.GrafoDirigidoImpl;
import uoc.ei.tads.grafos.Vertice;

public class TestGrafoDirigido extends TestGrafo {
	
	protected Grafo<Character,Integer> crearGrafo() {
		return new GrafoDirigidoImpl<Character,Integer>();
	}

	
	@Override
	protected Arista<Integer, Character> crearArista(
			Grafo<Character, Integer> grafo, Vertice<Character> v1,
			Vertice<Character> v2) {
		return ((GrafoDirigido<Character,Integer>)grafo).crearArista(v1, v2);
	}


	@Override
	protected Arista<Integer, Character> obtenerArista(
			Grafo<Character, Integer> grafo, Vertice<Character> v1,
			Vertice<Character> v2) {
		return ((GrafoDirigido<Character,Integer>)grafo).obtenerArista(v1, v2);
	}


	public void testCrearGrafoABC() { super.testCrearGrafoABC(); }
	public void testAdyacentes() { super.testAdyacentes(); }
	public void testVertices() { super.testVertices(); }
	public void testAristas() { super.testAristas(); }
	public void testObtenerArista() { super.testObtenerArista(); }
	public void testEliminarVertice() { super.testEliminarVertice(); }
	public void testEliminarArista() { super.testEliminarArista(); }

	
	public void testArestesAmbDestiA() {
		GrafoDirigido<Character,Integer> grafo=(GrafoDirigido<Character,Integer>)construirGrafoABC();
		Vertice<Character> va=grafo.consultarVertice('A');
		Vertice<Character> vc=grafo.consultarVertice('C');
		Vertice<Character> vg=grafo.consultarVertice('G');
		Vertice<Character> vb=grafo.consultarVertice('B');
		int[] etiquetasA={2, 10};
		checkAristas(grafo.aristasConDestinoEn(va),etiquetasA);
		int[] etiquetasC={9, 5};
		checkAristas(grafo.aristasConDestinoEn(vc),etiquetasC);
		int[] etiquetasG={};
		checkAristas(grafo.aristasConDestinoEn(vg),etiquetasG);
		int[] etiquetasB={1};
		checkAristas(grafo.aristasConDestinoEn(vb),etiquetasB);
	}

	
	public void testArestesAmbOrigenA() {
		GrafoDirigido<Character,Integer> grafo=(GrafoDirigido<Character,Integer>)construirGrafoABC();
		Vertice<Character> va=grafo.consultarVertice('A');
		Vertice<Character> vc=grafo.consultarVertice('C');
		Vertice<Character> vg=grafo.consultarVertice('G');
		Vertice<Character> vb=grafo.consultarVertice('B');
		int[] etiquetasA={5, 5, 4};
		checkAristas(grafo.aristasConOrigenEn(va),etiquetasA);
		int[] etiquetasC={};
		checkAristas(grafo.aristasConOrigenEn(vc),etiquetasC);
		int[] etiquetasG={9, 1};
		checkAristas(grafo.aristasConOrigenEn(vg),etiquetasG);
		int[] etiquetasB={2, 7};
		checkAristas(grafo.aristasConOrigenEn(vb),etiquetasB);
	}

	
}
