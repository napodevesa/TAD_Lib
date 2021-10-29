package uoc.ei.tads.tests;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Cola;
import uoc.ei.tads.ColaVectorImpl;
import uoc.ei.tads.ExcepcionContenedorVacio;
import uoc.ei.tads.ExcepcionContenedorLleno;

/** Conjunto de test casas sobre la implementación con vector del TAD cola.
 * ColaVectorImpl.
 */

public class TestColaVectorImpl extends TestContenedor {
	
	
	protected Contenedor<Integer> crearContenedor() {
		return new ColaVectorImpl<Integer>();
	}

	
	protected Contenedor<Integer> crearContenedor(int numElems) {
		return new ColaVectorImpl<Integer>(numElems);
	}

	
	/** Comprueba el lanzamiento de la excepción correspondiente cuando la cola es
	 * vacía e intentamos desencolar.
	 * Se comprueban las operaciones: encolar, desencolar, estaVacio.
	 */

	public void testVacio() {
		Cola<Integer> cola=(ColaVectorImpl<Integer>)crearContenedor();
		cola.encolar(5);
		int elem=cola.desencolar();
		assertEquals(5,elem);
		assertTrue(cola.estaVacio());
		try {
			cola.desencolar();
			fail("Expected ExcepcionContenedorVacio");
		} catch (ExcepcionContenedorVacio e) {}
		cola.encolar(8);
		elem=cola.desencolar();
		assertEquals(8,elem);
		assertTrue(cola.estaVacio());
	}

	
	/** Comprueba el lanzamiento de la excepción correspondiente cuando la cola está
	 * llena e intentamos encolar.
	 * Se comprueban las operaciones: encolar, desencolar, estaLleno.
	 */

	public void testLleno() {
		ColaVectorImpl<Integer> cola=(ColaVectorImpl<Integer>)crearContenedor(TAMANO_LLENO);
		cola.encolar(5);
		cola.encolar(7);
		cola.encolar(9);
		assertTrue(cola.estaLleno());
		try {
			cola.encolar(12);
			fail("Expected ExcepcionContenedorLleno");
		} catch (ExcepcionContenedorLleno e) {}
		int elem=cola.desencolar();
		assertEquals(5,elem);
		elem=cola.desencolar();
		assertEquals(7,elem);
		elem=cola.desencolar();
		assertEquals(9,elem);
		assertTrue(cola.estaVacio());
	}

	
	/** Comprueba que los elementos se encolan en una cola y se desamontonan
	 * en la orden esperado.
	 * Se comprueban las operaciones: encolar, desencolar.
	 */

	public void testOrden() {
		Cola<Integer> cola=(ColaVectorImpl<Integer>)crearContenedor();
		cola.encolar(5);
		cola.encolar(7);
		cola.encolar(9);
		int elem=cola.desencolar();
		assertEquals(5,elem);
		elem=cola.desencolar();
		assertEquals(7,elem);
		elem=cola.desencolar();
		assertEquals(9,elem);
	}

	
	/** Este test realiza muchas operaciones de encolar y desencolar
	 * sobre una misma cola. Rellena y vacía la cola un número elevado
	 * de golpes, comprobando cada vez que el orden en el que se obtienen los
	 * elementos es el correcto.
	 * Se comprueban las operaciones: encolar, desencolar, estaVacio, estaLleno, primero.
	 */
	
	public void testMuchasOperaciones() {
		ColaVectorImpl<Integer> cola=(ColaVectorImpl<Integer>)crearContenedor(TAMANO_MUCHAS_OPS);
		for (int i=0;i<REPETICIONES_MUCHAS_OPS;i++) {
			for(int j=0;j<TAMANO_MUCHAS_OPS;j++) {
				cola.encolar(j);
				int elem=cola.primero();
				assertEquals(0,elem);
			}
			assertTrue(cola.estaLleno());
			for(int j=0;j<TAMANO_MUCHAS_OPS;j++) {
				int primero=cola.primero();
				assertEquals(j,primero);
				int elem=cola.desencolar();
				assertEquals(j,elem);
			}
			assertTrue(cola.estaVacio());
		}
	}


}
