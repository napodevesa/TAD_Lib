package uoc.ei.ejemplos.modulo6;

import uoc.ei.tads.*;

class TablaDispersionSimbolos extends TablaDispersion<String,Pila<PropiedadesSimbolo>> {
	
	private static final int TAMANO_TABLA = 1023;
	
	public TablaDispersionSimbolos() {
		super(TAMANO_TABLA);
	}
	
	protected int calcularIndiceTabla(String clave) {
		int hash=0;
		for(int i=0;i<clave.length();i++)
			hash+=ordinal(clave.charAt(i));
		hash%=TAMANO_TABLA;
		return hash;
	}
	
	
	private int ordinal(char c) {
		if (c=='_') return 0;
		if (Character.isDigit(c)) return 1+c-'0';
		return 11+c-'a';
	}

}
