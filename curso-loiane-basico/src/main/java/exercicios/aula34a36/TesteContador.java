package exercicios.aula34a36;

public class TesteContador {

	  public static void main(String[] args) {
		
		System.out.println("Valor que comecou o contador: " + Contador.obterValorContador());

        
        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());

        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());
        
        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());

        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());
        
        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());
    
        Contador.zerarContador();
        System.out.println("Valor do contador após zerar: " + Contador.obterValorContador());
        
        Contador.incrementarContador();
        System.out.println("Valor atual do contador: " + Contador.obterValorContador());
        
        Contador.zerarContador();
        System.out.println("Valor do contador após zerar: " + Contador.obterValorContador());
       
   

     }
	
	}


