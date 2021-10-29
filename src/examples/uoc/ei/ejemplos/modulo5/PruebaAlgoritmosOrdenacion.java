package uoc.ei.ejemplos.modulo5;

import uoc.ei.tads.algoritmos.*;

public class PruebaAlgoritmosOrdenacion {
	
	protected static void mostrarVector(String str,Integer[] vector) {
		System.out.println(str);
		for(int i=0;i<vector.length;i++) {
			System.out.print(vector[i]);
			if (i<vector.length-1) System.out.print(", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		AlgoritmoOrdenacion<Integer> mergeSort=new MergeSort<Integer>();
		AlgoritmoOrdenacion<Integer> heapSortCua=new HeapSort<Integer>();
		AlgoritmoOrdenacion<Integer> heapSort=new HeapSortSinCola<Integer>();
		Integer[] vector1={40, 7, 18, 45, 24, 87, 90, 23, 41, 8, 9, 65, 23, 56, 70};
		Integer[] vector2={40, 7, 18, 45, 24, 87, 90, 23, 41, 8, 9, 65, 23, 56, 70};
		Integer[] vector3={40, 7, 18, 45, 24, 87, 90, 23, 41, 8, 9, 65, 23, 56, 70};
		mostrarVector("El vector original: ",vector1);
		mergeSort.ordenar(vector1,15);
		heapSortCua.ordenar(vector2,15);
		heapSort.ordenar(vector3,15);
		mostrarVector("El vector ordenado con merge sort es: ",vector1);
		mostrarVector("El vector ordenado con heap sort (con cola) es: ",vector2);
		mostrarVector("El vector ordenado con heap sort (sin cola) es: ",vector3);
	}

}
