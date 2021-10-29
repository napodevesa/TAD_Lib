package uoc.ei.tads.grafos;

/**
 * Clase que representa la arista de un grafo (dirigido o no dirigido).
 *
 * @author  Jordi Alvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
abstract class AristaAbstracta<TEtiqueta,TElem> implements Arista<TEtiqueta,TElem>
{
   /** Primer v�rtice de la arista. En el caso de una arista dirigida, representa
    * el origen. */
   protected Vertice<TElem> verticeA;

   /** Segundo v�rtice de la arista. En el caso de una arista dirigida representa
    * el destino. */
   protected Vertice<TElem> verticeB;

   /** Valor de la etiqueta de la arista, o null si la relaci�n es no valorada. */
   protected TEtiqueta etiqueta;


   /**
    * Constructor con dos par�metros. No se permiten bucles.
    * @param vertice1  primer v�rtice de la arista (u origen en el caso de dirigida)
    * @param vertice2  segundo v�rtice de la arista (o destino en el caso de dirigida)
    * @pre vertice1!=null && vertice2!=null, ExcepcionParametroIncorrecto
    */
   public AristaAbstracta(Vertice<TElem> vertice1, Vertice<TElem> vertice2)
   {
      this.verticeA = vertice1;
      this.verticeB = vertice2;
   }


   /**
    * Accesor de lectura del valor asociado a la arista.
    * @return  valor de la arista; o null, si es una relaci�n no valuada
    */
   public TEtiqueta getEtiqueta() { return etiqueta; }

   
   /**
    * Accesor de escritura del valor asociado a la arista.
    * @pre true
    * @param valor  valor de la relaci�n
    * @post getValor()@pre == valor
    */
   public void setEtiqueta(TEtiqueta etiqueta) { this.etiqueta = etiqueta; }
  

   /**
    * Indica si la arista incide en un v�rtice dado, como origen o
    * destino.
    * @param v  v�rtice del cual se quiere saber si pertenece a la arista
    * @pre v != null
    * @post true
    * @return  cierto si la arista incide en el v�rtice; falso en otro caso.
    * @pre v!=null, ExcepcionParametroIncorrecto
    */
   public boolean incideEnVertice(Vertice<TElem> vertice)
   {
      return ( this.verticeA.equals(vertice) || this.verticeB.equals(vertice) );
   }


   /**
    * M�todo que sobrescribe Object.equals(Object obj). Delega en los
    * mismos m�todos implementados o heredados por los v�rtices.
    * @return  cierto si el objeto recibido como par�metro es de la clase
    *          AristaDirigida y tiene el mismos v�rtices en el mismo orden;
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
    * M�todo que sobrescribe Object.toString(). Delega en los mismos
    * m�todos implementados o heredados por los v�rtices y por la etiqueta (si
    * hay).
    * @return  cadena de caracteres con el primer v�rtice, la etiqueta si
    *          tiene y el segundo v�rtice
    */
   public String toString()
   {
   	  return "("+verticeA+":"+etiqueta+":"+verticeB+")";
   }
}
