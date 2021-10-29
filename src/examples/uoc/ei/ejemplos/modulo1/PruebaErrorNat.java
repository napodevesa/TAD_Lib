package uoc.ei.ejemplos.modulo1;

public class PruebaErrorNat {
	public static void main(String[] args) {
		Nat n=new NatiImpl();
		n.pred();
		System.out.println("El valor del natural es: "+n);
	}
}
