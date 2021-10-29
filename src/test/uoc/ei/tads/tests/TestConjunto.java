package uoc.ei.tads.tests;

import java.util.ArrayList;

import uoc.ei.tads.Conjunto;
import uoc.ei.tads.Contenedor;
import uoc.ei.tads.ExcepcionPosicionInvalida;

public abstract class TestConjunto extends TestContenedor {

	
	protected void insertar(Contenedor<Integer> c,int elem) {
		((Conjunto<Integer>)c).insertar(elem);
	}

	
	protected boolean borrar(Contenedor<Integer> c,int elem) {
		return ((Conjunto<Integer>)c).borrar(elem)!=null;
	}
	
	
	protected int borrarElemento(Contenedor<Integer> contenedor,ArrayList<Integer> elements) {
		int element=super.borrarElemento(contenedor,elements);
		while (elements.remove(new Integer(element))) {}
		return element;
	}
	

	public void testVacio() {
		Conjunto<Integer> conjunto=(Conjunto<Integer>)crearContenedor();
		assertTrue(conjunto.estaVacio());
		assertFalse(conjunto.elementos().haySiguiente());
		try {
			conjunto.elementos().siguiente();
			fail("el diccionario és buit i caldria que s'hagués generat ExcepcionPosicionInvalida");
		} catch (ExcepcionPosicionInvalida e) {}
		assertFalse(conjunto.esta(5));
		assertNull(conjunto.borrar(5));
	}
	
	
	public void testOrden() {
		Conjunto<Integer> conjunto=(Conjunto<Integer>)crearContenedor();
		insertar(conjunto,7);
		insertar(conjunto,3);
		insertar(conjunto,5);
		insertar(conjunto,1);
		insertar(conjunto,9);
		insertar(conjunto,8);
		insertar(conjunto,6);
		insertar(conjunto,4);
		insertar(conjunto,2);
		insertar(conjunto,0);
		estaOrdenado(conjunto.elementos());
	}
	

	public void testOrdenRepetidos() {
		Conjunto<Integer> conjunto=(Conjunto<Integer>)crearContenedor();
		insertar(conjunto,7);
		insertar(conjunto,3);
		insertar(conjunto,3);
		insertar(conjunto,1);
		insertar(conjunto,7);
		insertar(conjunto,7);
		insertar(conjunto,7);
		insertar(conjunto,3);
		insertar(conjunto,5);
		insertar(conjunto,1);
		estaOrdenado(conjunto.elementos());
	}
	

	public void testLlenarVaciar() {
		Conjunto<Integer> conjunto=(Conjunto<Integer>)crearContenedor();
		insertar(conjunto,7);
		assertFalse(conjunto.estaVacio());
		insertar(conjunto,3);
		insertar(conjunto,5);
		insertar(conjunto,1);
		insertar(conjunto,9);
		assertEquals(1,(int)conjunto.borrar(1));
		assertEquals(null,conjunto.borrar(1));
		assertEquals(3,(int)conjunto.borrar(3));
		assertEquals(null,conjunto.borrar(3));
		assertEquals(5,(int)conjunto.borrar(5));
		assertEquals(null,conjunto.borrar(5));
		assertEquals(9,(int)conjunto.borrar(9));
		assertEquals(null,conjunto.borrar(9));
		assertEquals(7,(int)conjunto.borrar(7));
		assertEquals(null,conjunto.borrar(9));
		assertEquals(0,conjunto.numElems());
		assertTrue(conjunto.estaVacio());
	}
	
	
	protected void testAleatorio(int numElems,int rangoMaxim) {
		Conjunto<Integer> conjunto=(Conjunto<Integer>)crearContenedor();
		for (int i=0;i<NUM_TESTS_ALEATORIOS;i++) {
			ArrayList<Integer> elements=popularAleatoriamente(conjunto,numElems,rangoMaxim);
			estaOrdenado(conjunto.elementos());
			for(int j=0;j<numElems && !conjunto.estaVacio();j++) {
				borrarElemento(conjunto,elements);
				estaOrdenado(conjunto.elementos());
			}
			assertTrue(conjunto.estaVacio());
		}
	}
	
	
	public void testAleatorioRangoGrande() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO*10);
	}
	

	public void testAleatorioRangoPequeno() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO/10);
	}
	

}
