package uoc.ei.tads.grafos;

import uoc.ei.tads.ExcepcionParametroIncorrecto;
import uoc.ei.tads.Utilidades;

/** 
 * Clase que representa una arista de un graf dirigido. La relación es entre
 * dos vértices del mismo dominio y va del vértice origen al de destino.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0 */
class AristaDImpl<TEtiqueta,TElem>  extends AristaImpl<TEtiqueta,TElem>
									implements AristaDirigida<TEtiqueta,TElem>
{

   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor con dos parámetros.
    * @param origen  vértice origen de la arista
    * @param destino  vértice destino de la arista
    * @exception ExcepcionParametroIncorrecto  si algún vértice es null
    * @pre origen!=null && destino!=null, ExcepcionParametroIncorrecto
    */
   public AristaDImpl(VerticeDImpl<TElem,TEtiqueta> origen, VerticeDImpl<TElem,TEtiqueta> destino) {
      super(origen, destino);
   }

   /** Accesor de lectura del vértice origen de la relación dirigida.
    * @return  vértice origen */
   public Vertice<TElem> getVerticeOrigen() { return  verticeA; }

   /** Accesor de lectura del vértice destino de la relación dirigida.
    * @return  vértice destino */
   public Vertice<TElem> getVerticeDestino() { return  verticeB; }


}