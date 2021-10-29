package uoc.ei.ejemplos.modulo5;

import uoc.ei.tads.algoritmos.AlgoritmoOrdenacion;

public class HeapSortSinCola2<E> implements AlgoritmoOrdenacion<E> {
	
	
	private int seleccionarHijoAIntercambiar(E[] vector,int i,int n) {
		int hi=i*2+1;
		int hd=i*2+2;
		if (hd>=n) return hi;
		return comparar(vector,hi,hd)>0 ? hi : hd;
	}
	
	
	private int comparar(E[] vector,int i,int j) {
		return ((Comparable<E>)vector[i]).compareTo(vector[j]);
	}
	
	
	private int comparar(E a,E b) {
		return ((Comparable<E>)a).compareTo(b);
	}
	
	
	private void hundir(E[] vector,int i,int n) {
		E temp=vector[i];
		boolean salir=false;
		while (i*2+1<=n && !salir) {
			int hijo=seleccionarHijoAIntercambiar(vector,i,n);
			if (comparar(temp,vector[hijo])<0) {
				vector[i]=vector[hijo];
				i=hijo;
			}
			else
				salir=true;
		}
		vector[i]=temp;
	}


	private void insertar(E[] vector,int i,int n) {
		E temp=vector[i];
		boolean salir=false;
		while (i>0 && !salir) {
			int padre=(i-1)/2;
			if (comparar(vector[padre],temp)<0) {
				vector[i]=vector[padre];
				i=padre;
			}
			else
				salir=true;
		}
		vector[i]=temp;
	}


	public void ordenar(E[] vector, int n) {
		for(int i=1;i<n;i++)
			insertar(vector,i,n);
		for(int i=n-1;i>=1;i--) {
			E temp=vector[0];
			vector[0]=vector[i];
			vector[i]=temp;
			hundir(vector,0,i-1);
		}
	}

}
