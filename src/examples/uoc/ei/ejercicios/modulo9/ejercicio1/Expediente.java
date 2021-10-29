package uoc.ei.ejercicios.modulo9.ejercicio1;

public interface Expediente {
	
	public void nuevaNota(String alumno,double creditos,double nota);
	public double notaMedia(String alumno);
	public String alumnoConMejorExpediente();

}
