package uoc.ei.tads.tests;
import  uoc.ei.tads.*;
import uoc.ei.tads.RecorridoBidireccional.InicioRecorrido;
/**
 * Test de algunos métodos de la clase.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class TestListaDoblementeEncadenada  extends TestListaEncadenada {
   /**
    * Constructor con un parámetro. Delega en la superclase.
    * @param name  número del test
    */
   public TestListaDoblementeEncadenada(String name) { super(name); }


   protected Contenedor<Integer> crearContenedor() {
	   return new ListaDoblementeEncadenada<Integer>();
   }


   public void testInsertarAlFinal() {
	   super.testInsertarAlFinal();
   }


   public void testInsertarAlPrincipio() {
	   super.testInsertarAlPrincipio();
   }


   public void testInsertarDespuesDe() {
	   super.testInsertarDespuesDe();
   }
   
   
   public void testBorrarSiguiente() {
	   super.testBorrarSiguiente();
   }
   

   public void testPosiciones() {
	   super.testPosiciones();
   }

   
   public void testBorrar() {
	   super.testBorrar();
   }
   
   
   public void testBorrarPrimero() {
	   super.testBorrarPrimero();
   }

   
   /** Comprueba los RecorridosBidireccionales empezando desde el principio
    * Crea una lista con insertarAlPrincipio y insertarDespuesDe,
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
    * 	  posiciones(int).
    */
   public void testRecorridoBidireccionalDesdePrincipio() {
      ListaDoblementeEncadenada<Integer> lista = (ListaDoblementeEncadenada<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      RecorridoBidireccional<Integer> recorrido=lista.posiciones(InicioRecorrido.PRINCIPIO);
      assertFalse(recorrido.haySiguiente());
      assertFalse(recorrido.hayAnterior());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      recorrido=lista.posiciones(InicioRecorrido.PRINCIPIO);
      int elem=recorrido.siguiente().getElem();
      assertEquals(1,elem);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      lista.insertarDespuesDe(p2,3);
      lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      recorrido=lista.posiciones(InicioRecorrido.PRINCIPIO);
      for(int i=1;i<=5 && recorrido.haySiguiente();i++) {
    	  elem=recorrido.siguiente().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.hayAnterior());
      }
      try {
    	  recorrido.siguiente();
    	  fail("final recorrido: se debía lanzar ExcepcióPosicioInvàlida");
      } catch (ExcepcionPosicionInvalida e) {}
      for(int i=4;i>=1 && recorrido.hayAnterior();i--) {
    	  elem=recorrido.anterior().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.haySiguiente());
      }
      try {
    	  recorrido.anterior();
    	  fail("final recorrido: se debía lanzar ExcepcióPosicioInvàlida");
      } catch (ExcepcionPosicionInvalida e) {}
      for(int i=2;i<=5 && recorrido.haySiguiente();i++) {
    	  elem=recorrido.siguiente().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.hayAnterior());
      }
   }
   
   
   /** Comprueba los RecorridosBidireccionales empezando desde el final
    * Crea una lista con insertarAlPrincipio y insertarDespuesDe,
    * Operaciones comprobadas: insertarAlPrincipio, insertarDespuesDe, 
    * 	  posiciones(int).
    */
   public void testRecorridoBidireccionalDesdeFinal() {
      ListaDoblementeEncadenada<Integer> lista = (ListaDoblementeEncadenada<Integer>)crearContenedor();
      assertTrue(lista.estaVacio());
      RecorridoBidireccional<Integer> recorrido=lista.posiciones(InicioRecorrido.FINAL);
      assertFalse(recorrido.haySiguiente());
      assertFalse(recorrido.hayAnterior());
      Posicion<Integer> p1=lista.insertarAlPrincipio(1);
      recorrido=lista.posiciones(InicioRecorrido.FINAL);
      int elem=recorrido.anterior().getElem();
      assertEquals(1,elem);
      Posicion<Integer> p4=lista.insertarDespuesDe(p1,4);
      Posicion<Integer> p2=lista.insertarDespuesDe(p1,2);
      lista.insertarDespuesDe(p2,3);
      lista.insertarDespuesDe(p4,5);
      estaOrdenado(lista.elementos());
      recorrido=lista.posiciones(InicioRecorrido.FINAL);
      for(int i=5;i>=1 && recorrido.hayAnterior();i--) {
    	  elem=recorrido.anterior().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.haySiguiente());
      }
      try {
    	  recorrido.anterior();
    	  fail("final recorrido: se debía lanzar ExcepcióPosicioInvàlida");
      } catch (ExcepcionPosicionInvalida e) {}
      for(int i=2;i<=5 && recorrido.haySiguiente();i++) {
    	  elem=recorrido.siguiente().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.hayAnterior());
      }
      try {
    	  recorrido.siguiente();
    	  fail("final recorrido: se debía lanzar ExcepcióPosicioInvàlida");
      } catch (ExcepcionPosicionInvalida e) {}
      for(int i=4;i>=1 && recorrido.hayAnterior();i--) {
    	  elem=recorrido.anterior().getElem();
    	  assertEquals(i,elem);
    	  assertTrue(recorrido.haySiguiente());
      }
   }
   
   
   /** Permet fer el test. */
   public static void main(String[] args) 
   {
      junit.textui.TestRunner.run(TestListaDoblementeEncadenada.class);
   }
}
