package uoc.ei.ejemplos.modulo4.ficheros;

import uoc.ei.tads.Posicion;

public class Directorio extends EntradaSF {

	public Directorio(String nombre, Posicion<EntradaSF> padre) {
		super(nombre, padre);
	}

	public boolean esDirectorio() { return true; }

}
