package exercicios.aula34.exercicio2;

public class Calculadora {
		
	public static double somar(double num1, double num2) {
		return num1 + num2;
	}
	
	public static double multiplicar(double num1, double num2) {
		return num1 * num2;
	}
	
	public static double dividir(double num1, double num2) {
		return num1 / num2;
	}
	
	public static double elevar(double num1, double num2) {
		return Math.pow(num1, num2);
	}
	
	public static int fatorial(int num) {
		if (num == 0) {
			return 1;
		}
		int total = 1;
		for (int i = num; i > 1; i--) {
			total *= i;
		}
		return total;
	}
	
	

}
