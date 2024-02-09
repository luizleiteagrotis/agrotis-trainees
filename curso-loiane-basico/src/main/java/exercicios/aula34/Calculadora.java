package exercicios.aula34;

public class Calculadora {

	public static int somar(int n1, int n2) {
		return n1 + n2;
	}
	
	public static int subtrair(int n1, int n2) {
		return n1 - n2;
	}
	
	public static int multiplicar(int n1, int n2) {
		return n1 * n2;
	}
	
	public static double dividir(int n1, int n2) {
		return (double) n1 / (double) n2;
	}
	
	public static int potencia(int n1, int n2) {
		return (int) Math.pow(n1, n2);
	}
	
	public static int fatorial(int n) {
		if(n <= 1) {
			return n;
		}
		return n*fatorial(n-1);
	}
}
