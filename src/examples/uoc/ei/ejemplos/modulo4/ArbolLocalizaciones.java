package uoc.ei.ejemplos.modulo4;

import java.io.IOException;
import java.util.StringTokenizer;
import uoc.ei.tads.ArbolBinarioEncadenadoImpl;
import uoc.ei.tads.ArbolGeneralDelegImpl;
import uoc.ei.tads.Pila;
import uoc.ei.tads.PilaVectorImpl;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Utilidades;

public class ArbolLocalizaciones extends ArbolGeneralDelegImpl<String> {

	
	public ArbolLocalizaciones() {
		super();
	}
	
	
	public ArbolLocalizaciones(String arbolEnTexto) {
		super();
		leer(arbolEnTexto);
	}
	
	
	protected void leer(String arbreEnText) {
		Pila<Posicion<String>> antecesores=new PilaVectorImpl<Posicion<String>>();
		StringTokenizer tokenizer=new StringTokenizer(arbreEnText);
		// cogemos el n�mero de la ra�z y creamos el nodo ra�z
		String nombre=tokenizer.nextToken();
		Posicion<String> posicionActual=insertar(null,nombre);
		while (tokenizer.hasMoreTokens()) {
			nombre=tokenizer.nextToken();
			if (nombre.equals("SUB"))
				antecesores.apilar(posicionActual);
			else if (nombre.equals("FSUB"))
				antecesores.desapilar();
			else {
				Posicion<String> padre=antecesores.cima();
				posicionActual=insertar(padre,nombre);
			}
		}
	}
	
	
	public static void main(String args[]) {
		ArbolLocalizaciones arbre=new ArbolLocalizaciones("Catalunya SUB Vall�sOriental SUB Montmel� FSUB Barcelon�s SUB Barcelona SUB NouBarris Carmel Gr�cia Sarri� FSUB L�Hospitalet FSUB Bages");
		System.out.println("El �rbol resultante es: "+arbre);
	}
	
}
