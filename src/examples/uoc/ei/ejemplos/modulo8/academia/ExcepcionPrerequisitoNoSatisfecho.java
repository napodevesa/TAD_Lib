package uoc.ei.ejemplos.modulo8.academia;

public class ExcepcionPrerequisitoNoSatisfecho extends Exception {
	
	private String alumno;
	private String asignatura;
	private String prerequisito;
	
	
	public ExcepcionPrerequisitoNoSatisfecho(String alumne,String assignatura,String prerequisit) {
		this.alumno=alumne;
		this.asignatura=assignatura;
		this.prerequisito=prerequisit;
	}
	
	
	public String toString() {
		return "El alumno "+alumno+" no puede matricular la asignatura "+asignatura+" ya que no tiene la asignatura "+prerequisito+" aprobada";
	}

}
