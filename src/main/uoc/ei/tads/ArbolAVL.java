package uoc.ei.tads;
/**
 * Clase que implementa un arbol binario de busqueda equilibrado AVL (Adelson-
 * Velskii & Landis), el cual se caracteriza para tener la ra�z m�s grande
 * que todos los elementos de su sub�rbol izquierdo (si tiene) y m�s peque�a
 * que todos los elementos de su sub�rbol derecho (si tiene); y, adem�s, 
 * sus sub�rboles izquierdo y derecho (si hay) son tambi�n �rboles de busqueda.
 *
 * Se dice que el arbol est� equilibrado (height balanced) cuando el valor
 * absoluto de la diferencia de alturas de sus sub�rboles es menor o igual
 * que uno y sus sub�rboles tambi�n est�n equilibrados.
 *
 * Se espera que las clases de los elementos implementen java.lang.Comparable
 * o bien que se facilite un java.util.Comparator como par�metro del
 * constructor.
 *
 * @author  Jordi �lvarez Canal
 * @author  Esteve Marin� Gallis�.
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class ArbolAVL<E>  extends ArbolBinarioBusquedaEncadenado<E> {
   /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
    * serializables de la misma clase. El identificador se calcula
    * mediante un m�todo de la clase Utilidades. */
   private static final long serialVersionUID = Utilidades.getSerialVersionUID();
	
	
   /**
    * Constructor sin par�metros. Se espera que las clases de los elementos
    * implementen la interfaz java.lang.Comparable.
    */
   public ArbolAVL() { super(); }

   /**
    * Constructor con un par�metro y elementos de una clase comparable con
    * el comparador dado.
    * @param comparador  comparador que permite deducir la prioridad
    * @exception ExcepcionParametroIncorrecto  si el comparador es null
    */
   public ArbolAVL(java.util.Comparator<E> comparador) {
      super(comparador);
   }

   /**
    * M�todo que restaura, si es preciso, el equilibrio del arbol de nodos
    * despu�s de cada inserci�n o supresi�n. Se espera un NodoAVL no null.
    * @param nodo  arbol o sub�rbol de nodos que se debe equilibrar
    */
   protected void equilibrar(Posicion<E> nodo) {
      if (nodo != null)  ((NodoAVL)nodo).equilibrar();
   }

   /**
    * Sobrescribe el m�todo de la superclase para incorporar el atributo
    * de altura al nodo, que permite comprobar el equilibrio del arbol AVL.
    * @param padre  padre del nuevo nodo
    * @param elemComp  elemento comparable que se quiere guardar al nodo
    * @return  nodo arbol binario nuevo que almacena el elemento e incorpora
    *          la altura por defecto uno (hoja)
    */
   protected NodoArbol<E> crearNodo(Posicion<E> padre, E elemComp) {
      return  new NodoAVL<E>(elemComp);
   }

   /**
    * Clase que extiende NodoArbol para incorporar un atributo de altura que
    * permite comprobar y mantener el equilibrio de un arbol de nodos AVL.
    */
   protected static class NodoAVL<EN>  extends NodoArbol<EN> {
      /** Atributo que en Java 1.5 determina la compatibilidad entre objetos
       * serialitzables de la misma clase. El identificador se calcula
       * mediante un m�todo de la clase Utilidades. */
      private static final long serialVersionUID = Utilidades.getSerialVersionUID();
 	
 	
      /** Altura del nodo dentro de del arbol. Por defecto vale uno (hoja). */
      protected int altura = 1;

      /**
       * Constructor con un par�metro. Asigna el valor recibo al elemento
       * del nodo padre y da valor null a las posiciones hijas.
       * Delega en la superclase. La altura por defecto vale uno (hoja).
       * @param elem  valor del elemento que debe ir al nodo
       */
      public NodoAVL(EN elem) {
      	super(elem);
      }

      /**
       * Accesor de escritura de la altura contenida en el nodo.
       * @param altura  nuevo valor de la altura
       */
      public void setAltura(int altura) { this.altura = altura; }

      /**
       * Accesor de lectura de la altura contenida al nodo.
       * @return  altura del nodo
       */
      public int getAltura() { return  altura; }

      /**
       * M�todo que actualiza la altura del nodo. Suma uno (nodo padre) al
       * valor de la altura de su hijo m�s alto.
       */ 
      public void ajustarAltura() {
         int hi = (hijoIzquierdo == null) ? 0 :
                  ((NodoAVL<EN>)hijoIzquierdo).altura;
         int hd = (hijoDerecho == null) ? 0 : ((NodoAVL<EN>)hijoDerecho).altura;
         altura = 1 + ((hi > hd) ? hi : hd);
      }

      /**
       * M�todo que comprueba el equilibrio del arbol de nodos.
       * @return  cero si sus hijos tienen la misma altura;
       *          negativo si cuelga hacia la derecha;
       *          y positivo si el sub�rbol izquierdo es el m�s alto
       */ 
      public int balanceo() {
         int hi = (hijoIzquierdo == null) ? 0 :
                  ((NodoAVL<EN>)hijoIzquierdo).altura;
         int hd = (hijoDerecho == null) ? 0 : ((NodoAVL<EN>)hijoDerecho).altura;
         return  (hi - hd);
      }

      /**
       * M�todo que restaura, si es preciso, el equilibrio del arbol de nodos
       * despu�s de cada inserci�n o supresi�n. El factor de equilibrio ha
       * de estar entre -1 y 1. Se pueden dar cuatro tipo de desequilibrio
       * (EE, ED, DD y DE) seg�n el balanceo (a la izquierda o a la derecha)
       * del arbol y del sub�rbol correspondiente.
       */
      public void equilibrar() {
         ajustarAltura();
         if (balanceo() > 1) {               // balanceado a la izquierda
            if (((NodoAVL<EN>)hijoIzquierdo).balanceo() > -1)
				rotarEE();
            else
				rotarED();
         }
         else if (balanceo() < -1) {         // balanceado a la derecha
            if (((NodoAVL<EN>)hijoDerecho).balanceo() < 1 )
				rotarDD();
            else
				rotarDE();
         }
      }


	  /**
       * M�todo que corrige el desequilibrio DD (derecha derecha).
       * El nodo se ha insertado en el sub�rbol derecho del sub�rbol derecho, o bien
       * se ha suprimido un nodo del sub�rbol izquierdo en las mismas
       * circunstancias (en un arbol balanceado hacia la derecha).
       * Es decir, el arbol ha crecido por (T2) o ha menguado por (T0).
       * La ra�z B del sub�rbol derecho pasa a ser la nueva ra�z y conserva
       * su hijo derecho, que con este movimiento queda en un nivel m�s
       * cerca de la ra�z del arbol.
       * <PRE>
       *      A                                  B
       *    /   \                              /   \
       *  /       \                          /       \
       * T0        B       ---------->      A        T2
       *         /   \                    /   \
       *       /       \                /       \
       *      T1       T2              T0       T1
       * </PRE>
       * La antigua ra�z A pasa a ser hijo izquierdo de B y conserva su
       * sub�rbol izquierdo. Finalmente, el anterior sub�rbol izquierdo de B
       * paso a ser sub�rbol derecho de la antigua ra�z A para conservar la
       * propiedad de ordenaci�n de los �rboles de busqueda.
       */
      protected void rotarDD() {
         NodoAVL<EN> nodo = (NodoAVL<EN>)hijoIzquierdo;
         hijoIzquierdo = hijoDerecho;
         hijoDerecho = hijoIzquierdo.hijoDerecho;
         hijoIzquierdo.hijoDerecho =
                      hijoIzquierdo.hijoIzquierdo;
         hijoIzquierdo.hijoIzquierdo = nodo;

         EN elemComp = elemento;
         elemento = hijoIzquierdo.elemento;
         hijoIzquierdo.elemento = elemComp;

         // ajusta altura de A (antigua ra�z) y de B (nueva ra�z sub�rbol)
         ((NodoAVL<EN>)hijoIzquierdo).ajustarAltura();
         ajustarAltura();
      }

      /**
       * M�todo protegido que corrige el desequilibrio DE (derecha
       * izquierda). El nodo se ha insertado en el sub�rbol izquierdo del
       * sub�rbol derecho, o bien se ha suprimido un nodo del sub�rbol izquierdo
       * en las mismas circunstancias (en un arbol balanceado hacia la
       * derecha).
       * Es decir, el arbol ha crecido por (C) o ha menguado por (T0).
       * <PRE>
       *      A       ----->        A
       *    /   \                 /   \
       *  /       \             /       \
       * T0        B           T0        C        ----->        C
       *         /   \                 /   \                  /   \
       *       /       \             /       \              /       \
       *      C        T3           T1        B            A         B
       *     / \                             / \          / \       / \
       *    /   \                           /   \        /   \     /   \
       *   T1   T2                         T2   T3      T0   T1   T2   T3
       * </PRE>
       * Para restaurar el equilibrio se necesitan dos rotaciones: primero se
       * corrige el desequilibrio EE del sub�rbol derecho y, a continuaci�n,
       * el desequilibrio DD del arbol.
       * @see NodoAVL#rotarEE()
       * @see NodoAVL#rotarDD()
       */
      protected void rotarDE() {
         ((NodoAVL<EN>)hijoDerecho).rotarEE();
         rotarDD();
      }

      /**
       * M�todo que corrige el desequilibrio EE (izquierda izquierda).
       * El procedimiento es sim�trico al caso del desequilibrio DD.
       * <PRE>
       *            A                                  B
       *          /   \                              /   \
       *        /       \                          /       \
       *       B        T0       ---------->      T2        A
       *     /   \                                        /   \
       *   /       \                                    /       \
       *  T2       T1                                  T1       T0
       * </PRE>
       * @see NodoAVL#rotarDD()
       */
      protected void rotarEE() {
         NodoAVL<EN> nodo = (NodoAVL<EN>)hijoDerecho;
         hijoDerecho = hijoIzquierdo;
         hijoIzquierdo = hijoDerecho.hijoIzquierdo;
         hijoDerecho.hijoIzquierdo = hijoDerecho.hijoDerecho;
         hijoDerecho.hijoDerecho = nodo;

         EN elemComp = elemento;
         elemento = hijoDerecho.elemento;
         hijoDerecho.elemento = elemComp;

         ((NodoAVL<EN>)hijoDerecho).ajustarAltura();
         ajustarAltura();
      }

      /**
       * M�todo protegido que corrige el desequilibrio ED (izquierda
       * derecha).
       * El procedimiento es sim�trico al caso del desequilibrio DE.
       * <PRE>
       *            A        ----->        A
       *          /   \                  /   \
       *        /       \              /       \
       *       B        T0            C        T0    ----->     C
       *     /   \                  /   \                     /   \
       *   /       \              /       \                 /       \
       *  T3        C            B        T1               B         A
       *           / \          / \                       / \       / \
       *          /   \        /   \                     /   \     /   \
       *         T2   T1      T3   T2                   T3   T2   T1   T0
       * </PRE>
       * @see NodoAVL#rotarDE()
       */
      protected void rotarED() {
         ((NodoAVL<EN>)hijoIzquierdo).rotarDD();
         rotarEE();
      }
   }
}
