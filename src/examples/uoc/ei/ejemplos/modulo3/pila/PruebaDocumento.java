
package uoc.ei.ejemplos.modulo3.pila;

import java.io.IOException;

import uoc.ei.tads.Utilidades;

/** Esta clase proporciona un programa de prueba parecido a
 * PruebaConjunto visto en el módulo 1, pero para la clase Documento
 * definida para este package.
 */
public class PruebaDocumento {

	public static void main(String[] args) {
		System.out.println("Introduce cadenas de texto (páginas) a añadir al documento");
		boolean salir=false;
		Documento documento=new Documento(10);
		while (!salir) {
			try {
				String texto=Utilidades.leerString("Introduce una cadena de texto (o \"fin\" para acabar): ",System.in);
				salir=texto.equalsIgnoreCase("fin");
				if (!salir) {
					Pagina p=new Pagina();
					p.setTexto(texto+"\n");
					documento.insertar(p);
					if (documento.estaLleno()) {
						System.out.println("El documento está lleno. No se pueden añadir más páginas.");
						salir=true;
					}
				}
			} catch (IOException e) {
				System.out.println("Problema con la entrada, abortando el programa");
				salir=true;
			}
		}
		System.out.println("El documento al revés es: ");
		documento.imprimir(true);
	}
	
}
