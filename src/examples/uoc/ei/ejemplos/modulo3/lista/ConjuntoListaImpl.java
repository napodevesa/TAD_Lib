
package uoc.ei.ejemplos.modulo3.lista;

import java.io.IOException;

import uoc.ei.ejemplos.modulo1.generics.Conjunto;
import uoc.ei.tads.*;

/** Implementaci�n del TAD Conjunto que utiliza una tabla como representaci�n para
 * guardar los elementos. Esta implementaci�n tiene la restricci�n de que hay que
 * especificar en el momento de la creaci�n del conjunto el n�mero de elementos m�ximo que
 * se guardar�n en el conjunto.
 */
public class ConjuntoListaImpl<E> implements Conjunto<E> {
	
	/** Lista que usamos para representar los elementos
	 * del Conjunto. */
	private Lista<E> listaDeElementos;

	
	public ConjuntoListaImpl() {
		listaDeElementos=new ListaEncadenada<E>();
	}

	
	public void insertar(E elem) {
		if (!esta(elem))
			listaDeElementos.insertarAlFinal(elem);
	}
	
	
	public boolean esta(E elem) {
		boolean encontrado=false;
		Iterador<E> iter=listaDeElementos.elementos();
		while (!encontrado && iter.haySiguiente())
			encontrado=elem.equals(iter.siguiente());
		return encontrado;
	}
	

	public E borrar(E elem) {
		E elementoBorrado=null;
		boolean encontrado=false;
		Recorrido<E> rec=listaDeElementos.posiciones();
		Posicion<E> anterior=null,actual=null;
		while (!encontrado && rec.haySiguiente()) {
			anterior=actual;
			actual=rec.siguiente();
			encontrado=actual!=null && elem.equals(actual.getElem());
		}
		if (encontrado)
			elementoBorrado=listaDeElementos.borrarSiguiente(anterior);
		return elementoBorrado;
	}
	
	
	public boolean estaVacio() {
		return listaDeElementos.estaVacio();
	}
	
	
	public String toString() {
		return Utilidades.toStringContenedor("ConjuntoListaImpl",listaDeElementos.posiciones());
	}


	public static void main(String[] args) {
		System.out.println("Introduce n�meros naturales a a�adir al conjunto");
		boolean salir=false;
		Conjunto<Integer> cjt=new ConjuntoListaImpl<Integer>();
		while (!salir) {
			try {
				String elem=Utilidades.leerString("Introduce un n�mero (o \"fin\" para acabar): ",System.in);
				salir=elem.equalsIgnoreCase("fin");
				if (!salir)
					cjt.insertar(Integer.parseInt(elem));
			} catch (NumberFormatException e) {
				System.out.println("El texto introducido no es un n�mero, vuelvelo a probar.");
			} catch (IOException e) {
				System.out.println("Problema con la entrada, abortando el programa");
				salir=true;
			}
		}
		System.out.println("El conjunto resultante es: "+cjt);
	}

}
