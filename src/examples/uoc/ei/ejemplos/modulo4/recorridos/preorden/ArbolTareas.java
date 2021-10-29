package uoc.ei.ejemplos.modulo4.recorridos.preorden;

import java.io.IOException;

import uoc.ei.ejemplos.modulo4.recorridos.RecorridosArbol;
import uoc.ei.tads.ArbolGeneralDelegImpl;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;
import uoc.ei.tads.Utilidades;


public class ArbolTareas extends ArbolGeneralDelegImpl<Tarea> {
	
	public void repartirTareas() {
		ReparticionTareas repartimiento=new ReparticionTareas();
		repartimiento.preorden(this);
	}

	
	protected class ReparticionTareas extends RecorridosArbol<Tarea> {
		
		protected void tratarPosicion(Posicion<Tarea> posicion) {
			double recursosAbsolutosPadre=posicion.getElem().getRecursosAbsolutos();
			Recorrido<Tarea> hijos=hijos(posicion);
			while (hijos.haySiguiente()) {
				Tarea tareaHija=hijos.siguiente().getElem();
				tareaHija.setRecursosAbsolutos(recursosAbsolutosPadre*tareaHija.getRecursosPorcentaje()/100);
			}
		}
	}
	
	
	public static void popularArbolEjemplo(ArbolTareas arbol) {
		Posicion<Tarea> reformar=arbol.insertar(null,new Tarea("Reformar",100));
		Posicion<Tarea> paredes=arbol.insertar(reformar,new Tarea("Paredes",35));
		arbol.insertar(reformar,new Tarea("Suelo",50));
		arbol.insertar(reformar,new Tarea("Luces",15));
		arbol.insertar(paredes,new Tarea("TaparAgujeros",30));
		arbol.insertar(paredes,new Tarea("Pintar",70));
	}


	public static void main(String[] args) {
		ArbolTareas arbol=new ArbolTareas();
		popularArbolEjemplo(arbol);
		try {
			String str=Utilidades.leerString("Introdueix els recursos absoluts: ",System.in);
			double absolutos=Double.parseDouble(str);
			arbol.raiz().getElem().setRecursosAbsolutos(absolutos);
			arbol.repartirTareas();
			System.out.println("El árbol después de repartir las tareas ha quedado: "+arbol);
		} catch (IOException e) {
			System.out.println("Error al leer de la entrada: "+e);
		}
	}

}
