package uoc.ei.ejercicios.modulo6.ejercicio12;

import uoc.ei.tads.Iterador;

public interface CursoAcademico {
	
	void nuevoAlumno(Alumno alumno);
	void nuevaAsignatura(Asignatura asignatura);
	void matricular(String dniAlumno,int codigoAsignatura);
	Iterador<Asignatura> asignaturas(String dniAlumno);

}
