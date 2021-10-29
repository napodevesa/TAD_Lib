package uoc.ei.ejemplos.modulo3.cola;

import java.io.IOException;
import java.util.StringTokenizer;

import uoc.ei.tads.*;

public class ColaOperaciones {
	
	Cola<Operacion> operaciones;
	
	protected ColaOperaciones() {
		operaciones=crearCola();
		crearProcesador();
	}
	
	
	protected Cola<Operacion> crearCola() {
		return new ColaVectorImpl<Operacion>();
	}
	
	
	protected void crearProcesador() {
		Thread processador = new Procesador(operaciones);
		// esto hace que cuando el programa principal se acaba,
		// el procesador también acaba (aunque queden
		// operaciones por realizar)
		processador.setDaemon(true);
		processador.start();
	}
	
	
	public void leerOperaciones() {
		boolean fin=false;
		System.out.println("Simulador de una cola de procesamiento.");
		System.out.println("Unicamente realiza sumas.");
		System.out.println("Introduce fin para acabar.");
		try {
			while (!fin) {
				String str=Utilidades.leerString("Introduce dos enteros a sumar (separados por espacio): ",System.in);
				if (str.equalsIgnoreCase("fin"))
					fin=true;
				else {
					StringTokenizer tk=new StringTokenizer(str," ");
					if (tk.countTokens()!=2)
						System.out.println("Número de enteros diferente de dos, vuelvelo a probar!");
					try {
						long op1=Long.parseLong(tk.nextToken());
						long op2=Long.parseLong(tk.nextToken());
						Operacion op=new Suma(op1,op2);
						System.out.println("encolando operación: "+op);
						// Este bloque de sincronización es necesario para evitar que el thread
						// del procesador y el de usuario (este main) intenten acceder a la vez
						// a la cola.
						synchronized (operaciones) {
							operaciones.encolar(op);
							System.out.println("número de operaciones pendientes: "+operaciones.numElems());
						}
					} catch (NumberFormatException e) {
						System.out.println("Problema al leer los dos enteros de: "+str);
						System.out.println("Vuelvelo a probar!");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer de la entrada. Abortando la ejecución del programa:"+e);
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ColaOperaciones programaPrincipal=new ColaOperaciones();
		programaPrincipal.leerOperaciones();
	}

}
