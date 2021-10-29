package uoc.ei.ejercicios.modulo5.ejercicio3;

import uoc.ei.tads.*;

public class ServicioImpresion {

	/** Contenedor para la cola */
	 private ColaConPrioridad<DocumentoConPrioridad> cola;

	/** Identificador para un nuevo documento que llega */
	 private int ident;
	 
	 
	 public ServicioImpresion() {
		 ident=0;
		 cola=new ColaConPrioridad<DocumentoConPrioridad>();
	 }

	public ColaConPrioridad getCola(){
	  return(cola);
	 }


	 public int insertarDocumento (Documento documento, int prioridad) {
	    DocumentoConPrioridad docP = new DocumentoConPrioridad(documento, prioridad,  ident);
	    cola.encolar(docP);
	    System.out.println("Documento con identificador " + docP.getIdentificador() + " añadido a la lista");
	    return(ident++);
	 }


	 public void eliminarDocumento(int id) {
	    Lista<DocumentoConPrioridad> listaAux = new ListaEncadenada<DocumentoConPrioridad>();
	    boolean encontrado = false;
	    while(!cola.estaVacio() && !encontrado) {
	    	DocumentoConPrioridad docE = cola.desencolar();
	    	if(docE.getIdentificador()==id) encontrado=true;
	    	else listaAux.insertarAlFinal(docE);
	    }
	    Iterador<DocumentoConPrioridad> docs=listaAux.elementos();
	    while (docs.haySiguiente())
	    	cola.encolar(docs.siguiente());
	    System.out.println("Documento con identificador " + id + " eliminado");
	 }

	 
	 public void enviarDocumento () {
	  if(!cola.estaVacio()) {
	     DocumentoConPrioridad docE = cola.desencolar();
	     imprimir(docE.getDocumento());
	     System.out.println("Prioridad del doc impreso: " + docE.getPrioridad());
	    }
	 }


	 private void imprimir(Documento document) {
	     System.out.println(document);
	 }

}
