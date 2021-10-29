package uoc.ei.ejercicios.modulo9.ejercicio1;

import uoc.ei.tads.Posicion;

public class DatosAlumno implements Comparable<DatosAlumno> {
	
	private String nombre;
	private Posicion<DatosAlumno> posicionHeap;
	private double notaAcumulada;
	private double creditos;
	
	
	public DatosAlumno(String nombre) {
		this.nombre=nombre;
		posicionHeap=null;
		notaAcumulada=0;
		creditos=0;
	}

	
	public String getNombre() { return nombre; }
	public Posicion<DatosAlumno> getPosicionHeap() { return posicionHeap; }
	
	
	public double getNota() {
		if (creditos>0)
			return notaAcumulada/creditos;
		return 0;
	}

	
	public void nuevaNota(double creditos,double nota) {
		notaAcumulada=notaAcumulada+creditos*nota;
		this.creditos+=creditos;
	}
	
	
	public void setPosicionHeap(Posicion<DatosAlumno> posicion) {
		posicionHeap=posicion;
	}
	
	
	public int compareTo(DatosAlumno datos) {
		if (getNota()<datos.getNota())
			return 1;
		else if (getNota()>datos.getNota())
			return -1;
		return 0;
	}
	
	
	public String toString() {
		return "["+getNombre()+","+getNota()+"]";
	}
	
	
	
}
