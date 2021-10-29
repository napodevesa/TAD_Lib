package uoc.ei.ejemplos.modulo3.cola;

import uoc.ei.tads.Cola;

/** Simula un procesador de operaciones que puede realizar una operaci�n
 * cada TIEMPO_PROCESAMIENTO_OPERACION segundos.
 * Este procesador lee las operaciones a realizar de una cola. Cada vez
 * que realiza una operaci�n escribe el resultado por pantalla.
 */
public class Procesador extends Thread {
	
	private static final int TIEMPO_PROCESAMIENTO_OPERACION = 10;
	
	private volatile Cola<Operacion> operaciones;

	public Procesador(Cola<Operacion> cola) {
		super();
		operaciones=cola;
	}
	
	public void run() {
		// El try/catch es necesario por la instrucci�n sleep
		// (ver documentaci�n del propio JDK)
		try {
			// cuerpo principal del procesador: va leyendo operaciones
			// de la cola, y las va procesando, con la espera correspondiente
			// (que simula un tiempo de procesamiento bastante alto)
			while (true) {
				// Este bloque de sincronizaci�n es necesario para evitar que el thread
				// del procesador (�ste) y el de usuario (el del main) intenten acceder a la vez
				// a la cola.
				synchronized (operaciones) {
					// solo procesaremos la siguiente operaci�n de la cola, si
					// �sta est� disponible
					if (!operaciones.estaVacio()) {
						Operacion op=operaciones.desencolar();
						System.out.println("procesando: "+op);
						System.out.println("resultado: "+op.calcular());
						System.out.println("n�mero de operaciones pendientes: "+operaciones.numElems());
					}
				}
				sleep(TIEMPO_PROCESAMIENTO_OPERACION*1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread interrumpido mientras estaba esperando procesar la siguiente operaci�n.");
		}
	}

}
