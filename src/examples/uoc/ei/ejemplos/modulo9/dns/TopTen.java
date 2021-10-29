package uoc.ei.ejemplos.modulo9.dns;

/**
 * Clase que implementa la lista de los top 10.
 * @author David Fíguls
 *          Estructura de la Información,
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
   * Método que busca un host (comparando el dominio) dentro de de los top 10.
   * @param h información del host (contiene dominio, ip y número de visitas).
   * @return int posición dentro de de los top 10 del host h.
   */
  private int busca(InformacionHost h) {
    int pos=0;
    while (pos<n && t[pos]!=h) pos++;
    if (pos==n || t[pos]!=h) pos=-1;
    return pos;
  }

  /**
   * Método que reordena el host que ocupa la posición pos de acuerdo con el número de visitas.
   * @param pos posición del host que hay que reordenar.
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
   * Método que actualiza la lista de los top 10 teniendo en cuenta que
   * el host h ha recibido una nueva consulta.<br>
   * Si la host existe a los top 10 se actualiza su posicio.<br>
   * sino, si la lista de los top 10 no está llena se añade directamente y, 
   * de lo contrario, se añade si tiene más visitas que el menos visitado de los top 10.
   * @param h información del host (contiene dominio, ip y número de visitas).
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
   * Método que retorna el host n más visitado.
   * @param n número de host dentro de de los top ten.
   * @return ContHost información del host más visitado número n.
   */
  public InformacionHost consulta(int n) { return t[n]; }

}
 