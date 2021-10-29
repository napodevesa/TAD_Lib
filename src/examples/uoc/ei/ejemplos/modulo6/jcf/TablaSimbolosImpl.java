package uoc.ei.ejemplos.modulo6.jcf;

import java.util.*;

import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo;
import uoc.ei.ejemplos.modulo6.TablaSimbolos;
import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Categoria;
import uoc.ei.ejemplos.modulo6.PropiedadesSimbolo.Tipo;

public class TablaSimbolosImpl implements TablaSimbolos {
	
	private int numBloquesAnidados;
	private Map<String,Stack<PropiedadesSimbolo>> tabla;
	
	public TablaSimbolosImpl() {
		numBloquesAnidados=0;
		tabla=new TablaDispersionSimbolos();
	}
	

	public void entrar() {
		numBloquesAnidados++;
	}

	
	public void salir() {
		Collection<String> idsAEliminar=new ArrayList<String>();
		Iterator<String> ids=tabla.keySet().iterator();
		while (ids.hasNext()) {
			String id=ids.next();
			Stack<PropiedadesSimbolo> pila=tabla.get(id);
			PropiedadesSimbolo props=pila.peek();
			if (props.getBloque()==numBloquesAnidados) {
				pila.pop();
				if (pila.empty())
					idsAEliminar.add(id);
			}
		}
		ids=idsAEliminar.iterator();
		while (ids.hasNext()) {
			String id=ids.next();
			tabla.remove(id);
		}
	}

	
	public void declarar(String id, Categoria categoria, Tipo tipo, int dimension) {
		id=id.toLowerCase();
		PropiedadesSimbolo props=new PropiedadesSimbolo(numBloquesAnidados,categoria,tipo,dimension);
		Stack<PropiedadesSimbolo> pila=tabla.get(id);
		if (pila==null) {
			pila=new Stack<PropiedadesSimbolo>();
			tabla.put(id,pila);
		}
		pila.push(props);
	}

	public PropiedadesSimbolo consultar(String id) {
		id=id.toLowerCase();
		Stack<PropiedadesSimbolo> pila=tabla.get(id);
		if (pila==null)
			return null;
		return pila.peek();
	}

}
