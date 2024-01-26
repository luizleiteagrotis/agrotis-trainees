package exercicios.aula34.exercicio2;

public class Main {
	
	public static void main(String[] args) {
		Calculadora calculadora = new Calculadora();
		
		double[] valoresSomar = {4, 6};
		System.out.println(calculadora.somar(valoresSomar));
		
		System.out.println();
		double[] valoresSubtrair = {10, 6};
		System.out.println(calculadora.subtrair(valoresSubtrair));
		
		System.out.println();
		double[] valoresMultiplicar = {2, 6};
		System.out.println(calculadora.multiplicar(valoresMultiplicar));
		
		System.out.println();
		double valorDividendo = 10;
		double valorDivisor = 3;
		System.out.println(calculadora.dividir(valorDividendo, valorDivisor));
	}
}
