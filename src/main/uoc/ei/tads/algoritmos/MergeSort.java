package uoc.ei.tads.algoritmos;

/** Algoritmo de MergeSort. Usa la técnica de divide y vencerás,
 * dividiendo el vector a ordenar en dos partes a las que se
 * aplica el algoritmo recursivament para después fusionar
 * las dos partes. El caso base consiste al fusionar dos
 * secuencias de un elemento cada una.
 * @author Jordi Alvarez
 * @author Esteve Mariné
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class MergeSort<E> implements AlgoritmoOrdenacion<E> {

	public void ordenar(E[] vector,int n) {
		E[] vectorAuxiliar=(E[])new Object[n];
		mergeSort(vector,vectorAuxiliar,0,n-1);
	}
	
	
	private void mergeSort(E[] vector,E[] vectorAuxiliar,int inicio,int fin) {
		int medio=(fin+inicio)/2;
		if (inicio<fin) {
			mergeSort(vector,vectorAuxiliar,inicio,medio);
			mergeSort(vector,vectorAuxiliar,medio+1,fin);
			fusionar(vector,vectorAuxiliar,inicio,medio,medio+1,fin);
		}
	}
	
	
	private void fusionar(E[] vector,E[] vectorAuxiliar,int ini1,int fin1,int ini2, int fin2) {
		int i1=ini1;
		int i2=ini2;
		int k=ini1;
		while (k<=fin2) {
			if (i2>fin2 || i1<=fin1 && comparar(vector,i1,i2)<=0) {
				vectorAuxiliar[k]=vector[i1];
				i1++;
			}
			else {
				vectorAuxiliar[k]=vector[i2];
				i2++;
			}
			k++;
		}
		for(int i=ini1;i<=fin2;i++)
			vector[i]=vectorAuxiliar[i];
	}
	

	private int comparar(E[] vector,int i,int j) {
		return ((Comparable<E>)vector[i]).compareTo(vector[j]);
	}
	
	
}
