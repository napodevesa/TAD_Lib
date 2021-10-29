package uoc.ei.ejemplos.modulo4.recorridos.postorden;

public class Operacion extends ElementoExpresion {
	
	private char operador;
	
	public Operacion(char operador) { this.operador=operador; }
	public char getOperador() { return operador; }
	
	public String toString() { return String.valueOf(operador); }

}
