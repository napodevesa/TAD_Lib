package uoc.ei.ejemplos.modulo5;

import uoc.ei.tads.*;

public class UsoColaPrioridad {


	public static void main(String[] args) {
		ColaConPrioridad<Integer> cP=new ColaConPrioridad<Integer>();
		Integer e;
		cP.encolar(new Integer(5));
		cP.encolar(3); 	// Debido al autoboxing tiene el mismo efecto que encuar el Integer(3) 
		cP.encolar(4);
		cP.encolar(1);
		cP.encolar(2);
		// El contenido de la cola después de las inserciones anteriores
		// es: 1, 2, 3, 4, 5
		if (!cP.estaVacio()) {
			e = cP.desencolar();
			System.out.println(e); // Muestra 1
		}
		// El El contenido de la cola después de la
		// supresión anterior es: 2, 3, 4, 5
		e = cP.primero();
		System.out.println(e);  // Muestra 2
		while (!cP.estaVacio())
			System.out.print(cP.desencolar()+", "); 
		// Muestra 2, 3, 4, 5
	}

}
