package uoc.ei.ejemplos.modulo9.dns;

import uoc.ei.tads.*;

/** Clase auxiliar definida con la finalidad de hacer m�s claro
 * el c�digo, de manera que a la hora de definir variables y otros
 * tipo gen�ricos podamos usar directamente DiccionarioTLDs en lugar
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
