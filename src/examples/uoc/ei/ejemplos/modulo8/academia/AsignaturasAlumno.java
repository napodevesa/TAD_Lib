package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.*;
import uoc.ei.tads.grafos.*;

class AsignaturasAlumno {
	
	private ListaEncadenada<Vertice<Asignatura>> asignaturasEnCurso;
	private ListaEncadenada<AsignaturaAprobada> asignaturasAprobadas;
	
	
	public AsignaturasAlumno() {
		asignaturasEnCurso=new ListaEncadenada<Vertice<Asignatura>>();
		asignaturasAprobadas=new ListaEncadenada<AsignaturaAprobada>();
	}
	
	
	public void nuevaAsignaturaEnCurso(Vertice<Asignatura> assignatura) {
		asignaturasEnCurso.insertarAlFinal(assignatura);
	}
	

	public void asignaturaAprobada(Vertice<Asignatura> asignatura,double calificacion) {
		AsignaturaAprobada nota=new AsignaturaAprobada(asignatura,calificacion);
		borrarAsignaturaEnCurso(asignatura);
		asignaturasAprobadas.insertarAlFinal(nota);
	}
	
	
	public Iterador<AsignaturaAprobada> expediente() {
		return asignaturasAprobadas.elementos();
	}
	
	
	public double mediaExpediente() {
		double sumaNotas=0;
		double nCreditos=0;
		Iterador<AsignaturaAprobada> notas=expediente();
		while (notas.haySiguiente()) {
			Nota nota=notas.siguiente();
			sumaNotas+=nota.getCalificacion()*nota.getAsignatura().getCreditos();
			nCreditos+=nota.getAsignatura().getCreditos();
		}
		return sumaNotas/nCreditos;
	}
	
	
	public boolean estaAprobada(Vertice<Asignatura> asignatura) {
		Iterador<AsignaturaAprobada> asignaturas=expediente();
		boolean encontrado=false;
		while (asignaturas.haySiguiente() && !encontrado) {
			AsignaturaAprobada aprobada=asignaturas.siguiente();
			encontrado= asignatura==aprobada.getVerticeAsignatura();
		}
		return encontrado;
	}
	
	
	protected void borrarAsignaturaEnCurso(Vertice<Asignatura> asignatura) {
		Recorrido<Vertice<Asignatura>> posiciones=asignaturasEnCurso.posiciones();
		Posicion<Vertice<Asignatura>> posicion=null;
		boolean encontrado=false;
		while (posiciones.haySiguiente() && !encontrado) {
			posicion=posiciones.siguiente();
			encontrado=posicion.getElem()==asignatura;
		}
		asignaturasEnCurso.borrar(posicion);
	}
	
	
	public String toString() {
		return "{"+asignaturasEnCurso+";"+asignaturasAprobadas+"}";
	}
	
	
}
