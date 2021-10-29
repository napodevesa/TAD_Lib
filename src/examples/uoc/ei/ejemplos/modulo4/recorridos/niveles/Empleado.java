package uoc.ei.ejemplos.modulo4.recorridos.niveles;

public class Empleado {
	
	private String nombre;
	private String plazaParking;
	
	public Empleado(String nom) { this.nombre=nom; }
	public String getNombre() { return nombre; }
	public String getPlazaParking() { return plazaParking; }
	public void setPlazaParking(String placaParking) { this.plazaParking=placaParking; }
	
	public String toString() {
		return "["+nombre+","+plazaParking+"]";
	}

}
