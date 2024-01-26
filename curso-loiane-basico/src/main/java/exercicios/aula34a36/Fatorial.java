package exercicios.aula34a36;

public class Fatorial {
	
	   public static double soma(double numeroA, double numeroB) {
	        return numeroA + numeroB;
	    }


	    public static double diminuir(double numeroA, double numeroB) {
	        return numeroA - numeroB;
	    }

	 
	    public static double vezes(double numeroA, double numeroB) {
	        return numeroA * numeroB;
	    }
	    
	    public static double divisao(double numeroA, double numeroB) {
	        if (numeroB != 0) {
	            return numeroA / numeroB;
	        } else {
	            return Double.NaN;
	        }
	    }
	    
	    public static int fatorial(int numeroFatorial) {
	    	if (numeroFatorial == 0) {
	    		return 1;
	    	}
	    	int total = 1;
	    	for (int i=numeroFatorial; i>1; i--) {
	    	 total *= i;
	    	}
	    	return total;
	    }
	    
	    	

	    	    
	    public static double potencia(double base, int expoente) {
	        return Math.pow(base, expoente);
	    }

}
