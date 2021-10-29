package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de recorrido de los valores de los
 * elementos de un contenedor, por delegación en un recorrido de posiciones.
 *
 * @see  ClaveValor
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class IteradorRecorridoValoresImpl<C,E>  extends IteradorRecorridoImpl<E,ClaveValor<C,E>>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Constructor con un parámetro.
    * @param recorrido  recorrido que proporciona los elementos
    */
   public IteradorRecorridoValoresImpl(Recorrido<ClaveValor<C,E>> recorrido)
   {
      super(recorrido);
   }

   /**
    * Accesor de lectura del elemento almacenado en la posición.
    * @see  IteradorRecorridoImpl#siguiente()    
    * @param pos  posición que contiene el elemento
    * @return  elemento contenido a la posición
    */
   protected E getElem(Posicion<ClaveValor<C,E>> pos)
   {
      return  pos.getElem().getValor();
   }
}
