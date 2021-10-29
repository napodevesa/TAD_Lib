package uoc.ei.ejercicios.modulo5.ejercicio3;

final public class DocumentoConPrioridad implements Comparable<DocumentoConPrioridad> {

	private Documento documento;
	private int prioridad;
	private int identificador;

	 DocumentoConPrioridad(Documento doc, int prior, int id) {
	  setDocumento(doc);
	  setPrioridad(prior);
	  setIdentificador(id);
	 }

	 private void setDocumento(Documento doc) {
	  documento = doc;
	 }

	 private void setPrioridad(int prior) {
	  prioridad = prior;
	 }

	 private void setIdentificador(int id) {
	  identificador = id;
	 }

	 public Documento getDocumento() {
	  return(documento);
	 }

	 public int getPrioridad() {
	  return(prioridad);
	 }

	 public int getIdentificador() {
	  return(identificador);
	 }

	 public int compareTo(DocumentoConPrioridad doc2) {
		 if (getPrioridad() < doc2.getPrioridad()) return(-1);
		 else {
			 if (getPrioridad() > doc2.getPrioridad()) return(1);
			 else return(0);
		 }
	 }
}
