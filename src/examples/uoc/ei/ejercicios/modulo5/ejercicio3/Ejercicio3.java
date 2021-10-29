package uoc.ei.ejercicios.modulo5.ejercicio3;

public class Ejercicio3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Documento doc1 = new Documento("Doc1");
        Documento doc2 = new Documento("Doc2");
        Documento doc3 = new Documento("Doc3");
        Documento doc4 = new Documento("Doc4");
        Documento doc5 = new Documento("Doc5");
        Documento doc6 = new Documento("Doc6");
        System.out.println("Creamos una instancia de ServicioImpresion");
        ServicioImpresion servImpr = new ServicioImpresion();

        System.out.println("Añadimos 6 documentos:");
        int id1 = servImpr.insertarDocumento(doc1, 25);
        int id2 = servImpr.insertarDocumento(doc2, 12);
        int id3 = servImpr.insertarDocumento(doc3, 13);
        int id4 = servImpr.insertarDocumento(doc4,  7);
        int id5 = servImpr.insertarDocumento(doc5, 17);
        int id6 = servImpr.insertarDocumento(doc6, 44);
        System.out.println("Eliminar 2 documentos:");
        servImpr.eliminarDocumento(id4);
        servImpr.eliminarDocumento (id5);

        System.out.println("Imprimir 3 documentos:");
        servImpr.enviarDocumento();
        servImpr.enviarDocumento();
        servImpr.enviarDocumento();
        }
}
