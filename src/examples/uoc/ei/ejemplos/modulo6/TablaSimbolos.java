package uoc.ei.ejemplos.modulo6;

import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Categoria;
import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Tipo;

public interface TablaSimbolos {
	
	void entrar();
	void salir();
	void declarar(String id, Categoria categoria, Tipo tipo, int dimension);
	PropiedadesSimbolo consultar(String id);

}
