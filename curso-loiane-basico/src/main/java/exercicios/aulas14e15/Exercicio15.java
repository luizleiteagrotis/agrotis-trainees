
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio15{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o tamanho dos três lado do triângulo:");
		
		double lado1 = scan.nextDouble();
		double lado2 = scan.nextDouble();
		double lado3 = scan.nextDouble();
		
		String classificacaoTriangulo = "Escaleno";
		
		if(lado1 == lado2) {
			classificacaoTriangulo = "Isósceles";
			if(lado1 == lado3) {
				if(lado2 == lado3) {
					classificacaoTriangulo = "Equilátero";
				}		
			} 
		} else if(lado2 == lado3) {
			classificacaoTriangulo = "Isósceles";
			
		} else if(lado1 == lado3) {
			classificacaoTriangulo = "Isósceles";
			
		} 
		
		System.out.println("Triângulo " + classificacaoTriangulo);
	}
}