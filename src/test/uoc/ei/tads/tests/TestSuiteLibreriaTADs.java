package uoc.ei.tads.tests;
import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * Serie de tests.
 *
 * @author  Jordi Àlvarez Canal
 * @author  Esteve Mariné Gallisà.
 *          Estructura de la Informació,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public class TestSuiteLibreriaTADs extends TestSuite
{

	/** Define toda la serie de test cases. */
   public static Test suite()
   {
      TestSuite suite = new TestSuite();
      suite.addTest(new TestSuite(TestPilaVectorImpl.class));
      suite.addTest(new TestSuite(TestColaVectorImpl.class));
      suite.addTest(new TestSuite(TestListaEncadenada.class));
      suite.addTest(new TestSuite(TestListaDoblementeEncadenada.class));
      suite.addTest(new TestSuite(TestColaConPrioridad.class));
      suite.addTest(new TestSuite(TestDiccionarioDispersion.class));
      suite.addTest(new TestSuite(TestDiccionarioAVL.class));
      suite.addTest(new TestSuite(TestDiccionarioLista.class));
      suite.addTest(new TestSuite(TestDiccionarioVector.class));
      suite.addTest(new TestSuite(TestConjuntoDispersion.class));
      suite.addTest(new TestSuite(TestConjuntoAVL.class));
      suite.addTest(new TestSuite(TestArbolBinarioEncadenadoImpl.class));
      suite.addTest(new TestSuite(TestArbolBinarioVectorImpl.class));
      suite.addTest(new TestSuite(TestArbolGeneralDelegImpl.class));
      suite.addTest(new TestSuite(TestGrafoDirigido.class));
      suite.addTest(new TestSuite(TestGrafoNoDirigido.class));
      return suite;
   }

   /** Permite realizar el test. */
   public static void main(String[] args) 
   {
      junit.textui.TestRunner.run(suite());
   }
}
