package uoc.ei.ejercicios.modulo6.ejercicio11;

import java.util.*;

public class RepositorioURLJCFImpl implements RepositorioURL {
	
	private Set<String> repositorio;
	
	
	public RepositorioURLJCFImpl() {
		repositorio=new HashSet<String>();
	}
	
	
	public void insertar(String url) { repositorio.add(url); }
	public boolean esta(String url) { return repositorio.contains(url); }
	public boolean borrar(String url) { return repositorio.remove(url); }
	public Iterator<String> elementos() { return repositorio.iterator(); }

}
