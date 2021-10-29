package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de recorrido bidireccional de los
 * elementos de un contenedor.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface IteradorBidireccional<E>  extends Iterador<E>
{

	/**
	 * Comprueba si hay un �ltimo o anterior elemento.
	 */
   public boolean hayAnterior();

   /**
    * Accesor de lectura del �ltimo o anterior elemento de la enumeraci�n.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el anterior
    *            elemento de la enumeraci�n y no existe tal elemento.
    * @return  �ltimo o anterior elemento al actual.
    */
   public E anterior()  throws  ExcepcionPosicionInvalida;
 }
