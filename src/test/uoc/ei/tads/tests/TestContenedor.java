package uoc.ei.tads.tests;

import java.util.ArrayList;
import java.util.Random;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Recorrido;
import junit.framework.TestCase;

public abstract class TestContenedor extends TestCase {
	
	
	private static Random randomGenerator = new Random();
	
	// para aquellos tests que hacen tests aleatorios
	protected static final int NUM_TESTS_ALEATORIOS = 1;
	protected static final int NUM_ELEMS_TEST_ALEATORIO = 100;
	public static final int REPETICIONES_MUCHAS_OPS = 1000;
	// Sólo para los tests que usan representaciones acotadas
	public static final int TAMANO_MUCHAS_OPS = 1000;
	public static final int TAMANO_LLENO = 3;
	
    
	public TestContenedor(String name) { super(name); }
    
    
    public TestContenedor() { super(); }
	   
	   
	protected void insertar(Contenedor<Integer> c,int elem) {}
	protected boolean borrar(Contenedor<Integer> c,int elem) { return false; }
	protected Contenedor<Integer> crearContenedor() { return null; }
	protected Contenedor<Integer> crearContenedor(int numElems) { return null; }
	
	
	protected boolean estaOrdenado(Iterador<Integer> elems) {
		   boolean encontrado=false;
		   int anterior=Integer.MIN_VALUE;
		   while (elems.haySiguiente() && !encontrado) {
			   int elem=elems.siguiente();
			   encontrado=anterior>elem;
			   anterior=elem;
		   }
		   return !encontrado;
	   }
	
	
	protected ArrayList<Integer> popularAleatoriamente(Contenedor<Integer> contenedor,int numElems,int rangoMaxElem) {
		ArrayList<Integer> elements=new ArrayList<Integer>();
		for (int i=0;i<numElems;i++) {
			int element=randomGenerator.nextInt(rangoMaxElem);
			elements.add(element);
			insertar(contenedor,element);
		}
		return elements;
	}
	
	
	protected int borrarElemento(Contenedor<Integer> contenedor,ArrayList<Integer> elements) {
		int posicion=randomGenerator.nextInt(elements.size());
		int element=elements.remove(posicion);
		assertTrue(borrar(contenedor,element));
		return element;
	}


	protected int contarElems(Recorrido r) {
		int n=0;
		while (r.haySiguiente()) {
			r.siguiente();
			n++;
		}
		return n;
	}


	protected int contarElems(Iterador i) {
		int n=0;
		while (i.haySiguiente()) {
			i.siguiente();
			n++;
		}
		return n;
	}


	protected void checkRecorrido(Recorrido<Integer> r, int[] elems) {
		int i=0;
		while (i<elems.length)
			assertEquals(elems[i++],(int)r.siguiente().getElem());
	}
	

	protected void checkIterador(Iterador<Integer> r, int[] elems) {
		int i=0;
		while (i<elems.length)
			assertEquals(elems[i++],(int)r.siguiente());
	}
	

}
