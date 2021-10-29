/*
 */
package uoc.ei.tads;

/** Implementación de un diccionario mediante un vector de medida fija.
 * Esta implementación concreta està pensada para casos muy concretos
 * en que por una banda se requiere un diccionario donde, una vez inicializado,
 * tendremos siempre el mismo número de elementos (conocido de entrada); y por
 * otro lado, necesitamos acceso al vector de par claves valor.
 * Concretamente se usa en el algoritmo de cálculo de los caminos mínimos de un grafo
 * que proporciona la librería.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 */
public class DiccionarioVectorImpl<C,E> implements Diccionario<C,E> {
	

	protected ClaveValor<C,E>[] diccionario;
	protected int n;
	
	
	// Constructores
	
	public DiccionarioVectorImpl(int n) {
		diccionario=(ClaveValor<C,E>[])new ClaveValor[n];
	}
	
	
	// funcionalidades extra proporcionadas por esta implementación de diccionario
	
	public ClaveValor<C,E>[] getVector() { return diccionario; }
	
	
	// Implementación de los métodos de Diccionario
	
	public void insertar(C clave,E obj) {
		int i;
		for(i=0;i<n && !diccionario[i].getClave().equals(clave);i++);
		diccionario[i]=new ClaveValor<C,E>(clave,obj);
		if (i==n)
			n++;
	}


	public E borrar(C clave) {
		E elem=null;
		int i;
		for(i=0;i<n && !diccionario[i].getClave().equals(clave);i++);
		if (i<n) {
			elem=diccionario[i].getValor();
			diccionario[i]=diccionario[n-1];
			diccionario[n-1]=null;
			n--;
		}
		return elem;
	}


	public boolean esta(C clave) {
		int i;
		for(i=0;i<n && !diccionario[i].getClave().equals(clave);i++);
		return i<n;
	}

	
	public E consultar(C clave) {
		int i;
		for(i=0;i<n && !diccionario[i].getClave().equals(clave);i++);
		return i<n ? diccionario[i].getValor() : null;
	}


	public boolean estaVacio() {
		return n==0;
	}


	public int numElems() {
		return n;
	}


	public Iterador<C> claves() {
		return new IteradorClaves<C,E>(new IteradorVectorImpl<ClaveValor<C,E>>(diccionario,n,0));
	}


	public Iterador<E> elementos() {
		return new IteradorValores<C,E>(new IteradorVectorImpl<ClaveValor<C,E>>(diccionario,n,0));
	}
	
	
	public String toString() {
		StringBuffer sb=new StringBuffer("{DiccionarioVectorImpl: ");
		int i=0;
		while(i<n) {
			sb.append(diccionario[i]);
			i++;
			if (i<n) sb.append(", ");
		}
		sb.append("}");
		return sb.toString();
	}
	
	
	protected static class IteradorClaves<CI,EI> implements Iterador<CI> {
		
		private Iterador<ClaveValor<CI,EI>> iteradorClaveValor;
		
		public IteradorClaves(Iterador<ClaveValor<CI,EI>> iterador) {
			iteradorClaveValor=iterador;
		}
		
		public boolean haySiguiente() {
			return iteradorClaveValor.haySiguiente();
		}
		
		public CI siguiente() {
			if (!haySiguiente())
				throw new ExcepcionPosicionInvalida();
			return iteradorClaveValor.siguiente().getClave();
		}
		
	}
	

	protected static class IteradorValores<CI,EI> implements Iterador<EI> {
		
		private Iterador<ClaveValor<CI,EI>> iteradorClaveValor;
		
		public IteradorValores(Iterador<ClaveValor<CI,EI>> iterador) {
			iteradorClaveValor=iterador;
		}
		
		public boolean haySiguiente() {
			return iteradorClaveValor.haySiguiente();
		}
		
		public EI siguiente() {
			if (!haySiguiente())
				throw new ExcepcionPosicionInvalida();
			return iteradorClaveValor.siguiente().getValor();
		}

	}
	

}
