package uoc.ei.ejemplos.modulo2;

public class Matriz {
	
	private double[][] elementos;
	private int m,n;

	
	public Matriz (int m, int n) {
        this.m = m;
        this.n = n;
        elementos = new double[m][n];
      }


    public void set(int i, int j, double s) {
        elementos[i][j] = s;
     }
     
     
    public double get(int i, int j) {
        return elementos[i][j];
     }
     
     
    /**
     * #prec B.m==n
     * @param B
     * @return
     */
	
    public Matriz multiplicarPor(Matriz B) {
    	Matriz X = new Matriz(m,B.n);
    	for (int j = 0; j < B.n; j++) {
    		for (int i = 0; i < m; i++) {
    			double s = 0;
    			for (int k = 0; k < n; k++) {
    				s += get(i,k)*B.get(k,j);
    			}
    			X.set(i,j,s);
    		}
    	}
    	return X;
    }
    
    
    public String toString() {
    	StringBuffer sb=new StringBuffer("(");
    	for(int i=0;i<m;i++) {
    		sb.append("(");
    		for(int j=0;j<n;j++) {
    			sb.append(elementos[i][j]);
    			if (j<n-1) sb.append(", ");
    		}
    		sb.append(")");
    	}
		sb.append(")");
		return sb.toString();
    }
    
    
    public static void main(String[] args) {
    	Matriz m=new Matriz(2,3);
    	Matriz n=new Matriz(3,2);
    	m.set(0,0,2);
    	m.set(0,1,-3);
    	m.set(0,2,1);
    	m.set(1,0,6);
    	m.set(1,1,5);
    	m.set(1,2,3);
    	n.set(0,0,1);
    	n.set(0,1,-2);
    	n.set(1,0,5);
    	n.set(1,1,8);
    	n.set(2,0,3);
    	n.set(2,1,4);
    	System.out.println("M = "+m);
    	System.out.println("N = "+n);
    	Matriz resultado=m.multiplicarPor(n);
    	System.out.println("M x N = "+resultado);
    }

}
