package uoc.ei.ejemplos.modulo2;

import java.util.*;

import uoc.ei.ejemplos.modulo1.generics.*;

public class Bingo {
	

	private Jugador[] jugadores;
	private int numeroDeJugadores;
	private int numeroDeBolas;
	private int tamanoCarton;
	
	private Conjunto<Integer> bolasCantadas;

	private Random generadorNumerosCarton;
	
	
	public Bingo(int numeroDeJugadoresMaximo,int numeroDeBolas,int tamanoCarton) {
		jugadores=new Jugador[numeroDeJugadoresMaximo];
		numeroDeJugadores=0;
		this.numeroDeBolas=numeroDeBolas;
		this.tamanoCarton=tamanoCarton;
		generadorNumerosCarton=new Random();
	}
	
	
	public void nuevoJugador(String nom) {
		Jugador jugador=new Jugador(nom);
		jugadores[numeroDeJugadores]=jugador;
		numeroDeJugadores++;
	}
	
	
	public void prepararPartida() {
		System.out.println("Preparando partida...");
		for (int i=0;i<numeroDeJugadores;i++) {
			Conjunto<Integer> carton=generarCarton();
			jugadores[i].setCarton(carton);
		}
		bolasCantadas=new ConjuntoVectorImpl<Integer>(numeroDeBolas);
	}
	
	
	public String jugarPartida() {
		System.out.println("Inicio partida...");
		String ganador=null;
		while (ganador==null) {
			int numeroCantado=sacarNumeroDelBombo();
			System.out.println("Cantado el número "+numeroCantado);
			for (int i=0;i<numeroDeJugadores && ganador==null;i++) {
				jugadores[i].notificaNumeroCantado(numeroCantado);
				if (jugadores[i].cartonCompleto())
					ganador=jugadores[i].getNombre();
			}
		}
		System.out.println("Partida acabada");
		return ganador;
	}
	
	
	protected Conjunto<Integer> generarCarton() {
		Conjunto<Integer> cartro=new ConjuntoVectorImpl<Integer>(tamanoCarton);
		for (int n=1;n<tamanoCarton;n++)
			cartro.insertar(generadorNumerosCarton.nextInt(numeroDeBolas)+1);
		return cartro;
	}
	
	
	protected int sacarNumeroDelBombo() {
		int numero=generadorNumerosCarton.nextInt(numeroDeBolas)+1;
		while (bolasCantadas.esta(numero))
			numero=generadorNumerosCarton.nextInt(numeroDeBolas)+1;
		bolasCantadas.insertar(numero);
		return numero;
	}
	

	public static void main(String[] args) {
		Bingo bingo=new Bingo(4,100,10);
		bingo.nuevoJugador("Juan");
		bingo.nuevoJugador("Ana");
		bingo.nuevoJugador("Maria");
		bingo.nuevoJugador("Miguel");
		bingo.prepararPartida();
		String guanyador=bingo.jugarPartida();
		System.out.println("El ganador de la partida es: "+guanyador);
	}

}
