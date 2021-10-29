package uoc.ei.tads.grafos;

import uoc.ei.tads.ExcepcionParametroIncorrecto;
import uoc.ei.tads.Utilidades;

/** 
 * Clase que representa una arista de un grafo dirigido. La relación es entre
 * dos vértices del mismo dominio y va del vértice origen al de destino.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class AristaNDImpl<TEtiqueta,TElem>  extends AristaImpl<TEtiqueta,TElem>
									implements AristaNoDirigida<TEtiqueta,TElem>
{

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
		
   /** Constructor con dos parámetros.
    * @param v1 vértice A de la arista
    * @param v2 vértice B de la arista
    * @exception ExcepcionParametroIncorrecto  si algún vértice es null
    * @pre v1!=null && v2!=null, ExcepcionParametroIncorrecto
    */
   public AristaNDImpl(VerticeNDImpl<TElem,TEtiqueta> v1, VerticeNDImpl<TElem,TEtiqueta> v2) {
      super(v1,v2);
   }

   public Vertice<TElem> getVertice1() { return  verticeA; }
   public Vertice<TElem> getVertice2() { return  verticeB; }


}