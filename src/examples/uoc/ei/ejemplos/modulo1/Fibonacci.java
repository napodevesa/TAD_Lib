package uoc.ei.ejemplos.modulo1;

public class Fibonacci {
	
	protected static Nat crearNat() {
		return new NatbImpl();	// o bien NatbImpl()
	}
	
	public static void muestraNumeros(int n) {
		Nat fibN=crearNat();	// f[0] = 0
		Nat fibN2=crearNat();	// f[1] = 0
		Nat fibN3=crearNat();
		fibN3.succ();			// f[2] = 1
		int i=0;
		while (i<n) {
			fibN=fibN2;
			fibN2=fibN3;
			fibN3=crearNat();
			fibN3.sumarCantidad(fibN);
			fibN3.sumarCantidad(fibN2);
			i++;
		}
		System.out.println("El número es: "+fibN);
	}

	public static void main(String[] args) {
		if (args.length!=1)
			System.out.println("Has de proporcionar un único parámetro!");
		else {
			try {
				int n=Integer.parseInt(args[0]);
				muestraNumeros(n);
			} catch (NumberFormatException e) {
				System.out.println("El parámetro debe ser un entero!");
			}
		}
	}

}
