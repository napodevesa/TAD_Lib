package uoc.ei.tads;
/**
 * Clase que implementa las excepciones que se pueden producir generalmente
 * por errores de programación a la librería de TADs, los cuales se habrían
 * evitado haciendo correctamente las comprobaciones oportunas.
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
public class ExcepcionTADs  extends RuntimeException
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor sin parámetros. */
   public ExcepcionTADs() { super(); }

   /**
    * Constructor con un parámetro.
    * @param mensaje  mensaje con el error que ha generado la excepción
    */
   public ExcepcionTADs(String mensaje) { super(mensaje); }
}
