
package uoc.ei.tads;

import java.io.IOException;
import java.io.InputStream;

/** Esta clase proporciona un conjunto de m�todos est�ticos
 * que se utilizan en la implementaci�n de los TADs.
 * Tambi�n proporciona m�todos p�blicos est�ticos con el objetivo
 * de que se puedan usar por los usuarios de la
 * librer�a de TADs.
 * 
 * @author Jordi Alvarez
 * @author Esteve Marin�
 */
public class Utilidades {

	/** Versi�n actual de la librer�a de TADs. Se utiliza para
	 * dotar de un identificador a cada una de las clases de la
	 * librer�a. Este identificador se utiliza en la versi�n
	 * 1.5 de Java para determinar la compatibilidad entre objetos
	 * serializados.
	 */
	private static final String version = "2.00.00";
	

	/** Retorna la versi�n de la librer�a de TADs.
	 * @return La versi�n. El formato es x.yy.zz. Un cambio en la 'x'
	 * significa un cambio en el dise�o de la librer�a como tal.
	 * Un cambio en 'yy' significa un cambio a nivel de funcionalidad;
	 * y un cambio a 'zz' significa un cambio donde se han solucionado
	 * problemas existentes a la versi�n anterior.
	 */
	public static String getVersion() {
		return version;
	}
	
	
	/** Retorna la versi�n de la librer�a de TADs como un long.
	 * @return el n�mero correspondiente a la versi�n de la librer�a.
	 * El m�todo usado para la conversi�n es: versi�n 1.00.00 --> 10000.
	 */
	public static long getVersioLong() {
		String str=version.replaceAll("[.]","");
		return Long.parseLong(str);
	}
	
	/** Retorna un identificador que se puede usar por las clases
	 * de la librer�a para determinar la compatibilidad entre objetos
	 * serializados de la misma clase. Actualmente este identificador
	 * se corresponde con la versi�n de la librer�a.
	 * @return Identificador para la serializaci�n.
	 */
	public static long getSerialVersionUID() {
		return getVersioLong();
	}

	
	/** Lee una l�nea del stream de entrada dado.
	 * @param str String de informaci�n que se saca por la salida est�ndar
	 * (�til sobre todo si la entrada es la entrada est�ndar para sacar un
	 * mensaje del qu� se pide. Si no, se puede dejar vac�o.
	 * @param in Stream de entrada de donde se leer� la l�nea.
	 * @return Un String con el dato le�do.
	 * @throws IOException En caso de que se haya producido alg�n problema
	 * al leer el dato o sacar el mensaje por la salida est�ndar.
	 */
    public static String leerString(String str,InputStream in) throws IOException {
        if (str!=null) System.out.println(str);
        StringBuffer buffer=new StringBuffer();
        int c=in.read();
        while (c=='\n' || c=='\r') c=in.read();
        while (c!='\n' && c!='\r') {
            buffer.append((char)c);
            c=in.read();
        }
        return buffer.toString();
    }
    
    
	/** Retorna en un string una representaci�n textual del contenedor.
	 * @param nombreClase Nombre de la clase correspondiente al contenedor.
	 * @param rec Recorrido con todos los elementos del contenedor.
	 * @return un String con la representaci�n textual del contenedor.
	 */
	public static String toStringContenedor(String nombreClase,Recorrido rec) {
	      StringBuffer buffer = new StringBuffer("{"+nombreClase+": ");
	      while (rec.haySiguiente()) {
	      	Posicion kv=rec.siguiente();
	        buffer.append(kv.getElem());
	        if (rec.haySiguiente()) buffer.append(", ");
	      }
	      buffer.append("}");
	      return  buffer.toString();
		}


	/** Retorna en un string una representaci�n textual de un contenedor que delega
	 * su implementaci�n en otro contenedor (por ejemplo ConjuntoAVLImpl
	 * delega su implementaci�n en ArbolAVL).
	 * @param nombreClasse Nombre de la clase correspondiente al contenedor que
	 * usa delegaci�n.
	 * @param implementacion Contenedor Implementaci�n usado.
	 * @return un String con la representaci�n textual del contenedor.
	 */
	public static String toStringContenedorDelegacion(String nombreClasse,Contenedor implementacion) {
	   	return  "["+nombreClasse+": "+implementacion.toString()+"]";
		}

	
}
