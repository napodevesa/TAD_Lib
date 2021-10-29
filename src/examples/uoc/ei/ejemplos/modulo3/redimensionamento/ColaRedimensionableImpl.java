package uoc.ei.ejemplos.modulo3.redimensionamento;

import uoc.ei.tads.*;

/** Variación de la implementación de una Cola que redimensiona el vector donde
 * se guardan los elementos de manera automática cuando éste está lleno.
 * La implementación es prácticamente igual a Cola excepto por eso y el
 * hecho que como consecuencia no se implementa la interfaz ContenedorAcotado.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaRedimensionableImpl<E>  implements Cola<E> {

   private static final long serialVersionUID = 1;
	
	
   //-------------------------------------------------------
   // La siguiente parte de la implementación es idéntica
   // a la de ColaVectorImpl.
   // No la podemos reaprovechar dado que no queremos que
   // ColaRedimensionableImpl implemente ContenedorAcotado.
   // (para poder reaprovecharla habría que modificar la
   // librería de tads de la asignatura y crear una clase
   // padre común a ambas implementaciones que no implementase
   // ContenedorAcotado)
   //-------------------------------------------------------

   /** Capacidad máxima, por defecto, del contenedor. */
   public static final int TAMANO_INICIAL = 10;

   /** Tabla de elementos del contenedor. */
   protected E[] elementos;

   /** Número de elementos que hay actualmente al contenedor. */
   protected int n;

   /** Primer elemento de la cola. */
   private int primero;

   public ColaRedimensionableImpl() {
      elementos = (E[])new Object[TAMANO_INICIAL];
      n = 0;
      primero = 0;
   }

   private int posicion(int posicion) { return posicion % elementos.length; }
   private int siguiente(int posicion) { return (posicion+1)==elementos.length ? 0 : posicion+1; }
   private boolean estaLleno() { return  ( n == elementos.length ); }

   public int numElems() { return  n; }
   public boolean estaVacio() { return  ( n == 0 ); }
   public E primero() { return  elementos[primero]; }
   
   
   public E desencolar() {
      E elem = elementos[primero];
      elementos[primero]=null;
      primero = siguiente(primero);
      n--;
      return  elem;
   }

   public Iterador<E> elementos() {
		return new IteradorVectorImpl<E>(elementos,numElems(),primero);
   }

   /**
    * Método que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de línea de la plataforma.
    * @return  listado de los elementos
    */
   public String toString()
   {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{COLA:");
    for (Iterador<E> it = elementos(); it.haySiguiente(); ) {
       buffer.append(it.siguiente());
       if (it.haySiguiente()) buffer.append(',');
    }
    buffer.append("}");
    return  buffer.toString();
   }

   
   //-------------------------------------------------------
   // modificaciones sobre la implementación: ColaVectorImpl
   //-------------------------------------------------------

   /** La implementación de encolar comprueba si el vector está lleno, y
    * en tal caso redimensiona el vector.
    */
   public void encolar(E elem) {
	   if (estaLleno())
		   redimensionar();
	   int ultimo = posicion(primero + n);
	   elementos[ultimo] = elem;
   	   n++;
   }
   
   
   /** Este método duplica la medida del vector donde guardamos
    * los elementos.
    */
   private void redimensionar() {
	   // creación del nuevo vector (con el doble de capacidad)
	   E[] auxElementos = (E[])new Object[elementos.length*2];
	   // copia de los elementos de uno al otro
	   Iterador<E> it=elementos();
	   int i=0;
	   while (it.haySiguiente()) {
		   auxElementos[i]=it.siguiente();
		   i++;
	   }
	   // sustitución del vector antiguo lleno por el nuevo con más capacidad
	   primero=0;
	   elementos=auxElementos;
   }

}
