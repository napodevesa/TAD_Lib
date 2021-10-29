package uoc.ei.tads.tests;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.ColaConPrioridad;
import uoc.ei.tads.ExcepcionContenedorVacio;

public class TestColaConPrioridad extends TestContenedor {

	
	protected void insertar(Contenedor<Integer> c,int elem) {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)c;
		cola.encolar(elem);
	}
	
	
	protected Contenedor<Integer> crearContenedor() {
		return new ColaConPrioridad<Integer>();
	}

	
	protected Contenedor<Integer> crearContenedor(int numElems) {
		return new ColaConPrioridad<Integer>(numElems);
	}

	
	/** Este test comprueba los métodos de la cola con prioridad para la
	 * cola vacía. Operaciones probadas: estaVacio, estaLleno, desencolar, primero,
	 * nombreElems, elementos, posiciones.
	 */
	
	public void testColaVacia() {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)crearContenedor();
		assertTrue(cola.estaVacio());
		try {
			cola.desencolar();
		} catch (ExcepcionContenedorVacio e) {}
		try {
			cola.primero();
		} catch (ExcepcionContenedorVacio e) {}
		assertFalse(cola.elementos().haySiguiente());
		assertFalse(cola.estaLleno());
		assertEquals(0,cola.numElems());
		assertFalse(cola.elementos().haySiguiente());
		assertFalse(cola.posiciones().haySiguiente());
	}
	
	
	public void testOrden() {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)crearContenedor();
		cola.encolar(7);
		cola.encolar(3);
		cola.encolar(5);
		cola.encolar(1);
		cola.encolar(9);
		cola.encolar(8);
		cola.encolar(6);
		cola.encolar(4);
		cola.encolar(2);
		cola.encolar(0);
		estaOrdenado(cola.elementos());
	}
	

	public void testOrdenRepetidos() {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)crearContenedor();
		cola.encolar(7);
		cola.encolar(3);
		cola.encolar(3);
		cola.encolar(1);
		cola.encolar(7);
		cola.encolar(7);
		cola.encolar(7);
		cola.encolar(3);
		cola.encolar(5);
		cola.encolar(1);
		estaOrdenado(cola.elementos());
		assertEquals(1,(int)cola.desencolar());
		assertEquals(1,(int)cola.desencolar());
		assertEquals(3,(int)cola.desencolar());
		assertEquals(3,(int)cola.desencolar());
		assertEquals(3,(int)cola.desencolar());
		assertEquals(5,(int)cola.desencolar());
		assertEquals(7,(int)cola.desencolar());
		assertEquals(7,(int)cola.desencolar());
		assertEquals(7,(int)cola.desencolar());
		assertEquals(7,(int)cola.desencolar());
		assertEquals(0,cola.numElems());
		assertTrue(cola.estaVacio());
	}
	

	public void testLlenarVaciar() {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)crearContenedor();
		cola.encolar(7);
		cola.encolar(3);
		cola.encolar(5);
		cola.encolar(1);
		cola.encolar(9);
		assertEquals(1,(int)cola.desencolar());
		assertEquals(3,(int)cola.desencolar());
		assertEquals(5,(int)cola.desencolar());
		assertEquals(7,(int)cola.desencolar());
		assertEquals(9,(int)cola.desencolar());
		assertEquals(0,cola.numElems());
		assertTrue(cola.estaVacio());
		try {
			cola.desencolar();
			fail("la cola está vacía!!!");
		} catch (ExcepcionContenedorVacio e) {}
	}
	
	
	protected void testAleatorio(int numElems,int rangMaxim) {
		ColaConPrioridad<Integer> cola=(ColaConPrioridad<Integer>)crearContenedor(numElems);
		for (int i=0;i<NUM_TESTS_ALEATORIOS;i++) {
			popularAleatoriamente(cola,numElems,rangMaxim);
			estaOrdenado(cola.elementos());
			int elemAnt=Integer.MIN_VALUE,elem;
			for(int j=0;j<numElems;j++) {
				elem=cola.desencolar();
				assertTrue(elemAnt<=elem);
				elemAnt=elem;
			}
			assertTrue(cola.estaVacio());
		}
	}
	
	
	public void testAleatorioRangoGrande() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO*10);
	}
	

	public void testAleatorioRangoPequeno() {
		testAleatorio(NUM_ELEMS_TEST_ALEATORIO,NUM_ELEMS_TEST_ALEATORIO/10);
	}


}
