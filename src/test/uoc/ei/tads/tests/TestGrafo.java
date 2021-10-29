package uoc.ei.tads.tests;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.grafos.Arista;
import uoc.ei.tads.grafos.Grafo;
import uoc.ei.tads.grafos.GrafoNoDirigido;
import uoc.ei.tads.grafos.Vertice;

public abstract class TestGrafo extends TestContenedor {
	
	
	protected abstract Arista <Integer,Character> crearArista(Grafo<Character,Integer> graf,Vertice<Character> v1,Vertice<Character> v2);
	protected abstract Arista <Integer,Character> obtenerArista(Grafo<Character,Integer> graf,Vertice<Character> v1,Vertice<Character> v2);
	protected abstract Grafo<Character,Integer> crearGrafo();
	
	
	protected void checkVertices(Iterador<Vertice<Character>> i,char[] etiquetas) {
		boolean etiquetaEncontrada=true;
		boolean[] etiquetasVisitadas=new boolean[etiquetas.length];
		while (i.haySiguiente() && etiquetaEncontrada) {
			char etiqueta=i.siguiente().getValor();
			int posicion=0;
			boolean posicionEncontrada=false;
			while (posicion<etiquetas.length && !posicionEncontrada) {
				posicionEncontrada=!etiquetasVisitadas[posicion] && etiqueta==etiquetas[posicion];
				if (!posicionEncontrada)
					posicion++;
			}
			if (posicionEncontrada)
				etiquetasVisitadas[posicion]=true;
			else {
				etiquetaEncontrada=false;
				fail("label "+etiqueta+" not found in defined set of labels");
			}
		}
		for (int j=0;j<etiquetas.length;j++)
			if (!etiquetasVisitadas[j])
				fail("label "+etiquetas[j]+" not present in Iterador of Vertice");
	}
	
	
	protected void checkAristas(Iterador<Arista<Integer,Character>> i,int[] etiquetas) {
		boolean etiquetaEncontrada=true;
		boolean[] etiquetasVisitadas=new boolean[etiquetas.length];
		while (i.haySiguiente() && etiquetaEncontrada) {
			int etiqueta=i.siguiente().getEtiqueta();
			int posicion=0;
			boolean posicionEncontrada=false;
			while (posicion<etiquetas.length && !posicionEncontrada) {
				posicionEncontrada=!etiquetasVisitadas[posicion] && etiqueta==etiquetas[posicion];
				if (!posicionEncontrada)
					posicion++;
			}
			if (posicionEncontrada)
				etiquetasVisitadas[posicion]=true;
			else {
				etiquetaEncontrada=false;
				fail("label "+etiqueta+" not found in defined set of labels");
			}
		}
		for (int j=0;j<etiquetas.length;j++)
			if (!etiquetasVisitadas[j])
				fail("label "+etiquetas[j]+" not present in Iterador of Arista");
	}
	
	
	/** Este método crea un grafo. Sirve tanto para grafos dirigidos como para grafos
	 * no dirigidos.
	 * @return
	 */
	
