package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de recorrido bidireccional de los
 * elementos de un contenedor.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface IteradorBidireccional<E>  extends Iterador<E>
{

	/**
	 * Comprueba si hay un último o anterior elemento.
	 */
   public boolean hayAnterior();

   /**
    * Accesor de lectura del último o anterior elemento de la enumeración.
    * @exception ExcepcionPosicionInvalida  si se quiere obtener el anterior
    *            elemento de la enumeración y no existe tal elemento.
    * @return  último o anterior elemento al actual.
    */
   public E anterior()  throws  ExcepcionPosicionInvalida;
 }
