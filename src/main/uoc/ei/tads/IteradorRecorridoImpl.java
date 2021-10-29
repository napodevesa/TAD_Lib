package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de recorrido de los elementos de un
 * contenedor, por delegaci�n en un recorrido de posiciones.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class IteradorRecorridoImpl<ERes,ERec>  implements Iterador<ERes>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Recorrido que implementa las operaciones por delegaci�n. */
   protected Recorrido<ERec> recorrido;

   /**
    * Constructor con un par�metro.
    * @param recorrido  recorrido que proporciona los elementos
    */
   public IteradorRecorridoImpl(Recorrido<ERec> recorrido)
   {
      this.recorrido = recorrido;
   }

   /**
    * Comprueba si hay un primero o siguiente elemento. Es sensible a
    * eventuales alteraciones de la estructura de posiciones. Retorna falso si
    * el contenedor est� vac�o o ya se ha visitado el �ltimo elemento.
    * @return  cierto o falso, seg�n si se puede avanzar o no se puede
    */
   public boolean haySiguiente() { return  recorrido.haySiguiente(); }

   /**
    * Accesor de lectura del primero o siguiente elemento de la enumeraci�n.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
    *            elemento de la enumeraci�n y �ste no existe.
    * @return  primero o siguiente elemento al actual
    */
   public ERes siguiente()  throws  ExcepcionPosicionInvalida
   {
      return getElem(recorrido.siguiente());
   }
 
   /**
    * Accesor de lectura del elemento almacenado en la posici�n.
    * @see  IteradorRecorridoImpl#siguiente()    
    * @param pos  posici�n que contiene el elemento
    * @return  elemento contenido a la posici�n
    * @pre pos!=null, ExcepcionPosicionInvalida
    */
   protected ERes getElem(Posicion<ERec> pos) {
   	return  (ERes)pos.getElem();
   }
   
}