	protected Grafo<Character,Integer> construirGrafoABC() {
		Grafo<Character,Integer> grafo=crearGrafo();
		assertFalse(grafo.vertices().haySiguiente());
		assertFalse(grafo.aristas().haySiguiente());
		Vertice<Character> va=grafo.crearVertice('A');
		Vertice<Character> vb=grafo.crearVertice('B');
		Vertice<Character> vc=grafo.crearVertice('C');
		Vertice<Character> vd=grafo.crearVertice('D');
		assertEquals(4,grafo.numVertices());
		assertEquals(4,contarElems(grafo.vertices()));
		assertFalse(grafo.aristas().haySiguiente());
		Vertice<Character> ve=grafo.crearVertice('E');
		Vertice<Character> vf=grafo.crearVertice('F');
		Vertice<Character> vg=grafo.crearVertice('G');
		Vertice<Character> vh=grafo.crearVertice('H');
		assertEquals(8,grafo.numVertices());
		assertEquals(8,contarElems(grafo.vertices()));
		assertFalse(grafo.aristas().haySiguiente());
		Arista<Integer,Character> aristaAB=crearArista(grafo,vb,va);
		aristaAB.setEtiqueta(2);
		Arista<Integer,Character> aristaAC=crearArista(grafo,va,vc);
		aristaAC.setEtiqueta(5);
		Arista<Integer,Character> aristaAD=crearArista(grafo,vd,va);
		aristaAD.setEtiqueta(10);
		assertEquals(3,contarElems(grafo.aristas()));
		Arista<Integer,Character> aristaAE=crearArista(grafo,va,ve);
		aristaAE.setEtiqueta(5);
		Arista<Integer,Character> aristaAF=crearArista(grafo,va,vf);
		aristaAF.setEtiqueta(4);
		Arista<Integer,Character> aristaBG=crearArista(grafo,vg,vb);
		aristaBG.setEtiqueta(1);
		assertEquals(6,contarElems(grafo.aristas()));
		Arista<Integer,Character> aristaBH=crearArista(grafo,vb,vh);
		aristaBH.setEtiqueta(7);
		Arista<Integer,Character> aristaCG=crearArista(grafo,vg,vc);
		aristaCG.setEtiqueta(9);
		Arista<Integer,Character> aristaEH=crearArista(grafo,vh,ve);
		aristaEH.setEtiqueta(18);
		Arista<Integer,Character> aristaFH=crearArista(grafo,vf,vh);
		aristaFH.setEtiqueta(2);
		assertEquals(10,contarElems(grafo.aristas()));
		assertEquals(va,grafo.consultarVertice('A'));
		assertEquals(vb,grafo.consultarVertice('B'));
		assertEquals(vc,grafo.consultarVertice('C'));
		assertEquals(vd,grafo.consultarVertice('D'));
		assertEquals(ve,grafo.consultarVertice('E'));
		assertEquals(vf,grafo.consultarVertice('F'));
		assertEquals(vg,grafo.consultarVertice('G'));
		assertEquals(vh,grafo.consultarVertice('H'));
		assertEquals(aristaAB,obtenerArista(grafo, vb, va));
		assertEquals(aristaAC,obtenerArista(grafo, va, vc));
		assertEquals(aristaAD,obtenerArista(grafo, vd, va));
		assertEquals(aristaAE,obtenerArista(grafo, va, ve));
		assertEquals(aristaAF,obtenerArista(grafo, va, vf));
		assertEquals(aristaBG,obtenerArista(grafo, vg, vb));
		assertEquals(aristaBH,obtenerArista(grafo, vb, vh));
		assertEquals(aristaCG,obtenerArista(grafo, vg, vc));
		assertEquals(aristaEH,obtenerArista(grafo, vh, ve));
		assertEquals(aristaFH,obtenerArista(grafo, vf, vh));
		if (grafo instanceof GrafoNoDirigido) {
			assertEquals(aristaAB,obtenerArista(grafo, vb, va));
			assertEquals(aristaAC,obtenerArista(grafo, vc, va));
			assertEquals(aristaAD,obtenerArista(grafo, va, vd));
			assertEquals(aristaAE,obtenerArista(grafo, ve, va));
			assertEquals(aristaAF,obtenerArista(grafo, vf, va));
			assertEquals(aristaBG,obtenerArista(grafo, vb, vg));
			assertEquals(aristaBH,obtenerArista(grafo, vh, vb));
			assertEquals(aristaCG,obtenerArista(grafo, vc, vg));
			assertEquals(aristaEH,obtenerArista(grafo, ve, vh));
			assertEquals(aristaFH,obtenerArista(grafo, vh, vf));
		}
		return grafo;
	}
	
	
	public void testCrearGrafoABC() {
		construirGrafoABC();
	}
	
	
	public void testAdyacentes() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		Vertice<Character> va=grafo.consultarVertice('A');
		Iterador<Vertice<Character>> i=grafo.adyacentes(va);
		char[] valoresA={'B', 'C', 'D', 'E', 'F'}; 
		checkVertices(i,valoresA);
		Vertice<Character> vd=grafo.consultarVertice('D');
		i=grafo.adyacentes(vd);
		char[] valoresD={'A'}; 
		checkVertices(i,valoresD);
		Vertice<Character> ve=grafo.consultarVertice('E');
		i=grafo.adyacentes(ve);
		char[] valoresE={'A', 'H'}; 
		checkVertices(i,valoresE);
	}
	
	
	public void testVertices() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		char[] etiquetas={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'}; 
		checkVertices(grafo.vertices(),etiquetas);
	}
	
	
	public void testAristas() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		int[] etiquetas={1, 2, 2, 4, 5, 5, 7, 9, 10, 18};
		checkAristas(grafo.aristas(),etiquetas);
	}
	
	
	public void testObtenerArista() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		Vertice<Character> va=grafo.consultarVertice('A');
		Vertice<Character> vc=grafo.consultarVertice('C');
		Vertice<Character> vg=grafo.consultarVertice('G');
		Vertice<Character> vb=grafo.consultarVertice('B');
		Arista<Integer,Character> arista=obtenerArista(grafo,va,vc);
		assertEquals(5,(int)arista.getEtiqueta());
		arista=obtenerArista(grafo,vg,vc);
		assertEquals(9,(int)arista.getEtiqueta());
		arista=obtenerArista(grafo,vg,vb);
		assertEquals(1,(int)arista.getEtiqueta());
		arista=obtenerArista(grafo,vb,va);
		assertEquals(2,(int)arista.getEtiqueta());
	}
	
	
	public void testEliminarVertice() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		grafo.eliminarVertice(grafo.consultarVertice('B'));
		assertEquals(7,grafo.numVertices());
		char[] etiquetas1={'A', 'C', 'D', 'E', 'F', 'G', 'H'};
		checkVertices(grafo.vertices(),etiquetas1);

		grafo.eliminarVertice(grafo.consultarVertice('D'));
		assertEquals(6,grafo.numVertices());
		char[] etiquetas2={'A', 'C', 'E', 'F', 'G', 'H'};
		checkVertices(grafo.vertices(),etiquetas2);
		
		grafo.eliminarVertice(grafo.consultarVertice('F'));
		assertEquals(5,grafo.numVertices());
		char[] etiquetas3={'A', 'C', 'E', 'G', 'H'};
		checkVertices(grafo.vertices(),etiquetas3);

		Vertice<Character> va=grafo.consultarVertice('A');
		Iterador<Vertice<Character>> i=grafo.adyacentes(va);
		char[] valoresA={'C', 'E'}; 
		checkVertices(i,valoresA);

		Vertice<Character> vd=grafo.consultarVertice('D');
		assertNull(vd);

		Vertice<Character> ve=grafo.consultarVertice('E');
		i=grafo.adyacentes(ve);
		char[] valoresE={'A', 'H'}; 
		checkVertices(i,valoresE);
	}
	
	
	public void testEliminarArista() {
		Grafo<Character,Integer> grafo=construirGrafoABC();
		Vertice<Character> va=grafo.consultarVertice('A');
		Vertice<Character> vb=grafo.consultarVertice('B');
		Vertice<Character> vc=grafo.consultarVertice('C');
		Vertice<Character> vd=grafo.consultarVertice('D');
		Vertice<Character> vf=grafo.consultarVertice('F');
		Vertice<Character> vg=grafo.consultarVertice('G');
		Vertice<Character> vh=grafo.consultarVertice('H');
		
		grafo.eliminarArista(obtenerArista(grafo, vb, vh));
		grafo.eliminarArista(obtenerArista(grafo, va, vf));
		grafo.eliminarArista(obtenerArista(grafo, vd, va));
		grafo.eliminarArista(obtenerArista(grafo, vg, vc));
		
		assertNull(obtenerArista(grafo, vb, vh));
		assertNull(obtenerArista(grafo, va, vf));
		assertNull(obtenerArista(grafo, vd, va));
		assertNull(obtenerArista(grafo, vg, vc));
		
		assertNull(obtenerArista(grafo, vh, vb));
		assertNull(obtenerArista(grafo, vf, va));
		assertNull(obtenerArista(grafo, va, vd));
		assertNull(obtenerArista(grafo, vc, vg));
		
		int[] etiquetas={1, 2, 2, 5, 5, 18};
		checkAristas(grafo.aristas(),etiquetas);
		
		char[] valoresA={'B', 'C', 'E'}; 
		checkVertices(grafo.adyacentes(va),valoresA);
		char[] valoresB={'G', 'A'}; 
		checkVertices(grafo.adyacentes(vb),valoresB);
		char[] valoresC={'A'}; 
		checkVertices(grafo.adyacentes(vc),valoresC);
		char[] valoresD={}; 
		checkVertices(grafo.adyacentes(vd),valoresD);
		char[] valoresF={'H'}; 
		checkVertices(grafo.adyacentes(vf),valoresF);
		char[] valoresG={'B'}; 
		checkVertices(grafo.adyacentes(vg),valoresG);
		char[] valoresH={'F', 'E'}; 
		checkVertices(grafo.adyacentes(vh),valoresH);
	}
	
	
}
