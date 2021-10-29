package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de recorrido bidireccional de los
 * elementos de un contenedor, por delegaci�n en un recorrido de posiciones.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class IteradorRecorridoBidireccionalImpl<ERes,ERec>  extends IteradorRecorridoImpl<ERes,ERec>
      implements IteradorBidireccional<ERes>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Constructor con un par�metro.
    * @param recorrido  recorrido que proporciona los elementos
    */
   public IteradorRecorridoBidireccionalImpl(Recorrido<ERec> recorrido)
   {
      super(recorrido);
   }

   /**
    * Comprueba si hay un �ltimo o anterior elemento. Es sensible a
    * eventuales alteraciones de la estructura de posiciones. Retorna falso si
    * el contenedor est� vac�o o ya se ha visitado el primer elemento.
    * @return  cierto o falso, seg�n si se puede recular o no se puede
    */
   public boolean hayAnterior()
   {
      return  ((RecorridoBidireccional<ERec>)recorrido).hayAnterior();
   }

   /**
    * Accesor de lectura del �ltimo o anterior elemento de la enumeraci�n.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el anterior
    *            elemento de la enumeraci�n y �ste no existe.
    * @return  �ltimo o anterior elemento al actual
    */
   public ERes anterior()  throws  ExcepcionPosicionInvalida
   {
   	  Posicion<ERec> posicio=((RecorridoBidireccional<ERec>)recorrido).anterior();
      return (ERes)getElem(posicio);
   }
}
