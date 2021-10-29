package uoc.ei.ejemplos.modulo4;

public class EjemplosRecursividad {
	

	public static boolean esPar(int n) {
		boolean res;
		if (n==0)
			res=true;
		else {
			// (**) llamada recursiva
			res=esPar(n-1);
			res=!res;
			// (**) calculo resultado a partir de llamada recursiva
		}
		return res;
	}
	

	public static int mcd(int x, int y) {
		int res;
		if (x==y)
			res=x;
		else {
			if (x>y)
				res=mcd(x-y,y);
			else
				res=mcd(y-x,x);
		}
		// res és el máximo común divisor de x i y
		return res;
	}

	
	public static void main(String[] args) {
		// execució de esParell corresponent a la que podeu
		// trobar al material escrit
		System.out.print("Exemple 1: ");
		if (esPar(2))
			System.out.println("El 2 és parell");
		else
			System.out.println("El 2 no és parell");
		// exemple d'ús de mcd
		System.out.print("Exemple 2: ");
		System.out.println("El maxim comú divisor de 1035 i 375 és: "+mcd(1035,375));
	}

}
