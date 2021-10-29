
package uoc.ei.ejemplos.modulo3.pila;

/** Simplificación de una página de un documento,
 * representada mediante un string.
 */
public class Pagina {
	
	private String texto;
	
	public Pagina() { texto=null; }
	public void setTexto(String text) { this.texto=text; }
	public String getTexto() { return texto; }
	public String toString() { return texto; }

}
