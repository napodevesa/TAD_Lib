package uoc.ei.ejemplos.modulo6;

import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Categoria;
import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Tipo;
import uoc.ei.tads.*;

public class TablaSimbolosImpl implements TablaSimbolos {
	
	private int numBloquesAnidados;
	private TablaDispersionSimbolos tabla;
	
	public TablaSimbolosImpl() {
		numBloquesAnidados=0;
		tabla=new TablaDispersionSimbolos();
	}
	

	public void entrar() {
		numBloquesAnidados++;
	}

	
	public void salir() {
		Lista<String> idsAEliminar=new ListaEncadenada<String>();
		Iterador<String> ids=tabla.claves();
		while (ids.haySiguiente()) {
			String id=ids.siguiente();
			Pila<PropiedadesSimbolo> pila=tabla.consultar(id);
			PropiedadesSimbolo props=pila.cima();
			if (props.getBloque()==numBloquesAnidados) {
				pila.desapilar();
				if (pila.estaVacio())
					idsAEliminar.insertarAlFinal(id);
			}
		}
		ids=idsAEliminar.elementos();
		while (ids.haySiguiente()) {
			String id=ids.siguiente();
			tabla.borrar(id);
		}
	}

	
	public void declarar(String id, Categoria categoria, Tipo tipo, int dimension) {
		id=id.toLowerCase();
		PropiedadesSimbolo props=new PropiedadesSimbolo(numBloquesAnidados,categoria,tipo,dimension);
		Pila<PropiedadesSimbolo> pila=tabla.consultar(id);
		if (pila==null) {
			pila=new PilaEncadenadaImpl<PropiedadesSimbolo>();
			tabla.insertar(id,pila);
		}
		pila.apilar(props);
	}

	public PropiedadesSimbolo consultar(String id) {
		id=id.toLowerCase();
		Pila<PropiedadesSimbolo> pila=tabla.consultar(id);
		if (pila==null)
			return null;
		return pila.cima();
	}

}
