package exercicios.aula35.exercicio1;

public class Main {
	
	public static void main(String[] args) {
		for (int i = 1; i < 15; i++) {
			System.out.println("Fibonacci de " 
								+ i 
								+ " = " 
								+ Calculadora.calcularFibonacci(i));
		}
	}
}
