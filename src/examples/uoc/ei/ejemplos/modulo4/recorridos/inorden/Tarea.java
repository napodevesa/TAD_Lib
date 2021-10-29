package uoc.ei.ejemplos.modulo4.recorridos.inorden;

import java.util.Date;

public class Tarea {

	private String nombre;
	private long duracionMinutos;
	private Date inicio;
	
	
	public Tarea(String nom,int duradaMinuts) {
		this.nombre=nom;
		this.duracionMinutos=duradaMinuts;
	}
	
	
	public String getNombre() { return nombre; }
	public long getDuracionMinutos() { return duracionMinutos; }
	public Date getInicio() { return inicio; }
	public void setInicio(Date inici) { this.inicio=inici; }
	
	public String toString() {
		return "["+nombre+","+duracionMinutos+
			(inicio==null ? "]"
					: ","+inicio+"]");
	}
	
}
