package exercicios.aula34.exercicio2;

import java.util.Scanner;

public class CalculadoraTest {

	private static double valorA;
	private static double valorB;

	public static void main(String[] args) {

		calculadora();

	}

	private static void calculadora() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Calculadora");
		System.out.println("Escolha a operação: ");
		System.out.println("(*) Multiplicação");
		System.out.println("(/) Divisão");
		System.out.println("(-) Subtração");
		System.out.println("(+) Soma");
		System.out.println("(P) Potencia");
		System.out.println("(!) Fatorial");
		char operacao = sc.next().charAt(0);
		System.out.println("Informe os numeros: ");
		valorA = sc.nextDouble();
		valorB = sc.nextDouble();

		Calculadora calculadora = new Calculadora();
		calculadora.operacao(valorA, valorB, operacao);

		if (operacao == '!') {
			calculadora.calcularFatorial(valorA);

		}

	}

}
