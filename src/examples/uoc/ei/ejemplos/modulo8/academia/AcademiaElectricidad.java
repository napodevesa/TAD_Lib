package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.Iterador;

public interface AcademiaElectricidad {
	
	void insertarAlumno(String alumno);
	void matricular(String alumno,String asignatura) throws ExcepcionPrerequisitoNoSatisfecho;
	void asignaturaAprobada(String alumno,String asignatura,double calificacion);
	void imparteAsignatura(String profesor,String asignatura);
	Iterador<? extends Nota> expedienteAcademico(String alumno);
	double cargaDocente(String profesor);

}
