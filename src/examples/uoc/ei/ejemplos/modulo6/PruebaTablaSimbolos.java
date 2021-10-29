package uoc.ei.ejemplos.modulo6;

import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Categoria;
import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Tipo;


public class PruebaTablaSimbolos {
	
	
	public TablaSimbolos crearTablaSimbolos() {
		return new TablaSimbolosImpl();
	}
	
	
	public void test() {
		TablaSimbolos ts=crearTablaSimbolos();
		ts.declarar("P",Categoria.PROGRAM,Tipo.NO_DEFINIDO, 10230);
		ts.declarar("a",Categoria.VAR,Tipo.INT, 1024);
		ts.declarar("b",Categoria.VAR,Tipo.INT, 1026);
		ts.declarar("Q",Categoria.PROC,Tipo.NO_DEFINIDO, 10500);
		ts.entrar();
		ts.declarar("c",Categoria.PARAM,Tipo.INT, 1028);
		ts.declarar("a",Categoria.VAR,Tipo.REAL, 1030);
		ts.declarar("d",Categoria.VAR,Tipo.REAL, 1032);
		System.out.println("s�mbolo a: "+ts.consultar("a"));
		System.out.println("s�mbolo b: "+ts.consultar("b"));
		System.out.println("s�mbolo c: "+ts.consultar("c"));
		System.out.println("s�mbolo d: "+ts.consultar("d"));
		System.out.println("s�mbolo P: "+ts.consultar("P"));
		System.out.println("s�mbolo Q: "+ts.consultar("Q"));
		ts.salir();
		System.out.println("s�mbolo a: "+ts.consultar("a"));
		System.out.println("s�mbolo b: "+ts.consultar("b"));
		System.out.println("s�mbolo c: "+ts.consultar("c"));
		System.out.println("s�mbolo d: "+ts.consultar("d"));
		System.out.println("s�mbolo P: "+ts.consultar("P"));
		System.out.println("s�mbolo Q: "+ts.consultar("Q"));
	}


	public static void main(String[] args) {
		PruebaTablaSimbolos prova=new PruebaTablaSimbolos();
		prova.test();
	}

}
