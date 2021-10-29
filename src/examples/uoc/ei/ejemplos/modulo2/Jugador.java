package uoc.ei.ejemplos.modulo2;

import uoc.ei.ejemplos.modulo1.generics.*;

public class Jugador {
	
	private String nombre;
	private Conjunto<Integer> carton;
	
	
	public Jugador(String nom) {
		this.nombre=nom;
	}
	
	
	public void setCarton(Conjunto<Integer> cartro) {
		this.carton=cartro;
		System.out.println(this.toString());
	}
	
	
	public void notificaNumeroCantado(int numero) {
		Integer elementoBorrado=carton.borrar(numero);
		if (elementoBorrado!=null)
			System.out.println("("+nombre+" lo tacha)");
	}
	
	
	public boolean cartonCompleto() {
		return carton.estaVacio();
	}
	
	
	public String getNombre() { return nombre; }
	
	
	public String toString() {
		return nombre+": "+(carton==null ? "carton no inicializado" : carton.toString());
	}

}
