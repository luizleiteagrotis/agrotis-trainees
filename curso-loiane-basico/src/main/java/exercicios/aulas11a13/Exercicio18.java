package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio18{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Tamanho do arquivo (em MB):");
		
		double tamanhoArquivo = scan.nextDouble();
		
		System.out.println("Velocidade da Internet (em Mbps):");
		
		double velocidadeDownload = scan.nextDouble();
		
		double tempo = (Math.ceil (tamanhoArquivo / velocidadeDownload)) / 60;
		
		System.out.println("Tempo = " + String.format("%.2f", tempo) + " minutos");
	}
}