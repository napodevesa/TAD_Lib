package uoc.ei.ejemplos.modulo1;

import java.io.IOException;
import java.io.InputStream;

/** Prueba el TAD conjunto, creando un conjunto e introduïnt-hi
 * elementos que el usuario va dando por la entrada estàndar.
 * @author Jordi Alvarez
 * @author Esteve Marine
 * 			Universitat Oberta de Catalunya (UOC)
 */
public class PruebaConjunto {
	
	
    protected static String readLine(String str,InputStream in) throws IOException {
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
    
    
	public static void main(String[] args) {
		System.out.println("Introduce números naturales a añadir al conjunto");
		boolean salir=false;
		ConjuntoAcotado cjt=new ConjuntoVectorImpl(10);
		while (!salir) {
			try {
				String elem=readLine("Introduce un número (o \"fin\" para acabar): ",System.in);
				salir=elem.equalsIgnoreCase("fin");
				cjt.insertar(new Integer(Integer.parseInt(elem)));
				if (cjt.estaLleno()) {
					System.out.println("El conjunto está lleno. No se pueden añadir más elementos.");
					salir=true;
				}
			} catch (NumberFormatException e) {
				System.out.println("El texto introducido no es un numero, vuelve a probar provar.");
			} catch (IOException e) {
				System.out.println("Problema con la entrada, abortando el programa");
				salir=true;
			}
		}
		System.out.println("El conjunto resultante es: "+cjt);
	}

}
