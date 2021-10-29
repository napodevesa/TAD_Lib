package uoc.ei.ejercicios.modulo4;

import uoc.ei.ejemplos.modulo4.recorridos.preorden.*;
import uoc.ei.tads.*;

public class Ejercicio2<E> {
	
	public int numeroDeHojas(Arbol<E> arbol) {
		return numeroDeHojas(arbol,arbol.raiz());
	}
	
	
	protected int numeroDeHojas(Arbol<E> arbol,Posicion<E> posicion) {
		int nHojas=0;
		if (arbol.esHoja(posicion))
			nHojas=1;
		else {
			Recorrido<E> hijos=arbol.hijos(posicion);
			while (hijos.haySiguiente()) {
				Posicion<E> hijo=hijos.siguiente();
				nHojas+=numeroDeHojas(arbol,hijo);
			}
		}
		return nHojas;
	}
	

	public int numeroDeHojas(ArbolBinario<E> arbol) {
		return numeroDeHojas(arbol,arbol.raiz());
	}
	
	
	protected int numeroDeHojas(ArbolBinario<E> arbol,Posicion<E> posicion) {
		int nHojas=0;
		if (posicion!=null) {
			nHojas+=numeroDeHojas(arbol,arbol.hijoIzquierdo(posicion));
			nHojas+=numeroDeHojas(arbol,arbol.hijoDerecho(posicion));
			if (nHojas==0)
				nHojas=1;
		}
		return nHojas;
	}
	

	public static void main(String[] args) {
		Ejercicio2<Tarea> ejercicio2=new Ejercicio2<Tarea>();
		// Fem servir com a exemple d'arbol l'arbol de tasques definit per a l'exemple
		// del recorregut en preordre
		ArbolTareas arbol=new ArbolTareas();
		ArbolTareas.popularArbolEjemplo(arbol);
		System.out.println("El arbol es: "+arbol);
		System.out.println("I su número de hojas: "+ejercicio2.numeroDeHojas(arbol));
	}

}
