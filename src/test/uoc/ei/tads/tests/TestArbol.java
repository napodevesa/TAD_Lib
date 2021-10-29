package uoc.ei.tads.tests;

import uoc.ei.tads.Arbol;
import uoc.ei.tads.ArbolAbstracto;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;

public class TestArbol extends TestContenedor {
	
	
	protected Posicion<Integer> buscarPosicion(Arbol<Integer> arbol,int elem) {
		Posicion<Integer> posicio=null;
		Recorrido<Integer> r=arbol.posiciones();
		while (r.haySiguiente() && posicio==null) {
			Posicion<Integer> p=r.siguiente();
			if (p.getElem()==elem)
				posicio=p;
		}
		return posicio;
	}
	
	
	/** Este método crea un arbol no completo. Cada nodo tiene como máximo dos hijos.
	 * Durante el proceso de creación se hacen diversas comprobaciones sobre los nodos que
	 * se van añadiendo al arbol.
	 * El arbol creado es:
	 *                                       7
	 *                                  4          2
	 *                                    1      8   15
	 *                                   6           9
	 *                                              5
	 *                                            
	 * Si el contenedor creado es un arbol binario, el arbol se crea como arbol binario. Si no,
	 * se hace como arbol general.
	 * @return
	 */
	
	protected Arbol<Integer> construirArbol742() {
		ArbolAbstracto<Integer> arbol=(ArbolAbstracto<Integer>)crearContenedor();
		assertTrue(arbol.estaVacio());
		assertTrue(arbol.numElems()==0);
		Posicion<Integer> pos7=arbol.insertar(null,7);
		assertTrue(arbol.raiz()==pos7);
		assertTrue(arbol.esHoja(pos7));
		assertTrue(!arbol.estaVacio());
		assertEquals(1,arbol.numElems());
		assertEquals(1,arbol.numElems(pos7));
		Posicion<Integer> pos4=insertarHijoIzquierdo(arbol,pos7,4);
		assertTrue(arbol.esHoja(pos4));
		assertTrue(!arbol.esHoja(pos7));
		assertEquals(2,arbol.numElems());
		assertEquals(2,arbol.numElems(pos7));
		Posicion<Integer> pos2=insertarHijoDerecho(arbol,pos7,2);
		assertTrue(!arbol.esHoja(pos7));
		assertTrue(arbol.esHoja(pos2));
		assertEquals(3,arbol.numElems());
		assertEquals(3,arbol.numElems(pos7));
		Posicion<Integer> pos1=insertarHijoDerecho(arbol,pos4,1);
		assertTrue(arbol.esHoja(pos1));
		assertTrue(!arbol.esHoja(pos4));
		assertEquals(4,arbol.numElems());
		assertEquals(2,arbol.numElems(pos4));
		Posicion<Integer> pos6=insertarHijoIzquierdo(arbol,pos1,6);
		assertTrue(!arbol.esHoja(pos1));
		assertTrue(arbol.esHoja(pos6));
		assertEquals(5,arbol.numElems());
		assertEquals(2,arbol.numElems(pos1));
		Posicion<Integer> pos8=insertarHijoIzquierdo(arbol,pos2,8);
		assertTrue(!arbol.esHoja(pos2));
		assertTrue(arbol.esHoja(pos8));
		assertEquals(6,arbol.numElems());
		assertEquals(2,arbol.numElems(pos2));
		Posicion<Integer> pos15=insertarHijoDerecho(arbol,pos2,15);
		assertTrue(!arbol.esHoja(pos2));
		assertTrue(arbol.esHoja(pos15));
		assertEquals(7,arbol.numElems());
		assertEquals(3,arbol.numElems(pos2));
		Posicion<Integer> pos9=insertarHijoIzquierdo(arbol,pos15,9);
		assertTrue(arbol.esHoja(pos9));
		assertTrue(!arbol.esHoja(pos15));
		assertEquals(8,arbol.numElems());
		Posicion<Integer> pos5=insertarHijoIzquierdo(arbol,pos9,5);
		assertTrue(!arbol.esHoja(pos9));
		assertTrue(arbol.esHoja(pos5));
		assertTrue(arbol.raiz()==pos7);
		assertEquals(9,arbol.numElems());
		assertEquals(9,arbol.numElems(pos7));
		assertEquals(5,arbol.numElems(pos2));
		return arbol;
	}
	
	
	protected Posicion<Integer> insertarHijoIzquierdo(Arbol<Integer> arbol,Posicion<Integer> pare,int element) {
		return arbol.insertar(pare, element);
	}
	

