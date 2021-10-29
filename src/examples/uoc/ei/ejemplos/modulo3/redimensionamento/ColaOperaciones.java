package uoc.ei.ejemplos.modulo3.redimensionamento;

import uoc.ei.ejemplos.modulo3.cola.Operacion;
import uoc.ei.tads.*;

public class ColaOperaciones extends uoc.ei.ejemplos.modulo3.cola.ColaOperaciones {
	
	protected ColaOperaciones() {
		super();
	}
	
	
	protected Cola<Operacion> crearCola() {
		return new ColaRedimensionableImpl<Operacion>();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ColaOperaciones programaPrincipal=new ColaOperaciones();
		programaPrincipal.leerOperaciones();
	}

}
