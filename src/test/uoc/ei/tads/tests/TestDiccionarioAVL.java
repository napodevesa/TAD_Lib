package uoc.ei.tads.tests;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioAVLImpl;

public class TestDiccionarioAVL extends TestDiccionario {

	
	protected Diccionario<Integer,Integer> crearContenedor() {
		return new DiccionarioAVLImpl<Integer,Integer>();
	}

	
	public void testDiccionarioVacio() { super.testDiccionarioVacio(); }
	public void testOrden() { super.testOrden(); }
	public void testOrdenRepetidos() { super.testOrdenRepetidos(); }
	public void testLlenarVaciar() { super.testLlenarVaciar(); }
	public void testAleatorioRangoGrande() { super.testAleatorioRangoGrande(); }
	public void testAleatorioRangoPequeno() { super.testAleatorioRangoPequeno(); }

}
