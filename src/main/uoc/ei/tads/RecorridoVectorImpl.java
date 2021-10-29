package uoc.ei.tads;


/** Implementación de recorrido sobre un vector.
 * @author Jordi Alvarez
 * @author Esteve Marine
 */
public class RecorridoVectorImpl<E> implements Recorrido<E> {
	
	private IteradorVectorImpl<Posicion<E>> iterador;
	
	public RecorridoVectorImpl(Posicion<E>[] elements,int nombreElements,int primer) {
		iterador=new IteradorVectorImpl<Posicion<E>>(elements,nombreElements,primer);
	}

	public boolean haySiguiente() {
		return iterador.haySiguiente();
	}

	public Posicion<E> siguiente() throws ExcepcionPosicionInvalida {
		return iterador.siguiente();
	}

}
