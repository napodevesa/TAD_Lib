package uoc.ei.ejemplos.modulo3.redimensionamento;

import uoc.ei.tads.*;

/** Variaci�n de la implementaci�n de una Cola que redimensiona el vector donde
 * se guardan los elementos de manera autom�tica cuando �ste est� lleno.
 * La implementaci�n es pr�cticamente igual a Cola excepto por eso y el
 * hecho que como consecuencia no se implementa la interfaz ContenedorAcotado.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ColaRedimensionableImpl<E>  implements Cola<E> {

   private static final long serialVersionUID = 1;
	
	
   //-------------------------------------------------------
   // La siguiente parte de la implementaci�n es id�ntica
   // a la de ColaVectorImpl.
   // No la podemos reaprovechar dado que no queremos que
   // ColaRedimensionableImpl implemente ContenedorAcotado.
   // (para poder reaprovecharla habr�a que modificar la
   // librer�a de tads de la asignatura y crear una clase
   // padre com�n a ambas implementaciones que no implementase
   // ContenedorAcotado)
   //-------------------------------------------------------

   /** Capacidad m�xima, por defecto, del contenedor. */
   public static final int TAMANO_INICIAL = 10;

   /** Tabla de elementos del contenedor. */
   protected E[] elementos;

   /** N�mero de elementos que hay actualmente al contenedor. */
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
    * M�todo que sobrescribe Object.toString(). Saca los elementos separados
    * por el salto de l�nea de la plataforma.
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
   // modificaciones sobre la implementaci�n: ColaVectorImpl
   //-------------------------------------------------------

   /** La implementaci�n de encolar comprueba si el vector est� lleno, y
    * en tal caso redimensiona el vector.
    */
   public void encolar(E elem) {
	   if (estaLleno())
		   redimensionar();
	   int ultimo = posicion(primero + n);
	   elementos[ultimo] = elem;
   	   n++;
   }
   
   
   /** Este m�todo duplica la medida del vector donde guardamos
    * los elementos.
    */
   private void redimensionar() {
	   // creaci�n del nuevo vector (con el doble de capacidad)
	   E[] auxElementos = (E[])new Object[elementos.length*2];
	   // copia de los elementos de uno al otro
	   Iterador<E> it=elementos();
	   int i=0;
	   while (it.haySiguiente()) {
		   auxElementos[i]=it.siguiente();
		   i++;
	   }
	   // sustituci�n del vector antiguo lleno por el nuevo con m�s capacidad
	   primero=0;
	   elementos=auxElementos;
   }

}
