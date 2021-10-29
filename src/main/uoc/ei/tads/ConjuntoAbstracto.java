
package uoc.ei.tads;

/** Clase que implementa aquellas operaciones de conjunto que no
 * dependen de la representación elegida (AVL, Tablas de dispersión, ...).
 * Estas operaciones se corresponden con las que tratan
 * con subconjuntos.
 * 
 * @author Esteve Mariné
 * @author Jordi Alvarez
 */

public abstract class ConjuntoAbstracto<E> implements Conjunto<E>
{

	/**
	* Añade los elementos de un segundo conjunto que no existan en el
	* conjunto actual, si se puede. Si encuentra un elemento equivalente, según la
	* función de comparación, lo sobrescribe.
	* @param conj  conjunto que se quiere unir al actual;
	*              puede ser vacío, pero no null
	* @exception ExcepcionParametroIncorrecto  si el conjunto es null
	* @pre conj!=null, new ExcepcionParametroIncorrecto("conjunto null");
	*/
	public void union(Conjunto<E> conj) {
	   	Iterador<E> iter=conj.elementos();
	   	while (iter.haySiguiente())
	   		insertar(iter.siguiente());
	   }

	/**
	* Borra del conjunto actual los elementos que no existen en un
	* segundo conjunto, si se puede.
	* @exception ExcepcionParametroIncorrecto  si el conjunto es null
	* @param conj  conjunto que se quiere interseccionar con el actual;
	*              puede ser vacío, pero no null
	* @pre conj!=null, new ExcepcionParametroIncorrecto("conjunto null");
	*/
	public void interseccion(Conjunto<E> conj) {
	      Lista<E> elementosAEsborrar = new ListaEncadenada<E>();
	      // En primer lugar guardamos en una lista los elementos que tendremos
	      // que borrar del conjunto
	      Iterador<E> iter=elementos();
	      while (iter.haySiguiente()) {
	      	E elem=iter.siguiente();
	      	if (!conj.esta(elem))
	      		elementosAEsborrar.insertarAlFinal(elem);
	      }
	      // en segundo lugar borramos los elementos
	      iter=elementosAEsborrar.elementos();
	      while (iter.haySiguiente())
	      	borrar(iter.siguiente());
	   }

	/**
	* Borra del conjunto actual los elementos que existen en un segundo
	* conjunto, si se puede.
	* @exception ExcepcionParametroIncorrecto  si el conjunto es null
	* @param conj  conjunto que se quiere sustraer del actual;
	*              puede ser vacío, pero no null
	* @pre conj!=null, new ExcepcionParametroIncorrecto(conjunto null")
	*/
	public void diferencia(Conjunto<E> conj) {
	   	Iterador<E> iter=conj.elementos();
	   	while (iter.haySiguiente())
	   		borrar(iter.siguiente());
	   }

}
