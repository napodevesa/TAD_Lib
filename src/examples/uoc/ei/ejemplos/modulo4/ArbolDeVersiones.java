package uoc.ei.ejemplos.modulo4;

import uoc.ei.tads.ArbolBinarioEncadenadoImpl;
import uoc.ei.tads.Posicion;

public class ArbolDeVersiones extends ArbolBinarioEncadenadoImpl<String> {
	
	
	public String versionIncompatible() {
		String resultado="XX";
		Posicion<String> p=raiz();
		Posicion<String> h=hijoDerecho(p);
		while (h!=null) {
			p=h;
			h=hijoDerecho(p);
		}
		h=hijoIzquierdo(p);
		if (h!=null)
			resultado=h.getElem();
		return resultado;
	}


	public static void popularArbolEjemplo(ArbolDeVersiones arbol) {
		Posicion<String> v10=arbol.insertar(null,"1.0");
		Posicion<String> v11=arbol.insertarHijoIzquierdo(v10,"1.1");
		arbol.insertarHijoIzquierdo(v11,"1.2");
		Posicion<String> v20=arbol.insertarHijoDerecho(v10,"2.0");
		Posicion<String> v21=arbol.insertarHijoIzquierdo(v20,"2.1");
		arbol.insertarHijoDerecho(v20,"3.0");
		arbol.insertarHijoDerecho(v21,"4.0");
	}
	
	
	public static void main(String[] args) {
		ArbolDeVersiones arbol=new ArbolDeVersiones();
		popularArbolEjemplo(arbol);
		String versionIncompatible=arbol.versionIncompatible();
		System.out.println("Primera versión incompatible: "+versionIncompatible);
	}

}



