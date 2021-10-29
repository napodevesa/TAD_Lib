package uoc.ei.ejercicios.modulo6.ejercicio12;

import uoc.ei.tads.Iterador;

public class CursoAcademicoImpl implements CursoAcademico {
	
	TablaAlumnos alumnos;
	TablaAsignaturas asignaturas;
	
	
	public CursoAcademicoImpl() {
		alumnos=new TablaAlumnos();
		asignaturas=new TablaAsignaturas();
	}
	

	public void nuevoAlumno(Alumno alumno) {
		alumnos.insertar(alumno.getDNI(),alumno);
	}
	
	
	public void nuevaAsignatura(Asignatura asignatura) {
		asignaturas.insertar(asignatura.getCodigo(),asignatura);
	}

	
	public void matricular(String dniAlumno,int codiAsignatura) {
		Alumno alumno=alumnos.consultar(dniAlumno);
		Asignatura asignatura=asignaturas.consultar(codiAsignatura);
		alumno.insertarAsignatura(asignatura);
	}


	public Iterador<Asignatura> asignaturas(String dniAlumno) {
		Alumno alumno=alumnos.consultar(dniAlumno);
		return alumno.asignaturas();
	}

}
