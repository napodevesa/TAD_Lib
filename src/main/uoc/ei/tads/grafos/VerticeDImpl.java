package uoc.ei.tads.grafos;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorMultiple;
import uoc.ei.tads.ListaDoblementeEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Utilidades;

/** Clase que implementa un vértice para el caso de los Grafos Dirigidos.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class VerticeDImpl<TElem,TEtiqueta> extends VerticeImpl<TElem,TEtiqueta> {

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();


   protected ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>> aristasConOrigenEnElVertice;
   protected ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>> aristasConDestinoEnElVertice;


   public VerticeDImpl(TElem element) {
   	  super(element);
   	  aristasConOrigenEnElVertice=new ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>>();
   	  aristasConDestinoEnElVertice=new ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>>();
   }


   public void insertarArista(Arista<TEtiqueta,TElem> aresta) {
	   	AristaImpl<TEtiqueta,TElem> arestaI=(AristaImpl<TEtiqueta,TElem>)aresta;
   		if (arestaI.verticeA==this)
   			arestaI.setPosicionListaVerticeA(aristasConOrigenEnElVertice.insertarAlFinal((Arista<TEtiqueta,TElem>)aresta));
   		if (arestaI.verticeB==this)
   			arestaI.setPosicionListaVerticeB(aristasConDestinoEnElVertice.insertarAlFinal((Arista<TEtiqueta,TElem>)aresta));
   }


   public void eliminarArista(AristaImpl<TEtiqueta,TElem> aresta) {
   	  Posicion<Arista<TEtiqueta,TElem>> posicio;
   	  if (aresta.verticeA==this) {
   	  	  posicio=aresta.posicionListaDeVerticeA;
	   	  aristasConOrigenEnElVertice.borrar(posicio);
   	  }
   	  else {
   	  	  posicio=aresta.posicionListaDeVerticeB;
	   	  aristasConDestinoEnElVertice.borrar(posicio);
   	  }
   }

	
   public Iterador<Arista<TEtiqueta,TElem>> aristas() {
   	  IteradorMultiple<Arista<TEtiqueta,TElem>> arestes=new IteradorMultiple<Arista<TEtiqueta,TElem>>();
   	  arestes.insertarIterador(aristasConOrigenEnElVertice.elementos());
   	  arestes.insertarIterador(aristasConDestinoEnElVertice.elementos());
   	  return arestes;
   }
   
   /*
   public String toString() {
   		String str=super.toString()+"\n";
   		str+="  aristas-origen: "+aristasConOrigenEnElVertice+"\n";
   		str+="  aristas-desti: "+aristasConDestinoEnElVertice+"\n";
   		return str;
   }*/
   

}