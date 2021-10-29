package uoc.ei.ejercicios.modulo5.ejercicio3;

public class Documento {
	
	private String titulo;
	
	public Documento(String titol) {
		this.titulo=titol;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String toString() { return getTitulo(); }

}
