package uoc.ei.ejemplos.modulo3.cola;

/** Operacion que se corresponde a la suma de dos números enteros.
 * Por el momento es el único tipo de operación implementada.
 */
public class Suma extends Operacion {
	
	long op1,op2;

	public Suma(long op1,long op2) {
		super();
		this.op1=op1;
		this.op2=op2;
	}


	public String calcular() {
		long resultado=op1+op2;
		return Long.toString(resultado);
	}

	
	public String toString() {
		return op1+" + "+op2;
	}

}
