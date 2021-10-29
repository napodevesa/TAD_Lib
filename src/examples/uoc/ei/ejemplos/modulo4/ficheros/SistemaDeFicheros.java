package uoc.ei.ejemplos.modulo4.ficheros;

import uoc.ei.tads.*;

public class SistemaDeFicheros {
	
	private Arbol<EntradaSF> arbolDeDirectorios;
	private Posicion<EntradaSF> directorioDeTrabajo;
	
	public SistemaDeFicheros(String nombreDirectorioRaiz) {
		arbolDeDirectorios=new ArbolGeneralDelegImpl<EntradaSF>();
		Directorio directorioRaiz=new Directorio(nombreDirectorioRaiz,null);
		directorioDeTrabajo=arbolDeDirectorios.insertar(null,directorioRaiz);
	}
	
	
	public String getDirectorioDeTrabajo() {
		return directorioDeTrabajo.toString();
	}
	
	
	public void crearDirectorio(String nombre) throws ExcepcionSistemaDeFicheros {
		if (buscarHijo(nombre)!=null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" ya tiene un elemento con nombre "+nombre);
		Directorio subdirectorio=new Directorio(nombre,directorioDeTrabajo);
		arbolDeDirectorios.insertar(directorioDeTrabajo,subdirectorio);
	}

	
	public void crearFichero(String nombre,String contenido) throws ExcepcionSistemaDeFicheros {
		if (buscarHijo(nombre)!=null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" ya tiene un elemento con nombre "+nombre);
		FicheroDeTexto fichero=new FicheroDeTexto(nombre,directorioDeTrabajo);
		arbolDeDirectorios.insertar(directorioDeTrabajo,fichero);
		fichero.setContenido(contenido);
	}
	
	
	public void eliminarEntrada(String nombre) throws ExcepcionSistemaDeFicheros {
		Posicion<EntradaSF> entradaABorrar=buscarHijo(nombre);
		if (entradaABorrar==null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" no tiene ningún elemento con nombre "+nombre);
		arbolDeDirectorios.borrar(directorioDeTrabajo,entradaABorrar);
	}
	
	
	public Iterador<EntradaSF> elementos() {
		Recorrido<EntradaSF> hijos=arbolDeDirectorios.hijos(directorioDeTrabajo);
		return new IteradorRecorridoImpl<EntradaSF,EntradaSF>(hijos);
	}
	
	
	public void renombrar(String nombreViejo, String nombreNuevo) throws ExcepcionSistemaDeFicheros {
		Posicion<EntradaSF> entradaARenombrar=buscarHijo(nombreViejo);
		if (entradaARenombrar==null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" no tiene ningún elemento con nombre "+nombreViejo);
		entradaARenombrar.getElem().setNombre(nombreNuevo);
	}
	
	
	public void bajarDirectorio(String nombre) throws ExcepcionSistemaDeFicheros {
		Posicion<EntradaSF> hijo=buscarHijo(nombre);
		if (hijo==null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" no tiene ningún elemento con nombre "+nombre);
		if (!hijo.getElem().esDirectorio())
			throw new ExcepcionSistemaDeFicheros(hijo.getElem().getNombre()+" no es un directorio");
		directorioDeTrabajo=hijo;
	}

	
	public void subirDirectorio() throws ExcepcionSistemaDeFicheros {
		Posicion<EntradaSF> padre=directorioDeTrabajo.getElem().getPadre();
		if (padre==null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" és el directorio raiz");
		directorioDeTrabajo=padre;
	}
	
	
	public String getContenidoFichero(String nombre) throws ExcepcionSistemaDeFicheros {
		Posicion<EntradaSF> hijo=buscarHijo(nombre);
		if (hijo==null)
			throw new ExcepcionSistemaDeFicheros(directorioDeTrabajo+" no tiene ningún elemento con nombre "+nombre);
		if (hijo.getElem().esDirectorio())
			throw new ExcepcionSistemaDeFicheros(hijo.getElem().getNombre()+" no es un fichero");
		return ((FicheroDeTexto)hijo.getElem()).getContenido();
	}

	
	private Posicion<EntradaSF> buscarHijo(String nombre) {
		Recorrido<EntradaSF> hijos=arbolDeDirectorios.hijos(directorioDeTrabajo);
		Posicion<EntradaSF> hijoBuscado=null;
		while (hijos.haySiguiente() && hijoBuscado==null) {
			Posicion<EntradaSF> hijo=hijos.siguiente();
			if (hijo.getElem().getNombre().equals(nombre))
				hijoBuscado=hijo;
		}
		return hijoBuscado;
	}
	
	
}
