package exercicios.aula34a36;

public class TesteCalculadora {

	public static void main(String[] args) {
		
		
        double n1 = 5.0;
        double n2 = 2.0;
        
        
        mostrar(Calculadora.soma(n1, n2));
        
        mostrar(Calculadora.diminuir(n1, n2));
        
        mostrar(Calculadora.vezes(n1, n2));

        mostrar(Calculadora.divisao(n1, n2));
        
        mostrar(Calculadora.potencia(n2, 3));
        int expoente = 3;
        
        
       
    }
	 public static void mostrar(double num) {
	    	System.out.println(num);
	    }
}
