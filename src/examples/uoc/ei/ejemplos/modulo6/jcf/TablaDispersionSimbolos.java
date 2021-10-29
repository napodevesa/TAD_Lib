package uoc.ei.ejemplos.modulo6.jcf;

import java.util.*;

import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo;

class TablaDispersionSimbolos extends HashMap<String,Stack<PropiedadesSimbolo>> {
	
	private static final int TAMANO_TABLA = 1023;
	
	public TablaDispersionSimbolos() {
		super(TAMANO_TABLA);
	}
	
}
