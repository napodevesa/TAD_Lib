package uoc.ei.ejercicios.modulo6.ejercicio12;

import uoc.ei.tads.*;

public class Alumno {
	
	private String dni;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String direccionPostal;
	private String direccionElectronica;
	private String edad;
	private Lista<Asignatura> asignaturas;
	
	public Alumno(String dni,String nombre,String primerApellido,String segundoApellido) {
		this.dni=dni;
		this.nombre=nombre;
		this.primerApellido=primerApellido;
		this.segundoApellido=segundoApellido;
		asignaturas=new ListaEncadenada<Asignatura>();
	}
	
	
	public void setDireccionPostal(String direccionPostal) { this.direccionPostal=direccionPostal; }
	public void setDireccionElectronica(String direccionElectronica) { this.direccionElectronica=direccionElectronica; }
	public void setEdad(String edat) { this.edad=edat; }
	
	public String getDNI() { return dni; }
	public String getNombre() { return nombre; }
	public String getPrimerApellido() { return primerApellido; }
	public String getSegundoApellido() { return segundoApellido; }
	public String getDireccionPostal() { return direccionPostal; }
	public String getDireccionElectronica() { return direccionElectronica; }
	public String getEdad() { return edad; }
	
	void insertarAsignatura(Asignatura asignatura) {
		asignaturas.insertarAlFinal(asignatura);
	}
	
	Iterador<Asignatura> asignaturas() { return asignaturas.elementos(); }
	
}
