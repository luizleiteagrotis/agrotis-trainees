package exercicios.aula53a56;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o valor de x e y: ");
		double x = scan.nextDouble();
		double y = scan.nextDouble();
		
	for (OperacoesMatematica operacao : OperacoesMatematica.values()) {
		System.out.println(x + " " + operacao + " " + y + " = " + operacao.executarOperacao(x, y));
	}
		
		
	}
}
