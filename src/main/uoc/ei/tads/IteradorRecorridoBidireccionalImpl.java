package uoc.ei.tads;
/**
 * Clase que implementa las operaciones de recorrido bidireccional de los
 * elementos de un contenedor, por delegación en un recorrido de posiciones.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
class IteradorRecorridoBidireccionalImpl<ERes,ERec>  extends IteradorRecorridoImpl<ERes,ERec>
      implements IteradorBidireccional<ERes>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Constructor con un parámetro.
    * @param recorrido  recorrido que proporciona los elementos
    */
   public IteradorRecorridoBidireccionalImpl(Recorrido<ERec> recorrido)
   {
      super(recorrido);
   }

   /**
    * Comprueba si hay un último o anterior elemento. Es sensible a
    * eventuales alteraciones de la estructura de posiciones. Retorna falso si
    * el contenedor está vacío o ya se ha visitado el primer elemento.
    * @return  cierto o falso, según si se puede recular o no se puede
    */
   public boolean hayAnterior()
   {
      return  ((RecorridoBidireccional<ERec>)recorrido).hayAnterior();
   }

   /**
    * Accesor de lectura del último o anterior elemento de la enumeración.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el anterior
    *            elemento de la enumeración y éste no existe.
    * @return  último o anterior elemento al actual
    */
   public ERes anterior()  throws  ExcepcionPosicionInvalida
   {
   	  Posicion<ERec> posicio=((RecorridoBidireccional<ERec>)recorrido).anterior();
      return (ERes)getElem(posicio);
   }
}
