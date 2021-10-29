package uoc.ei.ejemplos.modulo9.dns;

import java.io.*;
import java.util.StringTokenizer;

import uoc.ei.tads.Iterador;

/**
 * Clase principal encargada de ejecutar un juego de pruebas proveniente de un 
 * fichero o de la entrada est�ndar. Los resultados se escriben en un fichero o
 * a la salida est�ndar.
 * @author David F�guls
 * @author Jordi Alvarez (adaptaci�n para la versi�n 2.0.0 de la librer�a)
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Principal {
	
	BufferedReader in;
	PrintStream out;
	DNS dns;
	
	
	   /**
	    * Constructor que recibe la lista de argumentos de la l�nea de comandos.
	    * 
	    * @param args  argumentos de la l�nea de comandos.<br>
	    * Podemos recibir diferentes n�meros de par�metros:<br>
	    * -ning�no (utilizaremos la entrada y salida est�ndar)<br>
	    * -uno (utilizamos la entrada est�ndar y la salida hacia fichero args[0])<br>
	    * -dos (entrada del fichero args[0] y salida en el fichero args[1]<br>
	    */
 	public Principal(String[] args) throws FileNotFoundException {
		switch (args.length) {
			case 0:
				in=new BufferedReader(new InputStreamReader(System.in));
				out=System.out;
				break;
			case 1:
				in=new BufferedReader(new FileReader(args[0]));
				out=System.out;
				break;
			case 2:
				in=new BufferedReader(new FileReader(args[0]));
				out=new PrintStream(new FileOutputStream(args[1]));
				break;
			default:
				throw new NullPointerException("ERROR en el n�mero de par�metros:"+args.length);
		}
	}

	
    /**
     * Destructor que se encarga de cerrar el fichero de entrada y salida.
     */
	protected void finalize() throws IOException {
		in.close();
		out.close();	
	}
		
	
   /**
    * M�todo que crea e inicializa un nuevo objeto DNS
    */
	void creaDNS() {
		dns=new DNSimpl();
		out.println("DNS CREAT");
	}
	
	
   /**
    * M�todo que crea e inicializa un nuevo objeto DNS
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu".
    * @param ip  cadena que contiene la direcci�n ip del dominio. ej: "129.34.2.3".
    */
	void creaDominio(String dominio,String ip) {
		dns.altaDominio(new Dominio(dominio),new IP(ip));
		out.println("ALTA "+dominio+"-->"+ip);
	}
	
	
   /**
    * M�todo que da de baja un dominio.
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu"
    */
	void bajaDominio(String dominio) {
		dns.bajaDomini(new Dominio(dominio));
		out.println("BAJA "+dominio);
	}
	
	
   /**
    * M�todo que consulta la direcci�n ip de un dominio.
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu"
    */
	void consultaDominio(String dominio) {
		IP ip=dns.consultar(new Dominio(dominio));
		if (ip!=null) out.println("CONSULTA "+dominio+"-->"+ip);
		else out.println("CONSULTA "+dominio+" inexistente");
	}
	
	
   /**
    * M�todo que consulta el dominio m�s visitado que ocupa la posici�n num dentro del ranking.
    * @param num posici�n dentro de de los top 10. Debe ir de 0 a 9.
    */
	void masVisitados(String num) {
		Dominio d=dns.masVisitados(Integer.parseInt(num));
		if (d!=null) out.println("MAS VISITADOS "+num+"-->"+d);
		else out.println("MAS VISITADOS "+num+" inexistent");
	}
	

	/** M�todo que inicializa un tld para hacer un recorrido por sus entidades.
	 * @param tld cadena que contiene el tld. ej: "edu"
	 */
	void entidades(String tld) {
		Iterador<Entidad> iter=dns.entidades(new TLD(tld));
		out.print("ENTIDADES "+tld+": ");
		if (iter!=null) {
			while (iter.haySiguiente()) {
				Entidad entidad=iter.siguiente();
				System.out.print(entidad);
				if (iter.haySiguiente())
					System.out.print(", ");
			}
			out.println();
		}
		else
			out.println("tld no encontrado!");
	}
	

	/**
	 * M�todo que interpreta una l�nea de test. Los comandos pueden ser:
     * CREA
     * ALTA <dominio> <ip>
     * BAJA <dominio>
     * CONSULTA <dominio> --> <ip>
     * MASVISITADOS <n�m> --> <dominio>
     * ENTIDADES <tld>
     * # 		//para a�adir comentarios
     * 
     * @param tld cadena que contiene el comando.
     */
	void interpretaLinia(String linia) {
	    StringTokenizer l=new StringTokenizer(linia," ");
	    if (l.hasMoreTokens()) {
	        String ins = l.nextToken();
            if (ins.equalsIgnoreCase("CREA")) {creaDNS();
            } else if (ins.equalsIgnoreCase("ALTA")) {creaDominio(l.nextToken(),l.nextToken());
            } else if (ins.equalsIgnoreCase("BAJA")) {bajaDominio(l.nextToken());
            } else if (ins.equalsIgnoreCase("CONSULTA")) {consultaDominio(l.nextToken());
            } else if (ins.equalsIgnoreCase("MASVISITADOS")) {masVisitados(l.nextToken());
            } else if (ins.equalsIgnoreCase("ENTIDADES")) {entidades(l.nextToken());
            } else if (ins.equals("#")) { System.exit(0);
            }
	    }
	}
	
   /**
    * M�todo que lee comandos de la entrada definida (est�ndar o fichero).
    */
	public void ejecuta() throws IOException{
		String linea;
		while ((linea = in.readLine())!=null && !linea.equals("FIN")) {
            interpretaLinia(linea);
        }
	}
	
   /**
    * Programa principal.
    */
	public static void main(String[] args) throws IOException {
		Principal p=new Principal(args);
		p.ejecuta();
		System.out.println("Final");
	}
}
