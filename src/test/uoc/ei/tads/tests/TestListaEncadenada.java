package uoc.ei.tads.tests;
import  uoc.ei.tads.*;

/**
 * Test de algunos métodos de la clase.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class TestListaEncadenada  extends TestContenedor
{
   /**
    * Constructor con un parámetro. Delega en la superclase.
    * @param name  número del test
    */
   public TestListaEncadenada(String name) { super(name); }
   
   
   protected Contenedor<Integer> crearContenedor() {
	   return new ListaEncadenada<Integer>();
   }


   /** Crea una lista con insertarAlFinal, y elimina los elementos con borrarPrimero.
    * Operaciones comprobadas: insertarAlFinal, borrarPrimero, estaVacio.
    */
   public void testInsertarAlFinal()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      int elem=lista.insertarAlFinal(1).getElem();
      assertEquals(1,elem);
      elem=lista.insertarAlFinal(2).getElem();
      assertEquals(2,elem);
      elem=lista.insertarAlFinal(3).getElem();
      assertEquals(3,elem);
      elem=lista.insertarAlFinal(4).getElem();
      assertEquals(4,elem);
      assertEquals(4,lista.numElems());
      elem=lista.borrarPrimero();
      assertEquals(1,elem);
      elem=lista.borrarPrimero();
      assertEquals(2,elem);
      elem=lista.borrarPrimero();
      assertEquals(3,elem);
      assertFalse(lista.estaVacio());
      elem=lista.borrarPrimero();
      assertEquals(4,elem);
      try {
    	  lista.borrarPrimero();
          fail("Expected ExcepcionContenedorVacio");
      } catch (ExcepcionContenedorVacio ex) {}
      assertTrue(lista.estaVacio());
   }


   /** Crea una lista con insertarAlPrincipio, y elimina los elementos con borrarPrimero.
    * Operaciones comprobadas: insertarAlPrincipio, borrarPrimero, estaVacio.
    */
   public void testInsertarAlPrincipio()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      int elem=lista.insertarAlPrincipio(1).getElem();
      assertEquals(1,elem);
      elem=lista.insertarAlPrincipio(2).getElem();
      assertEquals(2,elem);
      elem=lista.insertarAlPrincipio(3).getElem();
      assertEquals(3,elem);
      elem=lista.insertarAlPrincipio(4).getElem();
      assertEquals(4,elem);
      assertEquals(4,lista.numElems());
      elem=lista.borrarPrimero();
      assertEquals(4,elem);
      elem=lista.borrarPrimero();
      assertEquals(3,elem);
      elem=lista.borrarPrimero();
      assertEquals(2,elem);
      assertFalse(lista.estaVacio());
      elem=lista.borrarPrimero();
      assertEquals(1,elem);
      try {
    	  lista.borrarPrimero();
          fail("Expected ExcepcionContenedorVacio");
      } catch (ExcepcionContenedorVacio ex) {}
      assertTrue(lista.estaVacio());
   }


   /** Crea una lista con insertarAlPrincipio y insertarDespuesDe,
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, reemplazar, intercambiar,
    * 	  borrarSiguiente, elementos, estaVacio, numElems.
    */
   public void testInsertarDespuesDe()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      Posicion<Integer> p3=lista.insertarDespuesDe(p2,3);
      Posicion<Integer> p5=lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      int elem=p1.getElem();
      assertEquals(1,elem);
      elem=p2.getElem();
      assertEquals(2,elem);
      elem=p3.getElem();
      assertEquals(3,elem);
      elem=p4.getElem();
      assertEquals(4,elem);
      elem=p5.getElem();
      assertEquals(5,elem);
      lista.intercambiar(p2,p4);
      elem=p2.getElem();
      assertEquals(4,elem);
      elem=p4.getElem();
      assertEquals(2,elem);
      lista.reemplazar(p2,2);
      elem=p2.getElem();
      assertEquals(2,elem);
      assertEquals(5,lista.numElems());
      lista.borrarSiguiente(p3);
      assertEquals(4,lista.numElems());
      estaOrdenado(lista.elementos());
   }
   
   
   /** Crea una lista con insertarAlPrincipio y insertarDespuesDe,
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
    * 	  borrarSiguiente, elementos, estaVacio, numElems.
    */
   public void testBorrarSiguiente()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      Posicion<Integer> p3=lista.insertarDespuesDe(p2,3);
      Posicion<Integer> p5=lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      try {
    	  lista.borrarSiguiente(p5);
    	  fail("Se debería haber lanzado ExcepcionPosicionInvalida");
      } catch (ExcepcionPosicionInvalida e) {}
      int elem=lista.borrarSiguiente(p1);
      assertEquals(2,elem);
      elem=lista.borrarSiguiente(p3);
      assertEquals(4,elem);
      elem=lista.borrarSiguiente(p3);
      assertEquals(5,elem);
      assertEquals(2,lista.numElems());
      Iterador<Integer> elems=lista.elementos();
      elem=elems.siguiente();
      assertEquals(1,elem);
      elem=elems.siguiente();
      assertEquals(3,elem);
      assertFalse(elems.haySiguiente());
   }
   
   
   /** Crea una lista con insertarAlPrincipio y insertarDespuesDe,
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
    * 	  borrar, elementos, estaVacio, numElems.
    */
   public void testBorrar()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      Posicion<Integer> p3=lista.insertarDespuesDe(p2,3);
      Posicion<Integer> p5=lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      try {
    	  lista.borrarSiguiente(p5);
    	  fail("Se debería haber lanzado ExcepcionPosicionInvalida");
      } catch (ExcepcionPosicionInvalida e) {}
      int elem=lista.borrar(p1);
      assertEquals(1,elem);
      elem=lista.borrar(p3);
      assertEquals(3,elem);
      elem=lista.borrar(p5);
      assertEquals(5,elem);
      assertEquals(2,lista.numElems());
      Iterador<Integer> elems=lista.elementos();
      elem=elems.siguiente();
      assertEquals(2,elem);
      elem=elems.siguiente();
      assertEquals(4,elem);
      assertFalse(elems.haySiguiente());
      elem=lista.borrar(p2);
      assertEquals(2,elem);
      elem=lista.borrar(p4);
      assertEquals(4,elem);
      assertTrue(lista.estaVacio());
   }
   
   
   /** Prueba posiciones y elementos con 0, 1 y diversos elementos.
    * Para este ultimo caso, crea una lista de 5 elementos
    * con insertarAlPrincipio y insertarDespuesDe.
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
    * 	  borrarSiguiente, elementos, estaVacio, numElems.
    */
   public void testPosiciones()
   {
      Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      Iterador<Integer> iterador=lista.elementos();
      Recorrido<Integer> recorrido=lista.posiciones();
      assertTrue(lista.estaVacio());
      assertFalse(iterador.haySiguiente());
      assertFalse(recorrido.haySiguiente());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      iterador=lista.elementos();
      recorrido=lista.posiciones();
      int elemR=recorrido.siguiente().getElem();
      int elemI=iterador.siguiente();
      assertEquals(elemI,elemR);
      assertFalse(iterador.haySiguiente());
      assertFalse(recorrido.haySiguiente());
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      lista.insertarDespuesDe(p2,3);
      lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      iterador=lista.elementos();
      recorrido=lista.posiciones();
      while (recorrido.haySiguiente()) {
          elemR=recorrido.siguiente().getElem();
          elemI=iterador.siguiente();
          assertEquals(elemI,elemR);
      }
      assertFalse(iterador.haySiguiente());
      assertFalse(recorrido.haySiguiente());
   }
   
   
   /** Crea una lista con insertarAlPrincipio y insertarDespuesDe,
   * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
   * 	  borrar, elementos, estaVacio, numElems.
   */
public void testBorrarPrimero() {
	  Lista<Integer> lista = (Lista<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      try {
    	  lista.borrarPrimero();
    	  fail("Se debería haber lanzado ExcepcionPosicionInvalida");
      } catch (ExcepcionContenedorVacio e) {}
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      int elem=lista.borrarPrimero();
      assertEquals(1,elem);
      assertTrue(lista.estaVacio());
      p1=lista.insertarAlPrincipio(1);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      lista.insertarDespuesDe(p2,3);
      lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      for(int i=1;i<6 && !lista.estaVacio();i++) {
    	  elem=lista.borrarPrimero();
    	  assertEquals(i,elem);
      }
      assertTrue(lista.estaVacio());
   }


/** Ejecuta los tests usando una user interface textual. */
   public static void main(String[] args) 
   {
      junit.textui.TestRunner.run(TestListaEncadenada.class);
   }
}
