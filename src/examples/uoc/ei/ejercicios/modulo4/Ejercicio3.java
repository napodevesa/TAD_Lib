package uoc.ei.ejercicios.modulo4;

import uoc.ei.ejemplos.modulo4.recorridos.RecorridosArbol;
import uoc.ei.ejemplos.modulo4.recorridos.niveles.Departamento;
import uoc.ei.ejemplos.modulo4.recorridos.niveles.Empleado;
import uoc.ei.tads.Arbol;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;

public class Ejercicio3<E> extends RecorridosArbol<E> {
		
		public void inorden(Arbol<E> arbol) {
			if (!arbol.estaVacio())
				inorden(arbol,arbol.raiz());
		}
		

		public void inorden(Arbol<E> arbol,Posicion<E> p) {
			Recorrido<E> hijos=arbol.hijos(p);
			if (hijos.haySiguiente())
				inorden(arbol,hijos.siguiente());
			tratarPosicion(p);
			while (hijos.haySiguiente()) {
				Posicion<E> hijo=hijos.siguiente();
				inorden(arbol,hijo);
			}
		}


	public static void main(String[] args) {
		Ejercicio3<Empleado> ejercicio3=new Ejercicio3<Empleado>();
		// Usamos como ejemplo el árbol de ejemplo del recorrido por niveles
		Departamento arbol=new Departamento();
		Departamento.popularArbolEjemplo(arbol);
		ejercicio3.inorden(arbol);
	}

}
