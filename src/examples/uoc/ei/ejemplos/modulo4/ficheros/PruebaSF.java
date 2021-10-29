package uoc.ei.ejemplos.modulo4.ficheros;

import java.io.IOException;
import java.util.StringTokenizer;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.Utilidades;

public class PruebaSF {
	
	protected static void mostrarAyuda() {
		System.out.println("Simulación de una línea de comandos con comandos básicos para un sistema de ficheros");
		System.out.println("Comandos aceptados:");
		System.out.println("?, ayuda : muestra esta pantalla.");
		System.out.println("cd .. : sube al directorio padre.");
		System.out.println("cd <dir> : va al directorio hijo llamado <dir>.");
		System.out.println("md <dir> : crea un nuevo directorio hijo llamado <dir>.");
		System.out.println("mf <fichero> <contenido> : crea un nuevo fichero de texto con el contenido especificado (el contenido no puede contener espacios).");
		System.out.println("ls : lista el contenido del directorio actual.");
		System.out.println("cat : muestra el contenido de un fichero.");
		System.out.println("rm : elimina un directorio o fichero.");
		System.out.println("mv : cambia el número de un directorio o fichero.");
		System.out.println("exit, quit, stop, salir : acaba el simulador");
		System.out.println();
		System.out.println("Se debe tener en cuenta que no hay ningún control de errores en la lectura de los comandos!");
	}
	
	
	protected static boolean ejecutarComando(SistemaDeFicheros sistemaDeFicheros,String comanda) throws ExcepcionSistemaDeFicheros {
		boolean salir=false;
		StringTokenizer elementosComando=new StringTokenizer(comanda);
		if (elementosComando.countTokens()>0) {
			String tipoComando=elementosComando.nextToken();
			if (tipoComando.equalsIgnoreCase("ayuda") ||
					tipoComando.equals("?")) 
				mostrarAyuda();
			else if (tipoComando.equalsIgnoreCase("cd")) {
				String nuevoDir=elementosComando.nextToken();
				if (nuevoDir.equals(".."))
					sistemaDeFicheros.subirDirectorio();
				else
					sistemaDeFicheros.bajarDirectorio(nuevoDir);
			}
			else if (tipoComando.equalsIgnoreCase("md")) {
				String nuevoDir=elementosComando.nextToken();
				sistemaDeFicheros.crearDirectorio(nuevoDir);
			}
			else if (tipoComando.equalsIgnoreCase("mf")) {
				String nuevoFichero=elementosComando.nextToken();
				String contenido=elementosComando.nextToken();
				sistemaDeFicheros.crearFichero(nuevoFichero,contenido);
			}
			else if (tipoComando.equalsIgnoreCase("ls")) {
				Iterador<EntradaSF> entradas=sistemaDeFicheros.elementos();
				while (entradas.haySiguiente()) {
					EntradaSF entrada=entradas.siguiente();
					System.out.print(entrada);
					if (entrada.esDirectorio())
						System.out.println("       <directorio>");
					else
						System.out.println();
				}
			}
			else if (tipoComando.equalsIgnoreCase("cat")) {
				String fichero=elementosComando.nextToken();
				System.out.println(sistemaDeFicheros.getContenidoFichero(fichero));
			}
			else if (tipoComando.equalsIgnoreCase("rm")) {
				String entrada=elementosComando.nextToken();
				sistemaDeFicheros.eliminarEntrada(entrada);
			}
			else if (tipoComando.equalsIgnoreCase("mv")) {
				String nombreViejo=elementosComando.nextToken();
				String nombreNuevo=elementosComando.nextToken();
				sistemaDeFicheros.renombrar(nombreViejo,nombreNuevo);
			}
			else if ("exit,quit,salir,stop".indexOf(tipoComando)!=-1)
				salir=true;
			else {
				System.out.println("Comando no reconocido. Pulsa ? para ver ayuda.");
			}
		}
		return salir;
	}


	public static void main(String[] args) {
		SistemaDeFicheros sistemaDeFicheros=new SistemaDeFicheros("raiz");
		boolean salir=false;
		try {
			while (!salir) {
				try {
					String comando=Utilidades.leerString(sistemaDeFicheros.getDirectorioDeTrabajo()+"> ",System.in);
					comando=comando.trim();
					salir=ejecutarComando(sistemaDeFicheros,comando);
				} catch (ExcepcionSistemaDeFicheros e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Problema al leer de la entrada: "+e);
			System.out.println("Abortando el programa");
		}
	}

}
