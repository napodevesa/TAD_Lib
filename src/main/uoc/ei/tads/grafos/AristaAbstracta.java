package uoc.ei.tads.grafos;

/**
 * Clase que representa la arista de un grafo (dirigido o no dirigido).
 *
 * @author  Jordi Alvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
abstract class AristaAbstracta<TEtiqueta,TElem> implements Arista<TEtiqueta,TElem>
{
   /** Primer vértice de la arista. En el caso de una arista dirigida, representa
    * el origen. */
   protected Vertice<TElem> verticeA;

   /** Segundo vértice de la arista. En el caso de una arista dirigida representa
    * el destino. */
   protected Vertice<TElem> verticeB;

   /** Valor de la etiqueta de la arista, o null si la relación es no valorada. */
   protected TEtiqueta etiqueta;


   /**
    * Constructor con dos parámetros. No se permiten bucles.
    * @param vertice1  primer vértice de la arista (u origen en el caso de dirigida)
    * @param vertice2  segundo vértice de la arista (o destino en el caso de dirigida)
    * @pre vertice1!=null && vertice2!=null, ExcepcionParametroIncorrecto
    */
   public AristaAbstracta(Vertice<TElem> vertice1, Vertice<TElem> vertice2)
   {
      this.verticeA = vertice1;
      this.verticeB = vertice2;
   }


   /**
    * Accesor de lectura del valor asociado a la arista.
    * @return  valor de la arista; o null, si es una relación no valuada
    */
   public TEtiqueta getEtiqueta() { return etiqueta; }

   
   /**
    * Accesor de escritura del valor asociado a la arista.
    * @pre true
    * @param valor  valor de la relación
    * @post getValor()@pre == valor
    */
   public void setEtiqueta(TEtiqueta etiqueta) { this.etiqueta = etiqueta; }
  

   /**
    * Indica si la arista incide en un vértice dado, como origen o
    * destino.
    * @param v  vértice del cual se quiere saber si pertenece a la arista
    * @pre v != null
    * @post true
    * @return  cierto si la arista incide en el vértice; falso en otro caso.
    * @pre v!=null, ExcepcionParametroIncorrecto
    */
   public boolean incideEnVertice(Vertice<TElem> vertice)
   {
      return ( this.verticeA.equals(vertice) || this.verticeB.equals(vertice) );
   }


   /**
    * Método que sobrescribe Object.equals(Object obj). Delega en los
    * mismos métodos implementados o heredados por los vértices.
    * @return  cierto si el objeto recibido como parámetro es de la clase
    *          AristaDirigida y tiene el mismos vértices en el mismo orden;
    *          sino retorna falso
    */
   public boolean equals(Object object)
   {
   	  if (getClass()!=object.getClass())
   	  	return false;
   	  AristaAbstracta<TEtiqueta,TElem> aresta=(AristaAbstracta<TEtiqueta,TElem>)object;
   	  return verticeA.equals(aresta.verticeA) &&
   	  		 verticeB.equals(aresta.verticeB);
   }


   /**
    * Método que sobrescribe Object.toString(). Delega en los mismos
    * métodos implementados o heredados por los vértices y por la etiqueta (si
    * hay).
    * @return  cadena de caracteres con el primer vértice, la etiqueta si
    *          tiene y el segundo vértice
    */
   public String toString()
   {
   	  return "("+verticeA+":"+etiqueta+":"+verticeB+")";
   }
}
