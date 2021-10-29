package uoc.ei.ejemplos.modulo3.referencia;


public class Persona {
	
	String nombre;
	Persona padre;
	Persona madre;
	
	
	public Persona(String nom) {
		this.nombre=nom;
		madre=null;
		padre=null;
	}

	
	public Persona(String nom,Persona mare,Persona pare) {
		this.nombre=nom;
		this.madre=mare;
		this.padre=pare;
	}
	
	
	public String getNombre() { return nombre; }
	public Persona getMadre() { return madre; }
	public Persona getPadre() { return padre; }
	

	protected String familiaToString() {
		String familia="";
		if (padre!=null || madre!=null) {
			familia+=" descendiente de ";
			if (padre!=null)
				familia+=padre.getNombre();
			if (madre!=null && padre!=null)
				familia+=" i ";
			if (madre!=null)
				familia+=madre.getNombre();
		}
		return familia;
	}
	
	
	public String toString() {
		return getNombre()+familiaToString();
	}

	
	/** Crea un grupo de instancias de Persona, relacionadas
	 * con relaciones parentescas entre ellas y escribe
	 * la representación textual de estos objetos.
	 * @param args
	 */
	public static void main(String[] args) {
		Persona abueloPedro=new Persona("Pedro");
		Persona abuelaPaquita=new Persona("Paquita");
		Persona madre=new Persona("Carmen",abueloPedro,abuelaPaquita);
		Persona tioPedro=new Persona("Pedro",abueloPedro,abuelaPaquita);
		Persona padre=new Persona("Jorge");
		Persona juan=new Persona("Juan",madre,padre);
		System.out.println(abueloPedro);
		System.out.println(abuelaPaquita);
		System.out.println(tioPedro);
		System.out.println(madre);
		System.out.println(padre);
		System.out.println(juan);
	}


}
