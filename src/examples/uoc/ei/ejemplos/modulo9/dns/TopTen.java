package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que implementa la lista de los top 10.
 * @author David F�guls
 *          Estructura de la Informaci�n,
 *          Universitat Oberta de Catalunya (UOC)
 * @version 1.0.0
 */
class TopTen{
  InformacionHost t[];
  int n;

  /**
  * Constructor del top ten para 10 dominios.
  */
  public TopTen() {
    t=new InformacionHost[10];
    n=0;
  }

  /**
   * M�todo que busca un host (comparando el dominio) dentro de de los top 10.
   * @param h informaci�n del host (contiene dominio, ip y n�mero de visitas).
   * @return int posici�n dentro de de los top 10 del host h.
   */
  private int busca(InformacionHost h) {
    int pos=0;
    while (pos<n && t[pos]!=h) pos++;
    if (pos==n || t[pos]!=h) pos=-1;
    return pos;
  }

  /**
   * M�todo que reordena el host que ocupa la posici�n pos de acuerdo con el n�mero de visitas.
   * @param pos posici�n del host que hay que reordenar.
   */
  private void reordena(int pos) {
    while (pos-1>=0 && t[pos-1].getVisitas()<t[pos].getVisitas()) {
      InformacionHost tmp=t[pos];
      t[pos]=t[pos-1];
      t[pos-1]=tmp;
      pos--;
    }
  }

  /**
   * M�todo que actualiza la lista de los top 10 teniendo en cuenta que
   * el host h ha recibido una nueva consulta.<br>
   * Si la host existe a los top 10 se actualiza su posicio.<br>
   * sino, si la lista de los top 10 no est� llena se a�ade directamente y, 
   * de lo contrario, se a�ade si tiene m�s visitas que el menos visitado de los top 10.
   * @param h informaci�n del host (contiene dominio, ip y n�mero de visitas).
   */
  public void actualiza(InformacionHost h) {
    int pos=busca(h);
    if (pos>=0) { // si existe
      reordena(pos);
    } else {
      if (n<10) t[n++]=h;
      else {
        if (t[9].getVisitas()<h.getVisitas()) {
          t[9]=h;
          reordena(9);
        }
      }
    }
  }

  /**
   * M�todo que retorna el host n m�s visitado.
   * @param n n�mero de host dentro de de los top ten.
   * @return ContHost informaci�n del host m�s visitado n�mero n.
   */
  public InformacionHost consulta(int n) { return t[n]; }

}
 