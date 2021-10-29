package uoc.ei.tads;
/**
 * Clase que implementa las excepciones que se pueden producir generalmente
 * por errores de programaci�n a la librer�a de TADs, los cuales se habr�an
 * evitado haciendo correctamente las comprobaciones oportunas.
 *
 * Es una extensi�n de las excepciones en tiempo de ejecuci�n gestionadas por el
 * propio compilador.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ExcepcionTADs  extends RuntimeException
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor sin par�metros. */
   public ExcepcionTADs() { super(); }

   /**
    * Constructor con un par�metro.
    * @param mensaje  mensaje con el error que ha generado la excepci�n
    */
   public ExcepcionTADs(String mensaje) { super(mensaje); }
}
