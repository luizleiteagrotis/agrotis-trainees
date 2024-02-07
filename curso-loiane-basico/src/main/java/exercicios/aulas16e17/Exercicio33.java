
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio33{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean read = true;
		
		int n = 0;
		int somaTemp = 0;
		int menorTemp = Integer.MAX_VALUE;
		int maiorTemp = Integer.MIN_VALUE;
		double tempMedia = 0;
		
		while(read == true) {
			System.out.print("Insira uma temperatura: ");
			int temp = scan.nextInt();
			
			somaTemp += temp;
			
			if(temp > maiorTemp) maiorTemp = temp;
			if(temp < menorTemp) menorTemp = temp;
			
			n++;
			
			System.out.println("Deseja parar? 1 - SIM; 0 - NÃO");
			int op = scan.nextInt();
			
			if(op == 1) {
				read = false;
			}
		}

		tempMedia = somaTemp/n;
	
		System.out.println("Temperatura Média: " + (int) Math.floor(tempMedia) + "°C Menor Temperatura: " + menorTemp + "°C Maior Temperatura: " + maiorTemp + "°C");
		
		scan.close();
		
	}
}
