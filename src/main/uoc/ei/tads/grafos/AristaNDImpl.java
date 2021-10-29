package uoc.ei.tads.grafos;

import uoc.ei.tads.ExcepcionParametroIncorrecto;
import uoc.ei.tads.Utilidades;

/** 
 * Clase que representa una arista de un grafo dirigido. La relaci�n es entre
 * dos v�rtices del mismo dominio y va del v�rtice origen al de destino.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class AristaNDImpl<TEtiqueta,TElem>  extends AristaImpl<TEtiqueta,TElem>
									implements AristaNoDirigida<TEtiqueta,TElem>
{

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
		
   /** Constructor con dos par�metros.
    * @param v1 v�rtice A de la arista
    * @param v2 v�rtice B de la arista
    * @exception ExcepcionParametroIncorrecto  si alg�n v�rtice es null
    * @pre v1!=null && v2!=null, ExcepcionParametroIncorrecto
    */
   public AristaNDImpl(VerticeNDImpl<TElem,TEtiqueta> v1, VerticeNDImpl<TElem,TEtiqueta> v2) {
      super(v1,v2);
   }

   public Vertice<TElem> getVertice1() { return  verticeA; }
   public Vertice<TElem> getVertice2() { return  verticeB; }


}