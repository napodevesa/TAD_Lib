package uoc.ei.ejemplos.modulo1.defensivo;

public class PruebaErrorNat {
	public static void main(String[] args) {
		try {
			NatDefensivo n=new NatiDefensivoImpl();
			n.pred();
			System.out.println("El valor del natural es: "+n);
		} catch (ExcepcionNat e) {
			System.out.println(e);
		}
	}
}
