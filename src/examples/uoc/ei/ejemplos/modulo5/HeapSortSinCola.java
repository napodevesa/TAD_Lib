package uoc.ei.ejemplos.modulo5;

import uoc.ei.tads.algoritmos.AlgoritmoOrdenacion;

public class HeapSortSinCola<E> implements AlgoritmoOrdenacion<E> {
	
	
	public void ordenar(E[] vector, int n) {
		construirHeap(vector,n);
		consumirHeap(vector,n);
	}	

	
	private void construirHeap(E[] vector,int n) {
		for (int i = n/2; i >= 0; i--)
			hundir(vector,i,n);
	}
		 
		 
	private void consumirHeap(E[] vector,int n) {
		for (int i = n-1; i >= 0; i--) {
			intercambiar(vector,0,i);
			hundir(vector,0,i);
		}
	}


	final private void hundir(E[] vector,int i,int n) {
		if (i*2+1<n) {
			int hijo=seleccionarHijoAIntercambiar(vector,i,n);
			if (comparar(vector,i,hijo)<0) {
				intercambiar(vector,i,hijo);
				hundir(vector,hijo,n);
			}
		}
	}


	final private int seleccionarHijoAIntercambiar(E[] vector,int i,int n) {
		int hi=i*2+1;
		int hd=i*2+2;
		if (hd>=n) return hi;
		return comparar(vector,hi,hd)>0 ? hi : hd;
	}
	
	
	final private int comparar(E[] vector,int i,int j) {
		return ((Comparable<E>)vector[i]).compareTo(vector[j]);
	}
	
	
	final private int comparar(E a,E b) {
		return ((Comparable<E>)a).compareTo(b);
	}
	
	
	 final private void intercambiar(E[] vector,int i,int j) {
		 E aux=vector[i];
		 vector[i]=vector[j];
		 vector[j]=aux;
	 }
	
	

}
