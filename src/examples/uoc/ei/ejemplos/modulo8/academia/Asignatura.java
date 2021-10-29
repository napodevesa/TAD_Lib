package uoc.ei.ejemplos.modulo8.academia;

public class Asignatura {
	
	private String nombre;
	private double creditos;
	
	
	public Asignatura(String nom,double credits) {
		this.nombre=nom;
		this.creditos=credits;
	}
	
	
	public String getNombre() { return nombre; }
	public double getCreditos() { return creditos; }
	
	public boolean equals(Object asignatura) {
		return getNombre().equals(((Asignatura)asignatura).getNombre());
	}

	public String toString() { return "["+nombre+","+creditos+"]"; }

}
