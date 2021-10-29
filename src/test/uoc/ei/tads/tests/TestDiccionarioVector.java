package uoc.ei.tads.tests;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioVectorImpl;

public class TestDiccionarioVector extends TestDiccionario {

	public static final int TAMANO_MUCHAS_OPS = 1000;
	public static final int TAMANO_LLENO = 3;
	

	protected Diccionario<Integer,Integer> crearContenedor() {
		return new DiccionarioVectorImpl<Integer,Integer>(TAMANO_MUCHAS_OPS);
	}

	
	public void testDiccionarioVacio() { super.testDiccionarioVacio(); }
	public void testOrden() { super.testOrden(); }
	public void testOrdenRepetidos() { super.testOrdenRepetidos(); }
	public void testLlenarVaciar() { super.testLlenarVaciar(); }
	public void testAleatorioRangoGrande() { super.testAleatorioRangoGrande(); }
	public void testAleatorioRangoPequeno() { super.testAleatorioRangoPequeno(); }

}
