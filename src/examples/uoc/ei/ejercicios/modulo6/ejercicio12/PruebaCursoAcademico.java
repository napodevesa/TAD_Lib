package uoc.ei.ejercicios.modulo6.ejercicio12;

import uoc.ei.tads.Iterador;

public class PruebaCursoAcademico {
	
	
	protected static void mostrarAsignaturas(CursoAcademico curs,String dniAlumno) {
		System.out.println("El alumno con DNI "+dniAlumno+" ha matriculado las asignaturas: ");
		Iterador<Asignatura> asigs=curs.asignaturas(dniAlumno);
		while (asigs.haySiguiente()) {
			System.out.print(asigs.siguiente());
			if (asigs.haySiguiente())
				System.out.print(", ");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		CursoAcademico curso=new CursoAcademicoImpl();
		curso.nuevaAsignatura(new Asignatura(301,"Estadística",9));
		curso.nuevaAsignatura(new Asignatura(302,"Anàlisis Matemático",6));
		curso.nuevaAsignatura(new Asignatura(303,"Sistemas Operativos",6));
		curso.nuevaAsignatura(new Asignatura(304,"Estructura de la Información",6));
		curso.nuevaAsignatura(new Asignatura(305,"Inglés III",4.5));
		curso.nuevoAlumno(new Alumno("33333333","Juan","Pérez","Mir"));
		curso.nuevoAlumno(new Alumno("20761234","Ana","Estrada","Gimperà"));
		curso.nuevoAlumno(new Alumno("57980110","Maria","Gombren","Josafat"));
		curso.matricular("33333333",301);
		curso.matricular("33333333",302);
		curso.matricular("33333333",303);
		curso.matricular("33333333",304);
		curso.matricular("33333333",305);
		curso.matricular("20761234",303);
		curso.matricular("20761234",305);
		curso.matricular("57980110",301);
		mostrarAsignaturas(curso,"33333333");
		mostrarAsignaturas(curso,"20761234");
		mostrarAsignaturas(curso,"57980110");
	}

}
