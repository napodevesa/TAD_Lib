package uoc.ei.ejemplos.modulo9.dns;

import java.io.*;
import java.util.StringTokenizer;

import uoc.ei.tads.Iterador;

/**
 * Clase principal encargada de ejecutar un juego de pruebas proveniente de un 
 * fichero o de la entrada estándar. Los resultados se escriben en un fichero o
 * a la salida estándar.
 * @author David Fíguls
 * @author Jordi Alvarez (adaptación para la versión 2.0.0 de la librería)
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class Principal {
	
	BufferedReader in;
	PrintStream out;
	DNS dns;
	
	
	   /**
	    * Constructor que recibe la lista de argumentos de la línea de comandos.
	    * 
	    * @param args  argumentos de la línea de comandos.<br>
	    * Podemos recibir diferentes números de parámetros:<br>
	    * -ningúno (utilizaremos la entrada y salida estándar)<br>
	    * -uno (utilizamos la entrada estándar y la salida hacia fichero args[0])<br>
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
				throw new NullPointerException("ERROR en el número de parámetros:"+args.length);
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
    * Método que crea e inicializa un nuevo objeto DNS
    */
	void creaDNS() {
		dns=new DNSimpl();
		out.println("DNS CREAT");
	}
	
	
   /**
    * Método que crea e inicializa un nuevo objeto DNS
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu".
    * @param ip  cadena que contiene la dirección ip del dominio. ej: "129.34.2.3".
    */
	void creaDominio(String dominio,String ip) {
		dns.altaDominio(new Dominio(dominio),new IP(ip));
		out.println("ALTA "+dominio+"-->"+ip);
	}
	
	
   /**
    * Método que da de baja un dominio.
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu"
    */
	void bajaDominio(String dominio) {
		dns.bajaDomini(new Dominio(dominio));
		out.println("BAJA "+dominio);
	}
	
	
   /**
    * Método que consulta la dirección ip de un dominio.
    * 
    * @param dominio cadena que contiene el dominio. ej: "www.uoc.edu"
    */
	void consultaDominio(String dominio) {
		IP ip=dns.consultar(new Dominio(dominio));
		if (ip!=null) out.println("CONSULTA "+dominio+"-->"+ip);
		else out.println("CONSULTA "+dominio+" inexistente");
	}
	
	
   /**
    * Método que consulta el dominio más visitado que ocupa la posición num dentro del ranking.
    * @param num posición dentro de de los top 10. Debe ir de 0 a 9.
    */
	void masVisitados(String num) {
		Dominio d=dns.masVisitados(Integer.parseInt(num));
		if (d!=null) out.println("MAS VISITADOS "+num+"-->"+d);
		else out.println("MAS VISITADOS "+num+" inexistent");
	}
	

	/** Método que inicializa un tld para hacer un recorrido por sus entidades.
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
	 * Método que interpreta una línea de test. Los comandos pueden ser:
     * CREA
     * ALTA <dominio> <ip>
     * BAJA <dominio>
     * CONSULTA <dominio> --> <ip>
     * MASVISITADOS <núm> --> <dominio>
     * ENTIDADES <tld>
     * # 		//para añadir comentarios
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
    * Método que lee comandos de la entrada definida (estándar o fichero).
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
