package uoc.ei.ejemplos.modulo3.lista;

import java.io.IOException;

import uoc.ei.tads.*;

public class ListaEncadenadaConIterador<E> extends ListaEncadenada<E> {

	private static final long serialVersionUID = 1;
	
	
	public Iterador<E> elementos() {
		return new IteradorLlista<E>(this);
	}
	
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		Iterador<E> iter=elementos();
		while (iter.haySiguiente()) {
			sb.append(iter.siguiente());
			if (iter.haySiguiente())
				sb.append(", ");
		}
		return sb.toString();
	}
	

	protected static class IteradorLlista<EI> implements Iterador<EI> {
		
		private static final long serialVersionUID = 1;
		
		private NodoEncadenado<EI> ultimo;
		private NodoEncadenado<EI> siguiente;
		
		IteradorLlista(ListaEncadenadaConIterador<EI> ll) {
			this.ultimo=ll.ultimo;
			if (ultimo!=null)
				siguiente=ultimo.getSiguiente();
		}
		
		public boolean haySiguiente() {
			return siguiente!=null;
		}

		public EI siguiente() throws ExcepcionPosicionInvalida {
			NodoEncadenado<EI> aux=siguiente;
			siguiente = siguiente==ultimo ? null : siguiente.getSiguiente();
			return aux.getElem();
		}
	}


	public static void main(String[] args) {
		System.out.println("Introduce números naturales a añadir al conjunto");
		boolean salir=false;
		Lista<Integer> l=new ListaEncadenadaConIterador<Integer>();
		while (!salir) {
			try {
				String elem=Utilidades.leerString("Introduce un número a añadir al final de la lista (o \"fi\" para acabar): ",System.in);
				salir=elem.equalsIgnoreCase("fin");
				if (!salir)
					l.insertarAlFinal(Integer.parseInt(elem));
			} catch (NumberFormatException e) {
				System.out.println("El texto introducido no es un número, vuelvelo a probar.");
			} catch (IOException e) {
				System.out.println("Problema con la entrada, abortando el programa");
				salir=true;
			}
		}
		System.out.println("La lista resultante es: "+l);
	}

}
