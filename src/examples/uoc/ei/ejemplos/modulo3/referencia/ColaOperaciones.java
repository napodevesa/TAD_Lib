package uoc.ei.ejemplos.modulo3.referencia;

import uoc.ei.ejemplos.modulo3.cola.Operacion;
import uoc.ei.tads.*;

public class ColaOperaciones extends uoc.ei.ejemplos.modulo3.cola.ColaOperaciones {
	
	protected ColaOperaciones() {
		super();
	}
	
	
	protected Cola<Operacion> crearCola() {
		return new ColaEncadenadaImpl<Operacion>();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ColaOperaciones programaPrincipal=new ColaOperaciones();
		programaPrincipal.leerOperaciones();
	}

}
