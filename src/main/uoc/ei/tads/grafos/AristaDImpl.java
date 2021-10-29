package uoc.ei.tads.grafos;

import uoc.ei.tads.ExcepcionParametroIncorrecto;
import uoc.ei.tads.Utilidades;

/** 
 * Clase que representa una arista de un graf dirigido. La relaci�n es entre
 * dos v�rtices del mismo dominio y va del v�rtice origen al de destino.
 * @author Jordi Alvarez
 * @author Esteve Marin�
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0 */
class AristaDImpl<TEtiqueta,TElem>  extends AristaImpl<TEtiqueta,TElem>
									implements AristaDirigida<TEtiqueta,TElem>
{

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor con dos par�metros.
    * @param origen  v�rtice origen de la arista
    * @param destino  v�rtice destino de la arista
    * @exception ExcepcionParametroIncorrecto  si alg�n v�rtice es null
    * @pre origen!=null && destino!=null, ExcepcionParametroIncorrecto
    */
   public AristaDImpl(VerticeDImpl<TElem,TEtiqueta> origen, VerticeDImpl<TElem,TEtiqueta> destino) {
      super(origen, destino);
   }

   /** Accesor de lectura del v�rtice origen de la relaci�n dirigida.
    * @return  v�rtice origen */
   public Vertice<TElem> getVerticeOrigen() { return  verticeA; }

   /** Accesor de lectura del v�rtice destino de la relaci�n dirigida.
    * @return  v�rtice destino */
   public Vertice<TElem> getVerticeDestino() { return  verticeB; }


}