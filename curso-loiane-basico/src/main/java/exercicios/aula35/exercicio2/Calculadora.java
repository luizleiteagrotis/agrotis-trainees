package exercicios.aula35.exercicio2;

public class Calculadora {
	
	private Calculadora() {}
	
	public static int calcularSomatoria(int n) {
		if (n < 0) return 0;
		if (n == 1) return 1;
		return n + calcularSomatoria(n - 1);
	}
}
