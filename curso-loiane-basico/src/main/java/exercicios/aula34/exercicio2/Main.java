package exercicios.aula34.exercicio2;

public class Main {

	public static void main(String[] args) {

		System.out.println(Calculadora.somar(10, 20));

		System.out.println(Calculadora.multiplicar(3, 3));

		System.out.println(String.format("%.2f", Calculadora.dividir(10, 3)));

		System.out.println(Calculadora.elevar(3, 3));
		
		System.out.println(Calculadora.fatorial(6) );

	}
}
