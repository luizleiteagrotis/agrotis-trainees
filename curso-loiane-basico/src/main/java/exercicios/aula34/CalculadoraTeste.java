package exercicios.aula34;

public class CalculadoraTeste {

	public static void main(String[] args) {
		int n1 = 8;
		int n2 = 5;
		
		System.out.println("Soma: " + Calculadora.somar(n1, n2) );
		System.out.println("Subtrair: " + Calculadora.subtrair(n1, n2) );
		System.out.println("Multiplicar: " + Calculadora.multiplicar(n1, n2) );
		System.out.println("Dividir: " + Calculadora.dividir(n1, n2) );
		System.out.println("Potência: " + Calculadora.potencia(n1, n2) );
		System.out.println("Fatorial: " + Calculadora.fatorial(n2) );
	}

}
