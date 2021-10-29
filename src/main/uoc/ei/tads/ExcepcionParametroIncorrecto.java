package uoc.ei.tads;
/**
 * Clase que implementa la excepci�n que se genera cuando el constructor o
 * cualquier m�todo de una clase recibe un par�metro incorrecto que no
 * est� contemplado en otras excepciones.
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
public class ExcepcionParametroIncorrecto  extends ExcepcionTADs
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Constructor sin par�metros. */
   public ExcepcionParametroIncorrecto() { super(); }

   /**
    * Constructor con un par�metro.
    * @param mensaje  mensaje con el error que ha generado la excepci�n
    */
   public ExcepcionParametroIncorrecto(String mensaje)
   {
      super(mensaje);
   }
}
