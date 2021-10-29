package uoc.ei.tads.tests;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.TablaDispersion;

public class TestDiccionarioDispersion extends TestDiccionario {


	protected Diccionario<Integer,Integer> crearContenedor() {
		return new TablaDispersion<Integer,Integer>(TAMANO_MUCHAS_OPS);
	}

	
	public void testDiccionarioVacio() { super.testDiccionarioVacio(); }
	public void testOrden() { super.testOrden(); }
	public void testOrdenRepetidos() { super.testOrdenRepetidos(); }
	public void testLlenarVaciar() { super.testLlenarVaciar(); }
	public void testAleatorioRangoGrande() { super.testAleatorioRangoGrande(); }
	public void testAleatorioRangoPequeno() { super.testAleatorioRangoPequeno(); }

}
