package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de recorrido de las posiciones
 * de un contenedor en ambas direcciones.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface RecorridoBidireccional<E>  extends Recorrido<E> {

		/** Enumeraci�n que nos sirve para indicar por donde empieza el recorrido:
		 * si por el principio o por el final de la col.lecci�.
		 */
		public enum InicioRecorrido { PRINCIPIO, FINAL };
	
	   /**
	    * Comprueba si hay un primero o siguiente elemento.
	    */
	   public boolean hayAnterior();

	   /**
	    * Accesor de lectura del anterior elemento de la enumeraci�n.
	    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
	    *            elemento de la enumeraci�n y no existe tal elemento.
	    * @return  primero o siguiente elemento al actual.
	    */
	   public Posicion<E> anterior()  throws  ExcepcionPosicionInvalida;

}
