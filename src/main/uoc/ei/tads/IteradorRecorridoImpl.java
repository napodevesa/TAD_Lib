package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de recorrido de los elementos de un
 * contenedor, por delegación en un recorrido de posiciones.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class IteradorRecorridoImpl<ERes,ERec>  implements Iterador<ERes>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Recorrido que implementa las operaciones por delegación. */
   protected Recorrido<ERec> recorrido;

   /**
    * Constructor con un parámetro.
    * @param recorrido  recorrido que proporciona los elementos
    */
   public IteradorRecorridoImpl(Recorrido<ERec> recorrido)
   {
      this.recorrido = recorrido;
   }

   /**
    * Comprueba si hay un primero o siguiente elemento. Es sensible a
    * eventuales alteraciones de la estructura de posiciones. Retorna falso si
    * el contenedor está vacío o ya se ha visitado el último elemento.
    * @return  cierto o falso, según si se puede avanzar o no se puede
    */
   public boolean haySiguiente() { return  recorrido.haySiguiente(); }

   /**
    * Accesor de lectura del primero o siguiente elemento de la enumeración.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeración y éste no existe.
    * @return  primero o siguiente elemento al actual
    */
   public ERes siguiente()  throws  ExcepcionPosicionInvalida
   {
      return getElem(recorrido.siguiente());
   }
 
   /**
    * Accesor de lectura del elemento almacenado en la posición.
    * @see  IteradorRecorridoImpl#siguiente()    
    * @param pos  posición que contiene el elemento
    * @return  elemento contenido a la posición
    * @pre pos!=null, ExcepcionPosicionInvalida
    */
   protected ERes getElem(Posicion<ERec> pos) {
   	return  (ERes)pos.getElem();
   }
   
}
