package uoc.ei.tads;
/**
 * Clase que implementa la excepción que se genera cuando el constructor o
 * cualquier método de una clase recibe un parámetro incorrecto que no
 * esté contemplado en otras excepciones.
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
public class ExcepcionParametroIncorrecto  extends ExcepcionTADs
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor sin parámetros. */
   public ExcepcionParametroIncorrecto() { super(); }

   /**
    * Constructor con un parámetro.
    * @param mensaje  mensaje con el error que ha generado la excepción
    */
   public ExcepcionParametroIncorrecto(String mensaje)
   {
      super(mensaje);
   }
}
