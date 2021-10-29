package uoc.ei.ejercicios.modulo9.ejercicio1;

public class PruebaExpediente {
	
	private Expediente expediente;
	
	
	public PruebaExpediente() {
		expediente=new ExpedienteImpl();
	}

	
	public void introducirNota(String alumno,double creditos,double nota) {
		expediente.nuevaNota(alumno,creditos,nota);
		System.out.println("Nueva nota para el alumno "+alumno+" "+nota+" ("+creditos+" creditos)");
		System.out.println("La nota media del alumno "+alumno+" se "+expediente.notaMedia(alumno));
		System.out.println("El alumno con mejor nota media es: "+expediente.alumnoConMejorExpediente());
	}
	
	
	public static void main(String args[]) {
		PruebaExpediente prova=new PruebaExpediente();
		prova.introducirNota("Jose",4.5,5);
		prova.introducirNota("Pedro",6,8);
		prova.introducirNota("Monica",4.5,7);
		prova.introducirNota("Maria",3,10);
		prova.introducirNota("Maria",6,5);
		prova.introducirNota("Pedro",4.5,5);
		prova.introducirNota("Monica",3,9);
		prova.introducirNota("Pedro",4.5,9);
		prova.introducirNota("Monica",6,5);
	}

}
