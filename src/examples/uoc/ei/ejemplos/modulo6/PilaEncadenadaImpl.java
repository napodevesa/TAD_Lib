package uoc.ei.ejemplos.modulo6;

import uoc.ei.tads.*;

class PilaEncadenadaImpl<E>  implements Pila<E> {
	
   protected Lista<E> elementos;


   public PilaEncadenadaImpl() {
	   elementos=new ListaEncadenada<E>();
   }

   public int numElems() { return  elementos.numElems(); }

   public boolean estaVacio() { return  elementos.estaVacio(); }

   public void apilar(E elem) {
	   elementos.insertarAlPrincipio(elem);
   }

   public E desapilar() {
	   return elementos.borrarPrimero();
   }

   public E cima() {
      return  elementos.elementos().siguiente();
   }

   public Iterador<E> elementos() {
   		return elementos.elementos();
   }

   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("{PILA:");
      for (Iterador it = elementos(); it.haySiguiente(); ) {
         buffer.append(it.siguiente());
         if (it.haySiguiente()) buffer.append(',');
      }
      buffer.append("}");
      return  buffer.toString();
   }
}
