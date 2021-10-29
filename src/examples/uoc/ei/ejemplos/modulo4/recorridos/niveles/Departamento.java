package uoc.ei.ejemplos.modulo4.recorridos.niveles;

import java.io.IOException;

import uoc.ei.ejemplos.modulo4.ArbolGeneralVectorExtImpl;
import uoc.ei.ejemplos.modulo4.recorridos.RecorridosArbol;
import uoc.ei.tads.*;


public class Departamento extends ArbolGeneralVectorExtImpl<Empleado> {
	
	public void repartirPlazas(Lista<String> plazas) {
		ReparticionPlazas repartiment=new ReparticionPlazas(plazas);
		repartiment.porNiveles(this);
	}

	
	protected class ReparticionPlazas extends RecorridosArbol<Empleado> {
		
		private Lista<String> plazasParking;
		Iterador<String> iteradorPlazas;
		
		public ReparticionPlazas(Lista<String> plazasParking) {
			this.plazasParking=plazasParking;
		}
		
		protected void inicializarRecorrido() {
			iteradorPlazas=plazasParking.elementos();
		}
		
		protected void tratarPosicion(Posicion<Empleado> posicio) {
			String plazaDesignada="NO";
			if (iteradorPlazas.haySiguiente())
				plazaDesignada=iteradorPlazas.siguiente();
			posicio.getElem().setPlazaParking(plazaDesignada);
		}
	}
	
	
	public static void popularArbolEjemplo(Departamento arbol) {
		Posicion<Empleado> matt=arbol.insertar(null,new Empleado("Matt"));
		arbol.insertar(matt,new Empleado("Ned"));
		Posicion<Empleado> apu=arbol.insertar(matt,new Empleado("Apu"));
		Posicion<Empleado> homer=arbol.insertar(apu,new Empleado("Homer"));
		Posicion<Empleado> winnie=arbol.insertar(apu,new Empleado("Winnie"));
		arbol.insertar(winnie,new Empleado("Bart"));
		arbol.insertar(winnie,new Empleado("Lisa"));
		arbol.insertar(winnie,new Empleado("Maggie"));
	}


	public static void main(String[] args) {
		Departamento arbol=new Departamento();
		popularArbolEjemplo(arbol);
		Lista<String> plazas=new ListaEncadenada<String>();
		plazas.insertarAlFinal("plaza A");
		plazas.insertarAlFinal("plaza B");
		plazas.insertarAlFinal("plaza C");
		arbol.repartirPlazas(plazas);
		System.out.println("El departamento después de asignar las plazas ha quedado: "+arbol);
	}

}
