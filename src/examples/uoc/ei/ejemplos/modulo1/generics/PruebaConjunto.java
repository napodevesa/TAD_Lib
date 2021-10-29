package uoc.ei.ejemplos.modulo1.generics;

import java.io.IOException;
import uoc.ei.tads.Utilidades;

/** Prueba el TAD conjunto, creando un conjunto e insertando
 * elementos que el usuario va dando por la entrada est�ndar.
 * @author Jordi Alvarez
 * @author Esteve Marine
 * 			Universitat Oberta de Catalunya (UOC)
 */
public class PruebaConjunto {
	
	
	public static void main(String[] args) {
		System.out.println("Introduce n�meros naturales a a�adir al conjunto");
		boolean salir=false;
		ConjuntoAcotado<Integer> cjt=new ConjuntoVectorImpl<Integer>(10);
		while (!salir) {
			try {
				String elem=Utilidades.leerString("Introduce un n�mero (o \"fin\" para acabar): ",System.in);
				salir=elem.equalsIgnoreCase("fin");
				if (!salir)
					cjt.insertar(Integer.parseInt(elem));
				if (cjt.estaLleno()) {
					System.out.println("El conjunto est� lleno. No se pueden a�adir m�s elementos.");
					salir=true;
				}
			} catch (NumberFormatException e) {
				System.out.println("El texto introducido no es un n�mero, vuelvelo a probar.");
			} catch (IOException e) {
				System.out.println("Problema con la entrada, abortando el programa");
				salir=true;
			}
		}
		System.out.println("El conjunto resultante es: "+cjt);
	}

}