	protected Posicion<Integer> insertarHijoDerecho(ArbolAbstracto<Integer> arbol,Posicion<Integer> pare,int element) {
		return arbol.insertar(pare, element);
	}
	

	public void testCrear742() {
		construirArbol742();
	}

	public void testRecorridoPorNiveles742() {
		Arbol<Integer> arbol=construirArbol742();
		Recorrido<Integer> r=arbol.recorridoPorNiveles();
		int[] elems={7,4,2,1,8,15,6,9,5};
		checkRecorrido(r,elems);
	}
	
	
	public void testRecorridoPreorden742() {
		Arbol<Integer> arbol=construirArbol742();
		Recorrido<Integer> r=arbol.recorridoPreorden();
		int[] elems={7,4,1,6,2,8,15,9,5};
		checkRecorrido(r,elems);
	}


	public void testRecorridoPostorden742() {
		Arbol<Integer> arbol=construirArbol742();
		Recorrido<Integer> r=arbol.recorridoPostorden();
		int[] elems={6,1,4,8,5,9,15,2,7};
		checkRecorrido(r,elems);
	}
	
	
	public void testBorrar742() {
		Arbol<Integer> arbol=construirArbol742();
		Posicion<Integer> pos2=buscarPosicion(arbol,2);
		Posicion<Integer> pos15=buscarPosicion(arbol,15);
		arbol.borrar(pos2,pos15);
		assertEquals(6,arbol.numElems());
		assertEquals(1,contarElems(arbol.hijos(pos2)));
		Posicion<Integer> pos1=buscarPosicion(arbol,1);
		Posicion<Integer> pos6=buscarPosicion(arbol,6);
		arbol.borrar(pos1,pos6);
		assertEquals(5,arbol.numElems());
		assertEquals(0,contarElems(arbol.hijos(pos1)));
	}
	
	
	public void testIntercambiar742() {
		Arbol<Integer> arbol=construirArbol742();
		Posicion<Integer> pos4=buscarPosicion(arbol,4);
		Posicion<Integer> pos15=buscarPosicion(arbol,15);
		Posicion<Integer> pos8=buscarPosicion(arbol,8);
		arbol.intercambiar(pos8, pos15);
		Recorrido<Integer> r=arbol.recorridoPorNiveles();
		int[] elems={7,4,2,1,15,8,6,9,5};
		checkRecorrido(r,elems);
		arbol.intercambiar(pos15, pos4);
		arbol.intercambiar(pos4, pos8);
		arbol.intercambiar(pos4, pos15);
		r=arbol.recorridoPorNiveles();
		int[] elems2={7,4,2,1,8,15,6,9,5};
		checkRecorrido(r,elems2);
	}
	
	
	public void testReemplazar742() {
		Arbol<Integer> arbol=construirArbol742();
		Posicion<Integer> pos4=buscarPosicion(arbol,4);
		Posicion<Integer> pos15=buscarPosicion(arbol,15);
		Posicion<Integer> pos8=buscarPosicion(arbol,8);
		arbol.reemplazar(pos8,15);
		arbol.reemplazar(pos15,8);
		Recorrido<Integer> r=arbol.recorridoPorNiveles();
		int[] elems={7,4,2,1,15,8,6,9,5};
		checkRecorrido(r,elems);
		arbol.reemplazar(pos4, 16);
		arbol.reemplazar(pos8,17);
		arbol.reemplazar(pos15,18);
		r=arbol.recorridoPorNiveles();
		int[] elems2={7,16,2,1,17,18,6,9,5};
		checkRecorrido(r,elems2);
	}
	
	
}
