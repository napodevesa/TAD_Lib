package uoc.ei.ejemplos.modulo8;

import uoc.ei.tads.ClaveValor;
import uoc.ei.tads.algoritmos.grafos.CaminosMinimos;
import uoc.ei.tads.grafos.*;

public class PruebaCaminosMinimos {

	public static void main(String[] args) {
		GrafoDirigido<String,Integer> redCarreteres=new GrafoDirigidoImpl<String,Integer>();
		Vertice<String> madrid=redCarreteres.crearVertice("Madrid");
		Vertice<String> villar=redCarreteres.crearVertice("Villar");
		Vertice<String> collado=redCarreteres.crearVertice("Collado");
		Vertice<String> montes=redCarreteres.crearVertice("Montes");
		Vertice<String> aldea=redCarreteres.crearVertice("Aldea");
		Arista<Integer,String> mv=redCarreteres.crearArista(madrid,villar);
		mv.setEtiqueta(75);
		Arista<Integer,String> mc=redCarreteres.crearArista(madrid,collado);
		mc.setEtiqueta(60);
		Arista<Integer,String> mn=redCarreteres.crearArista(madrid,montes);
		mn.setEtiqueta(20);
		Arista<Integer,String> nv=redCarreteres.crearArista(montes,villar);
		nv.setEtiqueta(2);
		Arista<Integer,String> nc=redCarreteres.crearArista(montes,collado);
		nc.setEtiqueta(30);
		Arista<Integer,String> va=redCarreteres.crearArista(villar,aldea);
		va.setEtiqueta(200);
		Arista<Integer,String> ca=redCarreteres.crearArista(collado,aldea);
		ca.setEtiqueta(30);
		CaminosMinimos<String,Integer> ag=new CaminosMinimos<String,Integer>();
		ClaveValor<Vertice<String>,Number>[] distancias=ag.calcular(redCarreteres,madrid);
		for(int i=0;i<distancias.length;i++) {
			System.out.println("La distancia mínima de "+madrid.getValor()+" a "+distancias[i].getClave().getValor()+" es "+distancias[i].getValor());
		}
	}

}
