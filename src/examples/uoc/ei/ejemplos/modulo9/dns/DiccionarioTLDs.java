package uoc.ei.ejemplos.modulo9.dns;

import uoc.ei.tads.*;

/** Clase auxiliar definida con la finalidad de hacer más claro
 * el código, de manera que a la hora de definir variables y otros
 * tipo genéricos podamos usar directamente DiccionarioTLDs en lugar
 * de TablaDispersion<TLD,DiccionarioEntidades>.
 */
class DiccionarioTLDs extends TablaDispersion<TLD,DiccionarioEntidades> {

	   public DiccionarioTLDs() {
	   	  super();
	   }

	   public DiccionarioTLDs(int mida) {
		   super(mida);
	   }

	   
}
