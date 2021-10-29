package uoc.ei.ejemplos.modulo4.recorridos.postorden;

import uoc.ei.ejemplos.modulo4.recorridos.RecorridosArbol;
import uoc.ei.tads.ArbolBinarioEncadenadoImpl;
import uoc.ei.tads.Posicion;

public class Expresion extends ArbolBinarioEncadenadoImpl<ElementoExpresion> {

	public double evaluar() {
		Evaluador avaluador=new Evaluador();
		avaluador.postorden(this);
		return raiz().getElem().getResultado();
	}

	
	protected class Evaluador extends RecorridosArbol<ElementoExpresion> {
		
		protected void tratarPosicion(Posicion<ElementoExpresion> posicion) {
			if (!esHoja(posicion)) {
				Operacion op=(Operacion)posicion.getElem();
				double hi=hijoIzquierdo(posicion).getElem().getResultado();
				double hd=hijoDerecho(posicion).getElem().getResultado();
				switch (op.getOperador()) {
					case '+' : op.setResultado(hi+hd);
						break;
					case '-' : op.setResultado(hi-hd);
						break;
					case '*' : op.setResultado(hi*hd);
						break;
					case '/' : op.setResultado(hi/hd);
						break;
				}
			}
		}
	}
	
	
	public static void popularArbolEjemplo(Expresion arbre) {
		Posicion<ElementoExpresion> op1=arbre.insertar(null,new Operacion('+'));
		arbre.insertarHijoIzquierdo(op1,new Valor(3));
		Posicion<ElementoExpresion> op2=arbre.insertarHijoDerecho(op1,new Operacion('*'));
		arbre.insertarHijoIzquierdo(op2,new Valor(5));
		Posicion<ElementoExpresion> op3=arbre.insertarHijoDerecho(op2,new Operacion('+'));
		arbre.insertarHijoIzquierdo(op3,new Valor(6));
		arbre.insertarHijoDerecho(op3,new Valor(1));
	}


	public static void main(String[] args) {
		Expresion arbre=new Expresion();
		popularArbolEjemplo(arbre);
		System.out.println("L'expressió és: "+arbre);
		double resultat=arbre.evaluar();
		System.out.println("El resultat d'avaluar l'expressió és: "+resultat);
	}

}
