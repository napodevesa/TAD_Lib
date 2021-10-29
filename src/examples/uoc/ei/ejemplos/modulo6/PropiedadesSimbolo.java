package uoc.ei.ejemplos.modulo6;

public class PropiedadesSimbolo {
	
	public enum Categoria { PROGRAM, PROC, PARAM, VAR }
	public enum Tipo { NO_DEFINIDO, BOOLEAN, INT, REAL, CHAR }
	
	private Categoria categoria;
	private Tipo tipo;
	private int dimension;
	private int bloque;
	
	
	public PropiedadesSimbolo(int bloc,Categoria categoria,int dimensio) {
		this.bloque=bloc;
		this.categoria=categoria;
		this.tipo=Tipo.NO_DEFINIDO;
		this.dimension=dimensio;
	}

	public PropiedadesSimbolo(int bloc,Categoria categoria,Tipo tipus,int dimensio) {
		this.bloque=bloc;
		this.categoria=categoria;
		this.tipo=tipus;
		this.dimension=dimensio;
	}
	
	
	public int getBloque() { return bloque; }
	public Categoria getCategoria() { return categoria; }
	public Tipo getTipo() { return tipo; }
	public int getDimension() { return dimension; }
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("[CAT:"+categoria);
		sb.append(",T:"+tipo);
		sb.append(",DIM:"+dimension);
		sb.append(",BLOQUE:"+bloque+"]");
		return sb.toString();
	}

}
