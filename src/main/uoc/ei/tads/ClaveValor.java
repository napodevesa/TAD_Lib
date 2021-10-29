package uoc.ei.tads;
/**
 * Clase que empareja dos objetos. El primero (clave) debe identificar
 * el par, el segundo elemento (valor) almacena una información
 * asociada a la clave y puede ser cualquier objeto.
 *
 * Implementa la interfaz Serializable para poder convertir a cadenas o
 * flujos de bytes (streams) los objetos del contenedor y grabarlos o
 * transmitirlos.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Información,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ClaveValor<C,E>  implements java.io.Serializable, Comparable<ClaveValor<C,E>>
{
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serialitzables de la misma clase. El identificador se calcula
    * mediante un método de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /** Clave o índice que permite identificar la par. */
   protected C clave;

   /** Información asociada a la clave. Puede tener valor nulo. */
   protected E valor;

   /**
    * Constructor con dos parámetros.
    * @param clave  elemento comparable 
    * @param valor  información que debe almacenar el objeto
    * @pre clave!=null, new ExcepcionParametroIncorrecto("la clave debe ser diferente de null")
    * @exception ExcepcionParametroIncorrecto  si la clave es null
    */
   public ClaveValor(C clave, E valor)
   {
      this.clave = clave;
      this.valor = valor;
   }

   /**
    * Accesor de lectura de la clave o índice.
    * @return  valor de la información asociada a la clave
    */
   public C getClave() { return  clave; }

   /**
    * Accesor de escritura de la información asociada a la clave.
    * @param valor  nuevo valor de la información almacenada
    */
   public void setValor(E valor) { this.valor = valor; }

   /**
    * Accesor de lectura de la información asociada a la clave.
    * @return  valor de la información almacenada
    */
   public E getValor() { return  valor; }

   /**
    * Método que sobrescribe Object.hashCode(). Delega en el mismo método
    * implementado o heredado por la clave.
    * @return  código de dispersión correspondiente a la clave
    */
   public int hashCode() { return  clave.hashCode(); }

   /**
    * Método que sobrescribe Object.equals(Object obj). Delega en el
    * mismo método implementado o heredado por la clave.
    * @return  cierto si el objeto recibo como parámetro es de la clase
    *          ClaveValor y tiene la misma clave; sino retorna falso
    */
   public boolean equals(Object clauValor)
   {
   	  if (clauValor==null) return false;
   	  return clave.equals( ((ClaveValor)clauValor).getClave() );
   }

   
   /**
    * Método que implementa Comparable.compareTo(Object o). Delega en el
    * mismo método implementado o heredado por la clave.
    * @param claveValor  objeto de la misma clase (ClaveValor)
    * @return  un entero negativo, cero o positivo, según si la clave
    *          de este objeto (this.clave) es más pequeña, igual o más
    *          grande que la clave del objeto de la misma clase recibo como
    *          parámetro (claveValor.clave)
    */
   public int compareTo(ClaveValor<C,E> claveValor)
   {
      return  ((Comparable<C>)clave).compareTo(
                 (claveValor).getClave() );
   }

   
   /**
    * Método que define la conversión del objeto a String por
    * facilitar la depuración del código.
    * @return  cadena de caracteres con la clave y el valor
    */
   public String toString() { return  ( "[" + clave + ": " + valor +"]"); }
}
