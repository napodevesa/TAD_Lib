package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.grafos.*;

public class AsignaturaAprobada implements Nota {
	
	private Vertice<Asignatura> asignatura;
	private double calificacion;
	
	
	public AsignaturaAprobada(Vertice<Asignatura> asignatura,double calificacion) {
		this.asignatura=asignatura;
		this.calificacion=calificacion;
	}
	
	
	public Vertice<Asignatura> getVerticeAsignatura() { return asignatura; }
	public Asignatura getAsignatura() { return asignatura.getValor(); }
	public double getCalificacion() { return calificacion; }
	
	public String toString() { return "["+asignatura+","+calificacion+"]"; }

}
