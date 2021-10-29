package uoc.ei.tads;
/**
 * Interfaz que extiende contenedor a�adiendo las operaciones necesarias para
 * los contenedores acotados. Esta interfaz debe ser implementada por
 * todas las implementaciones de contenedores que son acotadas.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface ContenedorAcotado<E> extends Contenedor<E>
{
   /**
    * Comprueba si el contenedor est� lleno.
    * @return  cierto si el contenedor est� lleno
    */
   public boolean estaLleno();

}
