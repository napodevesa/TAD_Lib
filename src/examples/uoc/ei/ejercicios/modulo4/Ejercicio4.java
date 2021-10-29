package uoc.ei.ejercicios.modulo4;

import uoc.ei.ejemplos.modulo4.recorridos.postorden.*;
import uoc.ei.tads.*;

public class Ejercicio4 {

	
	protected static class ExpresionImprimible extends Expresion {
		
		public String toString() {
			return toString(raiz());
		}
		
		
		protected String toString(Posicion<ElementoExpresion> subexpresion) {
			String res;
			if (esHoja(subexpresion))
				res=subexpresion.getElem().toString();
			else
				res="("+toString(hijoIzquierdo(subexpresion))+subexpresion.getElem()+toString(hijoDerecho(subexpresion))+")";
			return res;
		}
	}
	

	public static void main(String[] args) {
		Expresion expresion=new ExpresionImprimible();
		Expresion.popularArbolEjemplo(expresion);
		System.out.println("La expresión es: "+expresion);
		double resultado=expresion.evaluar();
		System.out.println("El resultado de evaluar la expresión es: "+resultado);
	}

}
