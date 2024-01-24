package exercicios.aula34.exercicio2;

public class Calculadora {

	private Double valorA;
	private Double valorB;

	public Calculadora() {
	}

	public Calculadora(Double valorA, Double valorB) {
		this.valorA = valorA;
		this.valorB = valorB;
	}

	public Double getValorA() {
		return valorA;
	}

	public void setValorA(Double valorA) {
		this.valorA = valorA;
	}

	public Double getValorB() {
		return valorB;
	}

	public void setValorB(Double valorB) {
		this.valorB = valorB;
	}

	public void operacao(double valorA, double valorB, char operacao) {

		switch (operacao) {
		case '+':
			soma(valorA, valorB);
			break;
		case '-':
			subtracao(valorA, valorB);
			break;
		case '*':
			multiplicacao(valorA, valorB);
			break;
		case '/':
			divisao(valorA, valorB);
			break;
		case 'p':
			elevarPotencia(valorA, valorB);
		case '!':
			calcularFatorial(valorA);

		default:
			System.out.println("Operação inválida.");
			break;
		}

	}

	private void soma(double valorA2, double valorB2) {
		System.out.println("Soma dos valores" + (valorA2 + valorB2));
	}

	private void subtracao(double valorA2, double valorB2) {
		System.out.println("Soma dos valores" + (valorA2 - valorB2));

	}

	private void multiplicacao(double valorA2, double valorB2) {
		System.out.println("Multiplicação dos valores " + (valorA2 * valorB2));

	}

	private void divisao(double valorA2, double valorB2) {
		System.out.println("Soma dos valores" + (valorA2 / valorB2));

	}

	private double elevarPotencia(double valorA2, double valorB2) {
		System.out.println("Eleva potencia");

		return Math.pow(valorA2, valorB2);

	}

	public static double calcularFatorial(double valor) {
		System.out.println("Fatorial");

		if (valor < 0 || valor != Math.floor(valor)) {
			System.out.println("Fatorial não definido para valores negativos ou não inteiros.");
			return -1;
		}

		long resultado = 1;
		for (long i = 2; i <= valor; i++) {
			resultado *= i;
		}
		return resultado;
	}

}
