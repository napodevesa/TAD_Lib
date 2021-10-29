package uoc.ei.ejemplos.modulo8.academia;

import uoc.ei.tads.*;
import uoc.ei.tads.grafos.*;

public class AcademiaElectricidadImpl implements AcademiaElectricidad {
	
	private Diccionario<String,AsignaturasAlumno> alumnos;
	private GrafoAsignaturas asignaturas;
	private Diccionario<String,AsignaturasProfesor> profesores;
	
	
	public AcademiaElectricidadImpl(GrafoAsignaturas asignatures,Iterador<String> profes) {
		alumnos=new DiccionarioAVLImpl<String,AsignaturasAlumno>();
		this.asignaturas=asignatures;
		profesores=new DiccionarioListaImpl<String,AsignaturasProfesor>();
		while (profes.haySiguiente()) {
			String profesor=profes.siguiente();
			profesores.insertar(profesor,new AsignaturasProfesor());
		}
	}
	

	public void insertarAlumno(String alumno) {
		AsignaturasAlumno asignaturas=new AsignaturasAlumno();
		alumnos.insertar(alumno,asignaturas);
	}


	public void matricular(String alumno, String asignatura) throws ExcepcionPrerequisitoNoSatisfecho {
		Vertice<Asignatura> verticeAssignatura=asignaturas.consultarVertice(new Asignatura(asignatura,0));
		comprobarPrerequisitos(alumno,verticeAssignatura);
		AsignaturasAlumno asignaturasAlumno=alumnos.consultar(alumno);
		asignaturasAlumno.nuevaAsignaturaEnCurso(verticeAssignatura);
	}

	
	public void asignaturaAprobada(String alumno, String asignatura,double calificacion) {
		AsignaturasAlumno asignaturasAlumno=alumnos.consultar(alumno);
		Vertice<Asignatura> verticeAssignatura=asignaturas.consultarVertice(new Asignatura(asignatura,0));
		asignaturasAlumno.asignaturaAprobada(verticeAssignatura,calificacion);
	}

	
	public void imparteAsignatura(String profesor, String asignatura) {
		AsignaturasProfesor asignaturasProfesor=profesores.consultar(profesor);
		Vertice<Asignatura> verticeAssignatura=asignaturas.consultarVertice(new Asignatura(asignatura,0));
		asignaturasProfesor.insertarAlFinal(verticeAssignatura);
	}


	public Iterador<? extends Nota> expedienteAcademico(String alumno) {
		AsignaturasAlumno asignaturasAlumno=alumnos.consultar(alumno);
		return asignaturasAlumno.expediente();
	}

	
	public double cargaDocente(String profesor) {
		AsignaturasProfesor asignaturasProfesor=profesores.consultar(profesor);
		return asignaturasProfesor.getCreditos();
	}

	
	protected void comprobarPrerequisitos(String alumno,Vertice<Asignatura> asignatura)
						throws ExcepcionPrerequisitoNoSatisfecho {
		AsignaturasAlumno asignaturasAprobadas=alumnos.consultar(alumno);
		Iterador<Arista<EtiquetaVacia,Asignatura>> aristesPrerequisitos=asignaturas.aristasConDestinoEn(asignatura);
		while (aristesPrerequisitos.haySiguiente()) {
			AristaDirigida<EtiquetaVacia,Asignatura> aristaD=(AristaDirigida<EtiquetaVacia,Asignatura>)aristesPrerequisitos.siguiente();
			Vertice<Asignatura> prerequisito=aristaD.getVerticeOrigen();
			if (!asignaturasAprobadas.estaAprobada(prerequisito))
				throw new ExcepcionPrerequisitoNoSatisfecho(alumno,asignatura.getValor().getNombre(),prerequisito.getValor().getNombre());
		}
		
		
	}
	
	

}
