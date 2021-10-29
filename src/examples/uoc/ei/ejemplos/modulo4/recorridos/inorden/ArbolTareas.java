package uoc.ei.ejemplos.modulo4.recorridos.inorden;

import java.util.Date;

import uoc.ei.ejemplos.modulo4.recorridos.*;
import uoc.ei.tads.*;


public class ArbolTareas extends ArbolBinarioEncadenadoImpl<Tarea> {
	
	public void planificarTareas(Date inici) {
		PlanificadorTareas planificador=new PlanificadorTareas(inici);
		planificador.inorden(this);
	}

	
	/** Tal como se comenta en los materiales, esta clase dispone de un atributo
	 * especial que hace referencia a la anterior posición visitada. Esta técnica
	 * nos permite tener un tratar posicion muy sencillo. Notad pero que eso
	 * impone una restricción también: una instancia de PlanificacionTareas solo
	 * nos permitirá hacer un recorrido a la vez!!!
	 * @author Jordi Alvarez
	 * @author Esteve Marine
	 * 			Estructura de la Información
	 * 			Universitat Oberta de Catalunya (UOC)
	 */
	protected class PlanificadorTareas extends RecorridosArbolBinario<Tarea> {

		Date inicio;
		Tarea anterior;
		
		PlanificadorTareas(Date inicio) { this.inicio=inicio; }
		
		protected void inicializarRecorrido() { anterior=null; }
		
		protected void tratarPosicion(Posicion<Tarea> posicion) {
			Tarea tasca=posicion.getElem();
			if (anterior!=null) {
				long inicio=anterior.getInicio().getTime()+anterior.getDuracionMinutos()*60000;
				tasca.setInicio(new Date(inicio));
			}
			else
				tasca.setInicio(inicio);
			anterior=tasca;
		}
	}
	
	
	protected static void popularArbolEjemplo(ArbolTareas arbre) {
		Posicion<Tarea> pintar=arbre.insertar(null,new Tarea("Pintar",20));
		Posicion<Tarea> enyesar=arbre.insertar(pintar,new Tarea("Enyesar",30));
		arbre.insertar(pintar,new Tarea("Limpiar",7));
		arbre.insertar(enyesar,new Tarea("Instalación Eléctrica",80));
		arbre.insertar(enyesar,new Tarea("Tapar Agujeros",15));
	}


	public static void main(String[] args) {
		ArbolTareas arbol=new ArbolTareas();
		popularArbolEjemplo(arbol);
		Date inicio=new Date();
		arbol.planificarTareas(inicio);
		System.out.println("El arbol después de planificar las tareas ha quedado: "+arbol);
	}

}
