package exercicios.aula34.exercicio2;

public class Calculadora {
	
	public Calculadora() {}
	
	public double somar(double[] valores) {
		double soma = 0;
		for (double valor : valores) {
			soma += valor;
		}
		return soma;
	}
	
	public double subtrair(double[] valores) {
		double subtracao = 0;
		for (double valor : valores) {
			subtracao -= valor;
		}
		return subtracao;
	}
	
	public double multiplicar(double[] valores) {
		double multiplicacao = 1;
		for (double valor : valores) {
			multiplicacao *= valor;
		}
		return multiplicacao;
	}
	
	public double dividir(double dividendo, double divisor) {
		return dividendo / divisor;
	}
}
