package uoc.ei.ejemplos.modulo6.jcf;

import uoc.ei.ejemplos.modulo6.TablaSimbolos;


public class PruebaTablaSimbolos extends uoc.ei.ejemplos.modulo6.PruebaTablaSimbolos {
	
	
	public TablaSimbolos crearTablaSimbolos() {
		return new TablaSimbolosImpl();
	}
	
	
	public static void main(String[] args) {
		PruebaTablaSimbolos prueba=new PruebaTablaSimbolos();
		prueba.test();
	}

}
