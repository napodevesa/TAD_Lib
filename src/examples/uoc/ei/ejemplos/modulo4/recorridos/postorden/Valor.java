package uoc.ei.ejemplos.modulo4.recorridos.postorden;

public class Valor extends ElementoExpresion {
	
	public Valor(double valor) { setResultado(valor); }

	public String toString() { return String.valueOf(getResultado()); }

}
