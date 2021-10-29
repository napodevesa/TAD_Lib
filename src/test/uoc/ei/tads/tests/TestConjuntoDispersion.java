package uoc.ei.tads.tests;

import uoc.ei.tads.ConjuntoTablaImpl;
import uoc.ei.tads.Contenedor;

public class TestConjuntoDispersion extends TestConjunto {

	
	protected Contenedor<Integer> crearContenedor() {
		return new ConjuntoTablaImpl<Integer>();
	}

	
	public void testVacio() { super.testVacio(); }
	public void testOrden() { super.testOrden(); }
	public void testOrdenRepetidos() { super.testOrdenRepetidos(); }
	public void testLlenarVaciar() { super.testLlenarVaciar(); }
	public void testAleatorioRangoGrande() { super.testAleatorioRangoGrande(); }
	public void testAleatorioRangoPequeno() { super.testAleatorioRangoPequeno(); }

}
