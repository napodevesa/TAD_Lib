package uoc.ei.ejercicios.modulo9.ejercicio1;

import uoc.ei.tads.*;

public class ExpedienteImpl implements Expediente, ObservadorContenedorPosicional<DatosAlumno> {
	
	private Diccionario<String,DatosAlumno> alumnos;
	private ColaConPrioridadActualizable<DatosAlumno> rankingAlumnos;
	
	
	public ExpedienteImpl() {
		alumnos=new DiccionarioAVLImpl<String,DatosAlumno>();
		rankingAlumnos=new ColaConPrioridadActualizable<DatosAlumno>();
		rankingAlumnos.insertarObservador(this);
	}
	
	
	public void nuevaNota(String alumno,double creditos,double nota) {
		DatosAlumno datosAlumno=alumnos.consultar(alumno);
		if (datosAlumno==null) {
			datosAlumno=new DatosAlumno(alumno);
			alumnos.insertar(alumno,datosAlumno);
			rankingAlumnos.encolar(datosAlumno);
		}
		datosAlumno.nuevaNota(creditos,nota);
		rankingAlumnos.actualizarPosicion(datosAlumno.getPosicionHeap());
	}
	
	
	public double notaMedia(String alumno) {
		DatosAlumno datosAlumno=alumnos.consultar(alumno);
		return datosAlumno.getNota();
	}
	

	public String alumnoConMejorExpediente() {
		return rankingAlumnos.primero().getNombre();
	}
	

	public void notificarPosicionCreada(Posicion<DatosAlumno> posicion) {
		posicion.getElem().setPosicionHeap(posicion);
	}

	
	public void notificarPosicionBorrada(Posicion<DatosAlumno> posicion) {
	}
	
}
