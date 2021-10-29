package uoc.ei.ejemplos.modulo4.recorridos.preorden;

public class Tarea {

	private String nombre;
	private double recursosPorcentaje;
	private double recursosAbsolutos;
	
	
	public Tarea(String nombre,double recursosPorcentaje) {
		this.nombre=nombre;
		this.recursosPorcentaje=recursosPorcentaje;
		recursosAbsolutos=-1;
	}
	
	
	public String getNombre() { return nombre; }
	public double getRecursosPorcentaje() { return recursosPorcentaje; }
	public double getRecursosAbsolutos() { return recursosAbsolutos; }
	public void setRecursosAbsolutos(double recursosAbsolutos) { this.recursosAbsolutos=recursosAbsolutos; }
	
	public String toString() {
		return "["+nombre+",P:"+recursosPorcentaje+
			(recursosAbsolutos==-1 ? "]"
					: ",A:"+recursosAbsolutos+"]");
	}
	
}
