package uoc.ei.ejercicios.modulo9.ejercicio1;

import uoc.ei.tads.Posicion;

public interface ObservadorContenedorPosicional<E> {
	
	void notificarPosicionCreada(Posicion<E> posicion);
	void notificarPosicionBorrada(Posicion<E> posicion);

}
