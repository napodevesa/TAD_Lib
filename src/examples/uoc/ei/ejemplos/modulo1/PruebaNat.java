package uoc.ei.ejemplos.modulo1;

public class PruebaNat {
	
	public static Nat multiplicar(Nat a,Nat b) {
		Nat c=new NatiImpl();
		for(int i=0;i<b.consultar();i++)
			c.sumarCantidad(a);
		return c;
	}

	public static void main(String[] args) {
		// El momento de la creación es el único momento en que hacemos
		// referencia a la implementación que queremos hacer
		Nat n1=new NatbImpl();
		Nat n2=new NatiImpl();
		n1.succ();
		n1.succ();
		System.out.println("n1: "+n1);
		n2.succ();
		n2.succ();
		n2.succ();
		System.out.println("n2: "+n2);
		Nat n3=multiplicar(n1,n2);
		System.out.println("n1 * n2: "+n3);
	}
}
