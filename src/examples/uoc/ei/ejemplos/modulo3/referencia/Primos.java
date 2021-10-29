package uoc.ei.ejemplos.modulo3.referencia;

import java.io.IOException;

import uoc.ei.tads.Utilidades;

public class Primos {
	
	
	/**
	 * @param n
	 * @return Retorna true si n es primo y false en caso
	 * contrario.
	 */
	static boolean esPrimo(long n) {
		boolean encontrado=false;
		int i=2;
		while (i*i<=n && !encontrado) {
			encontrado= (n % i) == 0;
			i++;
		}
		return !encontrado;
	}

	
	/** Lee un entero de la entrada est�ndar e imprime
	 * por la salida est�ndar los n�meros primos inferiores
	 * o iguales a este entero.
	 * @param args
	 */
	public static void main(String[] args) {
		String str;
		long maxim;
		try {
			str=Utilidades.leerString("Introduce un n�mero entero: ",System.in);
			try {
				maxim=Long.parseLong(str);
				for(int i=2;i<maxim;i++)
					if (esPrimo(i))
						System.out.print(i+" ");
			} catch (NumberFormatException e) {
				System.out.println("La cadena introducida no es un n�mero entero: "+str);
				System.out.println("Vuelve a ejecutar el programa");
			}
		} catch (IOException e) {
			System.out.println("Problema con la entrada, abortando el programa");
		}
	}

}
