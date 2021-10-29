package uoc.ei.ejemplos.modulo4;

import uoc.ei.tads.*;

public class ArbolGeneralVectorExtImpl<E> extends ArbolAbstracto<E> {
	
	
	private Nodo<E> raiz;
	private int nElementos;
	

	public ArbolGeneralVectorExtImpl() {
		super();
		raiz=null;
		nElementos=0;
	}
	

	public Posicion<E> raiz() {
		return raiz;
	}
	

	public Recorrido<E> hijos(Posicion<E> padre) {
		return ((Nodo<E>)padre).hijos();
	}
	

	public Posicion<E> insertar(Posicion<E> padre, E elem) {
		Nodo<E> hijo=new Nodo<E>(elem);
		if (padre==null)
			raiz=hijo;
		else
			((Nodo<E>)padre).insertarHijo(hijo);
		nElementos++;
		return hijo;
	}
	

	public E reemplazar(Posicion<E> pos, E elem) {
		E elemAnterior=pos.getElem();
		Nodo<E> nodo=(Nodo<E>)pos;
		nodo.setElem(elem);
		return elemAnterior;
	}
	
	
	/** Las posiciones no son intercambiadas, únicamente sus valores.
	 */
	public void intercambiar(Posicion<E> pos1, Posicion<E> pos2) {
		Nodo<E> nodo1=(Nodo<E>)pos1;
		Nodo<E> nodo2=(Nodo<E>)pos2;
		E aux=nodo1.getElem();
		nodo1.setElem(nodo2.getElem());
		nodo2.setElem(aux);
	}
	

	public void borrar(Posicion<E> pare, Posicion<E> hijo) {
		if (pare==null)
			raiz=null;
		else
			((Nodo<E>)pare).borrarHijo((Nodo<E>)hijo);
		nElementos-=numElems(hijo);
	}


	public int numElems() {
		return nElementos;
	}

	
	protected static class Nodo<EN> implements Posicion<EN> {
		
		private EN elemento;
		int nhijos;
		private Nodo<EN>[] hijos;
		
		
		public Nodo(EN element) {
			this.elemento=element;
			hijos=null;
			nhijos=0;
		}
		
		
		public EN getElem() { return elemento; }
		public void setElem(EN elemento) { this.elemento=elemento; }
		public int numeroDeHijos() { return nhijos; }
		
		
		public Recorrido<EN> hijos() {
			return new RecorridoVectorImpl<EN>(hijos,nhijos,0);
		}
		
		
		public void insertarHijo(Nodo<EN> hijo) {
			if (hijos==null || nhijos==hijos.length)
				extenderVectorHijos();
			hijos[nhijos]=hijo;
			nhijos++;
		}
		
		
		public void borrarHijo(Nodo<EN> hijo) {
			int indice=0;
			while (hijos[indice]!=hijo)
				indice++;
			while (indice<nhijos-1)
				hijos[indice]=hijos[indice+1];
			hijos[indice]=null;
		}
		
		
		private void extenderVectorHijos() {
			if (hijos==null) hijos=(Nodo<EN>[])new Nodo[1];
			else {
				Nodo<EN>[] aux=(Nodo<EN>[])new Nodo[hijos.length*2];
				for (int i=0;i<hijos.length;i++)
					aux[i]=hijos[i];
				hijos=aux;
			}
		}
		
		public String toString() { return elemento.toString(); }

	}
	
}
