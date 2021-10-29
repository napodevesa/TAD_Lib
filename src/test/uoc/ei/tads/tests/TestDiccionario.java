package uoc.ei.tads.tests;

import java.util.ArrayList;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Diccionario;
import uoc.ei.tads.ExcepcionPosicionInvalida;

public abstract class TestDiccionario extends TestContenedor {

	
	
	protected void insertar(Contenedor<Integer> c,int elem) {
		((Diccionario<Integer,Integer>)c).insertar(elem,calcularValor(elem));
	}

	
	protected boolean borrar(Contenedor<Integer> c,int elem) {
		return ((Diccionario<Integer,Integer>)c).borrar(elem)!=null;
	}
	
	
	protected int calcularValor(int clave) { return clave*2; }

	
	protected int borrarElemento(Contenedor<Integer> contenedor,ArrayList<Integer> elementos) {
		int elemento=super.borrarElemento(contenedor,elementos);
		while (elementos.remove(new Integer(elemento))) {}
		return elemento;
	}
	

	public void testDiccionarioVacio() {
		Diccionario<Integer,Integer> diccionario=(Diccionario<Integer,Integer>)crearContenedor();
		assertTrue(diccionario.estaVacio());
		assertFalse(diccionario.claves().haySiguiente());
		assertFalse(diccionario.elementos().haySiguiente());
		try {
			diccionario.claves().siguiente();
			fail("el diccionario está vacío i se debería haber lanzado ExcepcionPosicionInvalida");
		} catch (ExcepcionPosicionInvalida e) {}
		try {
			diccionario.elementos().siguiente();
			fail("el diccionario está vacío i se debería haber lanzado ExcepcionPosicionInvalida");
		} catch (ExcepcionPosicionInvalida e) {}
		assertNull(diccionario.consultar(5));
		assertNull(diccionario.borrar(5));
	}
	
	
	public void testOrden() {
		Diccionario<Integer,Integer> diccionario=(Diccionario<Integer,Integer>)crearContenedor();
		insertar(diccionario,7);
		insertar(diccionario,3);
		insertar(diccionario,5);
		insertar(diccionario,1);
		insertar(diccionario,9);
		insertar(diccionario,8);
		insertar(diccionario,6);
		insertar(diccionario,4);
		insertar(diccionario,2);
		insertar(diccionario,0);
		estaOrdenado(diccionario.elementos());
	}
	

	public void testOrdenRepetidos() {
		Diccionario<Integer,Integer> diccionario=(Diccionario<Integer,Integer>)crearContenedor();
		insertar(diccionario,7);
		insertar(diccionario,3);
		insertar(diccionario,3);
		insertar(diccionario,1);
		insertar(diccionario,7);
		insertar(diccionario,7);
		insertar(diccionario,7);
		insertar(diccionario,3);
		insertar(diccionario,5);
		insertar(diccionario,1);
		estaOrdenado(diccionario.elementos());
	}
	

	public void testLlenarVaciar() {
		Diccionario<Integer,Integer> diccionario=(Diccionario<Integer,Integer>)crearContenedor();
		insertar(diccionario,7);
		assertFalse(diccionario.estaVacio());
		insertar(diccionario,3);
		insertar(diccionario,5);
		insertar(diccionario,1);
		insertar(diccionario,9);
		assertEquals(calcularValor(1),(int)diccionario.borrar(1));
		assertEquals(null,diccionario.borrar(1));
		assertEquals(calcularValor(3),(int)diccionario.borrar(3));
		assertEquals(null,diccionario.borrar(3));
		assertEquals(calcularValor(5),(int)diccionario.borrar(5));
		assertEquals(null,diccionario.borrar(5));
		assertEquals(calcularValor(9),(int)diccionario.borrar(9));
		assertEquals(null,diccionario.borrar(9));
		assertEquals(calcularValor(7),(int)diccionario.borrar(7));
		assertEquals(null,diccionario.borrar(9));
		assertEquals(0,diccionario.numElems());
		assertTrue(diccionario.estaVacio());
	}
	
	
	protected void testAleatorio(int numElems,int rangoMaxim) {
		Diccionario<Integer,Integer> diccionario=(Diccionario<Integer,Integer>)crearContenedor();
		for (int i=0;i<NUM_TESTS_ALEATORIOS;i++) {
			ArrayList<Integer> elementos=popularAleatoriamente(diccionario,numElems,rangoMaxim);
			estaOrdenado(diccionario.elementos());
			for(int j=0;j<numElems && !diccionario.estaVacio();j++) {
				borrarElemento(diccionario,elementos);
				estaOrdenado(diccionario.elementos());
			}
			assertTrue(diccionario.estaVacio());
		}
	}
	
	
	public void testAleatorioRangoGrande() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO*10);
	}
	

	public void testAleatorioRangoPequeno() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO/10);
	}
	

}
