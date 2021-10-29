package uoc.ei.tads;
/**
 * Interfaz que extiende contenedor añadiendo las operaciones necesarias para
 * los contenedores acotados. Esta interfaz debe ser implementada por
 * todas las implementaciones de contenedores que son acotadas.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface ContenedorAcotado<E> extends Contenedor<E>
{
   /**
    * Comprueba si el contenedor está lleno.
    * @return  cierto si el contenedor está lleno
    */
   public boolean estaLleno();

}
