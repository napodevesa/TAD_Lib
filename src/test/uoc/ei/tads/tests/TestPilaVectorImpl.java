package uoc.ei.tads.tests;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.ExcepcionContenedorVacio;
import uoc.ei.tads.ExcepcionContenedorLleno;
import uoc.ei.tads.Pila;
import uoc.ei.tads.PilaVectorImpl;

/** Conjunto de test casas sobre la implementación acotada del TAD pila.
 * PilaVectorImpl.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class TestPilaVectorImpl extends TestContenedor {
	

	protected Contenedor<Integer> crearContenedor() {
		return new PilaVectorImpl<Integer>();
	}

	
	protected Contenedor<Integer> crearContenedor(int numElems) {
		return new PilaVectorImpl<Integer>(numElems);
	}

	
	/** Comprueba el lanzamiento de la excepción correspondiente cuando la pila está
	 * vacía e intentamos desamontonar.
	 * Se comprueban las operaciones: apilar, desapilar, estaVacio.
	 */
	public void testVacio() {
		Pila<Integer> pila=(Pila<Integer>)crearContenedor();
		pila.apilar(5);
		int elem=pila.desapilar();
		assertEquals(5,elem);
		assertTrue(pila.estaVacio());
		try {
			pila.desapilar();
            fail("Expected ExcepcionContenedorVacio");
	} catch (ExcepcionContenedorVacio e) {}
		pila.apilar(8);
		elem=pila.desapilar();
		assertEquals(8,elem);
		assertTrue(pila.estaVacio());
	}

	
	/** Comprueba el lanzamiento de la excepción correspondiente cuando la pila está
	 * llena e intentamos apilar.
	 * Se comprueban las operaciones: apilar, desapilar, estaLleno.
	 */
	public void testLleno() {
		PilaVectorImpl<Integer> pila=(PilaVectorImpl<Integer>)crearContenedor(TAMANO_LLENO);
		pila.apilar(5);
		pila.apilar(7);
		pila.apilar(9);
		assertTrue(pila.estaLleno());
		try {
			pila.apilar(12);
			fail("Expected ExcepcionContenedorLleno");
		} catch (ExcepcionContenedorLleno e) {}
		int elem=pila.desapilar();
		assertEquals(9,elem);
		elem=pila.desapilar();
		assertEquals(7,elem);
		elem=pila.desapilar();
		assertEquals(5,elem);
		assertTrue(pila.estaVacio());
	}

	
	/** Comprueba que los elementos se apilan en una pila y se desapilan
	 * en el orden esperado.
	 * Se comprueban las operaciones: apilar, desapilar.
	 */
	public void testOrden() {
		Pila<Integer> pila=(Pila<Integer>)crearContenedor();
		pila.apilar(5);
		pila.apilar(7);
		pila.apilar(9);
		int elem=pila.desapilar();
		assertEquals(9,elem);
		elem=pila.desapilar();
		assertEquals(7,elem);
		elem=pila.desapilar();
		assertEquals(5,elem);
	}

	
	/** Este test realiza muchas operaciones de apilar y desapilar
	 * sobre una misma pila. Rellena y vacía la pila un número elevado
	 * de veces, comprobando cada vez que el orden en el que se obtienen los
	 * elementos es el correcto.
	 * Se comprueban las operaciones: apilar, desapilar, estaVacio, estaLleno, cima.
	 */
	public void testMuchasOperaciones() {
		PilaVectorImpl<Integer> pila=(PilaVectorImpl<Integer>)crearContenedor(TAMANO_MUCHAS_OPS);
		for (int i=0;i<REPETICIONES_MUCHAS_OPS;i++) {
			for(int j=0;j<TAMANO_MUCHAS_OPS;j++) {
				pila.apilar(j);
				int elem=pila.cima();
				assertEquals(j,elem);
			}
			assertTrue(pila.estaLleno());
			for(int j=0;j<TAMANO_MUCHAS_OPS;j++) {
				int elem=pila.desapilar();
				assertEquals(TAMANO_MUCHAS_OPS-j-1,elem);
			}
			assertTrue(pila.estaVacio());
		}
	}


}
