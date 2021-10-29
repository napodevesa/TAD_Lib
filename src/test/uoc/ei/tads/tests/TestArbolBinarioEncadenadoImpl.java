package uoc.ei.tads.tests;

import uoc.ei.tads.ArbolBinarioEncadenadoImpl;
import uoc.ei.tads.Contenedor;

public class TestArbolBinarioEncadenadoImpl extends TestArbol {

	
	protected Contenedor<Integer> crearContenedor() {
		return new ArbolBinarioEncadenadoImpl<Integer>();
	}

	
	public void testCrear742() { super.testCrear742(); }
	public void testRecorridoPorNiveles742() { super.testRecorridoPorNiveles742(); }
	public void testRecorridoPreorden742() { super.testRecorridoPreorden742(); }
	public void testRecorridoPostorden742() { super.testRecorridoPostorden742(); }
	public void testBorrar742() { super.testBorrar742(); }
	public void testIntercambiar742() { super.testIntercambiar742(); }
	public void testReemplazar742() { super.testReemplazar742(); }

}
