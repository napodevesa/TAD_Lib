package uoc.ei.tads.grafos;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.ListaDoblementeEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Utilidades;

/** Clase que implementa un vértice para el caso de los Grafos Dirigidos.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 * @param <TElem>
 * @param <TEtiqueta>
 */

class VerticeNDImpl<TElem,TEtiqueta> extends VerticeImpl<TElem,TEtiqueta> {

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   protected ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>> aristas;


   public VerticeNDImpl(TElem elemento) {
   	  super(elemento);
   	  aristas=new ListaDoblementeEncadenada<Arista<TEtiqueta,TElem>>();
   }


   public void insertarArista(Arista<TEtiqueta,TElem> arista) {
   	  Posicion<Arista<TEtiqueta,TElem>> posicion=aristas.insertarAlFinal(arista);
   	  AristaImpl<TEtiqueta,TElem> aristaI=(AristaImpl<TEtiqueta,TElem>)arista;
   	  if (this==aristaI.verticeA)
   		aristaI.setPosicionListaVerticeA(posicion);
   	  else
   		aristaI.setPosicionListaVerticeB(posicion);
   }

	
   public Iterador<Arista<TEtiqueta,TElem>> aristas() {
   	  return aristas.elementos();
   }


   public void eliminarArista(AristaImpl<TEtiqueta,TElem> arista) {
   	  Posicion<Arista<TEtiqueta,TElem>> posicion;
   	  AristaImpl<TEtiqueta,TElem> arestaI=(AristaImpl<TEtiqueta,TElem>)arista;
   	  if (arestaI.verticeA==this)
   		posicion=arestaI.posicionListaDeVerticeA;
   	  else
   		posicion=arestaI.posicionListaDeVerticeB;
   	  aristas.borrar(posicion);
   }

   
   /*public String toString() {
		String str=super.toString()+"\n";
		str+="  aristas: "+aristas+"\n";
		return str;
   }*/

}
