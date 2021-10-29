package uoc.ei.tads;
/**
 * Interfaz que define las operaciones de recorrido de las posiciones
 * de un contenedor en ambas direcciones.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface RecorridoBidireccional<E>  extends Recorrido<E> {

		/** Enumeración que nos sirve para indicar por donde empieza el recorrido:
		 * si por el principio o por el final de la col.lecció.
		 */
		public enum InicioRecorrido { PRINCIPIO, FINAL };
	
	   /**
	    * Comprueba si hay un primero o siguiente elemento.
	    */
	   public boolean hayAnterior();

	   /**
	    * Accesor de lectura del anterior elemento de la enumeración.
	    * @exception ExcepcionPosicionInvalida  si se quiere obtener el siguiente
	    *            elemento de la enumeración y no existe tal elemento.
	    * @return  primero o siguiente elemento al actual.
	    */
	   public Posicion<E> anterior()  throws  ExcepcionPosicionInvalida;

}
