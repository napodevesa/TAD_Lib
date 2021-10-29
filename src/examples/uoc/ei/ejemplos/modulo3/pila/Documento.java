
package uoc.ei.ejemplos.modulo3.pila;

import uoc.ei.tads.*;

/** Representación de un documento como un vector de páginas.
 */
public class Documento {
	
	Pagina[] paginas;
	int numPaginas;
	
	
	Documento(int numPaginas) {
		paginas=new Pagina[numPaginas];
		this.numPaginas=0;
	}
	
	
	public void insertar(Pagina pagina) {
		paginas[numPaginas]=pagina;
		numPaginas++;
	}
	
	
	public boolean estaLleno() { return numPaginas==paginas.length; }
	
	
	protected Documento girar() {
		Documento documentAlReves=new Documento(paginas.length);
		Pila<Pagina> pila=new PilaVectorImpl<Pagina>(paginas.length);
		for(int i=0;i<numPaginas;i++)
			pila.apilar(paginas[i]);
		while(!pila.estaVacio())
			documentAlReves.insertar(pila.desapilar());
		return documentAlReves;
	}

	
	public void imprimir(boolean ordenInverso) {
		Documento docOrdenAdecuado=
			ordenInverso ? girar() : this;
		System.out.println(docOrdenAdecuado.toString());
	}
	
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<numPaginas;i++)
			sb.append(paginas[i].toString());
		return sb.toString();
	}
	
	
}
