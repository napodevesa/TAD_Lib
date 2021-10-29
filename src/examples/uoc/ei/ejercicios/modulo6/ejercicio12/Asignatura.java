package uoc.ei.ejercicios.modulo6.ejercicio12;

public class Asignatura {
	
	private int codigo;
	private String nombre;
	private double creditos;
	
	
	public Asignatura(int codigo,String nombre,double creditos) {
		this.codigo=codigo;
		this.nombre=nombre;
		this.creditos=creditos;
	}
	
	
	public int getCodigo() { return codigo; }
	public String getNombre() { return nombre; }
	public double getCreditos() { return creditos; }
	
	public String toString() {
		return "["+codigo+","+nombre+","+creditos+"]";
	}

}
