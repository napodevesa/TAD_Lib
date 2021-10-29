package uoc.ei.tads;
/**
 * Clase que implementa la excepción que se genera cuando una clase no
 * implementa un método por razones de eficiencia o seguridad.
 *
 * Es una extensión de las excepciones en tiempo de ejecución gestionadas por el
 * propio compilador.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ExcepcionOperacionNoSoportada  extends ExcepcionTADs
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor sin parámetros. */
   public ExcepcionOperacionNoSoportada() { super(); }

   /**
    * Constructor con un parámetro.
    * @param mensaje  mensaje con el error que ha generado la excepción
    */
   public ExcepcionOperacionNoSoportada(String mensaje)
   {
      super(mensaje);
   }
}
