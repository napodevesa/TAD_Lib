package uoc.ei.ejercicios.modulo6.ejercicio11;

import uoc.ei.tads.*;

public class RepositorioURLImpl implements RepositorioURL {
	
	private Conjunto<String> repositorio;
	
	
	public RepositorioURLImpl() {
		repositorio=new ConjuntoAVLImpl<String>();
	}
	
	
	public void insertar(String url) { repositorio.insertar(url); }
	public boolean esta(String url) { return repositorio.esta(url); }
	public boolean borrar(String url) { return repositorio.borrar(url)!=null; }
	public Iterador<String> elementos() { return repositorio.elementos(); }

}
