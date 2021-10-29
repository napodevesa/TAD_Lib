package uoc.ei.ejemplos.modulo4.ficheros;

import uoc.ei.tads.Posicion;

public class FicheroDeTexto extends EntradaSF {
	
	private String contenido;

	public FicheroDeTexto(String nombre, Posicion<EntradaSF> padre) {
		super(nombre, padre);
	}

	public boolean esDirectorio() { return false; }
	public void setContenido(String contenido) { this.contenido=contenido; }
	public String getContenido()  { return contenido; }

}
