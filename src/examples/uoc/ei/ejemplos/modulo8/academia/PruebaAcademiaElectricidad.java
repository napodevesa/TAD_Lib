package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.*;
import uoc.ei.tads.grafos.*;

public class PruebaAcademiaElectricidad {
	
	
	public GrafoAsignaturas construirGrafoAsignaturas() {
		GrafoAsignaturas asignaturas=new GrafoAsignaturasImpl();
		Vertice<Asignatura> vA1=asignaturas.crearVertice(new Asignatura("A1",3.5));
		Vertice<Asignatura> vA2=asignaturas.crearVertice(new Asignatura("A2",3.5));
		Vertice<Asignatura> vA3=asignaturas.crearVertice(new Asignatura("A3",3.5));
		Vertice<Asignatura> vA4=asignaturas.crearVertice(new Asignatura("A4",3.5));
		Vertice<Asignatura> vA5=asignaturas.crearVertice(new Asignatura("A5",6));
		asignaturas.crearArista(vA1,vA2);
		asignaturas.crearArista(vA1,vA3);
		asignaturas.crearArista(vA2,vA4);
		asignaturas.crearArista(vA3,vA4);
		asignaturas.crearArista(vA4,vA5);
		return asignaturas;
	}
	
	
	public ListaEncadenada<String> construirProfesores() {
		ListaEncadenada<String> profes=new ListaEncadenada<String>();
		profes.insertarAlFinal("P1");
		profes.insertarAlFinal("P2");
		return profes;
	}
	
	
	public void matricular(AcademiaElectricidad academia,String alumne,String asignatura) {
		try {
			academia.matricular(alumne,asignatura);
			System.out.println("El alumno "+alumne+" ha matriculado la asignatura "+asignatura);
		} catch (ExcepcionPrerequisitoNoSatisfecho e) {
			System.out.println(e.toString());
		}
	}
	
	
	public static void main(String[] args) {
		PruebaAcademiaElectricidad prueba=new PruebaAcademiaElectricidad();
		GrafoAsignaturas asignaturas=prueba.construirGrafoAsignaturas();
		ListaEncadenada<String> profes=prueba.construirProfesores();
		AcademiaElectricidad academia=new AcademiaElectricidadImpl(asignaturas,profes.elementos());
		academia.imparteAsignatura("P1","A1");
		academia.imparteAsignatura("P1","A2");
		academia.imparteAsignatura("P1","A5");
		academia.imparteAsignatura("P2","A3");
		academia.imparteAsignatura("P2","A4");
		academia.insertarAlumno("E1");
		prueba.matricular(academia,"E1","A1");
		academia.asignaturaAprobada("E1","A1",8.0);
		prueba.matricular(academia,"E1","A2");
		academia.asignaturaAprobada("E1","A2",6.0);
		prueba.matricular(academia,"E1","A4");
		prueba.matricular(academia,"E1","A3");
	}

}
