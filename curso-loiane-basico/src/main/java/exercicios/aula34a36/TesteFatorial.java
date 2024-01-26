package exercicios.aula34a36;

public class TesteFatorial {

	public static void main(String[] args) {
		
        double n1 = 5.0;
        double n2 = 2.0;
        
        
        mostrar(Fatorial.soma(n1, n2));
        
        mostrar(Fatorial.diminuir(n1, n2));
        
        mostrar(Fatorial.vezes(n1, n2));

        mostrar(Fatorial.divisao(n1, n2));
        
        mostrar(Fatorial.potencia(n2, 3));
        
        int fatorial = 5;
		mostrar(Fatorial.fatorial(fatorial));
     
       
        
       
    }
	 public static void mostrar(double num) {
	    	System.out.println(num);
	    

	}

}
