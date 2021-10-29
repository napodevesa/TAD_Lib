package uoc.ei.ejemplos.modulo4.ficheros;

import uoc.ei.tads.Posicion;

public abstract class EntradaSF {
	
	private String nombre;
	private Posicion<EntradaSF> padre;
	
	public EntradaSF(String nombre,Posicion<EntradaSF> padre) {
		this.nombre=nombre;
		this.padre=padre;
	}
	
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public Posicion<EntradaSF> getPadre() { return padre; }
	public abstract boolean esDirectorio();
	
	public String toString() { return nombre; }

}
