package exercicios.aula35.exercicio1;

public class MetodosRecursivos {

	public static long calcularFibonacci(int n) {
		if (n <= 1) {
			return n;
		} else {
			return calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
		}
	}

	public static long calcularSomatorio(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n + calcularSomatorio(n - 1);
		}
	}

}
