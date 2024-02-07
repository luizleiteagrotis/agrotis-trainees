
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio37{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = 0;
		int codigo = 0;
		
		int codigoMaisAlto = 0;
		int codigoMaisBaixo = 0;
		
		int codigoMaisPesado = 0;
		int codigoMaisLeve = 0;

		double maiorAltura = Double.MIN_VALUE;
		double menorAltura = Double.MAX_VALUE;
		double alturaMedia = 0;
		double somaAlturas = 0;
		
		double maiorPeso = Double.MIN_VALUE;
		double menorPeso = Double.MAX_VALUE;
		double pesoMedio = 0;
		double somaPesos = 0;
		
		do {
			
			System.out.print("Digite seu código: ");
			codigo = scan.nextInt();
			
			if(codigo == 0) {
				break;
			}
			
			System.out.print("Insira sua altura: ");
			double altura = scan.nextDouble();
			
			System.out.print("Insira seu peso: ");
			double peso = scan.nextDouble();
			
			somaAlturas += altura;
			somaPesos += peso;
			
			if(altura > maiorAltura) { 
				codigoMaisAlto = codigo;
				maiorAltura = altura;
			}
			if(altura < menorAltura) {
				codigoMaisBaixo = codigo;
				menorAltura = altura;
			}
			
			if(peso > maiorPeso) {
				codigoMaisPesado = codigo;
				maiorPeso = peso;
			}
			if(peso < menorPeso) {
				codigoMaisLeve = codigo;
				menorPeso = peso;
			}
			
			n++;
		}while(codigo != 0);
		
		alturaMedia = somaAlturas / n;
		pesoMedio = somaPesos / n;
		
		System.out.println("Peso Médio: " + String.format("%.2f", pesoMedio) + "kg - Altura média: " + String.format("%.2f", alturaMedia) + "m");
		
		System.out.println("Cliente mais alto:\nCódigo: " + codigoMaisAlto + " - Altura: " + maiorAltura);
		System.out.println("Cliente mais baixo:\nCódigo: " + codigoMaisBaixo + " - Altura: " + menorAltura);
		
		System.out.println("Cliente mais gordo:\nCódigo: " + codigoMaisPesado + " - Peso: " + maiorPeso);
		System.out.println("Cliente mais magro:\nCódigo: " + codigoMaisLeve + " - Peso: " + menorPeso);
		
		scan.close();
	}
}
