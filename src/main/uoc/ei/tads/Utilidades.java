
package uoc.ei.tads;

import java.io.IOException;
import java.io.InputStream;

/** Esta clase proporciona un conjunto de métodos estáticos
 * que se utilizan en la implementación de los TADs.
 * También proporciona métodos públicos estáticos con el objetivo
 * de que se puedan usar por los usuarios de la
 * librería de TADs.
 * 
 * @author Jordi Alvarez
 * @author Esteve Mariné
 */
public class Utilidades {

	/** Versión actual de la librería de TADs. Se utiliza para
	 * dotar de un identificador a cada una de las clases de la
	 * librería. Este identificador se utiliza en la versión
	 * 1.5 de Java para determinar la compatibilidad entre objetos
	 * serializados.
	 */
	private static final String version = "2.00.00";
	

	/** Retorna la versión de la librería de TADs.
	 * @return La versión. El formato es x.yy.zz. Un cambio en la 'x'
	 * significa un cambio en el diseño de la librería como tal.
	 * Un cambio en 'yy' significa un cambio a nivel de funcionalidad;
	 * y un cambio a 'zz' significa un cambio donde se han solucionado
	 * problemas existentes a la versión anterior.
	 */
	public static String getVersion() {
		return version;
	}
	
	
	/** Retorna la versión de la librería de TADs como un long.
	 * @return el número correspondiente a la versión de la librería.
	 * El método usado para la conversión es: versión 1.00.00 --> 10000.
	 */
	public static long getVersioLong() {
		String str=version.replaceAll("[.]","");
		return Long.parseLong(str);
	}
	
	/** Retorna un identificador que se puede usar por las clases
	 * de la librería para determinar la compatibilidad entre objetos
	 * serializados de la misma clase. Actualmente este identificador
	 * se corresponde con la versión de la librería.
	 * @return Identificador para la serialización.
	 */
	public static long getSerialVersionUID() {
		return getVersioLong();
	}

	
	/** Lee una línea del stream de entrada dado.
	 * @param str String de información que se saca por la salida estándar
	 * (útil sobre todo si la entrada es la entrada estándar para sacar un
	 * mensaje del qué se pide. Si no, se puede dejar vacío.
	 * @param in Stream de entrada de donde se leerá la línea.
	 * @return Un String con el dato leído.
	 * @throws IOException En caso de que se haya producido algún problema
	 * al leer el dato o sacar el mensaje por la salida estàndar.
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
    
    
	/** Retorna en un string una representación textual del contenedor.
	 * @param nombreClase Nombre de la clase correspondiente al contenedor.
	 * @param rec Recorrido con todos los elementos del contenedor.
	 * @return un String con la representación textual del contenedor.
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


	/** Retorna en un string una representación textual de un contenedor que delega
	 * su implementación en otro contenedor (por ejemplo ConjuntoAVLImpl
	 * delega su implementación en ArbolAVL).
	 * @param nombreClasse Nombre de la clase correspondiente al contenedor que
	 * usa delegación.
	 * @param implementacion Contenedor Implementación usado.
	 * @return un String con la representación textual del contenedor.
	 */
	public static String toStringContenedorDelegacion(String nombreClasse,Contenedor implementacion) {
	   	return  "["+nombreClasse+": "+implementacion.toString()+"]";
		}

	
}
