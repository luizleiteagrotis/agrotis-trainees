package exercicios.aula35.exercicio1;

public class Calculadora {
	
	private Calculadora() {}
	
	public static int calcularFibonacci(int n) {
		if (n < 0 || n == 1) return 0;
		if (n == 2) return 1;
		return calcularFibonacci(n-1) + calcularFibonacci(n-2);
	}
}
